/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.asah.connector.internal.model.listener;

import com.liferay.analytics.settings.rest.manager.AnalyticsSettingsManager;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.segments.asah.connector.internal.client.AsahFaroBackendClientImpl;
import com.liferay.segments.asah.connector.internal.processor.AsahSegmentsExperimentProcessor;
import com.liferay.segments.asah.connector.internal.util.AsahUtil;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.model.SegmentsExperiment;
import com.liferay.segments.service.SegmentsEntryLocalService;
import com.liferay.segments.service.SegmentsExperienceLocalService;
import com.liferay.segments.service.SegmentsExperimentLocalService;
import com.liferay.segments.service.SegmentsExperimentRelLocalService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sarai Díaz
 * @author David Arques
 */
@Component(service = ModelListener.class)
public class SegmentsExperienceModelListener
	extends BaseModelListener<SegmentsExperience> {

	@Override
	public void onAfterUpdate(
			SegmentsExperience originalSegmentsExperience,
			SegmentsExperience segmentsExperience)
		throws ModelListenerException {

		try {
			if (AsahUtil.isSkipAsahEvent(
					_analyticsSettingsManager,
					segmentsExperience.getCompanyId(),
					segmentsExperience.getGroupId())) {

				return;
			}

			SegmentsExperiment segmentsExperiment =
				_segmentsExperimentLocalService.fetchSegmentsExperiment(
					segmentsExperience.getGroupId(),
					segmentsExperience.getSegmentsExperienceId(),
					segmentsExperience.getPlid());

			if (segmentsExperiment != null) {
				_processUpdateSegmentsExperience(
					segmentsExperience, segmentsExperiment);
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to update segments experience " +
						segmentsExperience.getSegmentsExperienceId(),
					exception);
			}
		}
	}

	@Activate
	protected void activate() {
		_asahSegmentsExperimentProcessor = new AsahSegmentsExperimentProcessor(
			_analyticsSettingsManager,
			new AsahFaroBackendClientImpl(_analyticsSettingsManager, _http),
			_companyLocalService, _groupLocalService, _layoutLocalService,
			_portal, _segmentsEntryLocalService,
			_segmentsExperienceLocalService);
	}

	@Deactivate
	protected void deactivate() {
		_asahSegmentsExperimentProcessor = null;
	}

	private void _processUpdateSegmentsExperience(
			SegmentsExperience segmentsExperience,
			SegmentsExperiment segmentsExperiment)
		throws Exception {

		if (segmentsExperience.getSegmentsExperienceId() ==
				segmentsExperiment.getSegmentsExperienceId()) {

			_asahSegmentsExperimentProcessor.processUpdateSegmentsExperiment(
				segmentsExperiment);

			return;
		}

		_asahSegmentsExperimentProcessor.processUpdateSegmentsExperimentRel(
			segmentsExperiment.getCompanyId(),
			segmentsExperiment.getSegmentsExperimentKey(),
			_segmentsExperimentRelLocalService.getSegmentsExperimentRels(
				segmentsExperiment.getSegmentsExperimentId()));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SegmentsExperienceModelListener.class);

	@Reference
	private AnalyticsSettingsManager _analyticsSettingsManager;

	private AsahSegmentsExperimentProcessor _asahSegmentsExperimentProcessor;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Http _http;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private SegmentsEntryLocalService _segmentsEntryLocalService;

	@Reference
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

	@Reference
	private SegmentsExperimentLocalService _segmentsExperimentLocalService;

	@Reference
	private SegmentsExperimentRelLocalService
		_segmentsExperimentRelLocalService;

}