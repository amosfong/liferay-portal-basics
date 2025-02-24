/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.test.util;

import com.liferay.dynamic.data.mapping.constants.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo García
 * @author André de Oliveira
 * @author Marcellus Tavares
 */
public class DDMStructureTestHelper {

	public static Map<Locale, String> getDefaultLocaleMap(String value) {
		Map<Locale, String> map = new HashMap<>();

		map.put(LocaleUtil.getSiteDefault(), value);

		return map;
	}

	public DDMStructureTestHelper(long classNameId, Group group)
		throws Exception {

		_classNameId = classNameId;
		_group = group;

		_userId = TestPropsValues.getUserId();
	}

	public DDMStructureTestHelper(long classNameId, Group group, long userId)
		throws Exception {

		_classNameId = classNameId;
		_group = group;
		_userId = userId;
	}

	public DDMStructure addStructure(
			DDMForm ddmForm, DDMFormLayout ddmFormLayout)
		throws Exception {

		return addStructure(
			DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID, _classNameId,
			null, "Test Structure", StringPool.BLANK, ddmForm, ddmFormLayout,
			StorageType.DEFAULT.toString(), DDMStructureConstants.TYPE_DEFAULT);
	}

	public DDMStructure addStructure(DDMForm ddmForm, String storageType)
		throws Exception {

		return addStructure(
			_classNameId, null, "Test Structure", ddmForm, storageType,
			DDMStructureConstants.TYPE_DEFAULT);
	}

	public DDMStructure addStructure(
			Group group, long parentStructureId, long classNameId,
			String structureKey, String name, String description,
			DDMForm ddmForm, DDMFormLayout ddmFormLayout, String storageType,
			int type, int status)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		serviceContext.setAttribute("status", status);

		return DDMStructureLocalServiceUtil.addStructure(
			null, _userId, group.getGroupId(), parentStructureId, classNameId,
			structureKey, getDefaultLocaleMap(name),
			getDefaultLocaleMap(description), ddmForm, ddmFormLayout,
			storageType, type, serviceContext);
	}

	public DDMStructure addStructure(
			long parentStructureId, long classNameId, String structureKey,
			String name, String description, DDMForm ddmForm,
			DDMFormLayout ddmFormLayout, String storageType, int type)
		throws Exception {

		return addStructure(
			parentStructureId, classNameId, structureKey, name, description,
			ddmForm, ddmFormLayout, storageType, type,
			WorkflowConstants.STATUS_APPROVED);
	}

	public DDMStructure addStructure(
			long parentStructureId, long classNameId, String structureKey,
			String name, String description, DDMForm ddmForm,
			DDMFormLayout ddmFormLayout, String storageType, int type,
			int status)
		throws Exception {

		return addStructure(
			_group, parentStructureId, classNameId, structureKey, name,
			description, ddmForm, ddmFormLayout, storageType, type, status);
	}

	public DDMStructure addStructure(
			long classNameId, String structureKey, String name, DDMForm ddmForm,
			String storageType, int type)
		throws Exception {

		DDMFormLayout ddmFormLayout = DDMUtil.getDefaultDDMFormLayout(ddmForm);

		return addStructure(
			DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID, classNameId,
			structureKey, name, StringPool.BLANK, ddmForm, ddmFormLayout,
			storageType, type);
	}

	public DDMStructure addStructure(
			long classNameId, String structureKey, String name,
			String definition, String storageType)
		throws Exception {

		return DDMStructureLocalServiceUtil.addStructure(
			null, _userId, _group.getGroupId(),
			DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID, classNameId,
			structureKey, getDefaultLocaleMap(name),
			getDefaultLocaleMap(StringPool.BLANK), definition, storageType,
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));
	}

	public DDMStructure updateStructure(long structureId, DDMForm ddmForm)
		throws Exception {

		return updateStructure(
			structureId, "Test Structure", StringPool.BLANK, ddmForm);
	}

	public DDMStructure updateStructure(
			long structureId, String name, String description, DDMForm ddmForm)
		throws Exception {

		DDMFormLayout ddmFormLayout = DDMUtil.getDefaultDDMFormLayout(ddmForm);

		return updateStructure(
			structureId, name, description, ddmForm, ddmFormLayout);
	}

	public DDMStructure updateStructure(
			long structureId, String name, String description, DDMForm ddmForm,
			DDMFormLayout ddmFormLayout)
		throws Exception {

		return DDMStructureLocalServiceUtil.updateStructure(
			_userId, structureId,
			DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID,
			getDefaultLocaleMap(name), getDefaultLocaleMap(description),
			ddmForm, ddmFormLayout,
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));
	}

	private final long _classNameId;
	private final Group _group;
	private final long _userId;

}