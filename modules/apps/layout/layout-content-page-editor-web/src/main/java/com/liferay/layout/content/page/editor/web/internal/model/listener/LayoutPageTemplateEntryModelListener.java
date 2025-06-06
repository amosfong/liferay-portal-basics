/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.model.listener;

import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.entry.processor.constants.FragmentEntryProcessorConstants;
import com.liferay.fragment.listener.FragmentEntryLinkListener;
import com.liferay.fragment.listener.FragmentEntryLinkListenerRegistry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLinkService;
import com.liferay.layout.content.page.editor.web.internal.manager.FormItemManager;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.util.UpdateLayoutStatusThreadLocal;
import com.liferay.layout.util.structure.FormStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(service = ModelListener.class)
public class LayoutPageTemplateEntryModelListener
	extends BaseModelListener<LayoutPageTemplateEntry> {

	@Override
	public void onAfterUpdate(
			LayoutPageTemplateEntry originalLayoutPageTemplateEntry,
			LayoutPageTemplateEntry layoutPageTemplateEntry)
		throws ModelListenerException {

		if (_isContentTypeChanged(
				layoutPageTemplateEntry, originalLayoutPageTemplateEntry)) {

			try {
				_removeContextReferences(
					layoutPageTemplateEntry, originalLayoutPageTemplateEntry);
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException);
				}
			}
		}
	}

	private boolean _isContentTypeChanged(
		LayoutPageTemplateEntry layoutPageTemplateEntry,
		LayoutPageTemplateEntry originalLayoutPageTemplateEntry) {

		if (!Objects.equals(
				layoutPageTemplateEntry.getType(),
				LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE) ||
			(originalLayoutPageTemplateEntry.getClassNameId() == 0)) {

			return false;
		}

		if (!Objects.equals(
				originalLayoutPageTemplateEntry.getClassNameId(),
				layoutPageTemplateEntry.getClassNameId()) ||
			!Objects.equals(
				originalLayoutPageTemplateEntry.getClassTypeId(),
				layoutPageTemplateEntry.getClassTypeId())) {

			return true;
		}

		return false;
	}

	private List<FragmentEntryLink> _processFormStyledLayoutStructureItem(
		FormStyledLayoutStructureItem formStyledLayoutStructureItem,
		Layout layout, LayoutPageTemplateEntry layoutPageTemplateEntry,
		LayoutPageTemplateEntry originalLayoutPageTemplateEntry,
		LayoutStructure layoutStructure, long segmentsExperienceId) {

		return Collections.emptyList();
	}

	private void _removeContextReferences(
			LayoutPageTemplateEntry layoutPageTemplateEntry,
			LayoutPageTemplateEntry originalLayoutPageTemplateEntry)
		throws PortalException {

		Layout layout = _layoutLocalService.getLayout(
			layoutPageTemplateEntry.getPlid());

		Layout draftLayout = layout.fetchDraftLayout();

		for (FragmentEntryLink fragmentEntryLink :
				_fragmentEntryLinkLocalService.getFragmentEntryLinksByPlid(
					layout.getGroupId(), layout.getPlid())) {

			_updateFragmentEntryLinkEditableValues(fragmentEntryLink);
		}

		if (draftLayout != null) {
			for (FragmentEntryLink fragmentEntryLink :
					_fragmentEntryLinkLocalService.getFragmentEntryLinksByPlid(
						draftLayout.getGroupId(), draftLayout.getPlid())) {

				_updateFragmentEntryLinkEditableValues(fragmentEntryLink);
			}
		}
	}

	private JSONObject _removeMappedFields(JSONObject jsonObject) {
		jsonObject.remove("mappedField");

		for (String key : jsonObject.keySet()) {
			Object object = jsonObject.get(key);

			if (object instanceof JSONObject) {
				_removeMappedFields((JSONObject)object);
			}
		}

		return jsonObject;
	}

	private List<FragmentEntryLink> _removeMappings(
		Layout layout, LayoutPageTemplateEntry layoutPageTemplateEntry,
		LayoutPageTemplateEntry originalLayoutPageTemplateEntry,
		LayoutStructure layoutStructure, long segmentsExperienceId) {

		List<FragmentEntryLink> fragmentEntryLinks = new ArrayList<>();

		for (LayoutStructureItem layoutStructureItem :
				ListUtil.copy(layoutStructure.getLayoutStructureItems())) {

			if (layoutStructure.isItemMarkedForDeletion(
					layoutStructureItem.getItemId())) {

				continue;
			}

			if (layoutStructureItem instanceof FormStyledLayoutStructureItem) {
				fragmentEntryLinks.addAll(
					_processFormStyledLayoutStructureItem(
						(FormStyledLayoutStructureItem)layoutStructureItem,
						layout, layoutPageTemplateEntry,
						originalLayoutPageTemplateEntry, layoutStructure,
						segmentsExperienceId));
			}

			layoutStructure.updateItemConfig(
				_removeMappedFields(
					layoutStructureItem.getItemConfigJSONObject()),
				layoutStructureItem.getItemId());
		}

		return fragmentEntryLinks;
	}

	private void _updateFragmentEntryLinkEditableValues(
		FragmentEntryLink fragmentEntryLink) {

		if ((fragmentEntryLink.getType() != FragmentConstants.TYPE_COMPONENT) &&
			(fragmentEntryLink.getType() != FragmentConstants.TYPE_SECTION)) {

			return;
		}

		JSONObject editableValuesJSONObject = null;

		try {
			editableValuesJSONObject = _jsonFactory.createJSONObject(
				fragmentEntryLink.getEditableValues());

			JSONObject editableFragmentEntryProcessorJSONObject =
				editableValuesJSONObject.getJSONObject(
					FragmentEntryProcessorConstants.
						KEY_EDITABLE_FRAGMENT_ENTRY_PROCESSOR);

			if (editableFragmentEntryProcessorJSONObject != null) {
				_removeMappedFields(editableFragmentEntryProcessorJSONObject);
			}
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(jsonException);
			}
		}

		if (editableValuesJSONObject == null) {
			return;
		}

		try (SafeCloseable safeCloseable =
				UpdateLayoutStatusThreadLocal.
					setUpdateLayoutStatusWithSafeCloseable(false)) {

			_fragmentEntryLinkService.updateFragmentEntryLink(
				fragmentEntryLink.getFragmentEntryLinkId(),
				editableValuesJSONObject.toString());
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}
		}
	}

	private void _updateLayoutPageTemplateStructureData(
		Layout layout, LayoutPageTemplateEntry layoutPageTemplateEntry,
		LayoutPageTemplateEntry originalLayoutPageTemplateEntry,
		long segmentsExperienceId) {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					layout.getGroupId(), layout.getPlid());

		if (layoutPageTemplateStructure == null) {
			return;
		}

		LayoutStructure layoutStructure = LayoutStructure.of(
			layoutPageTemplateStructure.getData(segmentsExperienceId));

		List<FragmentEntryLink> addedFragmentEntryLinks = _removeMappings(
			layout, layoutPageTemplateEntry, originalLayoutPageTemplateEntry,
			layoutStructure, segmentsExperienceId);

		try (SafeCloseable safeCloseable =
				UpdateLayoutStatusThreadLocal.
					setUpdateLayoutStatusWithSafeCloseable(false)) {

			_layoutPageTemplateStructureLocalService.
				updateLayoutPageTemplateStructureData(
					layout.getGroupId(), layout.getPlid(), segmentsExperienceId,
					layoutStructure.toString());
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}
		}

		for (FragmentEntryLink addedFragmentEntryLink :
				addedFragmentEntryLinks) {

			for (FragmentEntryLinkListener fragmentEntryLinkListener :
					_fragmentEntryLinkListenerRegistry.
						getFragmentEntryLinkListeners()) {

				fragmentEntryLinkListener.onAddFragmentEntryLink(
					addedFragmentEntryLink);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutPageTemplateEntryModelListener.class);

	@Reference
	private FormItemManager _formItemManager;

	@Reference
	private FragmentEntryLinkListenerRegistry
		_fragmentEntryLinkListenerRegistry;

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private FragmentEntryLinkService _fragmentEntryLinkService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private Portal _portal;

}