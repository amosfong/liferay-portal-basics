/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.storage;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.service.DDMFieldLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapter;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterDeleteRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterDeleteResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterGetRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterGetResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveResponse;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(
	property = {
		"ddm.storage.adapter.type=default", "service.ranking:Integer=100"
	},
	service = DDMStorageAdapter.class
)
public class DefaultDDMStorageAdapter implements DDMStorageAdapter {

	@Override
	public DDMStorageAdapterDeleteResponse delete(
		DDMStorageAdapterDeleteRequest ddmStorageAdapterDeleteRequest) {

		_ddmFieldLocalService.deleteDDMFormValues(
			ddmStorageAdapterDeleteRequest.getPrimaryKey());

		return DDMStorageAdapterDeleteResponse.Builder.newBuilder(
		).build();
	}

	@Override
	public DDMStorageAdapterGetResponse get(
		DDMStorageAdapterGetRequest ddmStorageAdapterGetRequest) {

		return DDMStorageAdapterGetResponse.Builder.newBuilder(
			_ddmFieldLocalService.getDDMFormValues(
				ddmStorageAdapterGetRequest.getDDMForm(),
				ddmStorageAdapterGetRequest.getPrimaryKey())
		).build();
	}

	@Override
	public DDMStorageAdapterSaveResponse save(
			DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
		throws StorageException {

		long primaryKey = ddmStorageAdapterSaveRequest.getPrimaryKey();

		if (ddmStorageAdapterSaveRequest.isInsert()) {
			primaryKey = _counterLocalService.increment();
		}

		try {
			_ddmFieldLocalService.updateDDMFormValues(
				ddmStorageAdapterSaveRequest.getStructureId(), primaryKey,
				ddmStorageAdapterSaveRequest.getDDMFormValues());
		}
		catch (PortalException portalException) {
			throw new StorageException(portalException);
		}

		return DDMStorageAdapterSaveResponse.Builder.newBuilder(
			primaryKey
		).build();
	}

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private DDMFieldLocalService _ddmFieldLocalService;

}