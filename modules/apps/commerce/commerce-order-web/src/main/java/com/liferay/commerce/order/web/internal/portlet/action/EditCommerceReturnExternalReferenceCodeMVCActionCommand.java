/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.web.internal.portlet.action;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.object.exception.DuplicateObjectEntryExternalReferenceCodeException;
import com.liferay.object.exception.NoSuchObjectEntryException;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.Serializable;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stefano Motta
 */
@Component(
	property = {
		"javax.portlet.name=" + CommercePortletKeys.COMMERCE_RETURN,
		"mvc.command.name=/commerce_return/edit_commerce_return_external_reference_code"
	},
	service = MVCActionCommand.class
)
public class EditCommerceReturnExternalReferenceCodeMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			_updateCommerceReturnExternalReferenceCode(actionRequest);
		}
		catch (Exception exception) {
			if (exception instanceof
					DuplicateObjectEntryExternalReferenceCodeException ||
				exception instanceof NoSuchObjectEntryException ||
				exception instanceof PrincipalException) {

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				throw exception;
			}
		}
	}

	private void _updateCommerceReturnExternalReferenceCode(
			ActionRequest actionRequest)
		throws Exception {

		ObjectEntry objectEntry = _objectEntryService.getObjectEntry(
			ParamUtil.getLong(actionRequest, "primaryKey"));

		Map<String, Serializable> values = objectEntry.getValues();

		values.put(
			"externalReferenceCode",
			ParamUtil.getString(actionRequest, "externalReferenceCode"));

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.getObjectDefinition(
				objectEntry.getObjectDefinitionId());

		_objectEntryService.updateObjectEntry(
			objectEntry.getObjectEntryId(), values,
			ServiceContextFactory.getInstance(
				objectDefinition.getClassName(), actionRequest));
	}

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryService _objectEntryService;

}