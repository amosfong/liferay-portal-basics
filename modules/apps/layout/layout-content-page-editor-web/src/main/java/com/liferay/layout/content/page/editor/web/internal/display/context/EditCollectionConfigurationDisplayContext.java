/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.display.context;

import com.liferay.layout.content.page.editor.web.internal.util.layout.structure.LayoutStructureUtil;
import com.liferay.layout.util.structure.CollectionStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lourdes Fernández Besada
 */
public class EditCollectionConfigurationDisplayContext {

	public EditCollectionConfigurationDisplayContext(
		HttpServletRequest httpServletRequest, RenderResponse renderResponse) {

		_httpServletRequest = httpServletRequest;
		_renderResponse = renderResponse;

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public long getClassNameId() {
		if (Validator.isNotNull(_classNameId)) {
			return _classNameId;
		}

		_classNameId = ParamUtil.getLong(_httpServletRequest, "classNameId");

		return _classNameId;
	}

	public long getClassPK() {
		if (Validator.isNotNull(_classPK)) {
			return _classPK;
		}

		_classPK = ParamUtil.getLong(_httpServletRequest, "classPK");

		return _classPK;
	}

	public String getCollectionKey() {
		if (_collectionKey != null) {
			return _collectionKey;
		}

		_collectionKey = ParamUtil.getString(
			_httpServletRequest, "collectionKey");

		return _collectionKey;
	}

	public Map<String, Object> getData() throws Exception {
		return HashMapBuilder.<String, Object>put(
			"collection", _getCollectionJSONObject()
		).put(
			"collectionItemTypeLabel", _getCollectionItemTypeLabel()
		).put(
			"collectionKey", getCollectionKey()
		).put(
			"collectionLabel", _getCollectionLabel()
		).put(
			"configurationDefinition", _getConfigurationJSONObject()
		).put(
			"languageId", _themeDisplay.getLanguageId()
		).build();
	}

	public String getItemId() {
		if (_itemId != null) {
			return _itemId;
		}

		_itemId = ParamUtil.getString(_httpServletRequest, "itemId");

		return _itemId;
	}

	public long getPlid() {
		if (_plid != null) {
			return _plid;
		}

		_plid = ParamUtil.getLong(_httpServletRequest, "plid");

		return _plid;
	}

	public String getRedirect() {
		if (Validator.isNotNull(_redirect)) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(_httpServletRequest, "redirect");

		return _redirect;
	}

	public long getSegmentsExperienceId() {
		if (_segmentsExperienceId != null) {
			return _segmentsExperienceId;
		}

		_segmentsExperienceId = ParamUtil.getLong(
			_httpServletRequest, "segmentsExperienceId");

		return _segmentsExperienceId;
	}

	public String getType() {
		if (Validator.isNotNull(_type)) {
			return _type;
		}

		_type = ParamUtil.getString(_httpServletRequest, "type");

		return _type;
	}

	private String _getCollectionItemTypeLabel() {
		return null;
	}

	private JSONObject _getCollectionJSONObject() throws Exception {
		LayoutStructure layoutStructure =
			LayoutStructureUtil.getLayoutStructure(
				_themeDisplay.getScopeGroupId(), getPlid(),
				getSegmentsExperienceId());

		LayoutStructureItem layoutStructureItem =
			layoutStructure.getLayoutStructureItem(getItemId());

		if (!(layoutStructureItem instanceof
				CollectionStyledLayoutStructureItem)) {

			return JSONFactoryUtil.createJSONObject();
		}

		CollectionStyledLayoutStructureItem
			collectionStyledLayoutStructureItem =
				(CollectionStyledLayoutStructureItem)layoutStructureItem;

		JSONObject collectionJSONObject =
			collectionStyledLayoutStructureItem.getCollectionJSONObject();

		if (collectionJSONObject != null) {
			return collectionJSONObject;
		}

		return JSONFactoryUtil.createJSONObject();
	}

	private String _getCollectionLabel() {
		return null;
	}

	private JSONObject _getConfigurationJSONObject() {
		return JSONFactoryUtil.createJSONObject();
	}

	private Long _classNameId;
	private Long _classPK;
	private String _collectionItemTypeLabel;
	private String _collectionKey;
	private String _collectionLabel;
	private final HttpServletRequest _httpServletRequest;
	private String _itemId;
	private Long _plid;
	private String _redirect;
	private final RenderResponse _renderResponse;
	private Long _segmentsExperienceId;
	private final ThemeDisplay _themeDisplay;
	private String _type;

}