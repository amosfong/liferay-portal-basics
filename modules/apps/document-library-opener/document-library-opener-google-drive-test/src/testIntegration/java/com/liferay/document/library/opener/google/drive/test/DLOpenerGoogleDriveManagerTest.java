/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.opener.google.drive.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.test.util.ConfigurationTemporarySwapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.AssumeTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.File;

import java.util.Dictionary;

import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alejandro Tardín
 */
@RunWith(Arquillian.class)
@Sync
public class DLOpenerGoogleDriveManagerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new AssumeTestRule("assume"), new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	public static void assume() {
		Assume.assumeTrue(Validator.isNotNull(_getGoogleDriveClientId()));
		Assume.assumeTrue(Validator.isNotNull(_getGoogleDriveClientSecret()));
		Assume.assumeTrue(Validator.isNotNull(_getGoogleDriveRefreshToken()));
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
		_company = CompanyTestUtil.addCompany();
	}

	@Before
	public void setUp() throws Exception {
		_user = UserTestUtil.addGroupAdminUser(_company.getGroup());

		_originalName = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(_user.getUserId());
	}

	@After
	public void tearDown() throws Exception {
		PrincipalThreadLocal.setName(_originalName);
	}

	@Test
	public void testCheckInUploadsAnEmptyFileToGoogle() throws Exception {
		_test(
			_company.getCompanyId(), _user.getUserId(),
			() -> {
				FileEntry fileEntry = _addFileEntry();

				Object dlOpenerGoogleDriveFileReference =
					ReflectionTestUtil.invoke(
						_dlOpenerGoogleDriveManager, "create",
						new Class<?>[] {long.class, FileEntry.class},
						_user.getUserId(), fileEntry);

				Assert.assertEquals(
					"\ufeff",
					FileUtil.read(
						(File)ReflectionTestUtil.invoke(
							dlOpenerGoogleDriveFileReference, "getContentFile",
							new Class<?>[0])));

				_dlAppService.checkInFileEntry(
					fileEntry.getFileEntryId(), RandomTestUtil.randomString(),
					ServiceContextTestUtil.getServiceContext(
						_company.getCompanyId(), _company.getGroupId(),
						_user.getUserId()));

				Assert.assertFalse(_isGoogleDriveFile(fileEntry));
			});
	}

	@Test
	public void testCheckOutUploadsTheFileToGoogle() throws Exception {
		_test(
			_company.getCompanyId(), _user.getUserId(),
			() -> {
				FileEntry fileEntry = _addFileEntry();

				Object dlOpenerGoogleDriveFileReference =
					ReflectionTestUtil.invoke(
						_dlOpenerGoogleDriveManager, "checkOut",
						new Class<?>[] {long.class, FileEntry.class},
						_user.getUserId(), fileEntry);

				Assert.assertEquals(
					"\ufeff" + StringUtil.read(fileEntry.getContentStream()),
					FileUtil.read(
						(File)ReflectionTestUtil.invoke(
							dlOpenerGoogleDriveFileReference, "getContentFile",
							new Class<?>[0])));

				Assert.assertTrue(_isGoogleDriveFile(fileEntry));

				ReflectionTestUtil.invoke(
					_dlOpenerGoogleDriveManager, "delete",
					new Class<?>[] {long.class, FileEntry.class},
					_user.getUserId(), fileEntry);

				Assert.assertFalse(_isGoogleDriveFile(fileEntry));
			});
	}

	@Test
	public void testCreateUploadsAnEmptyFileToGoogle() throws Exception {
		_test(
			_company.getCompanyId(), _user.getUserId(),
			() -> {
				FileEntry fileEntry = _addFileEntry();

				Object dlOpenerGoogleDriveFileReference =
					ReflectionTestUtil.invoke(
						_dlOpenerGoogleDriveManager, "create",
						new Class<?>[] {long.class, FileEntry.class},
						_user.getUserId(), fileEntry);

				Assert.assertEquals(
					"\ufeff",
					FileUtil.read(
						(File)ReflectionTestUtil.invoke(
							dlOpenerGoogleDriveFileReference, "getContentFile",
							new Class<?>[0])));

				Assert.assertTrue(_isGoogleDriveFile(fileEntry));

				ReflectionTestUtil.invoke(
					_dlOpenerGoogleDriveManager, "delete",
					new Class<?>[] {long.class, FileEntry.class},
					_user.getUserId(), fileEntry);

				Assert.assertFalse(_isGoogleDriveFile(fileEntry));
			});
	}

	@Test(expected = PortalException.class)
	public void testGetAuthorizationURLFailsIfThereIsNoAuthorizationCodeFlow()
		throws Exception {

		ReflectionTestUtil.invoke(
			_dlOpenerGoogleDriveManager, "getAuthorizationURL",
			new Class<?>[] {long.class, String.class, String.class},
			_company.getCompanyId(), RandomTestUtil.randomString(),
			"http://localhost:8080");
	}

	@Test
	public void testGetAuthorizationURLSucceedsIfThereAreValidCredentials()
		throws Exception {

		_test(
			() -> {
				String redirectUri = "http://localhost:8080";
				String state = RandomTestUtil.randomString();

				Assert.assertEquals(
					StringBundler.concat(
						"https://accounts.google.com/o/oauth2/auth?client_id=",
						_getGoogleDriveClientId(), "&redirect_uri=",
						redirectUri, "&response_type=code",
						"&scope=https://www.googleapis.com/auth/drive.file",
						"&state=", state),
					ReflectionTestUtil.invoke(
						_dlOpenerGoogleDriveManager, "getAuthorizationURL",
						new Class<?>[] {long.class, String.class, String.class},
						_company.getCompanyId(), state, redirectUri));
			});
	}

	@Test
	public void testHasValidCredentialIsFalseByDefault() throws Exception {
		Assert.assertFalse(
			ReflectionTestUtil.invoke(
				_dlOpenerGoogleDriveManager, "hasValidCredential",
				new Class<?>[] {long.class, long.class},
				_company.getCompanyId(), _user.getUserId()));
	}

	@Test
	public void testIsConfiguredIsFalseByDefault() {
		Assert.assertFalse(
			ReflectionTestUtil.invoke(
				_dlOpenerGoogleDriveManager, "isConfigured",
				new Class<?>[] {long.class}, _company.getCompanyId()));
	}

	@Test
	public void testIsConfiguredIsTrueWhenGoogleDriveSettingsAreFilled()
		throws Exception {

		_test(
			() -> Assert.assertTrue(
				ReflectionTestUtil.invoke(
					_dlOpenerGoogleDriveManager, "isConfigured",
					new Class<?>[] {long.class}, _company.getCompanyId())));
	}

	@Test
	public void testIsGoogleDriveFileIsFalseByDefault() throws Exception {
		Assert.assertFalse(_isGoogleDriveFile(_addFileEntry()));
	}

	private static String _getGoogleDriveClientId() {
		return PropsUtil.get("google.drive.integration.client.id.1");
	}

	private static String _getGoogleDriveClientSecret() {
		return PropsUtil.get("google.drive.integration.client.secret.1");
	}

	private static String _getGoogleDriveRefreshToken() {
		return PropsUtil.get("google.drive.integration.client.refresh.token.1");
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
			RandomTestUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, "liferay".getBytes(), null, null, null,
			serviceContext);
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

		String accessToken = jsonObject.getString("access_token");

		if (Validator.isNull(accessToken)) {
			throw new Exception(
				"JSON response does not contain an access token: " +
					jsonObject);
		}

		return accessToken;
	}

	private boolean _isGoogleDriveFile(FileEntry fileEntry) {
		return ReflectionTestUtil.invoke(
			_dlOpenerGoogleDriveManager, "isGoogleDriveFile",
			new Class<?>[] {FileEntry.class}, fileEntry);
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

	private String _originalName;
	private User _user;

}