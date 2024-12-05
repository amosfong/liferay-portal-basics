/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.product.type.virtual.constants.VirtualCPTypeConstants;
import com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting;
import com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingLocalService;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.headless.commerce.admin.catalog.client.dto.v1_0.ProductVirtualSettingsFileEntry;
import com.liferay.headless.commerce.admin.catalog.client.http.HttpInvoker;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.constants.TestDataConstants;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.util.PropsValues;

import java.io.File;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * @author Danny Situ
 */
@RunWith(Arquillian.class)
public class ProductVirtualSettingsFileEntryResourceTest
	extends BaseProductVirtualSettingsFileEntryResourceTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			testCompany.getCompanyId());
		_user = UserTestUtil.addOmniadminUser();

		_commerceCatalog = CommerceTestUtil.addCommerceCatalog(
			testCompany.getCompanyId(), testCompany.getGroupId(),
			_user.getUserId(), _commerceCurrency.getCode());

		_cpDefinition = CPTestUtil.addCPDefinitionFromCatalog(
			_commerceCatalog.getGroupId(), VirtualCPTypeConstants.NAME, true,
			true);

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			testGroup.getCompanyId(), testGroup.getGroupId(),
			_user.getUserId());

		_cpDefinitionVirtualSetting =
			_cpDefinitionVirtualSettingLocalService.
				addCPDefinitionVirtualSetting(
					_cpDefinition.getModelClassName(),
					_cpDefinition.getCPDefinitionId(), 0, null,
					CommerceOrderConstants.ORDER_STATUS_PENDING, 0,
					RandomTestUtil.randomInt(), true, 0, "https://liferay.com",
					false, null, 0, false, _serviceContext);
	}

	@Override
	protected void assertValid(
			ProductVirtualSettingsFileEntry productVirtualSettingsFileEntry,
			Map<String, File> multipartFiles)
		throws Exception {

		Assert.assertEquals(
			new String(FileUtil.getBytes(multipartFiles.get("file"))),
			_read(
				"http://localhost:8080" +
					productVirtualSettingsFileEntry.getSrc()));
	}

	@Override
	protected String[] getAdditionalAssertFieldNames() {
		return new String[] {"version"};
	}

	@Override
	protected Map<String, File> getMultipartFiles() throws Exception {
		return HashMapBuilder.<String, File>put(
			"file",
			() -> FileUtil.createTempFile(TestDataConstants.TEST_BYTE_ARRAY)
		).build();
	}

	@Override
	protected ProductVirtualSettingsFileEntry
			randomProductVirtualSettingsFileEntry()
		throws Exception {

		return new ProductVirtualSettingsFileEntry() {
			{
				version = String.valueOf(RandomTestUtil.randomInt());
			}
		};
	}

	@Override
	protected ProductVirtualSettingsFileEntry
			testDeleteProductVirtualSettingsFileEntry_addProductVirtualSettingsFileEntry()
		throws Exception {

		return productVirtualSettingsFileEntryResource.
			postProductVirtualSettingIdProductVirtualSettingsFileEntry(
				_cpDefinitionVirtualSetting.getCPDefinitionVirtualSettingId(),
				randomProductVirtualSettingsFileEntry(), getMultipartFiles());
	}

	@Override
	protected ProductVirtualSettingsFileEntry
			testGetProductVirtualSettingIdProductVirtualSettingsFileEntriesPage_addProductVirtualSettingsFileEntry(
				Long id,
				ProductVirtualSettingsFileEntry productVirtualSettingsFileEntry)
		throws Exception {

		return productVirtualSettingsFileEntryResource.
			postProductVirtualSettingIdProductVirtualSettingsFileEntry(
				id, productVirtualSettingsFileEntry, getMultipartFiles());
	}

	@Override
	protected Long
			testGetProductVirtualSettingIdProductVirtualSettingsFileEntriesPage_getId()
		throws Exception {

		return _cpDefinitionVirtualSetting.getCPDefinitionVirtualSettingId();
	}

	@Override
	protected ProductVirtualSettingsFileEntry
			testGetProductVirtualSettingsFileEntry_addProductVirtualSettingsFileEntry()
		throws Exception {

		return productVirtualSettingsFileEntryResource.
			postProductVirtualSettingIdProductVirtualSettingsFileEntry(
				_cpDefinitionVirtualSetting.getCPDefinitionVirtualSettingId(),
				randomProductVirtualSettingsFileEntry(), getMultipartFiles());
	}

	@Override
	protected ProductVirtualSettingsFileEntry
			testGraphQLProductVirtualSettingsFileEntry_addProductVirtualSettingsFileEntry()
		throws Exception {

		return productVirtualSettingsFileEntryResource.
			postProductVirtualSettingIdProductVirtualSettingsFileEntry(
				_cpDefinitionVirtualSetting.getCPDefinitionVirtualSettingId(),
				randomProductVirtualSettingsFileEntry(), getMultipartFiles());
	}

	@Override
	protected ProductVirtualSettingsFileEntry
			testPatchProductVirtualSettingsFileEntry_addProductVirtualSettingsFileEntry()
		throws Exception {

		return productVirtualSettingsFileEntryResource.
			postProductVirtualSettingIdProductVirtualSettingsFileEntry(
				_cpDefinitionVirtualSetting.getCPDefinitionVirtualSettingId(),
				randomProductVirtualSettingsFileEntry(), getMultipartFiles());
	}

	@Override
	protected ProductVirtualSettingsFileEntry
			testPostProductVirtualSettingIdProductVirtualSettingsFileEntry_addProductVirtualSettingsFileEntry(
				ProductVirtualSettingsFileEntry productVirtualSettingsFileEntry,
				Map<String, File> multipartFiles)
		throws Exception {

		return productVirtualSettingsFileEntryResource.
			postProductVirtualSettingIdProductVirtualSettingsFileEntry(
				_cpDefinitionVirtualSetting.getCPDefinitionVirtualSettingId(),
				productVirtualSettingsFileEntry, multipartFiles);
	}

	private String _read(String url) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);
		httpInvoker.path(url);
		httpInvoker.userNameAndPassword(
			PropsValues.ADMIN_EMAIL_FROM_ADDRESS + ":" +
				PropsValues.DEFAULT_ADMIN_PASSWORD);

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	@DeleteAfterTestRun
	private CommerceCatalog _commerceCatalog;

	@DeleteAfterTestRun
	private CommerceCurrency _commerceCurrency;

	@DeleteAfterTestRun
	private CPDefinition _cpDefinition;

	@DeleteAfterTestRun
	private CPDefinitionVirtualSetting _cpDefinitionVirtualSetting;

	@Inject
	private CPDefinitionVirtualSettingLocalService
		_cpDefinitionVirtualSettingLocalService;

	private ServiceContext _serviceContext;

	@DeleteAfterTestRun
	private User _user;

}