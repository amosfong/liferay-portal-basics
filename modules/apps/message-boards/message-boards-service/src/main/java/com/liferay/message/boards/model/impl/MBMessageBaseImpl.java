/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.model.impl;

import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.service.MBMessageLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

/**
 * The extended model base implementation for the MBMessage service. Represents a row in the &quot;MBMessage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MBMessageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBMessageImpl
 * @see MBMessage
 * @generated
 */
public abstract class MBMessageBaseImpl
	extends MBMessageModelImpl implements MBMessage {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a message-boards message model instance should use the <code>MBMessage</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			MBMessageLocalServiceUtil.addMBMessage(this);
		}
		else {
			MBMessageLocalServiceUtil.updateMBMessage(this);
		}
	}

	@Override
	@SuppressWarnings("unused")
	public String buildTreePath() throws PortalException {
		List<MBMessage> mbMessages = new ArrayList<MBMessage>();

		MBMessage mbMessage = this;

		while (mbMessage != null) {
			mbMessages.add(mbMessage);

			mbMessage = MBMessageLocalServiceUtil.fetchMBMessage(
				mbMessage.getParentMessageId());
		}

		StringBundler sb = new StringBundler((mbMessages.size() * 2) + 1);

		sb.append("/");

		for (int i = mbMessages.size() - 1; i >= 0; i--) {
			mbMessage = mbMessages.get(i);

			sb.append(mbMessage.getMessageId());
			sb.append("/");
		}

		return sb.toString();
	}

	@Override
	public void updateTreePath(String treePath) {
		MBMessage mbMessage = this;

		mbMessage.setTreePath(treePath);

		MBMessageLocalServiceUtil.updateMBMessage(mbMessage);
	}

}