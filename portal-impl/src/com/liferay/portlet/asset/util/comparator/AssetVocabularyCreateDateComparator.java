/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.util.comparator;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Eudaldo Alonso
 */
public class AssetVocabularyCreateDateComparator
	extends OrderByComparator<AssetVocabulary> {

	public static final String ORDER_BY_ASC = "AssetVocabulary.createDate ASC";

	public static final String ORDER_BY_DESC =
		"AssetVocabulary.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public static AssetVocabularyCreateDateComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		AssetVocabulary assetVocabulary1, AssetVocabulary assetVocabulary2) {

		int value = DateUtil.compareTo(
			assetVocabulary1.getCreateDate(), assetVocabulary2.getCreateDate());

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private AssetVocabularyCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final AssetVocabularyCreateDateComparator
		_INSTANCE_ASCENDING = new AssetVocabularyCreateDateComparator(true);

	private static final AssetVocabularyCreateDateComparator
		_INSTANCE_DESCENDING = new AssetVocabularyCreateDateComparator(false);

	private final boolean _ascending;

}