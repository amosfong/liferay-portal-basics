/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notification.model.impl;

import com.liferay.notification.model.NotificationQueueEntry;
import com.liferay.notification.service.NotificationQueueEntryLocalServiceUtil;

/**
 * The extended model base implementation for the NotificationQueueEntry service. Represents a row in the &quot;NotificationQueueEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NotificationQueueEntryImpl}.
 * </p>
 *
 * @author Gabriel Albuquerque
 * @see NotificationQueueEntryImpl
 * @see NotificationQueueEntry
 * @generated
 */
public abstract class NotificationQueueEntryBaseImpl
	extends NotificationQueueEntryModelImpl implements NotificationQueueEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a notification queue entry model instance should use the <code>NotificationQueueEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			NotificationQueueEntryLocalServiceUtil.addNotificationQueueEntry(
				this);
		}
		else {
			NotificationQueueEntryLocalServiceUtil.updateNotificationQueueEntry(
				this);
		}
	}

}