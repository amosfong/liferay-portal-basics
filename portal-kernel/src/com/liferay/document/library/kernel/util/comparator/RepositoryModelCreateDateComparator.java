/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.util.comparator;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.repository.model.RepositoryEntry;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;

/**
 * @author Alexander Chow
 */
public class RepositoryModelCreateDateComparator<T>
	extends OrderByComparator<T> {

	public static final String ORDER_BY_ASC = "createDate ASC";

	public static final String ORDER_BY_DESC = "createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public static final String ORDER_BY_MODEL_ASC =
		"modelFolder DESC, createDate ASC";

	public static final String ORDER_BY_MODEL_DESC =
		"modelFolder DESC, createDate DESC";

	public RepositoryModelCreateDateComparator() {
		this(false);
	}

	public RepositoryModelCreateDateComparator(boolean ascending) {
		_ascending = ascending;

		_orderByModel = false;
	}

	public RepositoryModelCreateDateComparator(
		boolean ascending, boolean orderByModel) {

		_ascending = ascending;
		_orderByModel = orderByModel;
	}

	@Override
	public int compare(T t1, T t2) {
		int value = 0;

		Date createDate1 = getCreateDate(t1);
		Date createDate2 = getCreateDate(t2);

		if (_orderByModel) {
			if ((t1 instanceof DLFolder || t1 instanceof Folder) &&
				(t2 instanceof DLFolder || t2 instanceof Folder)) {

				value = DateUtil.compareTo(createDate1, createDate2);
			}
			else if (t1 instanceof DLFolder || t1 instanceof Folder) {
				value = -1;
			}
			else if (t2 instanceof DLFolder || t2 instanceof Folder) {
				value = 1;
			}
			else {
				value = DateUtil.compareTo(createDate1, createDate2);
			}
		}
		else {
			value = DateUtil.compareTo(createDate1, createDate2);
		}

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_orderByModel) {
			if (_ascending) {
				return ORDER_BY_MODEL_ASC;
			}

			return ORDER_BY_MODEL_DESC;
		}

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

	protected Date getCreateDate(Object object) {
		if (object instanceof DLFileEntry) {
			DLFileEntry dlFileEntry = (DLFileEntry)object;

			return dlFileEntry.getCreateDate();
		}
		else if (object instanceof DLFileShortcut) {
			DLFileShortcut dlFileShortcut = (DLFileShortcut)object;

			return dlFileShortcut.getCreateDate();
		}
		else if (object instanceof DLFolder) {
			DLFolder dlFolder = (DLFolder)object;

			return dlFolder.getCreateDate();
		}

		RepositoryEntry repositoryEntry = (RepositoryEntry)object;

		return repositoryEntry.getCreateDate();
	}

	private final boolean _ascending;
	private final boolean _orderByModel;

}