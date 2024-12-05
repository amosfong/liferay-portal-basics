/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.taglib.internal.servlet.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.petra.memory.DeleteFileFinalizeAction;
import com.liferay.petra.memory.FinalizeManager;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.test.context.ContextUserReplace;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.upload.test.util.UploadTestUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.HashMap;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

/**
 * @author Jürgen Kappler
 */
@RunWith(Arquillian.class)
public class RepositoryBrowserServletTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_user = UserTestUtil.addUser();
	}

	@Test
	public void testDoPutFileEntryWithGuestPermissions() throws Exception {
		String name = RandomTestUtil.randomString();

		MockMultipartFile mockMultipartFile = new MockMultipartFile(
			"file", _BYTES);

		_servlet.service(
			_getMockMultipartHttpServletRequest(name, mockMultipartFile, true),
			new MockHttpServletResponse());

		PermissionChecker permissionChecker = _permissionCheckerFactory.create(
			_user);

		try (ContextUserReplace contextUserReplace = new ContextUserReplace(
				_user, permissionChecker)) {

			_dlAppService.getFileEntry(
				_group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				name);
		}
	}

	@Test(expected = PrincipalException.MustHavePermission.class)
	public void testDoPutFileEntryWithoutGuestPermissions() throws Exception {
		String name = RandomTestUtil.randomString();

		MockMultipartFile mockMultipartFile = new MockMultipartFile(
			"file", _BYTES);

		_servlet.service(
			_getMockMultipartHttpServletRequest(name, mockMultipartFile, false),
			new MockHttpServletResponse());

		PermissionChecker permissionChecker = _permissionCheckerFactory.create(
			_user);

		try (ContextUserReplace contextUserReplace = new ContextUserReplace(
				_user, permissionChecker)) {

			_dlAppService.getFileEntry(
				_group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				name);
		}
	}

	@Test
	public void testDoPutFolderWithGuestPermissions() throws Exception {
		String name = RandomTestUtil.randomString();

		_servlet.service(
			_getMockMultipartHttpServletRequest(name, true),
			new MockHttpServletResponse());

		PermissionChecker permissionChecker = _permissionCheckerFactory.create(
			_user);

		try (ContextUserReplace contextUserReplace = new ContextUserReplace(
				_user, permissionChecker)) {

			_dlAppService.getFolder(
				_group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				name);
		}
	}

	@Test(expected = PrincipalException.MustHavePermission.class)
	public void testDoPutFolderWithoutGuestPermissions() throws Exception {
		String name = RandomTestUtil.randomString();

		_servlet.service(
			_getMockMultipartHttpServletRequest(name, false),
			new MockHttpServletResponse());

		PermissionChecker permissionChecker = _permissionCheckerFactory.create(
			_user);

		try (ContextUserReplace contextUserReplace = new ContextUserReplace(
				_user, permissionChecker)) {

			_dlAppService.getFolder(
				_group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				name);
		}
	}

	private FileItem _createFileItem(byte[] bytes, String fileName)
		throws Exception {

		Path tempFilePath = Files.createTempFile(null, ".txt");

		Files.write(tempFilePath, bytes);

		File tempFile = tempFilePath.toFile();

		FinalizeManager.register(
			tempFile, new DeleteFileFinalizeAction(tempFile.getAbsolutePath()),
			FinalizeManager.PHANTOM_REFERENCE_FACTORY);

		return ProxyUtil.newDelegateProxyInstance(
			FileItem.class.getClassLoader(), FileItem.class,
			new Object() {

				public void delete() {
					tempFile.delete();
				}

				public String getContentType() {
					return StringPool.BLANK;
				}

				public String getFileName() {
					return fileName;
				}

				public String getFullFileName() {
					return tempFile.getName();
				}

				public InputStream getInputStream() throws IOException {
					return new FileInputStream(tempFile);
				}

				public long getSize() {
					return bytes.length;
				}

				public int getSizeThreshold() {
					return 1024;
				}

				public File getStoreLocation() {
					return tempFile;
				}

				public boolean isFormField() {
					return true;
				}

				public boolean isInMemory() {
					return false;
				}

			},
			null);
	}

	private MockMultipartHttpServletRequest _getMockMultipartHttpServletRequest(
			String name, boolean viewableByGuest)
		throws Exception {

		MockMultipartHttpServletRequest mockMultipartHttpServletRequest =
			new MockMultipartHttpServletRequest();

		mockMultipartHttpServletRequest.setAttribute(
			WebKeys.CURRENT_URL, RandomTestUtil.randomString());
		mockMultipartHttpServletRequest.setAttribute(
			WebKeys.USER, TestPropsValues.getUser());
		mockMultipartHttpServletRequest.setContentType(
			"multipart/form-data;boundary=" + System.currentTimeMillis());
		mockMultipartHttpServletRequest.setMethod("PUT");
		mockMultipartHttpServletRequest.setParameter("name", name);
		mockMultipartHttpServletRequest.setParameter(
			"repositoryId", String.valueOf(_group.getGroupId()));
		mockMultipartHttpServletRequest.setParameter(
			"viewableByGuest", String.valueOf(viewableByGuest));

		return mockMultipartHttpServletRequest;
	}

	private HttpServletRequest _getMockMultipartHttpServletRequest(
			String fileName, MockMultipartFile mockMultipartFile,
			boolean viewableByGuest)
		throws Exception {

		MockMultipartHttpServletRequest mockMultipartHttpServletRequest =
			_getMockMultipartHttpServletRequest(
				RandomTestUtil.randomString(), viewableByGuest);

		mockMultipartHttpServletRequest.addFile(mockMultipartFile);
		mockMultipartHttpServletRequest.setContent(_BYTES);
		mockMultipartHttpServletRequest.setContentType(
			"multipart/form-data;boundary=" + System.currentTimeMillis());

		return UploadTestUtil.createUploadPortletRequest(
			UploadTestUtil.createUploadServletRequest(
				mockMultipartHttpServletRequest,
				HashMapBuilder.put(
					"file", new FileItem[] {_createFileItem(_BYTES, fileName)}
				).build(),
				new HashMap<>()),
			null, RandomTestUtil.randomString());
	}

	private static final byte[] _BYTES =
		"Enterprise. Open Source. For Life.".getBytes();

	@Inject
	private DLAppService _dlAppService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Inject(
		filter = "osgi.http.whiteboard.servlet.name=com.liferay.document.library.taglib.internal.servlet.RepositoryBrowserServlet"
	)
	private Servlet _servlet;

	@DeleteAfterTestRun
	private User _user;

}