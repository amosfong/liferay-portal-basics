/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.spi.model.index.contributor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentContributor;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.GroupLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	property = "service.ranking:Integer=10000",
	service = DocumentContributor.class
)
public class GroupedModelDocumentContributor
	implements DocumentContributor<GroupedModel> {

	@Override
	public void contribute(
		Document document, BaseModel<GroupedModel> baseModel) {

		if (!(baseModel instanceof GroupedModel)) {
			return;
		}

		GroupedModel groupedModel = (GroupedModel)baseModel;

		long siteGroupId = GroupUtil.getSiteGroupId(
			groupLocalService, groupedModel.getGroupId());

		document.addKeyword(Field.GROUP_ID, siteGroupId);

		document.addKeyword(Field.SCOPE_GROUP_ID, groupedModel.getGroupId());
		document.addKeyword(
			"groupExternalReferenceCode",
			_getGroupExternalReferenceCode(siteGroupId));
		document.addKeyword(
			"scopeGroupExternalReferenceCode",
			_getScopeGroupExternalReferenceCode(groupedModel));
	}

	@Reference
	protected GroupLocalService groupLocalService;

	private String _getGroupExternalReferenceCode(long siteGroupId) {
		Group group = groupLocalService.fetchGroup(siteGroupId);

		if (group == null) {
			return StringPool.BLANK;
		}

		return group.getExternalReferenceCode();
	}

	private String _getScopeGroupExternalReferenceCode(
		GroupedModel groupedModel) {

		Group group = groupLocalService.fetchGroup(groupedModel.getGroupId());

		if (group == null) {
			return StringPool.BLANK;
		}

		return group.getExternalReferenceCode();
	}

}