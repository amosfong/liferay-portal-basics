/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.uad.anonymizer;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordVersionLocalService;
import com.liferay.dynamic.data.mapping.uad.constants.DDMUADConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.user.associated.data.anonymizer.UADAnonymizer;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(service = UADAnonymizer.class)
public class DDMFormInstanceRecordUADAnonymizer
	extends BaseDDMFormInstanceRecordUADAnonymizer {

	@Override
	public void autoAnonymize(
			DDMFormInstanceRecord ddmFormInstanceRecord, long userId,
			User anonymousUser)
		throws PortalException {

		super.autoAnonymize(ddmFormInstanceRecord, userId, anonymousUser);

		List<DDMFormInstanceRecordVersion> ddmFormInstanceRecordVersions =
			_ddmFormInstanceRecordVersionLocalService.
				getFormInstanceRecordVersions(
					ddmFormInstanceRecord.getFormInstanceRecordId(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
				ddmFormInstanceRecordVersions) {

			if (ddmFormInstanceRecordVersion.getUserId() == userId) {
				ddmFormInstanceRecordVersion.setUserId(
					anonymousUser.getUserId());
				ddmFormInstanceRecordVersion.setUserName(
					anonymousUser.getFullName());
			}

			if (ddmFormInstanceRecordVersion.getStatusByUserId() == userId) {
				ddmFormInstanceRecordVersion.setStatusByUserId(
					anonymousUser.getUserId());
				ddmFormInstanceRecordVersion.setStatusByUserName(
					anonymousUser.getFullName());
			}

			_ddmFormInstanceRecordVersionLocalService.
				updateDDMFormInstanceRecordVersion(
					ddmFormInstanceRecordVersion);
		}
	}

	@Override
	protected ActionableDynamicQuery getActionableDynamicQuery(long userId) {
		ActionableDynamicQuery actionableDynamicQuery =
			doGetActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

				Property formInstanceRecordIdProperty =
					PropertyFactoryUtil.forName("formInstanceRecordId");

				for (String userIdFieldName :
						DDMUADConstants.
							USER_ID_FIELD_NAMES_DDM_FORM_INSTANCE_RECORD) {

					disjunction.add(
						RestrictionsFactoryUtil.eq(userIdFieldName, userId));
				}

				DynamicQuery formInstanceRecordIdDynamicQuery =
					_ddmFormInstanceRecordVersionLocalService.dynamicQuery();

				formInstanceRecordIdDynamicQuery.add(
					RestrictionsFactoryUtil.eq("statusByUserId", userId));
				formInstanceRecordIdDynamicQuery.setProjection(
					ProjectionFactoryUtil.property("formInstanceRecordId"));

				disjunction.add(
					formInstanceRecordIdProperty.in(
						formInstanceRecordIdDynamicQuery));

				dynamicQuery.add(disjunction);
			});

		return actionableDynamicQuery;
	}

	@Reference
	private DDMFormInstanceRecordVersionLocalService
		_ddmFormInstanceRecordVersionLocalService;

}