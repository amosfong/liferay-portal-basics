/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.web.internal.helper;

import com.liferay.change.tracking.constants.CTActionKeys;
import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.constants.CTPortletKeys;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.model.CTCollectionTable;
import com.liferay.change.tracking.model.CTCollectionTemplate;
import com.liferay.change.tracking.model.CTPreferences;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.change.tracking.service.CTCollectionTemplateLocalService;
import com.liferay.change.tracking.service.CTPreferencesLocalService;
import com.liferay.change.tracking.web.internal.configuration.helper.CTSettingsConfigurationHelper;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.text.Format;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Truong
 */
@Component(service = SandboxHelper.class)
public class SandboxHelper {

	public void sandbox(CTPreferences ctPreferences) throws PortalException {
		if ((ctPreferences == null) ||
			!_ctSettingsConfigurationHelper.isSandboxEnabled(
				ctPreferences.getCompanyId())) {

			return;
		}

		if (ctPreferences.getCtCollectionId() !=
				CTConstants.CT_COLLECTION_ID_PRODUCTION) {

			CTCollection ctCollection =
				_ctCollectionLocalService.fetchCTCollection(
					ctPreferences.getCtCollectionId());

			if ((ctCollection != null) &&
				(ctCollection.getStatus() == WorkflowConstants.STATUS_DRAFT)) {

				return;
			}
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker == null) {
			permissionChecker = _permissionCheckerFactory.create(
				_userLocalService.getUser(ctPreferences.getUserId()));

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}

		if (PortletPermissionUtil.contains(
				permissionChecker, CTPortletKeys.PUBLICATIONS,
				CTActionKeys.WORK_ON_PRODUCTION)) {

			return;
		}

		CTCollection ctCollection = null;

		if (ctPreferences.getPreviousCtCollectionId() !=
				CTConstants.CT_COLLECTION_ID_PRODUCTION) {

			ctCollection = _ctCollectionLocalService.fetchCTCollection(
				ctPreferences.getPreviousCtCollectionId());
		}

		if ((ctCollection == null) ||
			(ctCollection.getStatus() != WorkflowConstants.STATUS_DRAFT)) {

			ctCollection = _findUserCTCollection(ctPreferences);
		}

		if (ctCollection == null) {
			ctCollection = _addSandboxCTCollection(ctPreferences.getUserId());
		}

		ctPreferences.setCtCollectionId(ctCollection.getCtCollectionId());
		ctPreferences.setPreviousCtCollectionId(
			CTConstants.CT_COLLECTION_ID_PRODUCTION);

		_ctPreferencesLocalService.updateCTPreferences(ctPreferences);
	}

	private CTCollection _addSandboxCTCollection(long userId)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		long ctCollectionTemplateId =
			_ctSettingsConfigurationHelper.
				getDefaultSandboxCTCollectionTemplateId(user.getCompanyId());

		if (ctCollectionTemplateId == 0) {
			ctCollectionTemplateId =
				_ctSettingsConfigurationHelper.getDefaultCTCollectionTemplateId(
					user.getCompanyId());
		}

		CTCollectionTemplate ctCollectionTemplate =
			_ctCollectionTemplateLocalService.fetchCTCollectionTemplate(
				ctCollectionTemplateId);

		String name;
		String description;

		if (ctCollectionTemplate != null) {
			name = ctCollectionTemplate.getParsedPublicationName();
			description =
				ctCollectionTemplate.getParsedPublicationDescription();
		}
		else {
			Format format = _fastDateFormatFactory.getDateTime(
				user.getLocale(), user.getTimeZone());

			name =
				user.getScreenName() + " - " +
					format.format(System.currentTimeMillis());

			description = _language.get(
				user.getLocale(), "autogenerated-by-sandbox-only-mode");
		}

		return _ctCollectionLocalService.addCTCollection(
			null, user.getCompanyId(), user.getUserId(), 0, name, description);
	}

	private CTCollection _findUserCTCollection(CTPreferences ctPreferences) {
		List<CTCollection> ctCollections = _ctCollectionLocalService.dslQuery(
			DSLQueryFactoryUtil.select(
				CTCollectionTable.INSTANCE
			).from(
				CTCollectionTable.INSTANCE
			).where(
				CTCollectionTable.INSTANCE.ctCollectionId.notIn(
					new Long[] {
						ctPreferences.getCtCollectionId(),
						ctPreferences.getPreviousCtCollectionId()
					}
				).and(
					CTCollectionTable.INSTANCE.userId.eq(
						ctPreferences.getUserId())
				).and(
					CTCollectionTable.INSTANCE.status.eq(
						WorkflowConstants.STATUS_DRAFT)
				)
			).orderBy(
				CTCollectionTable.INSTANCE.modifiedDate.descending()
			));

		if (ctCollections.isEmpty()) {
			return null;
		}

		return ctCollections.get(0);
	}

	@Reference
	private CTCollectionLocalService _ctCollectionLocalService;

	@Reference
	private CTCollectionTemplateLocalService _ctCollectionTemplateLocalService;

	@Reference
	private CTPreferencesLocalService _ctPreferencesLocalService;

	@Reference
	private CTSettingsConfigurationHelper _ctSettingsConfigurationHelper;

	@Reference
	private FastDateFormatFactory _fastDateFormatFactory;

	@Reference
	private Language _language;

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Reference
	private UserLocalService _userLocalService;

}