/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.internal.resource.v1_0;

import com.liferay.headless.admin.site.dto.v1_0.DisplayPageTemplate;
import com.liferay.headless.admin.site.dto.v1_0.MasterPage;
import com.liferay.headless.admin.site.dto.v1_0.PageSpecification;
import com.liferay.headless.admin.site.dto.v1_0.PageTemplate;
import com.liferay.headless.admin.site.dto.v1_0.SitePage;
import com.liferay.headless.admin.site.dto.v1_0.UtilityPage;
import com.liferay.headless.admin.site.internal.resource.util.GroupUtil;
import com.liferay.headless.admin.site.resource.v1_0.PageSpecificationResource;
import com.liferay.headless.common.spi.service.context.ServiceContextBuilder;
import com.liferay.layout.constants.LayoutTypeSettingsConstants;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.layout.utility.page.model.LayoutUtilityPageEntry;
import com.liferay.layout.utility.page.service.LayoutUtilityPageEntryService;
import com.liferay.portal.kernel.exception.LockedLayoutException;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.List;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Rubén Pulido
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/page-specification.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = PageSpecificationResource.class
)
public class PageSpecificationResourceImpl
	extends BasePageSpecificationResourceImpl {

	@Override
	public void deleteSiteSiteByExternalReferenceCodePageSpecification(
			String siteExternalReferenceCode,
			String pageSpecificationExternalReferenceCode)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled("LPD-35443")) {
			throw new UnsupportedOperationException();
		}

		Layout layout = _layoutService.getLayoutByExternalReferenceCode(
			pageSpecificationExternalReferenceCode,
			GroupUtil.getGroupId(
				true, contextCompany.getCompanyId(),
				siteExternalReferenceCode));

		if (!layout.isDraftLayout() ||
			(layout.isApproved() &&
			 GetterUtil.getBoolean(
				 layout.getTypeSettingsProperty("published")))) {

			throw new UnsupportedOperationException();
		}

		_discardDraftLayout(layout);
	}

	@NestedField(
		parentClass = DisplayPageTemplate.class, value = "pageSpecifications"
	)
	@Override
	public Page<PageSpecification>
			getSiteSiteByExternalReferenceCodeDisplayPageTemplatePageSpecificationsPage(
				String siteExternalReferenceCode,
				@NestedFieldId(value = "externalReferenceCode") String
					displayPageTemplateExternalReferenceCode)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled("LPD-35443")) {
			throw new UnsupportedOperationException();
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.
				getLayoutPageTemplateEntryByExternalReferenceCode(
					displayPageTemplateExternalReferenceCode,
					GroupUtil.getGroupId(
						true, contextCompany.getCompanyId(),
						siteExternalReferenceCode));

		if (!Objects.equals(
				LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE,
				layoutPageTemplateEntry.getType())) {

			throw new UnsupportedOperationException();
		}

		return Page.of(
			_toPageSpecifications(
				_layoutLocalService.getLayout(
					layoutPageTemplateEntry.getPlid())));
	}

	@NestedField(parentClass = MasterPage.class, value = "pageSpecifications")
	@Override
	public Page<PageSpecification>
			getSiteSiteByExternalReferenceCodeMasterPagePageSpecificationsPage(
				String siteExternalReferenceCode,
				@NestedFieldId(value = "externalReferenceCode") String
					masterPageExternalReferenceCode)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled("LPD-35443")) {
			throw new UnsupportedOperationException();
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.
				getLayoutPageTemplateEntryByExternalReferenceCode(
					masterPageExternalReferenceCode,
					GroupUtil.getGroupId(
						true, contextCompany.getCompanyId(),
						siteExternalReferenceCode));

		if (!Objects.equals(
				LayoutPageTemplateEntryTypeConstants.MASTER_LAYOUT,
				layoutPageTemplateEntry.getType())) {

			throw new UnsupportedOperationException();
		}

		return Page.of(
			_toPageSpecifications(
				_layoutLocalService.getLayout(
					layoutPageTemplateEntry.getPlid())));
	}

	@Override
	public PageSpecification
			getSiteSiteByExternalReferenceCodePageSpecification(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled("LPD-35443")) {
			throw new UnsupportedOperationException();
		}

		Layout layout = _layoutService.getLayoutByExternalReferenceCode(
			pageSpecificationExternalReferenceCode,
			GroupUtil.getGroupId(
				true, true, contextCompany.getCompanyId(),
				siteExternalReferenceCode));

		if (!_isPageSpecificationSupported(layout)) {
			throw new UnsupportedOperationException();
		}

		return _pageSpecificationDTOConverter.toDTO(layout);
	}

	@NestedField(parentClass = PageTemplate.class, value = "pageSpecifications")
	@Override
	public Page<PageSpecification>
			getSiteSiteByExternalReferenceCodePageTemplatePageSpecificationsPage(
				String siteExternalReferenceCode,
				@NestedFieldId(value = "externalReferenceCode") String
					pageTemplateExternalReferenceCode)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled("LPD-35443")) {
			throw new UnsupportedOperationException();
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.
				getLayoutPageTemplateEntryByExternalReferenceCode(
					pageTemplateExternalReferenceCode,
					GroupUtil.getGroupId(
						true, true, contextCompany.getCompanyId(),
						siteExternalReferenceCode));

		if (!Objects.equals(
				LayoutPageTemplateEntryTypeConstants.BASIC,
				layoutPageTemplateEntry.getType()) &&
			!Objects.equals(
				LayoutPageTemplateEntryTypeConstants.WIDGET_PAGE,
				layoutPageTemplateEntry.getType())) {

			throw new UnsupportedOperationException();
		}

		return Page.of(
			_toPageSpecifications(
				_layoutLocalService.getLayout(
					layoutPageTemplateEntry.getPlid())));
	}

	@NestedField(parentClass = SitePage.class, value = "pageSpecifications")
	@Override
	public Page<PageSpecification>
			getSiteSiteByExternalReferenceCodeSitePagePageSpecificationsPage(
				String siteExternalReferenceCode,
				@NestedFieldId(value = "externalReferenceCode") String
					sitePageExternalReferenceCode)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled("LPD-35443")) {
			throw new UnsupportedOperationException();
		}

		Layout layout = _layoutService.getLayoutByExternalReferenceCode(
			sitePageExternalReferenceCode,
			GroupUtil.getGroupId(
				true, contextCompany.getCompanyId(),
				siteExternalReferenceCode));

		if (layout.isDraftLayout() || layout.isTypeAssetDisplay() ||
			layout.isTypeUtility()) {

			throw new UnsupportedOperationException();
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.
				fetchLayoutPageTemplateEntryByPlid(layout.getPlid());

		if (layoutPageTemplateEntry != null) {
			throw new UnsupportedOperationException();
		}

		return Page.of(_toPageSpecifications(layout));
	}

	@NestedField(parentClass = UtilityPage.class, value = "pageSpecifications")
	@Override
	public Page<PageSpecification>
			getSiteSiteByExternalReferenceCodeUtilityPagePageSpecificationsPage(
				String siteExternalReferenceCode,
				@NestedFieldId(value = "externalReferenceCode") String
					utilityPageExternalReferenceCode)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled("LPD-35443")) {
			throw new UnsupportedOperationException();
		}

		LayoutUtilityPageEntry layoutUtilityPageEntry =
			_layoutUtilityPageEntryService.
				getLayoutUtilityPageEntryByExternalReferenceCode(
					utilityPageExternalReferenceCode,
					GroupUtil.getGroupId(
						true, contextCompany.getCompanyId(),
						siteExternalReferenceCode));

		return Page.of(
			_toPageSpecifications(
				_layoutLocalService.getLayout(
					layoutUtilityPageEntry.getPlid())));
	}

	private void _discardDraftLayout(Layout draftLayout) throws Exception {
		Layout layout = _layoutLocalService.getLayout(draftLayout.getClassPK());

		try {
			boolean published = layout.isPublished();

			draftLayout = _layoutLocalService.copyLayoutContent(
				layout, draftLayout);

			ServiceContext serviceContext = ServiceContextBuilder.create(
				layout.getGroupId(), contextHttpServletRequest, null
			).build();

			serviceContext.setAttribute(
				LayoutTypeSettingsConstants.KEY_PUBLISHED, published);
			serviceContext.setUserId(contextUser.getUserId());

			_layoutLocalService.updateStatus(
				contextUser.getUserId(), draftLayout.getPlid(),
				WorkflowConstants.STATUS_APPROVED, serviceContext);
		}
		catch (Exception exception) {
			if (!(exception instanceof LockedLayoutException) &&
				!(exception.getCause() instanceof LockedLayoutException)) {

				throw new UnsupportedOperationException();
			}
		}
	}

	private boolean _isPageSpecificationSupported(Layout layout) {
		if (_isPublished(layout)) {
			if (!layout.isApproved() || !layout.isDraftLayout()) {
				return true;
			}

			return false;
		}

		if (layout.isDraftLayout()) {
			return true;
		}

		return false;
	}

	private boolean _isPublished(Layout layout) {
		if (!layout.isTypeAssetDisplay() && !layout.isTypeContent()) {
			return true;
		}

		if (layout.isDraftLayout()) {
			return GetterUtil.getBoolean(
				layout.getTypeSettingsProperty("published"));
		}

		Layout draftLayout = layout.fetchDraftLayout();

		return GetterUtil.getBoolean(
			draftLayout.getTypeSettingsProperty("published"));
	}

	private List<PageSpecification> _toPageSpecifications(Layout layout)
		throws Exception {

		Layout draftLayout = layout.fetchDraftLayout();

		if (_isPublished(layout)) {
			if ((draftLayout != null) && !draftLayout.isApproved()) {
				return ListUtil.fromArray(
					_pageSpecificationDTOConverter.toDTO(layout),
					_pageSpecificationDTOConverter.toDTO(draftLayout));
			}

			return ListUtil.fromArray(
				_pageSpecificationDTOConverter.toDTO(layout));
		}

		if (draftLayout == null) {
			throw new UnsupportedOperationException();
		}

		return ListUtil.fromArray(
			_pageSpecificationDTOConverter.toDTO(draftLayout));
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Reference
	private LayoutPageTemplateEntryService _layoutPageTemplateEntryService;

	@Reference
	private LayoutService _layoutService;

	@Reference
	private LayoutUtilityPageEntryService _layoutUtilityPageEntryService;

	@Reference(
		target = "(component.name=com.liferay.headless.admin.site.internal.dto.v1_0.converter.PageSpecificationDTOConverter)"
	)
	private DTOConverter<Layout, PageSpecification>
		_pageSpecificationDTOConverter;

}