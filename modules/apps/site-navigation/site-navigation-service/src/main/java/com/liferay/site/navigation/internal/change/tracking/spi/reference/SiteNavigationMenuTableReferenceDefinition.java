/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.internal.change.tracking.spi.reference;

import com.liferay.change.tracking.spi.reference.TableReferenceDefinition;
import com.liferay.change.tracking.spi.reference.builder.ChildTableReferenceInfoBuilder;
import com.liferay.change.tracking.spi.reference.builder.ParentTableReferenceInfoBuilder;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuTable;
import com.liferay.site.navigation.service.persistence.SiteNavigationMenuPersistence;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Samuel Trong Tran
 */
@Component(service = TableReferenceDefinition.class)
public class SiteNavigationMenuTableReferenceDefinition
	implements TableReferenceDefinition<SiteNavigationMenuTable> {

	@Override
	public void defineChildTableReferences(
		ChildTableReferenceInfoBuilder<SiteNavigationMenuTable>
			childTableReferenceInfoBuilder) {

		childTableReferenceInfoBuilder.resourcePermissionReference(
			SiteNavigationMenuTable.INSTANCE.siteNavigationMenuId,
			SiteNavigationMenu.class);
	}

	@Override
	public void defineParentTableReferences(
		ParentTableReferenceInfoBuilder<SiteNavigationMenuTable>
			parentTableReferenceInfoBuilder) {

		parentTableReferenceInfoBuilder.groupedModel(
			SiteNavigationMenuTable.INSTANCE);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _siteNavigationMenuPersistence;
	}

	@Override
	public SiteNavigationMenuTable getTable() {
		return SiteNavigationMenuTable.INSTANCE;
	}

	@Reference
	private SiteNavigationMenuPersistence _siteNavigationMenuPersistence;

}