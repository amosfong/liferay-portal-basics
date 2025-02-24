/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.synonyms.web.internal.index.listener;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.tuning.synonyms.web.internal.BaseSynonymsWebTestCase;
import com.liferay.portal.search.tuning.synonyms.web.internal.synchronizer.IndexToFilterSynchronizer;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Wade Cao
 */
public class SynonymSetCompanyIndexListenerTest
	extends BaseSynonymsWebTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_synonymSetCompanyIndexListener = new SynonymSetCompanyIndexListener();

		ReflectionTestUtil.setFieldValue(
			_synonymSetCompanyIndexListener, "_indexToFilterSynchronizer",
			_indexToFilterSynchronizer);
		ReflectionTestUtil.setFieldValue(
			_synonymSetCompanyIndexListener, "_searchEngineInformation",
			_searchEngineInformation);
		ReflectionTestUtil.setFieldValue(
			_synonymSetCompanyIndexListener, "_synonymSetIndexReader",
			synonymSetIndexReader);
	}

	@Test
	public void testOnAfterCreateIndexNameExists() {
		setUpSynonymSetIndexReader(true);

		_synonymSetCompanyIndexListener.onAfterCreate("companyIndexName");

		Mockito.verify(
			_indexToFilterSynchronizer, Mockito.times(1)
		).copyToFilter(
			Mockito.any(), Mockito.anyString(), Mockito.anyBoolean()
		);
	}

	@Test
	public void testOnAfterCreateIndexNameNotExists() {
		_synonymSetCompanyIndexListener.onAfterCreate("companyIndexName");

		Mockito.verify(
			_indexToFilterSynchronizer, Mockito.never()
		).copyToFilter(
			Mockito.any(), Mockito.anyString(), Mockito.anyBoolean()
		);
	}

	private final IndexToFilterSynchronizer _indexToFilterSynchronizer =
		Mockito.mock(IndexToFilterSynchronizer.class);
	private final SearchEngineInformation _searchEngineInformation =
		Mockito.mock(SearchEngineInformation.class);
	private SynonymSetCompanyIndexListener _synonymSetCompanyIndexListener;

}