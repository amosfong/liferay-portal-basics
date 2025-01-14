/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.storage;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Eudaldo Alonso
 */
@ProviderType
public interface DDMStorageEngineManager {

	public static final String STORAGE_TYPE_DEFAULT = "json";

	public long create(
			long companyId, long ddmStructureId, DDMFormValues ddmFormValues,
			ServiceContext serviceContext)
		throws PortalException;

	public void deleteByClass(long classPK) throws PortalException;

	public DDMFormValues getDDMFormValues(long classPK) throws PortalException;

	public DDMFormValues getDDMFormValues(long classPK, DDMForm ddmForm)
		throws PortalException;

	public DDMFormValues getDDMFormValues(
			long ddmStructureId, String fieldNamespace,
			ServiceContext serviceContext)
		throws PortalException;

	public void update(
			long classPK, DDMFormValues ddmFormValues,
			ServiceContext serviceContext)
		throws PortalException;

}