/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.social.model.impl;

import com.liferay.social.kernel.model.SocialActivitySetting;
import com.liferay.social.kernel.service.SocialActivitySettingLocalServiceUtil;

/**
 * The extended model base implementation for the SocialActivitySetting service. Represents a row in the &quot;SocialActivitySetting&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialActivitySettingImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySettingImpl
 * @see SocialActivitySetting
 * @generated
 */
public abstract class SocialActivitySettingBaseImpl
	extends SocialActivitySettingModelImpl implements SocialActivitySetting {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a social activity setting model instance should use the <code>SocialActivitySetting</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			SocialActivitySettingLocalServiceUtil.addSocialActivitySetting(
				this);
		}
		else {
			SocialActivitySettingLocalServiceUtil.updateSocialActivitySetting(
				this);
		}
	}

}