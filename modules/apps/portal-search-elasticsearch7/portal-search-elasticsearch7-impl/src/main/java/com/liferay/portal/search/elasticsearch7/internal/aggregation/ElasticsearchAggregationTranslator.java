/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.aggregation;

import com.liferay.portal.search.aggregation.Aggregation;
import com.liferay.portal.search.aggregation.AggregationTranslator;
import com.liferay.portal.search.aggregation.AggregationVisitor;
import com.liferay.portal.search.aggregation.FieldAggregation;
import com.liferay.portal.search.aggregation.bucket.ChildrenAggregation;
import com.liferay.portal.search.aggregation.bucket.DateHistogramAggregation;
import com.liferay.portal.search.aggregation.bucket.DateRangeAggregation;
import com.liferay.portal.search.aggregation.bucket.DiversifiedSamplerAggregation;
import com.liferay.portal.search.aggregation.bucket.FilterAggregation;
import com.liferay.portal.search.aggregation.bucket.FiltersAggregation;
import com.liferay.portal.search.aggregation.bucket.GeoDistanceAggregation;
import com.liferay.portal.search.aggregation.bucket.GeoHashGridAggregation;
import com.liferay.portal.search.aggregation.bucket.GlobalAggregation;
import com.liferay.portal.search.aggregation.bucket.HistogramAggregation;
import com.liferay.portal.search.aggregation.bucket.MissingAggregation;
import com.liferay.portal.search.aggregation.bucket.NestedAggregation;
import com.liferay.portal.search.aggregation.bucket.RangeAggregation;
import com.liferay.portal.search.aggregation.bucket.ReverseNestedAggregation;
import com.liferay.portal.search.aggregation.bucket.SamplerAggregation;
import com.liferay.portal.search.aggregation.bucket.SignificantTermsAggregation;
import com.liferay.portal.search.aggregation.bucket.SignificantTextAggregation;
import com.liferay.portal.search.aggregation.bucket.TermsAggregation;
import com.liferay.portal.search.aggregation.metrics.AvgAggregation;
import com.liferay.portal.search.aggregation.metrics.CardinalityAggregation;
import com.liferay.portal.search.aggregation.metrics.ExtendedStatsAggregation;
import com.liferay.portal.search.aggregation.metrics.GeoBoundsAggregation;
import com.liferay.portal.search.aggregation.metrics.GeoCentroidAggregation;
import com.liferay.portal.search.aggregation.metrics.MaxAggregation;
import com.liferay.portal.search.aggregation.metrics.MinAggregation;
import com.liferay.portal.search.aggregation.metrics.PercentileRanksAggregation;
import com.liferay.portal.search.aggregation.metrics.PercentilesAggregation;
import com.liferay.portal.search.aggregation.metrics.PercentilesMethod;
import com.liferay.portal.search.aggregation.metrics.ScriptedMetricAggregation;
import com.liferay.portal.search.aggregation.metrics.StatsAggregation;
import com.liferay.portal.search.aggregation.metrics.SumAggregation;
import com.liferay.portal.search.aggregation.metrics.TopHitsAggregation;
import com.liferay.portal.search.aggregation.metrics.ValueCountAggregation;
import com.liferay.portal.search.aggregation.metrics.WeightedAvgAggregation;
import com.liferay.portal.search.aggregation.pipeline.PipelineAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.bucket.DateHistogramAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.bucket.DateRangeAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.bucket.FilterAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.bucket.FiltersAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.bucket.GeoDistanceAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.bucket.HistogramAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.bucket.RangeAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.bucket.SignificantTermsAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.bucket.SignificantTextAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.bucket.TermsAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.metrics.ScriptedMetricAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.metrics.TopHitsAggregationTranslator;
import com.liferay.portal.search.elasticsearch7.internal.aggregation.metrics.WeightedAvgAggregationTranslator;

