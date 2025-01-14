/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.forecast;

import com.liferay.commerce.machine.learning.forecast.CommerceAccountCommerceMLForecast;
import com.liferay.commerce.machine.learning.forecast.CommerceAccountCommerceMLForecastManager;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastField;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastPeriod;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastScope;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastTarget;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(service = CommerceAccountCommerceMLForecastManager.class)
public class CommerceAccountCommerceMLForecastManagerImpl
	extends BaseCommerceMLForecastServiceImpl<CommerceAccountCommerceMLForecast>
	implements CommerceAccountCommerceMLForecastManager {

	@Override
	public CommerceAccountCommerceMLForecast
			addCommerceAccountCommerceMLForecast(
				CommerceAccountCommerceMLForecast
					commerceAccountCommerceMLForecast)
		throws PortalException {

		commerceAccountCommerceMLForecast.setForecastId(
			getHash(
				commerceAccountCommerceMLForecast.getCommerceAccountId(),
				commerceAccountCommerceMLForecast.getPeriod(),
				commerceAccountCommerceMLForecast.getScope(),
				commerceAccountCommerceMLForecast.getTarget(),
				commerceAccountCommerceMLForecast.getTimestamp()));

		return addCommerceMLForecast(commerceAccountCommerceMLForecast);
	}

	@Override
	public CommerceAccountCommerceMLForecast create() {
		return new CommerceAccountCommerceMLForecastImpl();
	}

	@Override
	public CommerceAccountCommerceMLForecast
			getCommerceAccountCommerceMLForecast(
				long companyId, long forecastId)
		throws PortalException {

		return getCommerceMLForecast(companyId, forecastId);
	}

	@Override
	public List<CommerceAccountCommerceMLForecast>
			getMonthlyRevenueCommerceAccountCommerceMLForecasts(
				long companyId, long[] commerceAccountIds, Date actualDate,
				int historyLength, int forecastLength)
		throws PortalException {

		return getMonthlyRevenueCommerceAccountCommerceMLForecasts(
			companyId, commerceAccountIds, actualDate, historyLength,
			forecastLength, 0,
			commerceAccountIds.length * (historyLength + forecastLength));
	}

	@Override
	public List<CommerceAccountCommerceMLForecast>
			getMonthlyRevenueCommerceAccountCommerceMLForecasts(
				long companyId, long[] commerceAccountIds, Date actualDate,
				int historyLength, int forecastLength, int start, int end)
		throws PortalException {

		return getSearchResults(
			getSearchSearchRequest(
				getIndexName(companyId),
				_getMonthlyRevenueQuery(
					commerceAccountIds, actualDate, historyLength,
					forecastLength),
				start, end - start, getDefaultSort(true)));
	}

	@Override
	public long getMonthlyRevenueCommerceAccountCommerceMLForecastsCount(
			long companyId, long[] commerceAccountIds, Date actualDate,
			int historyLength, int forecastLength)
		throws PortalException {

		return getCountResult(
			getCountSearchRequest(
				getIndexName(companyId),
				_getMonthlyRevenueQuery(
					commerceAccountIds, actualDate, historyLength,
					forecastLength)));
	}

	@Override
	protected Document toDocumentModel(
		CommerceAccountCommerceMLForecast commerceAccountCommerceMLForecast) {

		Document document = getDocument(commerceAccountCommerceMLForecast);

		document.addNumber(
			CommerceMLForecastField.COMMERCE_ACCOUNT_ID,
			commerceAccountCommerceMLForecast.getCommerceAccountId());

		return document;
	}

	@Override
	protected CommerceAccountCommerceMLForecast toForecastModel(
		Document document) {

		CommerceAccountCommerceMLForecast commerceAccountCommerceMLForecast =
			getCommerceMLForecastModel(
				new CommerceAccountCommerceMLForecastImpl(), document);

		commerceAccountCommerceMLForecast.setCommerceAccountId(
			GetterUtil.getLong(
				document.get(CommerceMLForecastField.COMMERCE_ACCOUNT_ID)));

		return commerceAccountCommerceMLForecast;
	}

	private Query _getMonthlyRevenueQuery(
			long[] commerceAccountIds, Date actualDate, int historyLength,
			int forecastLength)
		throws ParseException {

		CommerceMLForecastPeriod commerceMLForecastPeriod =
			CommerceMLForecastPeriod.MONTH;
		CommerceMLForecastTarget commerceMLForecastTarget =
			CommerceMLForecastTarget.REVENUE;

		BooleanQuery booleanQuery = getBooleanQuery(
			_commerceMLForecastScope.getLabel(),
			commerceMLForecastPeriod.getLabel(),
			commerceMLForecastTarget.getLabel(),
			getStartDate(actualDate, commerceMLForecastPeriod, historyLength),
			getEndDate(actualDate, commerceMLForecastPeriod, forecastLength));

		BooleanFilter preBooleanFilter = booleanQuery.getPreBooleanFilter();

		preBooleanFilter.add(
			new TermsFilter(CommerceMLForecastField.COMMERCE_ACCOUNT_ID) {
				{
					addValues(ArrayUtil.toStringArray(commerceAccountIds));
				}
			},
			BooleanClauseOccur.MUST);

		return booleanQuery;
	}

	private static final CommerceMLForecastScope _commerceMLForecastScope =
		CommerceMLForecastScope.COMMERCE_ACCOUNT;

}