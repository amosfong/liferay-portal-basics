/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the SiteFriendlyURL service. Represents a row in the &quot;SiteFriendlyURL&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SiteFriendlyURLModel
 * @generated
 */
@ImplementationClassName("com.liferay.site.model.impl.SiteFriendlyURLImpl")
@ProviderType
public interface SiteFriendlyURL extends PersistedModel, SiteFriendlyURLModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.site.model.impl.SiteFriendlyURLImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SiteFriendlyURL, Long>
		SITE_FRIENDLY_URL_ID_ACCESSOR = new Accessor<SiteFriendlyURL, Long>() {

			@Override
			public Long get(SiteFriendlyURL siteFriendlyURL) {
				return siteFriendlyURL.getSiteFriendlyURLId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SiteFriendlyURL> getTypeClass() {
				return SiteFriendlyURL.class;
			}

		};

}