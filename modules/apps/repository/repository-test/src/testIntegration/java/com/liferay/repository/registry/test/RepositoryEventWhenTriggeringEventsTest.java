/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.repository.registry.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.repository.event.RepositoryEventType;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.repository.registry.DefaultRepositoryEventRegistry;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.repository.registry.test.util.RepositoryEventTestUtil;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adolfo Pérez
 */
@RunWith(Arquillian.class)
public class RepositoryEventWhenTriggeringEventsTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testShouldExecuteAllMatchingListeners() throws Exception {
		AtomicInteger count = new AtomicInteger();

		for (int i = 0; i < 3; i++) {
			RepositoryEventTestUtil.registerCounterRepositoryEventListener(
				_defaultRepositoryEventRegistry, RepositoryEventType.Add.class,
				FileEntry.class, count);
		}

		_defaultRepositoryEventRegistry.trigger(
			RepositoryEventType.Add.class, FileEntry.class, null);

		Assert.assertEquals(3, count.get());
	}

	@Test
	public void testShouldExecuteListenerExactlyOncePerEvent()
		throws Exception {

		AtomicInteger count =
			RepositoryEventTestUtil.registerCounterRepositoryEventListener(
				_defaultRepositoryEventRegistry, RepositoryEventType.Add.class,
				FileEntry.class);

		int randomInt = Math.abs(RandomTestUtil.nextInt());

		for (int i = 0; i < randomInt; i++) {
			_defaultRepositoryEventRegistry.trigger(
				RepositoryEventType.Add.class, FileEntry.class, null);
		}

		Assert.assertEquals(randomInt, count.get());
	}

	@Test
	public void testShouldExecuteOnlyMatchingListeners() throws Exception {
		AtomicInteger count =
			RepositoryEventTestUtil.registerCounterRepositoryEventListener(
				_defaultRepositoryEventRegistry, RepositoryEventType.Add.class,
				FileEntry.class);

		_defaultRepositoryEventRegistry.registerRepositoryEventListener(
			RepositoryEventType.Update.class, FileEntry.class,
			new RepositoryEventTestUtil.AlwaysFailingRepositoryEventListener
				<RepositoryEventType.Update, FileEntry>());

		_defaultRepositoryEventRegistry.trigger(
			RepositoryEventType.Add.class, FileEntry.class, null);

		Assert.assertEquals(1, count.get());
	}

	private final DefaultRepositoryEventRegistry
		_defaultRepositoryEventRegistry = new DefaultRepositoryEventRegistry(
			null);

}