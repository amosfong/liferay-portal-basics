/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import com.liferay.portal.search.elasticsearch7.internal.connection.helper.IndexCreationHelper;
import com.liferay.portal.search.elasticsearch7.internal.connection.helper.LiferayIndexCreationHelper;
import com.liferay.portal.search.elasticsearch7.internal.settings.SettingsHelperImpl;
import com.liferay.portal.search.engine.SearchEngineInformation;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;

import org.mockito.Mockito;

/**
 * @author André de Oliveira
 */
public class IndexCreator {

	public Index createIndex(IndexName indexName) {
		IndicesClient indicesClient = _getIndicesClient();

		String name = indexName.getName();

		deleteIndex(indicesClient, name);

		CreateIndexRequest createIndexRequest = new CreateIndexRequest(name);

		IndexCreationHelper indexCreationHelper = _getIndexCreationHelper();

		indexCreationHelper.contribute(createIndexRequest);

		SettingsHelperImpl settingsHelperImpl = new SettingsHelperImpl(
			Settings.builder());

		settingsHelperImpl.put("index.number_of_replicas", "0");
		settingsHelperImpl.put("index.number_of_shards", "1");

		indexCreationHelper.contributeIndexSettings(settingsHelperImpl);

		createIndexRequest.settings(settingsHelperImpl.getBuilder());

		try {
			indicesClient.create(createIndexRequest, RequestOptions.DEFAULT);
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}

		indexCreationHelper.whenIndexCreated(name);

		return new Index(indexName);
	}

	public void deleteIndex(IndexName indexName) {
		deleteIndex(_getIndicesClient(), indexName.getName());
	}

	protected void deleteIndex(IndicesClient indicesClient, String name) {
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(name);

		deleteIndexRequest.indicesOptions(IndicesOptions.lenientExpandOpen());

		try {
			indicesClient.delete(deleteIndexRequest, RequestOptions.DEFAULT);
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	protected void setIndexCreationHelper(
		IndexCreationHelper indexCreationHelper) {

		_indexCreationHelper = indexCreationHelper;
	}

	protected void setLiferayMappingsAddedToIndex(
		boolean liferayMappingsAddedToIndex) {

		_liferayMappingsAddedToIndex = liferayMappingsAddedToIndex;
	}

	protected void setSearchEngineInformation(
		SearchEngineInformation searchEngineInformation) {

		_searchEngineInformation = searchEngineInformation;
	}

	private IndexCreationHelper _getIndexCreationHelper() {
		if (!_liferayMappingsAddedToIndex) {
			if (_indexCreationHelper != null) {
				return _indexCreationHelper;
			}

			return Mockito.mock(IndexCreationHelper.class);
		}

		LiferayIndexCreationHelper liferayIndexCreationHelper =
			new LiferayIndexCreationHelper(
				_elasticsearchClientResolver, _searchEngineInformation);

		if (_indexCreationHelper == null) {
			return liferayIndexCreationHelper;
		}

		return new IndexCreationHelper() {

			@Override
			public void contribute(CreateIndexRequest createIndexRequest) {
				_indexCreationHelper.contribute(createIndexRequest);

				liferayIndexCreationHelper.contribute(createIndexRequest);
			}

			@Override
			public void contributeIndexSettings(
				SettingsHelperImpl settingsHelperImpl) {

				_indexCreationHelper.contributeIndexSettings(
					settingsHelperImpl);

				liferayIndexCreationHelper.contributeIndexSettings(
					settingsHelperImpl);
			}

			@Override
			public void whenIndexCreated(String indexName) {
				_indexCreationHelper.whenIndexCreated(indexName);

				liferayIndexCreationHelper.whenIndexCreated(indexName);
			}

		};
	}

	private final IndicesClient _getIndicesClient() {
		RestHighLevelClient restHighLevelClient =
			_elasticsearchClientResolver.getRestHighLevelClient();

		return restHighLevelClient.indices();
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;
	private IndexCreationHelper _indexCreationHelper;
	private boolean _liferayMappingsAddedToIndex;
	private SearchEngineInformation _searchEngineInformation;

}