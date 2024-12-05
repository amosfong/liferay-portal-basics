/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.planner.rest.internal.resource.v1_0;

import com.liferay.batch.planner.batch.engine.task.TaskItemUtil;
import com.liferay.batch.planner.rest.dto.v1_0.SiteScope;
import com.liferay.batch.planner.rest.internal.vulcan.yaml.openapi.OpenAPIYAMLProvider;
import com.liferay.batch.planner.rest.resource.v1_0.SiteScopeResource;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.util.OpenAPIUtil;
import com.liferay.portal.vulcan.yaml.openapi.OpenAPIYAML;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Matija Petanjek
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/site-scope.properties",
	scope = ServiceScope.PROTOTYPE, service = SiteScopeResource.class
)
public class SiteScopeResourceImpl extends BaseSiteScopeResourceImpl {

	@Override
	public Page<SiteScope> getPlanInternalClassNameKeySiteScopesPage(
			String internalClassNameKey, Boolean export)
		throws Exception {

		if (internalClassNameKey.contains(StringPool.POUND)) {
			ObjectDefinition objectDefinition =
				_objectDefinitionLocalService.getObjectDefinition(
					contextCompany.getCompanyId(),
					TaskItemUtil.getTaskItemDelegateName(internalClassNameKey));

			return Page.of(
				_getSiteScopes(
					Collections.singletonList(objectDefinition.getScope())));
		}

		List<String> entityScopes = null;

		OpenAPIYAML openAPIYAML = _openAPIYAMLProvider.getOpenAPIYAML(
			contextCompany.getCompanyId(), internalClassNameKey);

		if (GetterUtil.getBoolean(export)) {
			entityScopes = OpenAPIUtil.getReadEntityScopes(
				TaskItemUtil.getSimpleClassName(internalClassNameKey),
				openAPIYAML);
		}
		else {
			entityScopes = OpenAPIUtil.getCreateEntityScopes(
				TaskItemUtil.getSimpleClassName(internalClassNameKey),
				openAPIYAML);
		}

		return Page.of(_getSiteScopes(entityScopes));
	}

	private List<SiteScope> _getSiteScopes(List<String> entityScopes)
		throws Exception {

		List<SiteScope> siteScopes = new ArrayList<>();

		if (entityScopes.contains("site")) {
			for (Group group :
					_groupService.getUserSitesGroups(
						_CLASS_NAMES, QueryUtil.ALL_POS)) {

				siteScopes.add(
					new SiteScope() {
						{
							setLabel(group::getDescriptiveName);
							setValue(group::getGroupId);
						}
					});
			}
		}

		return siteScopes;
	}

	private static final String[] _CLASS_NAMES = {
		Company.class.getName(), Group.class.getName(),
		Organization.class.getName()
	};

	@Reference
	private GroupService _groupService;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private OpenAPIYAMLProvider _openAPIYAMLProvider;

}