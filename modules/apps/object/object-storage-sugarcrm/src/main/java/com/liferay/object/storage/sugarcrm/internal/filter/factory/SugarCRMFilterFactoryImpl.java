/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.storage.sugarcrm.internal.filter.factory;

import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.rest.filter.factory.BaseFilterFactory;
import com.liferay.object.rest.filter.factory.FilterFactory;
import com.liferay.object.storage.sugarcrm.internal.odata.filter.expression.SugarCRMQueryExpressionVisitorImpl;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.filter.expression.ExpressionVisitor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Maurice Sepe
 */
@Component(
	property = "filter.factory.key=" + ObjectDefinitionConstants.STORAGE_TYPE_SUGARCRM,
	service = FilterFactory.class
)
public class SugarCRMFilterFactoryImpl
	extends BaseFilterFactory<String> implements FilterFactory<String> {

	@Override
	public ExpressionVisitor<?> getExpressionVisitor(
		EntityModel entityModel, ObjectDefinition objectDefinition) {

		return new SugarCRMQueryExpressionVisitorImpl(
			objectDefinition.getObjectDefinitionId(), objectFieldLocalService);
	}

}