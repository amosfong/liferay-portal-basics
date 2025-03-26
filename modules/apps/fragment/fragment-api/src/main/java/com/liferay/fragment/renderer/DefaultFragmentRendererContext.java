/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.renderer;

import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.util.Locale;
import java.util.Objects;

/**
 * @author Jorge Ferrer
 */
public class DefaultFragmentRendererContext implements FragmentRendererContext {

	public DefaultFragmentRendererContext(FragmentEntryLink fragmentEntryLink) {
		_fragmentEntryLink = fragmentEntryLink;

		_fragmentEntryElementId = "fragment-" + PortalUUIDUtil.generate();
	}

	@Override
	public String getFragmentElementId() {
		return _fragmentEntryElementId;
	}

	@Override
	public FragmentEntryLink getFragmentEntryLink() {
		return _fragmentEntryLink;
	}

	@Override
	public Locale getLocale() {
		return _locale;
	}

	@Override
	public String getMode() {
		return _mode;
	}

	@Override
	public long getPreviewClassNameId() {
		return _previewClassNameId;
	}

	@Override
	public long getPreviewClassPK() {
		return _previewClassPK;
	}

	@Override
	public int getPreviewType() {
		return _previewType;
	}

	@Override
	public long[] getSegmentsEntryIds() {
		return _segmentsSegmentsEntryIds;
	}

	@Override
	public boolean isEditMode() {
		if (Objects.equals(getMode(), FragmentEntryLinkConstants.EDIT)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isIndexMode() {
		if (Objects.equals(getMode(), FragmentEntryLinkConstants.INDEX)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isPreviewMode() {
		if (Objects.equals(getMode(), FragmentEntryLinkConstants.PREVIEW)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isUseCachedContent() {
		return _useCachedContent;
	}

	@Override
	public boolean isViewMode() {
		if (Objects.equals(getMode(), FragmentEntryLinkConstants.VIEW)) {
			return true;
		}

		return false;
	}

	public void setLocale(Locale locale) {
		_locale = locale;
	}

	public void setMode(String mode) {
		_mode = mode;
	}

	public void setPreviewClassNameId(long previewClassNameId) {
		_previewClassNameId = previewClassNameId;
	}

	public void setPreviewClassPK(long previewClassPK) {
		_previewClassPK = previewClassPK;
	}

	public void setPreviewType(int previewType) {
		_previewType = previewType;
	}

	public void setSegmentsEntryIds(long[] segmentsSegmentsEntryIds) {
		_segmentsSegmentsEntryIds = segmentsSegmentsEntryIds;
	}

	public void setUseCachedContent(boolean useCachedContent) {
		_useCachedContent = useCachedContent;
	}

	private final String _fragmentEntryElementId;
	private final FragmentEntryLink _fragmentEntryLink;
	private Locale _locale = LocaleUtil.getMostRelevantLocale();
	private String _mode = FragmentEntryLinkConstants.VIEW;
	private long _previewClassNameId;
	private long _previewClassPK;
	private int _previewType;
	private long[] _segmentsSegmentsEntryIds = new long[0];
	private boolean _useCachedContent = true;

}