/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.WildcardMode;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.site.navigation.constants.SiteNavigationConstants;
import com.liferay.site.navigation.exception.DuplicateSiteNavigationMenuException;
import com.liferay.site.navigation.exception.SiteNavigationMenuNameException;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalService;
import com.liferay.site.navigation.service.base.SiteNavigationMenuLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	property = "model.class.name=com.liferay.site.navigation.model.SiteNavigationMenu",
	service = AopService.class
)
public class SiteNavigationMenuLocalServiceImpl
	extends SiteNavigationMenuLocalServiceBaseImpl {

	@Override
	public SiteNavigationMenu addSiteNavigationMenu(
			String externalReferenceCode, long userId, long groupId,
			String name, int type, boolean auto, ServiceContext serviceContext)
		throws PortalException {

		// Site navigation menu

		_validate(groupId, name);

		User user = _userLocalService.getUser(userId);

		long siteNavigationMenuId = counterLocalService.increment();

		SiteNavigationMenu siteNavigationMenu =
			siteNavigationMenuPersistence.create(siteNavigationMenuId);

		siteNavigationMenu.setUuid(serviceContext.getUuid());
		siteNavigationMenu.setExternalReferenceCode(externalReferenceCode);
		siteNavigationMenu.setGroupId(groupId);
		siteNavigationMenu.setCompanyId(user.getCompanyId());
		siteNavigationMenu.setUserId(userId);
		siteNavigationMenu.setUserName(user.getFullName());
		siteNavigationMenu.setName(name);
		siteNavigationMenu.setType(type);
		siteNavigationMenu.setAuto(auto);

		siteNavigationMenu = siteNavigationMenuPersistence.update(
			siteNavigationMenu);

		// Resources

		_resourceLocalService.addResources(
			siteNavigationMenu.getCompanyId(), siteNavigationMenu.getGroupId(),
			siteNavigationMenu.getUserId(), SiteNavigationMenu.class.getName(),
			siteNavigationMenu.getSiteNavigationMenuId(), false, true, true);

		_updateOldSiteNavigationMenuType(siteNavigationMenu, type);

		return siteNavigationMenu;
	}

	@Override
	public SiteNavigationMenu addSiteNavigationMenu(
			String externalReferenceCode, long userId, long groupId,
			String name, int type, ServiceContext serviceContext)
		throws PortalException {

		return addSiteNavigationMenu(
			externalReferenceCode, userId, groupId, name, type, false,
			serviceContext);
	}

	@Override
	public SiteNavigationMenu addSiteNavigationMenu(
			String externalReferenceCode, long userId, long groupId,
			String name, ServiceContext serviceContext)
		throws PortalException {

		return addSiteNavigationMenu(
			externalReferenceCode, userId, groupId, name,
			SiteNavigationConstants.TYPE_DEFAULT, serviceContext);
	}

	@Override
	public SiteNavigationMenu deleteSiteNavigationMenu(
			long siteNavigationMenuId)
		throws PortalException {

		return siteNavigationMenuLocalService.deleteSiteNavigationMenu(
			getSiteNavigationMenu(siteNavigationMenuId));
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public SiteNavigationMenu deleteSiteNavigationMenu(
			SiteNavigationMenu siteNavigationMenu)
		throws PortalException {

		// Site navigation menu

		siteNavigationMenuPersistence.remove(
			siteNavigationMenu.getSiteNavigationMenuId());

		// Resources

		_resourceLocalService.deleteResource(
			siteNavigationMenu.getCompanyId(),
			SiteNavigationMenu.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			siteNavigationMenu.getSiteNavigationMenuId());

		// Site navigation menu items

		List<SiteNavigationMenuItem> siteNavigationMenuItems =
			_siteNavigationMenuItemLocalService.getSiteNavigationMenuItems(
				siteNavigationMenu.getSiteNavigationMenuId());

		for (SiteNavigationMenuItem siteNavigationMenuItem :
				siteNavigationMenuItems) {

			_siteNavigationMenuItemLocalService.deleteSiteNavigationMenuItem(
				siteNavigationMenuItem.getSiteNavigationMenuItemId());
		}

		return siteNavigationMenu;
	}

	@Override
	public SiteNavigationMenu deleteSiteNavigationMenu(
			String externalReferenceCode, long groupId)
		throws PortalException {

		SiteNavigationMenu siteNavigationMenu =
			siteNavigationMenuPersistence.findByERC_G(
				externalReferenceCode, groupId);

		return deleteSiteNavigationMenu(siteNavigationMenu);
	}

	@Override
	public void deleteSiteNavigationMenus(long groupId) throws PortalException {
		for (SiteNavigationMenu siteNavigationMenu :
				getSiteNavigationMenus(groupId)) {

			deleteSiteNavigationMenu(siteNavigationMenu);
		}
	}

	@Override
	public SiteNavigationMenu fetchPrimarySiteNavigationMenu(long groupId) {
		return fetchSiteNavigationMenu(
			groupId, SiteNavigationConstants.TYPE_PRIMARY);
	}

	@Override
	public SiteNavigationMenu fetchSiteNavigationMenu(long groupId, int type) {
		List<SiteNavigationMenu> siteNavigationMenus =
			siteNavigationMenuPersistence.findByG_T(groupId, type, 0, 1);

		if (siteNavigationMenus.isEmpty()) {
			return null;
		}

		return siteNavigationMenus.get(0);
	}

	@Override
	public SiteNavigationMenu fetchSiteNavigationMenuByName(
		long groupId, String name) {

		return siteNavigationMenuPersistence.fetchByG_N(groupId, name);
	}

	@Override
	public List<SiteNavigationMenu> getAutoSiteNavigationMenus(long groupId) {
		return siteNavigationMenuPersistence.findByG_A(groupId, true);
	}

	@Override
	public List<SiteNavigationMenu> getSiteNavigationMenus(long groupId) {
		return siteNavigationMenuPersistence.findByGroupId(groupId);
	}

	@Override
	public List<SiteNavigationMenu> getSiteNavigationMenus(
		long groupId, int start, int end,
		OrderByComparator<SiteNavigationMenu> orderByComparator) {

		return siteNavigationMenuPersistence.findByGroupId(
			groupId, start, end, orderByComparator);
	}

	@Override
	public List<SiteNavigationMenu> getSiteNavigationMenus(
		long groupId, String keywords, int start, int end,
		OrderByComparator<SiteNavigationMenu> orderByComparator) {

		return siteNavigationMenuPersistence.findByG_LikeN(
			groupId,
			_customSQL.keywords(keywords, false, WildcardMode.SURROUND)[0],
			start, end, orderByComparator);
	}

	@Override
	public int getSiteNavigationMenusCount(long groupId) {
		return siteNavigationMenuPersistence.countByGroupId(groupId);
	}

	@Override
	public int getSiteNavigationMenusCount(long groupId, String keywords) {
		return siteNavigationMenuPersistence.countByG_LikeN(
			groupId,
			_customSQL.keywords(keywords, false, WildcardMode.SURROUND)[0]);
	}

	@Override
	public SiteNavigationMenu updateSiteNavigationMenu(
			long userId, long siteNavigationMenuId, int type, boolean auto,
			ServiceContext serviceContext)
		throws PortalException {

		SiteNavigationMenu siteNavigationMenu = getSiteNavigationMenu(
			siteNavigationMenuId);

		_updateOldSiteNavigationMenuType(siteNavigationMenu, type);

		User user = _userLocalService.getUser(userId);

		siteNavigationMenu.setUserId(userId);
		siteNavigationMenu.setUserName(user.getFullName());
		siteNavigationMenu.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		siteNavigationMenu.setType(type);
		siteNavigationMenu.setAuto(auto);

		return siteNavigationMenuPersistence.update(siteNavigationMenu);
	}

	@Override
	public SiteNavigationMenu updateSiteNavigationMenu(
			long userId, long siteNavigationMenuId, long groupId, String name,
			int type, boolean auto)
		throws PortalException {

		SiteNavigationMenu siteNavigationMenu =
			siteNavigationMenuPersistence.findByPrimaryKey(
				siteNavigationMenuId);

		siteNavigationMenu.setGroupId(groupId);
		siteNavigationMenu.setUserId(userId);
		siteNavigationMenu.setName(name);
		siteNavigationMenu.setType(type);
		siteNavigationMenu.setAuto(auto);

		return siteNavigationMenuPersistence.update(siteNavigationMenu);
	}

	@Override
	public SiteNavigationMenu updateSiteNavigationMenu(
			long userId, long siteNavigationMenuId, String name,
			ServiceContext serviceContext)
		throws PortalException {

		SiteNavigationMenu siteNavigationMenu = getSiteNavigationMenu(
			siteNavigationMenuId);

		if (Objects.equals(siteNavigationMenu.getName(), name)) {
			return siteNavigationMenu;
		}

		User user = _userLocalService.getUser(userId);

		_validate(siteNavigationMenu.getGroupId(), name);

		siteNavigationMenu.setUserId(userId);
		siteNavigationMenu.setUserName(user.getFullName());
		siteNavigationMenu.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		siteNavigationMenu.setName(name);

		return siteNavigationMenuPersistence.update(siteNavigationMenu);
	}

	private void _updateOldSiteNavigationMenuType(
		SiteNavigationMenu siteNavigationMenu, int type) {

		if (type == SiteNavigationConstants.TYPE_DEFAULT) {
			return;
		}

		List<SiteNavigationMenu> siteNavigationMenus =
			siteNavigationMenuPersistence.findByG_T(
				siteNavigationMenu.getGroupId(), type, 0, 1);

		if (siteNavigationMenus.isEmpty()) {
			return;
		}

		SiteNavigationMenu actualTypeSiteNavigationMenu =
			siteNavigationMenus.get(0);

		if ((actualTypeSiteNavigationMenu.getType() == type) &&
			(actualTypeSiteNavigationMenu.getSiteNavigationMenuId() ==
				siteNavigationMenu.getSiteNavigationMenuId())) {

			return;
		}

		actualTypeSiteNavigationMenu.setType(
			SiteNavigationConstants.TYPE_DEFAULT);

		siteNavigationMenuPersistence.update(actualTypeSiteNavigationMenu);
	}

	private void _validate(long groupId, String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new SiteNavigationMenuNameException();
		}

		int nameMaxLength = ModelHintsUtil.getMaxLength(
			SiteNavigationMenu.class.getName(), "name");

		if (name.length() > nameMaxLength) {
			throw new SiteNavigationMenuNameException(
				"Maximum length of name exceeded");
		}

		SiteNavigationMenu siteNavigationMenu =
			siteNavigationMenuPersistence.fetchByG_N(groupId, name);

		if (siteNavigationMenu != null) {
			throw new DuplicateSiteNavigationMenuException(name);
		}
	}

	@Reference
	private CustomSQL _customSQL;

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
	private SiteNavigationMenuItemLocalService
		_siteNavigationMenuItemLocalService;

	@Reference
	private UserLocalService _userLocalService;

}