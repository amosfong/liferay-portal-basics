/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.subscription.web.internal.frontend.data.set.view.table;

import com.liferay.commerce.subscription.web.internal.constants.CommerceSubscriptionFDSNames;
import com.liferay.frontend.data.set.view.FDSView;
import com.liferay.frontend.data.set.view.table.BaseTableFDSView;
import com.liferay.frontend.data.set.view.table.FDSTableSchema;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaBuilder;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaBuilderFactory;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "frontend.data.set.name=" + CommerceSubscriptionFDSNames.SUBSCRIPTION_SHIPMENTS,
	service = FDSView.class
)
public class CommerceSubscriptionShipmentsTableFDSView
	extends BaseTableFDSView {

	@Override
	public FDSTableSchema getFDSTableSchema(Locale locale) {
		FDSTableSchemaBuilder fdsTableSchemaBuilder =
			_fdsTableSchemaBuilderFactory.create();

		return fdsTableSchemaBuilder.add(
			"createDate", "create-date"
		).add(
			"shipmentId", "shipment-id",
			fdsTableSchemaField -> fdsTableSchemaField.setContentRenderer(
				"link")
		).add(
			"status", "status",
			fdsTableSchemaField -> fdsTableSchemaField.setContentRenderer(
				"label")
		).add(
			"orderId", "order-id",
			fdsTableSchemaField -> fdsTableSchemaField.setContentRenderer(
				"link")
		).add(
			"receiver", "sent-to"
		).add(
			"tracking", "tracking",
			fdsTableSchemaField -> fdsTableSchemaField.setContentRenderer(
				"link")
		).build();
	}

	@Reference
	private FDSTableSchemaBuilderFactory _fdsTableSchemaBuilderFactory;

}