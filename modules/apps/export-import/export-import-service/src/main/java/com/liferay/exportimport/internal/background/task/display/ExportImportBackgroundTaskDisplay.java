/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.background.task.display;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.background.task.display.BaseBackgroundTaskDisplay;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HtmlUtil_IW;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * @author Andrew Betts
 */
public class ExportImportBackgroundTaskDisplay
	extends BaseBackgroundTaskDisplay {

	public ExportImportBackgroundTaskDisplay(BackgroundTask backgroundTask) {
		super(backgroundTask);

		_cmd = MapUtil.getString(
			backgroundTask.getTaskContextMap(), Constants.CMD);

		_percentage = PERCENTAGE_NONE;

		if (backgroundTaskStatus == null) {
			_allProgressBarCountersTotal = 0;
			_currentProgressBarCountersTotal = 0;
			_phase = null;
			_stagedModelName = null;
			_stagedModelType = null;

			return;
		}

		long allModelAdditionCountersTotal =
			getBackgroundTaskStatusAttributeLong(
				"allModelAdditionCountersTotal");
		long allPortletAdditionCounter = getBackgroundTaskStatusAttributeLong(
			"allPortletAdditionCounter");

		_allProgressBarCountersTotal =
			allModelAdditionCountersTotal + allPortletAdditionCounter;

		long currentModelAdditionCountersTotal =
			getBackgroundTaskStatusAttributeLong(
				"currentModelAdditionCountersTotal");
		long currentPortletAdditionCounter =
			getBackgroundTaskStatusAttributeLong(
				"currentPortletAdditionCounter");

		_currentProgressBarCountersTotal =
			currentModelAdditionCountersTotal + currentPortletAdditionCounter;

		_phase = getBackgroundTaskStatusAttributeString("phase");
		_stagedModelName = getBackgroundTaskStatusAttributeString(
			"stagedModelName");
		_stagedModelType = getBackgroundTaskStatusAttributeString(
			"stagedModelType");
	}

	@Override
	public int getPercentage() {
		if (_percentage > PERCENTAGE_NONE) {
			return _percentage;
		}

		_percentage = PERCENTAGE_MAX;

		if (_allProgressBarCountersTotal > PERCENTAGE_MIN) {
			int base = PERCENTAGE_MAX;

			if (_phase.equals(Constants.EXPORT) &&
				!Objects.equals(_cmd, Constants.PUBLISH_TO_REMOTE)) {

				base = _EXPORT_PHASE_MAX_PERCENTAGE;
			}

			_percentage = Math.round(
				(float)_currentProgressBarCountersTotal /
					_allProgressBarCountersTotal * base);
		}

		return _percentage;
	}

	@Override
	public boolean hasPercentage() {
		if (!hasBackgroundTaskStatus()) {
			return false;
		}

		if ((_allProgressBarCountersTotal > PERCENTAGE_MIN) &&
			(!Objects.equals(_cmd, Constants.PUBLISH_TO_REMOTE) ||
			 (getPercentage() < PERCENTAGE_MAX))) {

			return true;
		}

		return false;
	}

	@Override
	public String renderDisplayTemplate(Locale locale) {
		if (!backgroundTask.isInProgress()) {
			return super.renderDisplayTemplate(locale);
		}

		if (_hasStagedModelMessage()) {
			return _getStagedModelMessage(locale);
		}

		return LanguageUtil.get(locale, _getStatusMessageKey());
	}

	@Override
	protected TemplateResource getTemplateResource() {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		return new URLTemplateResource(
			_DETIALS_TEMPLATE, classLoader.getResource(_DETIALS_TEMPLATE));
	}

	@Override
	protected Map<String, Object> getTemplateVars() {
		return HashMapBuilder.<String, Object>put(
			"exported",
			MapUtil.getBoolean(backgroundTask.getTaskContextMap(), "exported")
		).put(
			"htmlUtil", HtmlUtil_IW.getInstance()
		).put(
			"validated",
			MapUtil.getBoolean(backgroundTask.getTaskContextMap(), "validated")
		).build();
	}

	private String _getStagedModelMessage(Locale locale) {
		return StringBundler.concat(
			"<strong>", LanguageUtil.get(locale, _getStatusMessageKey()),
			"...</strong>",
			ResourceActionsUtil.getModelResource(locale, _stagedModelType),
			"<em>", HtmlUtil.escape(_stagedModelName), "</em>");
	}

	private String _getStatusMessageKey() {
		if (Validator.isNotNull(_messageKey)) {
			return _messageKey;
		}

		_messageKey = StringPool.BLANK;

		if (_hasRemoteMessage()) {
			_messageKey =
				"please-wait-as-the-publish-processes-complete-on-the-remote-" +
					"site";
		}
		else if (_hasStagedModelMessage()) {
			_messageKey = "exporting";

			if (Objects.equals(_cmd, Constants.IMPORT)) {
				_messageKey = "importing";
			}
			else if (Objects.equals(_cmd, Constants.PUBLISH_TO_LIVE) ||
					 Objects.equals(_cmd, Constants.PUBLISH_TO_REMOTE)) {

				_messageKey = "publishing";
			}
		}

		return _messageKey;
	}

	private boolean _hasRemoteMessage() {
		if (Objects.equals(_cmd, Constants.PUBLISH_TO_REMOTE) &&
			(getPercentage() == PERCENTAGE_MAX)) {

			return true;
		}

		return false;
	}

	private boolean _hasStagedModelMessage() {
		if (Validator.isNotNull(_stagedModelName) &&
			Validator.isNotNull(_stagedModelType)) {

			return true;
		}

		return false;
	}

	private static final String _DETIALS_TEMPLATE =
		"com/liferay/exportimport/internal/background/task/display" +
			"/dependencies/export_import_background_task_details.ftl";

	private static final int _EXPORT_PHASE_MAX_PERCENTAGE = 50;

	private final long _allProgressBarCountersTotal;
	private final String _cmd;
	private final long _currentProgressBarCountersTotal;
	private String _messageKey;
	private int _percentage;
	private final String _phase;
	private final String _stagedModelName;
	private final String _stagedModelType;

}