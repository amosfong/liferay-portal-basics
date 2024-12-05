/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.index.creation.instance.lifecycle;

import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.search.capabilities.SearchCapabilities;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.spi.index.creation.instance.lifecycle.BaseIndexPortalInstanceLifecycleListener;
import com.liferay.portal.search.tuning.rankings.index.RankingIndexReader;
import com.liferay.portal.search.tuning.rankings.index.name.RankingIndexName;
import com.liferay.portal.search.tuning.rankings.index.name.RankingIndexNameBuilder;
import com.liferay.portal.search.tuning.rankings.web.internal.index.RankingIndexCreatorUtil;
import com.liferay.portal.search.tuning.rankings.web.internal.index.importer.SingleIndexToMultipleIndexImporter;

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration",
	service = PortalInstanceLifecycleListener.class
)
public class RankingIndexPortalInstanceLifecycleListener
	extends BaseIndexPortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		if (!_searchCapabilities.isResultRankingsSupported() ||
			(company.getCompanyId() == CompanyConstants.SYSTEM)) {

			return;
		}

		RankingIndexName rankingIndexName =
			_rankingIndexNameBuilder.getRankingIndexName(
				company.getCompanyId());

		if (_rankingIndexReader.isExists(rankingIndexName)) {
			return;
		}

		RankingIndexCreatorUtil.create(_searchEngineAdapter, rankingIndexName);

		if (_singleIndexToMultipleIndexImporter.needImport()) {
			_singleIndexToMultipleIndexImporter.importRankings(
				company.getCompanyId());
		}
	}

	@Override
	public void portalInstanceUnregistered(Company company) throws Exception {
		if (!_searchCapabilities.isResultRankingsSupported() ||
			(company.getCompanyId() == CompanyConstants.SYSTEM)) {

			return;
		}

		RankingIndexCreatorUtil.deleteIfExists(
			_searchEngineAdapter,
			_rankingIndexNameBuilder.getRankingIndexName(
				company.getCompanyId()));
	}

	@Activate
	@Override
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		super.activate(bundleContext, properties);

		_singleIndexToMultipleIndexImporter =
			new SingleIndexToMultipleIndexImporter(
				_indexNameBuilder, _queries, _rankingIndexReader,
				_searchEngineAdapter);
	}

	@Reference
	private IndexNameBuilder _indexNameBuilder;

	@Reference
	private Queries _queries;

	@Reference
	private RankingIndexNameBuilder _rankingIndexNameBuilder;

	@Reference
	private RankingIndexReader _rankingIndexReader;

	@Reference
	private SearchCapabilities _searchCapabilities;

	@Reference
	private SearchEngineAdapter _searchEngineAdapter;

	private SingleIndexToMultipleIndexImporter
		_singleIndexToMultipleIndexImporter;

}