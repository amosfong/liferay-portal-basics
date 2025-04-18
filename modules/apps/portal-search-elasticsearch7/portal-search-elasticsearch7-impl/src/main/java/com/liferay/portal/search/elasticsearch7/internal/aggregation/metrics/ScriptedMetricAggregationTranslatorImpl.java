/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.aggregation.metrics;

import com.liferay.portal.search.aggregation.AggregationTranslator;
import com.liferay.portal.search.aggregation.metrics.ScriptedMetricAggregation;
import com.liferay.portal.search.aggregation.pipeline.PipelineAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.BaseAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.script.ScriptTranslator;

import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ScriptedMetricAggregationBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(service = ScriptedMetricAggregationTranslator.class)
public class ScriptedMetricAggregationTranslatorImpl
	implements ScriptedMetricAggregationTranslator {

	@Override
	public ScriptedMetricAggregationBuilder translate(
		ScriptedMetricAggregation scriptedMetricAggregation,
		AggregationTranslator<AggregationBuilder> aggregationTranslator,
		PipelineAggregationTranslator<PipelineAggregationBuilder>
			pipelineAggregationTranslator) {

		ScriptedMetricAggregationBuilder scriptedMetricAggregationBuilder =
			AggregationBuilders.scriptedMetric(
				scriptedMetricAggregation.getName());

		if (scriptedMetricAggregation.getCombineScript() != null) {
			Script elasticsearchCombineScript = _scriptTranslator.translate(
				scriptedMetricAggregation.getCombineScript());

			scriptedMetricAggregationBuilder.combineScript(
				elasticsearchCombineScript);
		}

		if (scriptedMetricAggregation.getInitScript() != null) {
			Script elasticsearchInitScript = _scriptTranslator.translate(
				scriptedMetricAggregation.getInitScript());

			scriptedMetricAggregationBuilder.initScript(
				elasticsearchInitScript);
		}

		if (scriptedMetricAggregation.getMapScript() != null) {
			Script elasticsearchMapScript = _scriptTranslator.translate(
				scriptedMetricAggregation.getMapScript());

			scriptedMetricAggregationBuilder.mapScript(elasticsearchMapScript);
		}

		if (scriptedMetricAggregation.getReduceScript() != null) {
			Script elasticsearchReduceScript = _scriptTranslator.translate(
				scriptedMetricAggregation.getReduceScript());

			scriptedMetricAggregationBuilder.reduceScript(
				elasticsearchReduceScript);
		}

		scriptedMetricAggregationBuilder.params(
			scriptedMetricAggregation.getParameters());

		_baseAggregationTranslator.translate(
			scriptedMetricAggregationBuilder, scriptedMetricAggregation,
			aggregationTranslator, pipelineAggregationTranslator);

		return scriptedMetricAggregationBuilder;
	}

	private final BaseAggregationTranslator _baseAggregationTranslator =
		new BaseAggregationTranslator();
	private final ScriptTranslator _scriptTranslator = new ScriptTranslator();

}