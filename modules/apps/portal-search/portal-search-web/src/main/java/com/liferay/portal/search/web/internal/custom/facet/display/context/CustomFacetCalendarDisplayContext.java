/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.custom.facet.display.context;

import java.io.Serializable;

/**
 * @author Petteri Karttunen
 */
public class CustomFacetCalendarDisplayContext implements Serializable {

	public int getFromDayValue() {
		return _fromDayValue;
	}

	public int getFromFirstDayOfWeek() {
		return _fromFirstDayOfWeek;
	}

	public int getFromMonthValue() {
		return _fromMonthValue;
	}

	public int getFromYearValue() {
		return _fromYearValue;
	}

	public String getRangeURL() {
		return _rangeURL;
	}

	public int getToDayValue() {
		return _toDayValue;
	}

	public int getToFirstDayOfWeek() {
		return _toFirstDayOfWeek;
	}

	public int getToMonthValue() {
		return _toMonthValue;
	}

	public int getToYearValue() {
		return _toYearValue;
	}

	public boolean isRangeBackwards() {
		return _rangeBackwards;
	}

	public boolean isSelected() {
		return _selected;
	}

	public void setFromDayValue(int fromDayValue) {
		_fromDayValue = fromDayValue;
	}

	public void setFromFirstDayOfWeek(int fromFirstDayOfWeek) {
		_fromFirstDayOfWeek = fromFirstDayOfWeek;
	}

	public void setFromMonthValue(int fromMonthValue) {
		_fromMonthValue = fromMonthValue;
	}

	public void setFromYearValue(int fromYearValue) {
		_fromYearValue = fromYearValue;
	}

	public void setRangeBackwards(boolean rangeBackwards) {
		_rangeBackwards = rangeBackwards;
	}

	public void setRangeURL(String rangeURL) {
		_rangeURL = rangeURL;
	}

	public void setSelected(boolean selected) {
		_selected = selected;
	}

	public void setToDayValue(int toDayValue) {
		_toDayValue = toDayValue;
	}

	public void setToFirstDayOfWeek(int toFirstDayOfWeek) {
		_toFirstDayOfWeek = toFirstDayOfWeek;
	}

	public void setToMonthValue(int toMonthValue) {
		_toMonthValue = toMonthValue;
	}

	public void setToYearValue(int toYearValue) {
		_toYearValue = toYearValue;
	}

	private int _fromDayValue;
	private int _fromFirstDayOfWeek;
	private int _fromMonthValue;
	private int _fromYearValue;
	private boolean _rangeBackwards;
	private String _rangeURL;
	private boolean _selected;
	private int _toDayValue;
	private int _toFirstDayOfWeek;
	private int _toMonthValue;
	private int _toYearValue;

}