import org.elasticsearch.join.aggregations.ChildrenAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.geogrid.GeoGridAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ReverseNestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.sampler.DiversifiedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.sampler.SamplerAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ExtendedStatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.GeoBoundsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.PercentileRanksAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.PercentilesAggregationBuilder;
import org.elasticsearch.search.aggregations.support.ValuesSourceAggregationBuilder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	property = "search.engine.impl=Elasticsearch",
	service = AggregationTranslator.class
)
public class ElasticsearchAggregationTranslator
	implements AggregationTranslator<AggregationBuilder>,
			   AggregationVisitor<AggregationBuilder> {

	@Override
	public AggregationBuilder translate(Aggregation aggregation) {
		return aggregation.accept(this);
	}

	@Override
	public AggregationBuilder visit(AvgAggregation avgAggregation) {
		return _assemble(
			AggregationBuilders.avg(avgAggregation.getName()), avgAggregation);
	}

	@Override
	public AggregationBuilder visit(
		CardinalityAggregation cardinalityAggregation) {

		CardinalityAggregationBuilder cardinalityAggregationBuilder =
			AggregationBuilders.cardinality(cardinalityAggregation.getName());

		if (cardinalityAggregation.getPrecisionThreshold() != null) {
			cardinalityAggregationBuilder.precisionThreshold(
				cardinalityAggregation.getPrecisionThreshold());
		}

		return _assemble(cardinalityAggregationBuilder, cardinalityAggregation);
	}

	@Override
	public AggregationBuilder visit(ChildrenAggregation childrenAggregation) {
		return _baseFieldAggregationTranslator.translate(
			baseMetricsAggregation -> new ChildrenAggregationBuilder(
				baseMetricsAggregation.getName(),
				childrenAggregation.getChildType()),
			childrenAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(
		DateHistogramAggregation dateHistogramAggregation) {

		return _assemble(
			_dateHistogramAggregationTranslator.translate(
				dateHistogramAggregation),
			dateHistogramAggregation);
	}

	@Override
	public AggregationBuilder visit(DateRangeAggregation dateRangeAggregation) {
		return _dateRangeAggregationTranslator.translate(
			dateRangeAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(
		DiversifiedSamplerAggregation diversifiedSamplerAggregation) {

		DiversifiedAggregationBuilder diversifiedAggregationBuilder =
			_baseFieldAggregationTranslator.translate(
				baseMetricsAggregation ->
					AggregationBuilders.diversifiedSampler(
						diversifiedSamplerAggregation.getName()),
				diversifiedSamplerAggregation, this,
				_pipelineAggregationTranslator);

		if (diversifiedSamplerAggregation.getExecutionHint() != null) {
			diversifiedAggregationBuilder.executionHint(
				diversifiedSamplerAggregation.getExecutionHint());
		}

		if (diversifiedSamplerAggregation.getMaxDocsPerValue() != null) {
			diversifiedAggregationBuilder.maxDocsPerValue(
				diversifiedSamplerAggregation.getMaxDocsPerValue());
		}

		if (diversifiedSamplerAggregation.getShardSize() != null) {
			diversifiedAggregationBuilder.shardSize(
				diversifiedSamplerAggregation.getShardSize());
		}

		return diversifiedAggregationBuilder;
	}

	@Override
	public AggregationBuilder visit(
		ExtendedStatsAggregation extendedStatsAggregation) {

		ExtendedStatsAggregationBuilder extendedStatsAggregationBuilder =
			_baseFieldAggregationTranslator.translate(
				baseMetricsAggregation -> AggregationBuilders.extendedStats(
					baseMetricsAggregation.getName()),
				extendedStatsAggregation, this, _pipelineAggregationTranslator);

		if (extendedStatsAggregation.getSigma() != null) {
			extendedStatsAggregationBuilder.sigma(
				extendedStatsAggregation.getSigma());
		}

		return extendedStatsAggregationBuilder;
	}

	@Override
	public AggregationBuilder visit(FilterAggregation filterAggregation) {
		return _filterAggregationTranslator.translate(
			filterAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(FiltersAggregation filtersAggregation) {
		return _filtersAggregationTranslator.translate(
			filtersAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(GeoBoundsAggregation geoBoundsAggregation) {
		GeoBoundsAggregationBuilder geoBoundsAggregationBuilder =
			_baseFieldAggregationTranslator.translate(
				baseMetricsAggregation -> AggregationBuilders.geoBounds(
					geoBoundsAggregation.getName()),
				geoBoundsAggregation, this, _pipelineAggregationTranslator);

		if (geoBoundsAggregation.getWrapLongitude() != null) {
			geoBoundsAggregationBuilder.wrapLongitude(
				geoBoundsAggregation.getWrapLongitude());
		}

		return geoBoundsAggregationBuilder;
	}

	@Override
	public AggregationBuilder visit(
		GeoCentroidAggregation geoCentroidAggregation) {

		return _baseFieldAggregationTranslator.translate(
			baseMetricsAggregation -> AggregationBuilders.geoCentroid(
				geoCentroidAggregation.getName()),
			geoCentroidAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(
		GeoDistanceAggregation geoDistanceAggregation) {

		return _geoDistanceAggregationTranslator.translate(
			geoDistanceAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(
		GeoHashGridAggregation geoHashGridAggregation) {

		GeoGridAggregationBuilder geoGridAggregationBuilder =
			_baseFieldAggregationTranslator.translate(
				baseMetricsAggregation -> AggregationBuilders.geohashGrid(
					geoHashGridAggregation.getName()),
				geoHashGridAggregation, this, _pipelineAggregationTranslator);

		if (geoHashGridAggregation.getPrecision() != null) {
			geoGridAggregationBuilder.precision(
				geoHashGridAggregation.getPrecision());
		}

		if (geoHashGridAggregation.getShardSize() != null) {
			geoGridAggregationBuilder.shardSize(
				geoHashGridAggregation.getShardSize());
		}

		if (geoHashGridAggregation.getSize() != null) {
			geoGridAggregationBuilder.size(geoHashGridAggregation.getSize());
		}

		return geoGridAggregationBuilder;
	}

	@Override
	public AggregationBuilder visit(GlobalAggregation globalAggregation) {
		return _assemble(
			AggregationBuilders.global(globalAggregation.getName()),
			globalAggregation);
	}

	@Override
	public AggregationBuilder visit(HistogramAggregation histogramAggregation) {
		return _histogramAggregationTranslator.translate(
			histogramAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(MaxAggregation maxAggregation) {
		return _baseFieldAggregationTranslator.translate(
			baseMetricsAggregation -> AggregationBuilders.max(
				baseMetricsAggregation.getName()),
			maxAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(MinAggregation minAggregation) {
		return _baseFieldAggregationTranslator.translate(
			baseMetricsAggregation -> AggregationBuilders.min(
				baseMetricsAggregation.getName()),
			minAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(MissingAggregation missingAggregation) {
		return _baseFieldAggregationTranslator.translate(
			baseMetricsAggregation -> AggregationBuilders.missing(
				baseMetricsAggregation.getName()),
			missingAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(NestedAggregation nestedAggregation) {
		return _assemble(
			AggregationBuilders.nested(
				nestedAggregation.getName(), nestedAggregation.getPath()),
			nestedAggregation);
	}

	@Override
	public AggregationBuilder visit(
		PercentileRanksAggregation percentileRanksAggregation) {

		PercentileRanksAggregationBuilder percentileRanksAggregationBuilder =
			_baseFieldAggregationTranslator.translate(
				baseMetricsAggregation -> AggregationBuilders.percentileRanks(
					baseMetricsAggregation.getName(),
					percentileRanksAggregation.getValues()),
				percentileRanksAggregation, this,
				_pipelineAggregationTranslator);

		if (percentileRanksAggregation.getCompression() != null) {
			percentileRanksAggregationBuilder.compression(
				percentileRanksAggregation.getCompression());
		}

		if (percentileRanksAggregation.getHdrSignificantValueDigits() != null) {
			percentileRanksAggregationBuilder.numberOfSignificantValueDigits(
				percentileRanksAggregation.getHdrSignificantValueDigits());
		}

		if (percentileRanksAggregation.getKeyed() != null) {
			percentileRanksAggregationBuilder.keyed(
				percentileRanksAggregation.getKeyed());
		}

		if (percentileRanksAggregation.getPercentilesMethod() != null) {
			PercentilesMethod percentilesMethod =
				percentileRanksAggregation.getPercentilesMethod();

			percentileRanksAggregationBuilder.method(
				org.elasticsearch.search.aggregations.metrics.PercentilesMethod.
					valueOf(percentilesMethod.name()));
		}

		return percentileRanksAggregationBuilder;
	}

	@Override
	public AggregationBuilder visit(
		PercentilesAggregation percentilesAggregation) {

		PercentilesAggregationBuilder percentilesAggregationBuilder =
			_baseFieldAggregationTranslator.translate(
				baseMetricsAggregation -> AggregationBuilders.percentiles(
					baseMetricsAggregation.getName()),
				percentilesAggregation, this, _pipelineAggregationTranslator);

		if (percentilesAggregation.getCompression() != null) {
			percentilesAggregationBuilder.compression(
				percentilesAggregation.getCompression());
		}

		if (percentilesAggregation.getHdrSignificantValueDigits() != null) {
			percentilesAggregationBuilder.numberOfSignificantValueDigits(
				percentilesAggregation.getHdrSignificantValueDigits());
		}

		if (percentilesAggregation.getKeyed() != null) {
			percentilesAggregationBuilder.keyed(
				percentilesAggregation.getKeyed());
		}

		double[] percents = percentilesAggregation.getPercents();

		if (percents != null) {
			percentilesAggregationBuilder.percentiles(percents);
		}

		if (percentilesAggregation.getPercentilesMethod() != null) {
			PercentilesMethod percentilesMethod =
				percentilesAggregation.getPercentilesMethod();

			percentilesAggregationBuilder.method(
				org.elasticsearch.search.aggregations.metrics.PercentilesMethod.
					valueOf(percentilesMethod.name()));
		}

		return percentilesAggregationBuilder;
	}

	@Override
	public AggregationBuilder visit(RangeAggregation rangeAggregation) {
		return _rangeAggregationTranslator.translate(
			rangeAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(
		ReverseNestedAggregation reverseNestedAggregation) {

		ReverseNestedAggregationBuilder reverseNestedAggregationBuilder =
			AggregationBuilders.reverseNested(
				reverseNestedAggregation.getName());

		if (reverseNestedAggregation.getPath() != null) {
			reverseNestedAggregationBuilder.path(
				reverseNestedAggregation.getPath());
		}

		return _assemble(
			reverseNestedAggregationBuilder, reverseNestedAggregation);
	}

	@Override
	public AggregationBuilder visit(SamplerAggregation samplerAggregation) {
		SamplerAggregationBuilder samplerAggregationBuilder =
			AggregationBuilders.sampler(samplerAggregation.getName());

		if (samplerAggregation.getShardSize() != null) {
			samplerAggregationBuilder.shardSize(
				samplerAggregation.getShardSize());
		}

		return _assemble(samplerAggregationBuilder, samplerAggregation);
	}

	@Override
	public AggregationBuilder visit(
		ScriptedMetricAggregation scriptedMetricAggregation) {

		return _scriptedMetricAggregationTranslator.translate(
			scriptedMetricAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(
		SignificantTermsAggregation significantTermsAggregation) {

		return _assemble(
			_significantTermsAggregationTranslator.translate(
				significantTermsAggregation),
			significantTermsAggregation);
	}

	@Override
	public AggregationBuilder visit(
		SignificantTextAggregation significantTextAggregation) {

		return _significantTextAggregationTranslator.translate(
			significantTextAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(StatsAggregation statsAggregation) {
		return _baseFieldAggregationTranslator.translate(
			baseMetricsAggregation -> AggregationBuilders.stats(
				baseMetricsAggregation.getName()),
			statsAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(SumAggregation sumAggregation) {
		return _baseFieldAggregationTranslator.translate(
			baseMetricsAggregation -> AggregationBuilders.sum(
				baseMetricsAggregation.getName()),
			sumAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(TermsAggregation termsAggregation) {
		return _assemble(
			_termsAggregationTranslator.translate(termsAggregation),
			termsAggregation);
	}

	@Override
	public AggregationBuilder visit(TopHitsAggregation topHitsAggregation) {
		return _topHitsAggregationTranslator.translate(
			topHitsAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(
		ValueCountAggregation valueCountAggregation) {

		return _baseFieldAggregationTranslator.translate(
			baseMetricsAggregation -> AggregationBuilders.count(
				baseMetricsAggregation.getName()),
			valueCountAggregation, this, _pipelineAggregationTranslator);
	}

	@Override
	public AggregationBuilder visit(
		WeightedAvgAggregation weightedAvgAggregation) {

		return _weightedAvgAggregationTranslator.translate(
			weightedAvgAggregation, this, _pipelineAggregationTranslator);
	}

	private <AB extends AggregationBuilder> AB _assemble(
		AB aggregationBuilder, Aggregation aggregation) {

		AggregationBuilderAssemblerImpl aggregationBuilderAssemblerImpl =
			_aggregationBuilderAssemblerFactory.getAggregationBuilderAssembler(
				this);

		return aggregationBuilderAssemblerImpl.assembleAggregation(
			aggregationBuilder, aggregation);
	}

	private <VSAB extends ValuesSourceAggregationBuilder> VSAB _assemble(
		VSAB valuesSourceAggregationBuilder,
		FieldAggregation fieldAggregation) {

		AggregationBuilderAssemblerImpl aggregationBuilderAssemblerImpl =
			_aggregationBuilderAssemblerFactory.getAggregationBuilderAssembler(
				this);

		return aggregationBuilderAssemblerImpl.assembleFieldAggregation(
			valuesSourceAggregationBuilder, fieldAggregation);
	}

	@Reference
	private AggregationBuilderAssemblerFactory
		_aggregationBuilderAssemblerFactory;

	private final BaseFieldAggregationTranslator
		_baseFieldAggregationTranslator = new BaseFieldAggregationTranslator();

	@Reference
	private DateHistogramAggregationTranslator
		_dateHistogramAggregationTranslator;

	@Reference
	private DateRangeAggregationTranslator _dateRangeAggregationTranslator;

	@Reference
	private FilterAggregationTranslator _filterAggregationTranslator;

	@Reference
	private FiltersAggregationTranslator _filtersAggregationTranslator;

	@Reference
	private GeoDistanceAggregationTranslator _geoDistanceAggregationTranslator;

	@Reference
	private HistogramAggregationTranslator _histogramAggregationTranslator;

	@Reference(target = "(search.engine.impl=Elasticsearch)")
	private PipelineAggregationTranslator<PipelineAggregationBuilder>
		_pipelineAggregationTranslator;

	@Reference
	private RangeAggregationTranslator _rangeAggregationTranslator;

	@Reference
	private ScriptedMetricAggregationTranslator
		_scriptedMetricAggregationTranslator;

	@Reference
	private SignificantTermsAggregationTranslator
		_significantTermsAggregationTranslator;

	@Reference
	private SignificantTextAggregationTranslator
		_significantTextAggregationTranslator;

	@Reference
	private TermsAggregationTranslator _termsAggregationTranslator;

	@Reference
	private TopHitsAggregationTranslator _topHitsAggregationTranslator;

	@Reference
	private WeightedAvgAggregationTranslator _weightedAvgAggregationTranslator;

}