/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.entry.processor.helper;

import aQute.bnd.annotation.ProviderType;

import com.liferay.fragment.processor.FragmentEntryProcessorContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Locale;
import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
@ProviderType
public interface FragmentEntryProcessorHelper {

	public String getEditableValue(JSONObject jsonObject, Locale locale);

	public long getFileEntryId(String className, long classPK);

	public boolean hasViewPermission(
		JSONObject editableValueJSONObject,
		FragmentEntryProcessorContext fragmentEntryProcessorContext);

	public boolean isMapped(JSONObject jsonObject);

	public boolean isMappedCollection(JSONObject jsonObject);

	public boolean isMappedDisplayPage(JSONObject jsonObject);

}