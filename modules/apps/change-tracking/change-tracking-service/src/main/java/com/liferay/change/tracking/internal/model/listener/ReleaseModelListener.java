/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.internal.model.listener;

import com.liferay.change.tracking.internal.util.CTSchemaVersionHelper;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.version.Version;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(service = ModelListener.class)
public class ReleaseModelListener extends BaseModelListener<Release> {

	@Override
	public void onAfterCreate(Release release) {
		if (!Objects.equals(release.getSchemaVersion(), "0.0.0")) {
			_ctSchemaVersionHelper.expireCTCollections();
		}
	}

	@Override
	public void onAfterRemove(Release release) {
		_ctSchemaVersionHelper.expireCTCollections();
	}

	@Override
	public void onAfterUpdate(Release originalRelease, Release release) {
		if (!Objects.equals(
				originalRelease.getSchemaVersion(),
				release.getSchemaVersion())) {

			Version version1 = Version.parseVersion(
				originalRelease.getSchemaVersion());
			Version version2 = Version.parseVersion(release.getSchemaVersion());

			if ((version1.getMajor() != version2.getMajor()) ||
				(version1.getMinor() != version2.getMinor())) {

				_ctSchemaVersionHelper.expireCTCollections();
			}
		}
	}

	@Reference
	private CTSchemaVersionHelper _ctSchemaVersionHelper;

	@Reference(target = ModuleServiceLifecycle.PORTLETS_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

}