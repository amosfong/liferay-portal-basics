/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.opener.google.drive.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.test.util.ConfigurationTemporarySwapper;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.InputStream;

import java.util.Dictionary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Gergely Szalay
 */
@RunWith(Arquillian.class)
@Sync
public class EditInGoogleDocsMVCActionCommandTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@BeforeClass
	public static void setUpClass() throws Exception {
		_company = CompanyTestUtil.addCompany();
	}

	@Before
	public void setUp() throws Exception {
		_user = UserTestUtil.addGroupAdminUser(_company.getGroup());

		PrincipalThreadLocal.setName(_user.getUserId());
	}

	@Test
	public void testCheckOutedFileUploadsToGoogle() throws Exception {
		_test(
			_company.getCompanyId(), _user.getUserId(),
			() -> {
				FileEntry fileEntry = _addFileEntry();

				ServiceContext serviceContext =
					ServiceContextTestUtil.getServiceContext(
						_company.getCompanyId(), _company.getGroupId(),
						_user.getUserId());

				_dlAppService.checkOutFileEntry(
					fileEntry.getFileEntryId(), serviceContext);

				ReflectionTestUtil.invoke(
					_mvcActionCommand, "_checkOutGoogleDriveFileEntry",
					new Class<?>[] {long.class, ServiceContext.class},
					fileEntry.getFileEntryId(), serviceContext);

				FileVersion fileVersion = fileEntry.getLatestFileVersion();

				InputStream inputStream = fileVersion.getContentStream(false);

				Assert.assertTrue(inputStream.available() > 0);
			});
	}

	private FileEntry _addFileEntry() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_company.getGroupId());

		Folder folder = _dlAppLocalService.addFolder(
			null, TestPropsValues.getUserId(), _company.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			serviceContext);

		return _dlAppLocalService.addFileEntry(
			null, serviceContext.getUserId(), folder.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), StringPool.BLANK,
			"liferay".getBytes(), null, null, null, serviceContext);
	}

	private String _getAuthorizationToken() throws Exception {
		Http.Options options = new Http.Options();

		options.addHeader(
			"Content-Type", ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED);
		options.addPart("client_id", _getGoogleDriveClientId());
		options.addPart("client_secret", _getGoogleDriveClientSecret());
		options.addPart("grant_type", "refresh_token");
		options.addPart("refresh_token", _getGoogleDriveRefreshToken());
		options.setLocation("https://www.googleapis.com/oauth2/v4/token");
		options.setPost(true);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			_http.URLtoString(options));

		return jsonObject.getString("access_token");
	}

	private String _getGoogleDriveClientId() {
		return PropsUtil.get("google.drive.integration.client.id.1");
	}

	private String _getGoogleDriveClientSecret() {
		return PropsUtil.get("google.drive.integration.client.secret.1");
	}

	private String _getGoogleDriveRefreshToken() {
		return PropsUtil.get("google.drive.integration.client.refresh.token.1");
	}

	private <E extends Exception> void _test(
			long companyId, long userId, UnsafeRunnable<E> unsafeRunnable)
		throws Exception {

		_test(
			() -> {
				ReflectionTestUtil.invoke(
					_dlOpenerGoogleDriveManager, "setAuthorizationToken",
					new Class<?>[] {long.class, long.class, String.class},
					companyId, userId, _getAuthorizationToken());

				try {
					unsafeRunnable.run();
				}
				finally {
					ReflectionTestUtil.invoke(
						_dlOpenerGoogleDriveManager, "setAuthorizationToken",
						new Class<?>[] {long.class, long.class, String.class},
						companyId, userId, null);
				}
			});
	}

	private <E extends Exception> void _test(UnsafeRunnable<E> unsafeRunnable)
		throws Exception {

		Dictionary<String, Object> dictionary =
			HashMapDictionaryBuilder.<String, Object>put(
				"clientId", _getGoogleDriveClientId()
			).put(
				"clientSecret", _getGoogleDriveClientSecret()
			).build();

		try (ConfigurationTemporarySwapper configurationTemporarySwapper =
				new ConfigurationTemporarySwapper(
					"com.liferay.document.library.google.drive.configuration." +
						"DLGoogleDriveCompanyConfiguration",
					dictionary)) {

			unsafeRunnable.run();
		}
	}

	private static Company _company;

	@Inject
	private DLAppLocalService _dlAppLocalService;

	@Inject
	private DLAppService _dlAppService;

	@Inject(
		filter = "component.name=com.liferay.document.library.opener.google.drive.web.internal.DLOpenerGoogleDriveManager",
		type = Inject.NoType.class
	)
	private Object _dlOpenerGoogleDriveManager;

	@Inject
	private Http _http;

	@Inject(filter = "mvc.command.name=/document_library/edit_in_google_docs")
	private MVCActionCommand _mvcActionCommand;

	private User _user;

}