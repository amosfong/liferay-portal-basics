/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.change.tracking.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.change.tracking.test.util.BaseTableReferenceDefinitionTestCase;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileEntryTypeConstants;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.dynamic.data.mapping.constants.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.form.field.type.constants.DDMFormFieldTypeConstants;
import com.liferay.dynamic.data.mapping.kernel.DDMForm;
import com.liferay.dynamic.data.mapping.kernel.DDMFormField;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.change.tracking.CTModel;
import com.liferay.portal.kernel.test.constants.TestDataConstants;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Cheryl Tang
 */
@RunWith(Arquillian.class)
public class DLFileEntryMetadataTableReferenceDefinitionTest
	extends BaseTableReferenceDefinitionTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		byte[] bytes = TestDataConstants.TEST_BYTE_ARRAY;

		InputStream inputStream = new ByteArrayInputStream(bytes);

		_dlFileEntry = _dlFileEntryLocalService.addFileEntry(
			null, TestPropsValues.getUserId(), group.getGroupId(),
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			StringPool.BLANK, StringPool.BLANK,
			DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT, null,
			null, inputStream, bytes.length, null, null, null,
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));

		_ddmStructure = _ddmStructureLocalService.addStructure(
			null, group.getCreatorUserId(), group.getGroupId(),
			DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID,
			PortalUtil.getClassNameId(DLFileEntryMetadata.class),
			StringPool.BLANK,
			HashMapBuilder.put(
				LocaleUtil.getDefault(),
				DLFileEntryMetadata.class.getSimpleName()
			).build(),
			new HashMap<>(), StringPool.BLANK, StorageType.DEFAULT.toString(),
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));

		_dlFileEntryType = _dlFileEntryTypeLocalService.addFileEntryType(
			null, group.getCreatorUserId(), group.getGroupId(),
			_ddmStructure.getStructureId(),
			DLFileEntryMetadataTableReferenceDefinitionTest.class.
				getSimpleName(),
			HashMapBuilder.put(
				LocaleUtil.getDefault(),
				DLFileEntryMetadataTableReferenceDefinitionTest.class.
					getSimpleName()
			).build(),
			new HashMap<>(),
			DLFileEntryTypeConstants.FILE_ENTRY_TYPE_SCOPE_DEFAULT,
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));
	}

	@Override
	protected CTModel<?> addCTModel() throws Exception {
		DLFileVersion dlFileVersion = _dlFileEntry.getFileVersion();

		_dlFileEntryMetadataLocalService.updateFileEntryMetadata(
			null, _dlFileEntryType.getFileEntryTypeId(),
			_dlFileEntry.getFileEntryId(), dlFileVersion.getFileVersionId(),
			_createDDMFormValuesMap(),
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));

		return _dlFileEntryMetadataLocalService.getFileEntryMetadata(
			_ddmStructure.getStructureId(), dlFileVersion.getFileVersionId());
	}

	private Map<String, DDMFormValues> _createDDMFormValuesMap() {
		DDMForm ddmForm = new DDMForm();

		ddmForm.setAvailableLocales(
			DDMFormTestUtil.createAvailableLocales(LocaleUtil.US));
		ddmForm.setDefaultLocale(LocaleUtil.US);

		DDMFormField ddmFormField = new DDMFormField(
			DLFileEntryMetadataTableReferenceDefinitionTest.class.
				getSimpleName(),
			DDMFormFieldTypeConstants.TEXT);

		ddmFormField.setDataType("string");
		ddmFormField.setRequired(false);
		ddmFormField.setLocalizable(false);

		ddmForm.addDDMFormField(ddmFormField);

		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		ddmFormValues.setAvailableLocales(
			DDMFormTestUtil.createAvailableLocales(LocaleUtil.US));
		ddmFormValues.setDefaultLocale(LocaleUtil.US);

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setName(
			DLFileEntryMetadataTableReferenceDefinitionTest.class.
				getSimpleName());
		ddmFormFieldValue.setInstanceId(RandomTestUtil.randomString());
		ddmFormFieldValue.setValue(
			new UnlocalizedValue(RandomTestUtil.randomString()));

		ddmFormValues.addDDMFormFieldValue(ddmFormFieldValue);

		return HashMapBuilder.<String, DDMFormValues>put(
			_ddmStructure.getStructureKey(), ddmFormValues
		).build();
	}

	@Inject
	private static DDMStructureLocalService _ddmStructureLocalService;

	@Inject
	private static DLFileEntryLocalService _dlFileEntryLocalService;

	@Inject
	private static DLFileEntryMetadataLocalService
		_dlFileEntryMetadataLocalService;

	@Inject
	private static DLFileEntryTypeLocalService _dlFileEntryTypeLocalService;

	private DDMStructure _ddmStructure;
	private DLFileEntry _dlFileEntry;
	private DLFileEntryType _dlFileEntryType;

}