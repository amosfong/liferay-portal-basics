/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.transaction.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.model.impl.ClassNameImpl;
import com.liferay.portal.spring.hibernate.PortalTransactionManager;
import com.liferay.portal.spring.hibernate.PortletTransactionManager;
import com.liferay.portal.spring.transaction.TransactionExecutor;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.hibernate.engine.spi.SessionFactoryImplementor;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * @author Shuyang Zhou
 */
@RunWith(Arquillian.class)
public class TransactionInterceptorTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testFailOnCommit() throws Exception {
		CacheRegistryUtil.clear();

		long classNameId = _counterLocalService.increment();

		TransactionExecutor transactionExecutor =
			(TransactionExecutor)PortalBeanLocatorUtil.locate(
				"transactionExecutor");

		try (AutoCloseable autoCloseable =
				ReflectionTestUtil.setFieldValueWithAutoCloseable(
					transactionExecutor, "_platformTransactionManager",
					new MockPlatformTransactionManager(
						(PortalTransactionManager)
							InfrastructureUtil.getTransactionManager()))) {

			_classNameLocalService.addClassName(
				_classNamePersistence.create(classNameId));

			Assert.fail();
		}
		catch (RuntimeException runtimeException) {
			Assert.assertEquals(
				"MockPlatformTransactionManager",
				runtimeException.getMessage());
		}

		Assert.assertNull(
			_entityCache.getResult(ClassNameImpl.class, classNameId));
	}

	@Inject
	private ClassNameLocalService _classNameLocalService;

	@Inject
	private ClassNamePersistence _classNamePersistence;

	@Inject
	private CounterLocalService _counterLocalService;

	@Inject
	private EntityCache _entityCache;

	private static class MockPlatformTransactionManager
		extends PortletTransactionManager {

		public MockPlatformTransactionManager(
			PortalTransactionManager portalTransactionManager) {

			super(
				portalTransactionManager,
				(SessionFactoryImplementor)
					portalTransactionManager.getSessionFactory());

			_platformTransactionManager = portalTransactionManager;
		}

		@Override
		public void commit(TransactionStatus transactionStatus)
			throws TransactionException {

			_platformTransactionManager.rollback(transactionStatus);

			throw new RuntimeException("MockPlatformTransactionManager");
		}

		@Override
		public TransactionStatus getTransaction(
				TransactionDefinition transactionDefinition)
			throws TransactionException {

			return _platformTransactionManager.getTransaction(
				transactionDefinition);
		}

		@Override
		public void rollback(TransactionStatus transactionStatus)
			throws TransactionException {

			_platformTransactionManager.rollback(transactionStatus);
		}

		private final PlatformTransactionManager _platformTransactionManager;

	}

}