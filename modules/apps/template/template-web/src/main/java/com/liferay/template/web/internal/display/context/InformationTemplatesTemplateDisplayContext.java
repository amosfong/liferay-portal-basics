/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.template.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.template.model.TemplateEntry;
import com.liferay.template.service.TemplateEntryLocalServiceUtil;
import com.liferay.template.web.internal.security.permissions.resource.TemplateEntryPermission;
import com.liferay.template.web.internal.util.TemplateEntryActionDropdownItemsProvider;

import java.util.List;

/**
 * @author Eudaldo Alonso
 * @author Lourdes Fernández Besada
 */
public class InformationTemplatesTemplateDisplayContext
	extends BaseTemplateDisplayContext {

	public InformationTemplatesTemplateDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		super(liferayPortletRequest, liferayPortletResponse);
	}

	public List<DropdownItem> getTemplateEntryActionDropdownItems(
		TemplateEntry templateEntry) {

		TemplateEntryActionDropdownItemsProvider
			templateEntryActionDropdownItemsProvider =
				new TemplateEntryActionDropdownItemsProvider(
					isAddButtonEnabled(),
					PortalUtil.getHttpServletRequest(liferayPortletRequest),
					liferayPortletResponse, getTabs1(), templateEntry);

		return templateEntryActionDropdownItemsProvider.
			getActionDropdownItems();
	}

	public String getTemplateEntryEditURL(TemplateEntry templateEntry)
		throws PortalException {

		if (!isStagingGroup() ||
			!TemplateEntryPermission.contains(
				themeDisplay.getPermissionChecker(), templateEntry,
				ActionKeys.UPDATE)) {

			return StringPool.BLANK;
		}

		return PortletURLBuilder.createRenderURL(
			liferayPortletResponse
		).setMVCRenderCommandName(
			"/template/edit_ddm_template"
		).setRedirect(
			themeDisplay.getURLCurrent()
		).setTabs1(
			getTabs1()
		).setParameter(
			"ddmTemplateId", templateEntry.getDDMTemplateId()
		).setParameter(
			"templateEntryId", templateEntry.getTemplateEntryId()
		).buildString();
	}

	public SearchContainer<TemplateEntry> getTemplateSearchContainer() {
		if (_templateEntrySearchContainer != null) {
			return _templateEntrySearchContainer;
		}

		SearchContainer<TemplateEntry> templateEntrySearchContainer =
			new SearchContainer<>(
				liferayPortletRequest, getPortletURL(), null,
				"there-are-no-templates");

		templateEntrySearchContainer.setOrderByCol(getOrderByCol());
		templateEntrySearchContainer.setOrderByType(getOrderByType());
		templateEntrySearchContainer.setResultsAndTotal(
			() -> TemplateEntryLocalServiceUtil.getTemplateEntries(
				themeDisplay.getScopeGroupId(),
				templateEntrySearchContainer.getStart(),
				templateEntrySearchContainer.getEnd(), null),
			TemplateEntryLocalServiceUtil.getTemplateEntriesCount(
				themeDisplay.getScopeGroupId()));
		templateEntrySearchContainer.setRowChecker(
			new EmptyOnClickRowChecker(liferayPortletResponse));

		_templateEntrySearchContainer = templateEntrySearchContainer;

		return _templateEntrySearchContainer;
	}

	public String getTemplateSubtypeLabel(TemplateEntry templateEntry) {
		if (Validator.isNull(templateEntry.getInfoItemFormVariationKey())) {
			return StringPool.BLANK;
		}

		return StringPool.BLANK;
	}

	public String getTemplateTypeLabel(TemplateEntry templateEntry) {
		String defaultValue = ResourceActionsUtil.getModelResource(
			themeDisplay.getLocale(), templateEntry.getInfoItemClassName());

		return defaultValue;
	}

	private SearchContainer<TemplateEntry> _templateEntrySearchContainer;

}