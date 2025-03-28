/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.service;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.fragment.cache.FragmentEntryLinkCache;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.processor.PortletRegistry;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalService;
import com.liferay.layout.constants.LayoutTypeSettingsConstants;
import com.liferay.layout.friendly.url.LayoutFriendlyURLEntryHelper;
import com.liferay.layout.model.LayoutClassedModelUsage;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.seo.model.LayoutSEOEntry;
import com.liferay.layout.seo.service.LayoutSEOEntryLocalService;
import com.liferay.layout.service.LayoutClassedModelUsageLocalService;
import com.liferay.layout.util.structure.DeletedLayoutStructureItem;
import com.liferay.layout.util.structure.FragmentStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.PortletPreferencesIds;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactory;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.security.auth.GuestOrUserUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ImageLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CopyLayoutThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.exportimport.staging.StagingAdvicesThreadLocal;
import com.liferay.sites.kernel.util.Sites;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(service = ServiceWrapper.class)
public class LayoutLocalServiceWrapper
	extends com.liferay.portal.kernel.service.LayoutLocalServiceWrapper {

	@Override
	public Layout copyLayoutContent(Layout sourceLayout, Layout targetLayout)
		throws Exception {

		return copyLayoutContent(new long[0], sourceLayout, targetLayout);
	}

	@Override
	public Layout copyLayoutContent(
			long segmentsExperienceId, Layout sourceLayout, Layout targetLayout)
		throws Exception {

		return _copyLayoutContent(
			true, sourceLayout, new long[] {segmentsExperienceId}, targetLayout,
			new long[] {0});
	}

	@Override
	public Layout copyLayoutContent(
			long[] segmentsExperiencesIds, Layout sourceLayout,
			Layout targetLayout)
		throws Exception {

		return _copyLayoutContent(
			false, sourceLayout, segmentsExperiencesIds, targetLayout,
			segmentsExperiencesIds);
	}

	@Override
	public Layout fetchLayoutByFriendlyURL(
		long groupId, boolean privateLayout, String friendlyURL) {

		Layout layout = super.fetchLayoutByFriendlyURL(
			groupId, privateLayout, friendlyURL);

		if (layout != null) {
			return layout;
		}

		return _fetchLayoutByFriendlyURL(groupId, privateLayout, friendlyURL);
	}

	@Override
	public Layout getFriendlyURLLayout(
			long groupId, boolean privateLayout, String friendlyURL)
		throws PortalException {

		try {
			return super.getFriendlyURLLayout(
				groupId, privateLayout, friendlyURL);
		}
		catch (NoSuchLayoutException noSuchLayoutException) {
			Layout layout = _fetchLayoutByFriendlyURL(
				groupId, privateLayout, friendlyURL);

			if (layout != null) {
				return layout;
			}

			throw noSuchLayoutException;
		}
	}

	private void _copyAssetCategoryIdsAndAssetTagNames(
			Layout sourceLayout, Layout targetLayout, long userId)
		throws Exception {

		if (sourceLayout.isDraftLayout() || targetLayout.isDraftLayout()) {
			return;
		}

		updateAsset(
			userId, targetLayout,
			_assetCategoryLocalService.getCategoryIds(
				Layout.class.getName(), sourceLayout.getPlid()),
			_assetTagLocalService.getTagNames(
				Layout.class.getName(), sourceLayout.getPlid()));
	}

	private void _copyLayoutClassedModelUsages(
		Layout sourceLayout, Layout targetLayout) {

		List<LayoutClassedModelUsage> sourceLayoutLayoutClassedModelUsages =
			_layoutClassedModelUsageLocalService.
				getLayoutClassedModelUsagesByPlid(sourceLayout.getPlid());

		_deleteLayoutClassedModelUsages(
			sourceLayoutLayoutClassedModelUsages, targetLayout);

		List<LayoutClassedModelUsage> targetLayoutLayoutClassedModelUsages =
			_layoutClassedModelUsageLocalService.
				getLayoutClassedModelUsagesByPlid(targetLayout.getPlid());

		for (LayoutClassedModelUsage sourceLayoutLayoutClassedModelUsage :
				sourceLayoutLayoutClassedModelUsages) {

			if (_hasLayoutClassedModelUsage(
					targetLayoutLayoutClassedModelUsages,
					sourceLayoutLayoutClassedModelUsage)) {

				continue;
			}

			String containerKey =
				sourceLayoutLayoutClassedModelUsage.getContainerKey();

			long containerType =
				sourceLayoutLayoutClassedModelUsage.getContainerType();

			if (containerType == _portal.getClassNameId(
					FragmentEntryLink.class.getName())) {

				long fragmentEntryLinkId = GetterUtil.getLong(
					sourceLayoutLayoutClassedModelUsage.getContainerKey());

				FragmentEntryLink fragmentEntryLink =
					_fragmentEntryLinkLocalService.getFragmentEntryLink(
						sourceLayout.getGroupId(), fragmentEntryLinkId,
						targetLayout.getPlid());

				if (fragmentEntryLink != null) {
					containerKey = String.valueOf(
						fragmentEntryLink.getFragmentEntryLinkId());

					LayoutClassedModelUsage layoutClassedModelUsage =
						_layoutClassedModelUsageLocalService.
							fetchLayoutClassedModelUsage(
								targetLayout.getGroupId(),
								sourceLayoutLayoutClassedModelUsage.
									getClassNameId(),
								sourceLayoutLayoutClassedModelUsage.
									getClassPK(),
								sourceLayoutLayoutClassedModelUsage.
									getClassedModelExternalReferenceCode(),
								containerKey,
								sourceLayoutLayoutClassedModelUsage.
									getContainerType(),
								targetLayout.getPlid());

					if (layoutClassedModelUsage != null) {
						continue;
					}
				}
			}

			_layoutClassedModelUsageLocalService.addLayoutClassedModelUsage(
				sourceLayoutLayoutClassedModelUsage.getGroupId(),
				sourceLayoutLayoutClassedModelUsage.getClassNameId(),
				sourceLayoutLayoutClassedModelUsage.getClassPK(),
				sourceLayoutLayoutClassedModelUsage.
					getClassedModelExternalReferenceCode(),
				containerKey,
				sourceLayoutLayoutClassedModelUsage.getContainerType(),
				targetLayout.getPlid(),
				ServiceContextThreadLocal.getServiceContext());
		}
	}

	private Layout _copyLayoutContent(
			boolean copySegmentsExperience, Layout sourceLayout,
			long[] sourceSegmentsExperiencesIds, Layout targetLayout,
			long[] targetSegmentsExperiencesIds)
		throws Exception {

		boolean copyLayout = CopyLayoutThreadLocal.isCopyLayout();

		ServiceContext currentServiceContext =
			ServiceContextThreadLocal.getServiceContext();

		long ctCollectionId = sourceLayout.getCtCollectionId();

		if (ctCollectionId == 0) {
			ctCollectionId = targetLayout.getCtCollectionId();
		}

		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					ctCollectionId)) {

			CopyLayoutThreadLocal.setCopyLayout(true);

			User user = _getUser(
				sourceLayout, targetLayout, currentServiceContext);

			if ((currentServiceContext == null) ||
				(currentServiceContext.getUserId() != user.getUserId())) {

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setCompanyId(targetLayout.getCompanyId());
				serviceContext.setUserId(user.getUserId());

				ServiceContextThreadLocal.pushServiceContext(serviceContext);
			}

			return TransactionInvokerUtil.invoke(
				_transactionConfig,
				new CopyLayoutCallable(
					copySegmentsExperience, sourceLayout,
					sourceSegmentsExperiencesIds, targetLayout,
					targetSegmentsExperiencesIds, user));
		}
		catch (Throwable throwable) {
			if (_log.isDebugEnabled()) {
				_log.debug(throwable);
			}

			throw new Exception(throwable);
		}
		finally {
			CopyLayoutThreadLocal.setCopyLayout(copyLayout);

			ServiceContextThreadLocal.pushServiceContext(currentServiceContext);
		}
	}

	private void _copyLayoutPageTemplateStructure(
			long[] segmentsExperiencesIds, Layout sourceLayout,
			Layout targetLayout, User user)
		throws Exception {

		Map<Long, FragmentEntryLink> fragmentEntryLinksMap =
			_getFragmentEntryLinksMap(
				sourceLayout, segmentsExperiencesIds, targetLayout);
		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					sourceLayout.getGroupId(), sourceLayout.getPlid());
		Set<Long> targetFragmentEntryLinkIds = _getTargetFragmentEntryLinkIds(
			segmentsExperiencesIds, targetLayout);

		LayoutPageTemplateStructure targetLayoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					targetLayout.getGroupId(), targetLayout.getPlid());

		if (targetLayoutPageTemplateStructure == null) {
			_layoutPageTemplateStructureLocalService.
				addLayoutPageTemplateStructure(
					user.getUserId(), targetLayout.getGroupId(),
					targetLayout.getPlid(), 0, null,
					ServiceContextThreadLocal.getServiceContext());
		}

		_fragmentEntryLinkLocalService.deleteFragmentEntryLinks(
			ArrayUtil.toLongArray(targetFragmentEntryLinkIds));
	}

	private void _copyLayoutPageTemplateStructureFromSegmentsExperience(
			Layout sourceLayout, long sourceSegmentsExperienceId,
			Layout targetLayout, long targetSegmentsExperienceId, User user)
		throws Exception {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					sourceLayout.getGroupId(), sourceLayout.getPlid());

		String data = layoutPageTemplateStructure.getData(
			sourceSegmentsExperienceId);

		if (Validator.isNull(data)) {
			return;
		}

		Set<Long> targetFragmentEntryLinkIds = _getTargetFragmentEntryLinkIds(
			new long[] {sourceSegmentsExperienceId}, targetLayout);

		LayoutStructure layoutStructure = LayoutStructure.of(data);

		for (DeletedLayoutStructureItem deletedLayoutStructureItem :
				layoutStructure.getDeletedLayoutStructureItems()) {

			layoutStructure.deleteLayoutStructureItem(
				deletedLayoutStructureItem.getItemId());
		}

		JSONObject dataJSONObject = _processDataJSONObject(
			layoutStructure, sourceLayout, targetLayout,
			_getFragmentEntryLinksMap(
				sourceLayout, new long[] {sourceSegmentsExperienceId},
				targetLayout),
			targetFragmentEntryLinkIds, targetSegmentsExperienceId, user);

		_layoutPageTemplateStructureLocalService.
			updateLayoutPageTemplateStructureData(
				targetLayout.getGroupId(), targetLayout.getPlid(),
				targetSegmentsExperienceId, dataJSONObject.toString());

		_fragmentEntryLinkLocalService.deleteFragmentEntryLinks(
			ArrayUtil.toLongArray(targetFragmentEntryLinkIds));
	}

	private void _copyLayoutSEOEntry(
			Layout sourceLayout, Layout targetLayout, long userId)
		throws Exception {

		if (sourceLayout.isDraftLayout() || targetLayout.isDraftLayout()) {
			return;
		}

		LayoutSEOEntry layoutSEOEntry =
			_layoutSEOEntryLocalService.fetchLayoutSEOEntry(
				sourceLayout.getGroupId(), sourceLayout.isPrivateLayout(),
				sourceLayout.getLayoutId());

		if (layoutSEOEntry == null) {
			LayoutSEOEntry targetLayoutSEOEntry =
				_layoutSEOEntryLocalService.fetchLayoutSEOEntry(
					targetLayout.getGroupId(), targetLayout.isPrivateLayout(),
					targetLayout.getLayoutId());

			if (targetLayoutSEOEntry != null) {
				_layoutSEOEntryLocalService.deleteLayoutSEOEntry(
					targetLayout.getGroupId(), targetLayout.isPrivateLayout(),
					targetLayout.getLayoutId());
			}

			return;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		serviceContext.setAttribute(
			"layout.instanceable.allowed", Boolean.TRUE);

		_layoutSEOEntryLocalService.copyLayoutSEOEntry(
			userId, targetLayout.getGroupId(), targetLayout.isPrivateLayout(),
			targetLayout.getLayoutId(), layoutSEOEntry.isCanonicalURLEnabled(),
			layoutSEOEntry.getCanonicalURLMap(),
			layoutSEOEntry.getDDMStorageId(),
			layoutSEOEntry.isOpenGraphDescriptionEnabled(),
			layoutSEOEntry.getOpenGraphDescriptionMap(),
			layoutSEOEntry.getOpenGraphImageAltMap(),
			layoutSEOEntry.getOpenGraphImageFileEntryId(),
			layoutSEOEntry.isOpenGraphTitleEnabled(),
			layoutSEOEntry.getOpenGraphTitleMap(), serviceContext);
	}

	private void _copyPortletPermissions(
			List<String> portletIds, Layout sourceLayout, Layout targetLayout)
		throws Exception {

		for (String portletId : portletIds) {
			String resourceName = PortletIdCodec.decodePortletName(portletId);
			String sourceResourcePrimKey = PortletPermissionUtil.getPrimaryKey(
				sourceLayout.getPlid(), portletId);

			Map<Long, Set<String>> sourceRoleIdsToActionIds =
				_resourcePermissionLocalService.
					getAvailableResourcePermissionActionIds(
						targetLayout.getCompanyId(), resourceName,
						ResourceConstants.SCOPE_INDIVIDUAL,
						sourceResourcePrimKey,
						ResourceActionsUtil.getPortletResourceActions(
							resourceName));

			if (sourceRoleIdsToActionIds.isEmpty()) {
				continue;
			}

			List<Long> roleIds = TransformUtil.transform(
				ListUtil.filter(
					_roleLocalService.getGroupRelatedRoles(
						targetLayout.getGroupId()),
					role -> !Objects.equals(
						RoleConstants.ADMINISTRATOR, role.getName())),
				Role::getRoleId);

			Map<Long, String[]> targetRoleIdsToActionIds = new HashMap<>();

			for (Map.Entry<Long, Set<String>> entry :
					sourceRoleIdsToActionIds.entrySet()) {

				Long roleId = entry.getKey();

				if (roleIds.contains(roleId)) {
					Set<String> sourceActionIds = entry.getValue();

					targetRoleIdsToActionIds.put(
						roleId, sourceActionIds.toArray(new String[0]));
				}
			}

			_resourcePermissionLocalService.setResourcePermissions(
				targetLayout.getCompanyId(), resourceName,
				ResourceConstants.SCOPE_INDIVIDUAL,
				PortletPermissionUtil.getPrimaryKey(
					targetLayout.getPlid(), portletId),
				targetRoleIdsToActionIds);
		}
	}

	private void _copyPortletPreferences(
		List<String> portletIds, Layout sourceLayout, Layout targetLayout) {

		boolean stagingAdvicesThreadLocalEnabled =
			StagingAdvicesThreadLocal.isEnabled();

		try {
			StagingAdvicesThreadLocal.setEnabled(false);

			for (String portletId : portletIds) {
				Portlet portlet = _portletLocalService.getPortletById(
					portletId);

				if ((portlet == null) || portlet.isUndeployedPortlet()) {
					continue;
				}

				PortletPreferencesIds portletPreferencesIds =
					_portletPreferencesFactory.getPortletPreferencesIds(
						sourceLayout.getCompanyId(), sourceLayout.getGroupId(),
						0, sourceLayout.getPlid(), portletId);

				javax.portlet.PortletPreferences jxPortletPreferences =
					_portletPreferencesLocalService.fetchPreferences(
						portletPreferencesIds);

				if (jxPortletPreferences == null) {
					continue;
				}

				PortletPreferences targetPortletPreferences =
					_portletPreferencesLocalService.fetchPortletPreferences(
						portletPreferencesIds.getOwnerId(),
						portletPreferencesIds.getOwnerType(),
						targetLayout.getPlid(), portletId);

				if (targetPortletPreferences != null) {
					_portletPreferencesLocalService.updatePreferences(
						targetPortletPreferences.getOwnerId(),
						targetPortletPreferences.getOwnerType(),
						targetPortletPreferences.getPlid(),
						targetPortletPreferences.getPortletId(),
						jxPortletPreferences);
				}
				else {
					_portletPreferencesLocalService.addPortletPreferences(
						targetLayout.getCompanyId(),
						portletPreferencesIds.getOwnerId(),
						portletPreferencesIds.getOwnerType(),
						targetLayout.getPlid(), portletId, portlet,
						PortletPreferencesFactoryUtil.toXML(
							jxPortletPreferences));
				}
			}
		}
		finally {
			StagingAdvicesThreadLocal.setEnabled(
				stagingAdvicesThreadLocalEnabled);
		}
	}

	private void _deleteLayoutClassedModelUsages(
		List<LayoutClassedModelUsage> sourceLayoutLayoutClassedModelUsages,
		Layout targetLayout) {

		for (LayoutClassedModelUsage targetLayoutClassedModelUsage :
				_layoutClassedModelUsageLocalService.
					getLayoutClassedModelUsagesByPlid(targetLayout.getPlid())) {

			if (!_hasLayoutClassedModelUsage(
					sourceLayoutLayoutClassedModelUsages,
					targetLayoutClassedModelUsage)) {

				_layoutClassedModelUsageLocalService.
					deleteLayoutClassedModelUsage(
						targetLayoutClassedModelUsage);
			}
		}
	}

	private Layout _fetchLayoutByFriendlyURL(
		long groupId, boolean privateLayout, String friendlyURL) {

		FriendlyURLEntry friendlyURLEntry =
			_friendlyURLEntryLocalService.fetchFriendlyURLEntry(
				groupId,
				_layoutFriendlyURLEntryHelper.getClassNameId(privateLayout),
				friendlyURL);

		if (friendlyURLEntry != null) {
			Layout layout = fetchLayout(friendlyURLEntry.getClassPK());

			if (layout != null) {
				return layout;
			}
		}

		return null;
	}

	private Map<Long, FragmentEntryLink> _getFragmentEntryLinksMap(
		Layout sourceLayout, long[] segmentsExperiencesIds,
		Layout targetLayout) {

		Map<Long, FragmentEntryLink> fragmentEntryLinksMap = new HashMap<>();

		for (FragmentEntryLink fragmentEntryLink :
				_fragmentEntryLinkLocalService.
					getFragmentEntryLinksBySegmentsExperienceId(
						sourceLayout.getGroupId(), segmentsExperiencesIds,
						sourceLayout.getPlid())) {

			if (fragmentEntryLink.isDeleted()) {
				FragmentEntryLink targetLayoutFragmentEntryLink =
					_fragmentEntryLinkLocalService.getFragmentEntryLink(
						targetLayout.getGroupId(),
						fragmentEntryLink.getFragmentEntryLinkId(),
						targetLayout.getPlid());

				if (targetLayoutFragmentEntryLink != null) {
					_fragmentEntryLinkLocalService.deleteFragmentEntryLink(
						targetLayoutFragmentEntryLink);
				}

				continue;
			}

			fragmentEntryLinksMap.put(
				fragmentEntryLink.getFragmentEntryLinkId(), fragmentEntryLink);
		}

		return fragmentEntryLinksMap;
	}

	private Set<Long> _getTargetFragmentEntryLinkIds(
		long[] segmentsExperiencesIds, Layout targetLayout) {

		return new HashSet<>(
			ListUtil.toList(
				_fragmentEntryLinkLocalService.
					getFragmentEntryLinksBySegmentsExperienceId(
						targetLayout.getGroupId(), segmentsExperiencesIds,
						targetLayout.getPlid()),
				FragmentEntryLink.FRAGMENT_ENTRY_LINK_ID_ACCESSOR));
	}

	private String _getTypeSettings(Layout sourceLayout, Layout targetLayout) {
		if (!sourceLayout.isDraftLayout() && !targetLayout.isDraftLayout()) {
			return sourceLayout.getTypeSettings();
		}

		if (targetLayout.isDraftLayout()) {
			UnicodeProperties typeSettingsUnicodeProperties =
				UnicodePropertiesBuilder.create(
					true
				).fastLoad(
					sourceLayout.getTypeSettings()
				).build();

			return UnicodePropertiesBuilder.create(
				true
			).fastLoad(
				sourceLayout.getTypeSettings()
			).setProperty(
				LayoutTypeSettingsConstants.KEY_PUBLISHED,
				Boolean.FALSE.toString()
			).setProperty(
				"query-string",
				typeSettingsUnicodeProperties.getProperty("query-string")
			).setProperty(
				"target", typeSettingsUnicodeProperties.getProperty("target")
			).setProperty(
				"targetType",
				typeSettingsUnicodeProperties.getProperty("targetType")
			).buildString();
		}

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.create(
				true
			).fastLoad(
				targetLayout.getTypeSettings()
			).build();

		return UnicodePropertiesBuilder.create(
			true
		).fastLoad(
			sourceLayout.getTypeSettings()
		).setProperty(
			"query-string",
			typeSettingsUnicodeProperties.getProperty("query-string")
		).setProperty(
			"target", typeSettingsUnicodeProperties.getProperty("target")
		).setProperty(
			"targetType",
			typeSettingsUnicodeProperties.getProperty("targetType")
		).buildString();
	}

	private User _getUser(
			Layout sourceLayout, Layout targetLayout,
			ServiceContext serviceContext)
		throws PortalException {

		User user = _userLocalService.fetchUser(targetLayout.getUserId());

		if (user != null) {
			return user;
		}

		user = _userLocalService.fetchUser(sourceLayout.getUserId());

		if (user != null) {
			return user;
		}

		if (serviceContext != null) {
			user = serviceContext.fetchUser();
		}

		if (user != null) {
			return user;
		}

		return GuestOrUserUtil.getGuestOrUser();
	}

	private boolean _hasLayoutClassedModelUsage(
		List<LayoutClassedModelUsage> layoutClassedModelUsages,
		LayoutClassedModelUsage targetLayoutClassedModelUsage) {

		for (LayoutClassedModelUsage layoutClassedModelUsage :
				layoutClassedModelUsages) {

			if ((layoutClassedModelUsage.getClassNameId() ==
					targetLayoutClassedModelUsage.getClassNameId()) &&
				(layoutClassedModelUsage.getClassPK() ==
					targetLayoutClassedModelUsage.getClassPK()) &&
				Objects.equals(
					layoutClassedModelUsage.getContainerKey(),
					targetLayoutClassedModelUsage.getContainerKey()) &&
				(layoutClassedModelUsage.getContainerType() ==
					targetLayoutClassedModelUsage.getContainerType())) {

				return true;
			}
		}

		return false;
	}

	private JSONObject _processDataJSONObject(
			LayoutStructure layoutStructure, Layout sourceLayout,
			Layout targetLayout,
			Map<Long, FragmentEntryLink> sourceFragmentEntryLinksMap,
			Set<Long> targetFragmentEntryLinkIds,
			long targetSegmentsExperienceId, User user)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		for (LayoutStructureItem layoutStructureItem :
				layoutStructure.getLayoutStructureItems()) {

			if (!(layoutStructureItem instanceof
					FragmentStyledLayoutStructureItem)) {

				continue;
			}

			FragmentStyledLayoutStructureItem
				fragmentStyledLayoutStructureItem =
					(FragmentStyledLayoutStructureItem)layoutStructureItem;

			FragmentEntryLink sourceLayoutfragmentEntryLink =
				sourceFragmentEntryLinksMap.get(
					fragmentStyledLayoutStructureItem.getFragmentEntryLinkId());

			if (sourceLayoutfragmentEntryLink == null) {
				continue;
			}

			FragmentEntryLink newFragmentEntryLink = null;

			FragmentEntryLink targetLayoutFragmentEntryLink =
				_fragmentEntryLinkLocalService.getFragmentEntryLink(
					targetLayout.getGroupId(),
					fragmentStyledLayoutStructureItem.getFragmentEntryLinkId(),
					targetLayout.getPlid());

			if (targetLayoutFragmentEntryLink != null) {
				targetLayoutFragmentEntryLink.setUserId(user.getUserId());
				targetLayoutFragmentEntryLink.setUserName(user.getFullName());
				targetLayoutFragmentEntryLink.setModifiedDate(
					serviceContext.getModifiedDate(new Date()));

				if (sourceLayout.getClassPK() == targetLayout.getPlid()) {
					targetLayoutFragmentEntryLink.
						setOriginalFragmentEntryLinkId(
							sourceLayoutfragmentEntryLink.
								getFragmentEntryLinkId());
				}
				else {
					targetLayoutFragmentEntryLink.
						setOriginalFragmentEntryLinkId(0);
				}

				targetLayoutFragmentEntryLink.setSegmentsExperienceId(
					targetSegmentsExperienceId);
				targetLayoutFragmentEntryLink.setClassPK(
					targetLayout.getPlid());
				targetLayoutFragmentEntryLink.setPlid(targetLayout.getPlid());
				targetLayoutFragmentEntryLink.setCss(
					sourceLayoutfragmentEntryLink.getCss());
				targetLayoutFragmentEntryLink.setHtml(
					sourceLayoutfragmentEntryLink.getHtml());
				targetLayoutFragmentEntryLink.setJs(
					sourceLayoutfragmentEntryLink.getJs());
				targetLayoutFragmentEntryLink.setConfiguration(
					sourceLayoutfragmentEntryLink.getConfiguration());
				targetLayoutFragmentEntryLink.setEditableValues(
					sourceLayoutfragmentEntryLink.getEditableValues());
				targetLayoutFragmentEntryLink.setLastPropagationDate(
					sourceLayoutfragmentEntryLink.getLastPropagationDate());

				newFragmentEntryLink =
					_fragmentEntryLinkLocalService.updateFragmentEntryLink(
						targetLayoutFragmentEntryLink);

				_commentManager.deleteDiscussion(
					FragmentEntryLink.class.getName(),
					newFragmentEntryLink.getFragmentEntryLinkId());

				_fragmentEntryLinkCache.removeFragmentEntryLinkCache(
					newFragmentEntryLink);
			}
			else {
				newFragmentEntryLink =
					(FragmentEntryLink)sourceLayoutfragmentEntryLink.clone();

				newFragmentEntryLink.setUuid(serviceContext.getUuid());
				newFragmentEntryLink.setExternalReferenceCode(null);
				newFragmentEntryLink.setFragmentEntryLinkId(
					_counterLocalService.increment());
				newFragmentEntryLink.setUserId(user.getUserId());
				newFragmentEntryLink.setUserName(user.getFullName());
				newFragmentEntryLink.setCreateDate(
					serviceContext.getCreateDate(new Date()));
				newFragmentEntryLink.setModifiedDate(
					serviceContext.getModifiedDate(new Date()));

				if (sourceLayout.getClassPK() == targetLayout.getPlid()) {
					newFragmentEntryLink.setOriginalFragmentEntryLinkId(
						sourceLayoutfragmentEntryLink.getFragmentEntryLinkId());
				}
				else {
					newFragmentEntryLink.setOriginalFragmentEntryLinkId(0);
				}

				newFragmentEntryLink.setSegmentsExperienceId(
					targetSegmentsExperienceId);
				newFragmentEntryLink.setClassNameId(
					_portal.getClassNameId(Layout.class));
				newFragmentEntryLink.setClassPK(targetLayout.getPlid());
				newFragmentEntryLink.setPlid(targetLayout.getPlid());
				newFragmentEntryLink.setLastPropagationDate(
					sourceLayoutfragmentEntryLink.getLastPropagationDate());

				newFragmentEntryLink =
					_fragmentEntryLinkLocalService.addFragmentEntryLink(
						newFragmentEntryLink);
			}

			fragmentStyledLayoutStructureItem.setFragmentEntryLinkId(
				newFragmentEntryLink.getFragmentEntryLinkId());

			targetFragmentEntryLinkIds.remove(
				newFragmentEntryLink.getFragmentEntryLinkId());

			_commentManager.copyDiscussion(
				user.getUserId(), targetLayout.getGroupId(),
				FragmentEntryLink.class.getName(),
				sourceLayoutfragmentEntryLink.getFragmentEntryLinkId(),
				newFragmentEntryLink.getFragmentEntryLinkId(),
				className -> serviceContext);
		}

		return layoutStructure.toJSONObject();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutLocalServiceWrapper.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private CommentManager _commentManager;

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private FragmentEntryLinkCache _fragmentEntryLinkCache;

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private FriendlyURLEntryLocalService _friendlyURLEntryLocalService;

	@Reference
	private ImageLocalService _imageLocalService;

	@Reference
	private LayoutClassedModelUsageLocalService
		_layoutClassedModelUsageLocalService;

	@Reference
	private LayoutFriendlyURLEntryHelper _layoutFriendlyURLEntryHelper;

	@Reference
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private LayoutSEOEntryLocalService _layoutSEOEntryLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletLocalService _portletLocalService;

	@Reference
	private PortletPreferencesFactory _portletPreferencesFactory;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	@Reference
	private PortletRegistry _portletRegistry;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private Sites _sites;

	@Reference
	private UserLocalService _userLocalService;

	private class CopyLayoutCallable implements Callable<Layout> {

		@Override
		public Layout call() throws Exception {
			if (Objects.equals(
					_sourceLayout.getType(), LayoutConstants.TYPE_PORTLET)) {

				_sites.copyPortletPermissions(_targetLayout, _sourceLayout);
			}
			else {
			}

			// Copy classedModelUsages after copying the structure

			_copyLayoutClassedModelUsages(_sourceLayout, _targetLayout);

			_sites.copyExpandoBridgeAttributes(_sourceLayout, _targetLayout);
			_sites.copyPortletSetups(_sourceLayout, _targetLayout);

			_copyAssetCategoryIdsAndAssetTagNames(
				_sourceLayout, _targetLayout, _user.getUserId());

			_copyLayoutSEOEntry(
				_sourceLayout, _targetLayout, _user.getUserId());

			Image image = _imageLocalService.getImage(
				_sourceLayout.getIconImageId());

			byte[] imageBytes = null;

			if (image != null) {
				imageBytes = image.getTextObj();
			}

			return updateLayout(
				_targetLayout.getGroupId(), _targetLayout.isPrivateLayout(),
				_targetLayout.getLayoutId(),
				_getTypeSettings(_sourceLayout, _targetLayout), imageBytes,
				_sourceLayout.getThemeId(), _sourceLayout.getColorSchemeId(),
				_sourceLayout.getStyleBookEntryId(), _sourceLayout.getCss(),
				_sourceLayout.getFaviconFileEntryId(),
				_sourceLayout.getMasterLayoutPlid());
		}

		private CopyLayoutCallable(
			boolean copySegmentsExperience, Layout sourceLayout,
			long[] sourceSegmentsExperiencesIds, Layout targetLayout,
			long[] targetSegmentsExperiencesIds, User user) {

			_copySegmentsExperience = copySegmentsExperience;
			_sourceLayout = sourceLayout;
			_sourceSegmentsExperiencesIds = sourceSegmentsExperiencesIds;
			_targetLayout = targetLayout;
			_targetSegmentsExperiencesIds = targetSegmentsExperiencesIds;
			_user = user;
		}

		private void _deleteOrphanPortletPreferences(
			List<String> portletIds, List<String> oldPortletIds) {

			String[] deletedPortletIds = TransformUtil.transformToArray(
				oldPortletIds,
				portletId -> {
					if (portletIds.contains(portletId)) {
						return null;
					}

					return portletId;
				},
				String.class);

			if (ArrayUtil.isEmpty(deletedPortletIds)) {
				return;
			}

			LayoutPageTemplateEntry layoutPageTemplateEntry =
				_layoutPageTemplateEntryLocalService.
					fetchLayoutPageTemplateEntryByPlid(_targetLayout.getPlid());

			if ((layoutPageTemplateEntry == null) ||
				!Objects.equals(
					layoutPageTemplateEntry.getType(),
					LayoutPageTemplateEntryTypeConstants.MASTER_LAYOUT)) {

				return;
			}

			for (String portletId : deletedPortletIds) {
				for (PortletPreferences portletPreferences :
						_portletPreferencesLocalService.
							getPortletPreferencesByPortletId(portletId)) {

					try {
						_portletPreferencesLocalService.
							deletePortletPreferences(portletPreferences);
					}
					catch (Exception exception) {
						if (_log.isDebugEnabled()) {
							_log.debug(exception);
						}
					}
				}
			}
		}

		private final boolean _copySegmentsExperience;
		private final Layout _sourceLayout;
		private final long[] _sourceSegmentsExperiencesIds;
		private final Layout _targetLayout;
		private final long[] _targetSegmentsExperiencesIds;
		private final User _user;

	}

}