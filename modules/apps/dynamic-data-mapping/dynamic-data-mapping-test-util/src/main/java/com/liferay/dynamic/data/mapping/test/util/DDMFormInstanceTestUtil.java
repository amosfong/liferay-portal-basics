/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.test.util;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceSettings;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.DDMFormFactory;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;

/**
 * @author Gabriel Ibson
 */
public class DDMFormInstanceTestUtil {

	public static DDMFormInstance addDDMFormInstance(
			DDMForm ddmForm, Group group, DDMFormValues settingsDDMFormValues,
			long userId)
		throws Exception {

		return addDDMFormInstance(
			ddmForm, group, settingsDDMFormValues,
			DDMFormInstance.class.getName(), userId);
	}

	public static DDMFormInstance addDDMFormInstance(
			DDMForm ddmForm, Group group, DDMFormValues settingsDDMFormValues,
			String className, long userId)
		throws Exception {

		return addDDMFormInstance(
			DDMStructureTestUtil.addStructure(
				group.getGroupId(), className, ddmForm, LocaleUtil.US),
			group, settingsDDMFormValues, userId);
	}

	public static DDMFormInstance addDDMFormInstance(
			DDMForm ddmForm, Group group, long userId)
		throws Exception {

		return addDDMFormInstance(
			ddmForm, group, createSettingsDDMFormValues(), userId);
	}

	public static DDMFormInstance addDDMFormInstance(
			DDMForm ddmForm, Group group, String className, long userId)
		throws Exception {

		return addDDMFormInstance(
			ddmForm, group, createSettingsDDMFormValues(), className, userId);
	}

	public static DDMFormInstance addDDMFormInstance(
			DDMStructure ddmStructure, Group group,
			DDMFormValues settingsDDMFormValues, long userId)
		throws Exception {

		return DDMFormInstanceLocalServiceUtil.addFormInstance(
			userId, group.getGroupId(), ddmStructure.getStructureId(),
			HashMapBuilder.put(
				LocaleUtil.US, RandomTestUtil.randomString()
			).build(),
			HashMapBuilder.put(
				LocaleUtil.US, RandomTestUtil.randomString()
			).build(),
			settingsDDMFormValues, ServiceContextTestUtil.getServiceContext());
	}

	public static DDMFormInstance addDDMFormInstance(Group group, long userId)
		throws Exception {

		DDMForm ddmForm = DDMFormTestUtil.createDDMForm("text");

		return addDDMFormInstance(
			ddmForm, group, createSettingsDDMFormValues(), userId);
	}

	public static DDMFormValues createSettingsDDMFormValues() {
		return createSettingsDDMFormValues(true);
	}

	public static DDMFormValues createSettingsDDMFormValues(
		boolean requireCaptcha) {

		DDMForm ddmForm = DDMFormFactory.create(DDMFormInstanceSettings.class);

		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		ddmFormValues.setAvailableLocales(ddmForm.getAvailableLocales());
		ddmFormValues.setDefaultLocale(ddmForm.getDefaultLocale());

		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"autosaveEnabled", "true"));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"emailFromAddress", "from@liferay.com"));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"emailFromName", "Joe Bloggs"));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"emailSubject", "New Form Submission"));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"emailToAddress", "to@liferay.com"));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"published", "Joe Bloggs"));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"redirectURL", StringPool.BLANK));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"requireAuthentication", "false"));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"requireCaptcha", String.valueOf(requireCaptcha)));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"sendEmailNotification", "false"));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"storageType", StorageType.DEFAULT.getValue()));
		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"workflowDefinition", "[\"no-workflow\"]"));

		return ddmFormValues;
	}

	public static void deleteFormInstance(DDMFormInstance ddmFormInstance)
		throws PortalException {

		DDMFormInstanceLocalServiceUtil.deleteFormInstance(ddmFormInstance);
	}

	public static DDMFormInstance updateDDMFormInstance(
			long formInstanceId, DDMFormValues settingsDDMFormValues)
		throws PortalException {

		return DDMFormInstanceLocalServiceUtil.updateFormInstance(
			formInstanceId, settingsDDMFormValues);
	}

}