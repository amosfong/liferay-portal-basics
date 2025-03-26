/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.processor;

import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.util.Locale;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Pavel Savinov
 */
public class DefaultFragmentEntryProcessorContext
	implements FragmentEntryProcessorContext {

	public DefaultFragmentEntryProcessorContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, String mode, Locale locale) {

		_httpServletRequest = httpServletRequest;
		_httpServletResponse = httpServletResponse;
		_mode = mode;
		_locale = locale;

		_fragmentElementId = "fragment-" + PortalUUIDUtil.generate();
	}

	@Override
	public String getFragmentElementId() {
		return _fragmentElementId;
	}

	@Override
	public HttpServletRequest getHttpServletRequest() {
		return _httpServletRequest;
	}

	@Override
	public HttpServletResponse getHttpServletResponse() {
		return _httpServletResponse;
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
		return _segmentsEntryIds;
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
	public boolean isViewMode() {
		if (Objects.equals(getMode(), FragmentEntryLinkConstants.VIEW)) {
			return true;
		}

		return false;
	}

	public void setFragmentElementId(String fragmentElementId) {
		_fragmentElementId = fragmentElementId;
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

	public void setSegmentsEntryIds(long[] segmentsEntryIds) {
		_segmentsEntryIds = segmentsEntryIds;
	}

	private String _fragmentElementId;
	private final HttpServletRequest _httpServletRequest;
	private final HttpServletResponse _httpServletResponse;
	private final Locale _locale;
	private final String _mode;
	private long _previewClassNameId;
	private long _previewClassPK;
	private int _previewType = AssetRendererFactory.TYPE_LATEST_APPROVED;
	private long[] _segmentsEntryIds = new long[0];

}