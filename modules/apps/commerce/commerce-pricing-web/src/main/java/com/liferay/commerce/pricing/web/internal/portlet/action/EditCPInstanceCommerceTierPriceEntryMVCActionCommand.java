/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.web.internal.portlet.action;

import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.price.list.exception.CommerceTierPriceEntryMinQuantityException;
import com.liferay.commerce.price.list.exception.DuplicateCommerceTierPriceEntryException;
import com.liferay.commerce.price.list.exception.DuplicateCommerceTierPriceEntryExternalReferenceCodeException;
import com.liferay.commerce.price.list.exception.NoSuchTierPriceEntryException;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryService;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryService;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.util.CommerceOrderItemQuantityFormatter;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_DEFINITIONS,
		"mvc.command.name=/cp_definitions/edit_cp_instance_commerce_tier_price_entry"
	},
	service = MVCActionCommand.class
)
public class EditCPInstanceCommerceTierPriceEntryMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteCommerceTierPriceEntries(
			long commerceTierPriceEntryId, ActionRequest actionRequest)
		throws Exception {

		long[] deleteCommerceTierPriceEntryIds = null;

		if (commerceTierPriceEntryId > 0) {
			deleteCommerceTierPriceEntryIds = new long[] {
				commerceTierPriceEntryId
			};
		}
		else {
			deleteCommerceTierPriceEntryIds = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "deleteCommerceTierPriceEntryIds"),
				0L);
		}

		for (long deleteCommerceTierPriceEntryId :
				deleteCommerceTierPriceEntryIds) {

			_commerceTierPriceEntryService.deleteCommerceTierPriceEntry(
				deleteCommerceTierPriceEntryId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long commerceTierPriceEntryId = ParamUtil.getLong(
			actionRequest, "commerceTierPriceEntryId");

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateCommerceTierPriceEntry(
					commerceTierPriceEntryId, actionRequest);

				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteCommerceTierPriceEntries(
					commerceTierPriceEntryId, actionRequest);
			}
		}
		catch (Exception exception) {
			if (exception instanceof
					DuplicateCommerceTierPriceEntryExternalReferenceCodeException ||
				exception instanceof NoSuchTierPriceEntryException ||
				exception instanceof PrincipalException) {

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (exception instanceof
						CommerceTierPriceEntryMinQuantityException ||
					 exception instanceof
						 DuplicateCommerceTierPriceEntryException) {

				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);

				SessionErrors.add(actionRequest, exception.getClass());

				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else {
				throw exception;
			}
		}
	}

	protected CommerceTierPriceEntry updateCommerceTierPriceEntry(
			long commerceTierPriceEntryId, ActionRequest actionRequest)
		throws Exception {

		long commercePriceEntryId = ParamUtil.getLong(
			actionRequest, "commercePriceEntryId");

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryService.getCommercePriceEntry(
				commercePriceEntryId);

		BigDecimal price = _commercePriceFormatter.parse(
			actionRequest, "price");
		BigDecimal minQuantity = _commerceOrderItemQuantityFormatter.parse(
			actionRequest, "minQuantity");
		boolean overrideDiscount = ParamUtil.getBoolean(
			actionRequest, "overrideDiscount");
		BigDecimal discountLevel1 = _commercePriceFormatter.parse(
			actionRequest, "discountLevel1");
		BigDecimal discountLevel2 = _commercePriceFormatter.parse(
			actionRequest, "discountLevel2");
		BigDecimal discountLevel3 = _commercePriceFormatter.parse(
			actionRequest, "discountLevel3");
		BigDecimal discountLevel4 = _commercePriceFormatter.parse(
			actionRequest, "discountLevel4");

		Date date = new Date();

		Calendar calendar = CalendarFactoryUtil.getCalendar(date.getTime());

		int displayDateMonth = ParamUtil.getInteger(
			actionRequest, "displayDateMonth", calendar.get(Calendar.MONTH));
		int displayDateDay = ParamUtil.getInteger(
			actionRequest, "displayDateDay",
			calendar.get(Calendar.DAY_OF_MONTH));
		int displayDateYear = ParamUtil.getInteger(
			actionRequest, "displayDateYear", calendar.get(Calendar.YEAR));
		int displayDateHour = ParamUtil.getInteger(
			actionRequest, "displayDateHour", calendar.get(Calendar.HOUR));
		int displayDateMinute = ParamUtil.getInteger(
			actionRequest, "displayDateMinute", calendar.get(Calendar.MINUTE));
		int displayDateAmPm = ParamUtil.getInteger(
			actionRequest, "displayDateAmPm", calendar.get(Calendar.AM_PM));

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		int expirationDateMonth = ParamUtil.getInteger(
			actionRequest, "expirationDateMonth");
		int expirationDateDay = ParamUtil.getInteger(
			actionRequest, "expirationDateDay");
		int expirationDateYear = ParamUtil.getInteger(
			actionRequest, "expirationDateYear");
		int expirationDateHour = ParamUtil.getInteger(
			actionRequest, "expirationDateHour");
		int expirationDateMinute = ParamUtil.getInteger(
			actionRequest, "expirationDateMinute");
		int expirationDateAmPm = ParamUtil.getInteger(
			actionRequest, "expirationDateAmPm");

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		boolean neverExpire = ParamUtil.getBoolean(
			actionRequest, "neverExpire", true);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceTierPriceEntry.class.getName(), actionRequest);

		CommerceTierPriceEntry commerceTierPriceEntry = null;

		if (commerceTierPriceEntryId <= 0) {
			commerceTierPriceEntry =
				_commerceTierPriceEntryService.addCommerceTierPriceEntry(
					null, commercePriceEntryId, price, minQuantity,
					commercePriceEntry.isBulkPricing(), !overrideDiscount,
					discountLevel1, discountLevel2, discountLevel3,
					discountLevel4, displayDateMonth, displayDateDay,
					displayDateYear, displayDateHour, displayDateMinute,
					expirationDateMonth, expirationDateDay, expirationDateYear,
					expirationDateHour, expirationDateMinute, neverExpire,
					serviceContext);
		}
		else {
			commerceTierPriceEntry =
				_commerceTierPriceEntryService.updateCommerceTierPriceEntry(
					commerceTierPriceEntryId, price, minQuantity,
					commercePriceEntry.isBulkPricing(), !overrideDiscount,
					discountLevel1, discountLevel2, discountLevel3,
					discountLevel4, displayDateMonth, displayDateDay,
					displayDateYear, displayDateHour, displayDateMinute,
					expirationDateMonth, expirationDateDay, expirationDateYear,
					expirationDateHour, expirationDateMinute, neverExpire,
					serviceContext);
		}

		return commerceTierPriceEntry;
	}

	@Reference
	private CommerceOrderItemQuantityFormatter
		_commerceOrderItemQuantityFormatter;

	@Reference
	private CommercePriceEntryService _commercePriceEntryService;

	@Reference
	private CommercePriceFormatter _commercePriceFormatter;

	@Reference
	private CommerceTierPriceEntryService _commerceTierPriceEntryService;

}