/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.util;

import com.liferay.portal.util.PropsValues;

import java.util.Objects;

/**
 * @author Eudaldo Alonso
 */
public class CollectionPaginationUtil {

	public static final String PAGINATION_TYPE_NONE = "none";

	public static final String PAGINATION_TYPE_NUMERIC = "numeric";

	public static final String PAGINATION_TYPE_REGULAR = "regular";

	public static final String PAGINATION_TYPE_SIMPLE = "simple";

	public static int getTotalNumberOfItems(
		int count, boolean displayAllPages, boolean displayAllItems,
		int numberOfItems, int numberOfItemsPerPage, int numberOfPages,
		String paginationType) {

		if (!isPaginationEnabled(paginationType)) {
			if (displayAllItems) {
				return count;
			}

			return Math.min(count, numberOfItems);
		}

		if (displayAllPages || (numberOfPages <= 0)) {
			return count;
		}

		if ((numberOfItemsPerPage <= 0) ||
			(numberOfItemsPerPage >
				PropsValues.SEARCH_CONTAINER_PAGE_MAX_DELTA)) {

			numberOfItemsPerPage = PropsValues.SEARCH_CONTAINER_PAGE_MAX_DELTA;
		}

		return Math.min(count, numberOfPages * numberOfItemsPerPage);
	}

	public static boolean isPaginationEnabled(String paginationType) {
		if (Objects.equals(
				paginationType,
				CollectionPaginationUtil.PAGINATION_TYPE_NUMERIC) ||
			Objects.equals(
				paginationType,
				CollectionPaginationUtil.PAGINATION_TYPE_REGULAR) ||
			Objects.equals(
				paginationType,
				CollectionPaginationUtil.PAGINATION_TYPE_SIMPLE)) {

			return true;
		}

		return false;
	}

}