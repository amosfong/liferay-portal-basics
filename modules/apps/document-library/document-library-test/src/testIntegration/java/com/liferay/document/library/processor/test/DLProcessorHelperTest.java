/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.processor.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.processor.DLProcessor;
import com.liferay.document.library.kernel.processor.DLProcessorHelper;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.test.constants.TestDataConstants;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Cristina González
 */
@RunWith(Arquillian.class)
public class DLProcessorHelperTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_cleanUp = new AtomicBoolean(false);
		_trigger = new AtomicBoolean(false);

		Bundle bundle = FrameworkUtil.getBundle(DLProcessorHelperTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		_serviceRegistration = bundleContext.registerService(
			DLProcessor.class,
			new DLProcessor() {

				@Override
				public void cleanUp(FileEntry fileEntry) {
					_cleanUp.set(true);
				}

				@Override
				public void cleanUp(FileVersion fileVersion) {
					_cleanUp.set(true);
				}

				@Override
				public void copy(
					FileVersion sourceFileVersion,
					FileVersion destinationFileVersion) {
				}

				@Override
				public void exportGeneratedFiles(
						PortletDataContext portletDataContext,
						FileEntry fileEntry, Element fileEntryElement)
					throws Exception {
				}

				@Override
				public String getType() {
					return "TEST";
				}

				@Override
				public void importGeneratedFiles(
						PortletDataContext portletDataContext,
						FileEntry fileEntry, FileEntry importedFileEntry,
						Element fileEntryElement)
					throws Exception {
				}

				@Override
				public boolean isSupported(FileVersion fileVersion) {
					return true;
				}

				@Override
				public boolean isSupported(String mimeType) {
					return true;
				}

				@Override
				public void trigger(
					FileVersion sourceFileVersion,
					FileVersion destinationFileVersion) {

					_trigger.set(true);
				}

			},
			MapUtil.singletonDictionary("type", "TEST"));

		_group = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {
		_serviceRegistration.unregister();
	}

	@Test
	public void testCleanUp() throws PortalException {
		FileEntry fileEntry = _dlAppLocalService.addFileEntry(
			null, TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(),
			ContentTypes.APPLICATION_OCTET_STREAM,
			TestDataConstants.TEST_BYTE_ARRAY, null, null, null,
			ServiceContextTestUtil.getServiceContext());

		_dlProcessorHelper.cleanUp(fileEntry);

		Assert.assertTrue(_cleanUp.get());
	}

	@Test
	public void testCleanUpFileVersion() throws PortalException {
		FileEntry fileEntry = _dlAppLocalService.addFileEntry(
			null, TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(),
			ContentTypes.APPLICATION_OCTET_STREAM,
			TestDataConstants.TEST_BYTE_ARRAY, null, null, null,
			ServiceContextTestUtil.getServiceContext());

		_dlProcessorHelper.cleanUp(fileEntry.getFileVersion());

		Assert.assertTrue(_cleanUp.get());
	}

	@Test
	public void testTrigger() throws PortalException {
		FileEntry fileEntry = _dlAppLocalService.addFileEntry(
			null, TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(),
			ContentTypes.APPLICATION_OCTET_STREAM,
			TestDataConstants.TEST_BYTE_ARRAY, null, null, null,
			ServiceContextTestUtil.getServiceContext());

		_dlProcessorHelper.trigger(fileEntry, fileEntry.getFileVersion());

		Assert.assertTrue(_trigger.get());
	}

	@Test
	public void testTriggerAfterDeleteTheFile() throws PortalException {
		FileEntry fileEntry = _dlAppLocalService.addFileEntry(
			null, TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(),
			ContentTypes.APPLICATION_OCTET_STREAM,
			TestDataConstants.TEST_BYTE_ARRAY, null, null, null,
			ServiceContextTestUtil.getServiceContext());

		FileVersion fileVersion = fileEntry.getFileVersion();

		_dlAppLocalService.deleteFileEntry(fileEntry.getFileEntryId());

		_dlProcessorHelper.trigger(fileEntry, fileVersion);

		Assert.assertTrue(_trigger.get());
	}

	private AtomicBoolean _cleanUp;

	@Inject
	private DLAppLocalService _dlAppLocalService;

	@Inject
	private DLProcessorHelper _dlProcessorHelper;

	private Group _group;
	private ServiceRegistration<DLProcessor> _serviceRegistration;
	private AtomicBoolean _trigger;

}