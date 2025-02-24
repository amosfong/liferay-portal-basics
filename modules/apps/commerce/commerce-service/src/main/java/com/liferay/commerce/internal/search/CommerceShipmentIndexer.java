/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.search;

import com.liferay.commerce.address.CommerceAddressFormatter;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceAddressLocalService;
import com.liferay.commerce.service.CommerceShipmentItemLocalService;
import com.liferay.commerce.service.CommerceShipmentLocalService;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.MissingFilter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = Indexer.class)
public class CommerceShipmentIndexer extends BaseIndexer<CommerceShipment> {

	public static final String CLASS_NAME = CommerceShipment.class.getName();

	public CommerceShipmentIndexer() {
		setFilterSearch(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		long[] commerceAccountIds = GetterUtil.getLongValues(
			searchContext.getAttribute("commerceAccountIds"), null);

		if (commerceAccountIds != null) {
			BooleanFilter commerceAccountIdBooleanFilter = new BooleanFilter();

			for (long commerceAccountId : commerceAccountIds) {
				commerceAccountIdBooleanFilter.add(
					new TermFilter(
						"commerceAccountId", String.valueOf(commerceAccountId)),
					BooleanClauseOccur.SHOULD);
			}

			commerceAccountIdBooleanFilter.add(
				new MissingFilter("commerceAccountId"),
				BooleanClauseOccur.SHOULD);

			contextBooleanFilter.add(
				commerceAccountIdBooleanFilter, BooleanClauseOccur.MUST);
		}

		int[] shipmentStatuses = GetterUtil.getIntegerValues(
			searchContext.getAttribute("shipmentStatuses"), null);

		if (shipmentStatuses != null) {
			BooleanFilter shipmentStatusesBooleanFilter = new BooleanFilter();

			for (long shipmentStatus : shipmentStatuses) {
				shipmentStatusesBooleanFilter.add(
					new TermFilter(
						Field.STATUS, String.valueOf(shipmentStatus)),
					BooleanClauseOccur.SHOULD);
			}

			shipmentStatusesBooleanFilter.add(
				new MissingFilter(Field.STATUS), BooleanClauseOccur.SHOULD);

			if (GetterUtil.getBoolean(
					searchContext.getAttribute("negateShipmentStatuses"))) {

				contextBooleanFilter.add(
					shipmentStatusesBooleanFilter, BooleanClauseOccur.MUST_NOT);
			}
			else {
				contextBooleanFilter.add(
					shipmentStatusesBooleanFilter, BooleanClauseOccur.MUST);
			}
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		super.postProcessSearchQuery(
			searchQuery, fullQueryBooleanFilter, searchContext);

		addSearchTerm(searchQuery, searchContext, Field.ENTRY_CLASS_PK, false);
		addSearchTerm(searchQuery, searchContext, "commerceAccountName", false);
		addSearchTerm(searchQuery, searchContext, "commerceChannelName", false);
		addSearchTerm(searchQuery, searchContext, "oneLineAddress", false);
		addSearchTerm(searchQuery, searchContext, "trackingNumber", false);
	}

	@Override
	protected void doDelete(CommerceShipment commerceShipment)
		throws Exception {

		deleteDocument(
			commerceShipment.getCompanyId(),
			commerceShipment.getCommerceShipmentId());
	}

	@Override
	protected Document doGetDocument(CommerceShipment commerceShipment)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Indexing commerce shipment " + commerceShipment);
		}

		Document document = getBaseModelDocument(CLASS_NAME, commerceShipment);

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.getCommerceChannelByOrderGroupId(
				commerceShipment.getGroupId());

		document.addNumberSortable(
			Field.ENTRY_CLASS_PK, commerceShipment.getCommerceShipmentId());
		document.addKeyword(Field.STATUS, commerceShipment.getStatus());
		document.addKeyword(
			"commerceAccountId", commerceShipment.getCommerceAccountId());
		document.addKeyword(
			"commerceAccountName", commerceShipment.getAccountEntryName());
		document.addKeyword(
			"commerceChannelId", commerceChannel.getCommerceChannelId());
		document.addKeyword("commerceChannelName", commerceChannel.getName());
		document.addNumber(
			"itemsCount",
			_commerceShipmentItemLocalService.getCommerceShipmentItemsCount(
				commerceShipment.getCommerceShipmentId()));

		CommerceAddress commerceAddress =
			_commerceAddressLocalService.fetchCommerceAddress(
				commerceShipment.getCommerceAddressId());

		if (commerceAddress != null) {
			document.addKeyword(
				"oneLineAddress",
				_commerceAddressFormatter.getOneLineAddress(commerceAddress));
		}

		document.addKeyword(
			"trackingNumber", commerceShipment.getTrackingNumber());

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Commerce shipment " + commerceShipment +
					" indexed successfully");
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
			Document document, Locale locale, String snippet,
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		return null;
	}

	@Override
	protected void doReindex(CommerceShipment commerceShipment)
		throws Exception {

		_indexWriterHelper.updateDocument(
			commerceShipment.getCompanyId(), getDocument(commerceShipment));
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		doReindex(_commerceShipmentLocalService.getCommerceShipment(classPK));
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		_reindexCommerceShipments(companyId);
	}

	@Override
	protected boolean isUseSearchResultPermissionFilter(
		SearchContext searchContext) {

		Boolean useSearchResultPermissionFilter =
			(Boolean)searchContext.getAttribute(
				"useSearchResultPermissionFilter");

		if (useSearchResultPermissionFilter != null) {
			return useSearchResultPermissionFilter;
		}

		return super.isUseSearchResultPermissionFilter(searchContext);
	}

	private void _reindexCommerceShipments(long companyId) throws Exception {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_commerceShipmentLocalService.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			(CommerceShipment commerceShipment) -> {
				try {
					indexableActionableDynamicQuery.addDocuments(
						getDocument(commerceShipment));
				}
				catch (PortalException portalException) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to index commerce shipment " +
								commerceShipment,
							portalException);
					}
				}
			});

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceShipmentIndexer.class);

	@Reference
	private CommerceAddressFormatter _commerceAddressFormatter;

	@Reference
	private CommerceAddressLocalService _commerceAddressLocalService;

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceShipmentItemLocalService _commerceShipmentItemLocalService;

	@Reference
	private CommerceShipmentLocalService _commerceShipmentLocalService;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

}