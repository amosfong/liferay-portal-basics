/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.social.model.impl;

import com.liferay.social.kernel.model.SocialActivityAchievement;
import com.liferay.social.kernel.service.SocialActivityAchievementLocalServiceUtil;

/**
 * The extended model base implementation for the SocialActivityAchievement service. Represents a row in the &quot;SocialActivityAchievement&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialActivityAchievementImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityAchievementImpl
 * @see SocialActivityAchievement
 * @generated
 */
public abstract class SocialActivityAchievementBaseImpl
	extends SocialActivityAchievementModelImpl
	implements SocialActivityAchievement {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a social activity achievement model instance should use the <code>SocialActivityAchievement</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			SocialActivityAchievementLocalServiceUtil.
				addSocialActivityAchievement(this);
		}
		else {
			SocialActivityAchievementLocalServiceUtil.
				updateSocialActivityAchievement(this);
		}
	}

}