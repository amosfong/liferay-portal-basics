/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.upgrade.registry;

import com.liferay.layout.internal.upgrade.v1_0_0.LayoutUpgradeProcess;
import com.liferay.layout.internal.upgrade.v1_1_0.UpgradeCompanyId;
import com.liferay.layout.internal.upgrade.v1_2_2.LayoutSEOUpgradeProcess;
import com.liferay.layout.internal.upgrade.v1_2_3.LayoutRevisionUpgradeProcess;
import com.liferay.layout.internal.upgrade.v1_3_0.util.LayoutLocalizationTable;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutBranchLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutRevisionLocalService;
import com.liferay.portal.kernel.service.LayoutSetBranchLocalService;
import com.liferay.portal.kernel.service.PortalPreferencesLocalService;
import com.liferay.portal.kernel.upgrade.CTModelUpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.release.feature.flag.ReleaseFeatureFlagManager;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = UpgradeStepRegistrator.class)
public class LayoutServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("1.0.0", "1.0.1", new LayoutUpgradeProcess());

		registry.register("1.0.1", "1.1.0", new UpgradeCompanyId());

		registry.register(
			"1.1.0", "1.2.0",
			new CTModelUpgradeProcess("LayoutClassedModelUsage"));

		registry.register(
			"1.2.1", "1.2.2", new LayoutSEOUpgradeProcess(_layoutLocalService));

		registry.register(
			"1.2.2", "1.2.3",
			new LayoutRevisionUpgradeProcess(
				_layoutBranchLocalService, _layoutLocalService,
				_layoutRevisionLocalService, _layoutSetBranchLocalService));

		registry.register("1.2.3", "1.3.0", LayoutLocalizationTable.create());

		registry.register(
			"1.3.1", "1.4.0",
			UpgradeProcessFactory.addColumns(
				"LayoutClassedModelUsage",
				"cmExternalReferenceCode VARCHAR(75) null"));

		registry.register(
			"1.4.0", "1.4.1",
			new com.liferay.layout.internal.upgrade.v1_4_1.
				LayoutClassedModelUsageUpgradeProcess(
					_classNameLocalService, _jsonFactory));

		registry.register(
			"1.4.1", "1.4.2",
			new com.liferay.layout.internal.upgrade.v1_4_2.LayoutUpgradeProcess(
				_layoutLocalService));

		registry.register(
			"1.4.2", "1.4.3",
			new com.liferay.layout.internal.upgrade.v1_4_3.
				LayoutClassedModelUsageUpgradeProcess(_classNameLocalService));

		registry.register(
			"1.4.3", "1.4.4",
			new com.liferay.layout.internal.upgrade.v1_4_4.
				LayoutPrivateLayoutsUpgradeProcess(
					_companyLocalService, _portalPreferencesLocalService,
					_releaseFeatureFlagManager));
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.fragment.service)(&(release.schema.version>=2.5.0)))"
	)
	private Release _fragmentServiceRelease;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutBranchLocalService _layoutBranchLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.layout.page.template.service)(&(release.schema.version>=2.1.0)))"
	)
	private Release _layoutPageTemplateServiceRelease;

	@Reference
	private LayoutRevisionLocalService _layoutRevisionLocalService;

	@Reference
	private LayoutSetBranchLocalService _layoutSetBranchLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortalPreferencesLocalService _portalPreferencesLocalService;

	@Reference
	private ReleaseFeatureFlagManager _releaseFeatureFlagManager;

}