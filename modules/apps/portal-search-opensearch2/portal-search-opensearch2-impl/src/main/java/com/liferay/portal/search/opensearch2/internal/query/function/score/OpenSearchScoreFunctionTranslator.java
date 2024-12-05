/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.query.function.score;

import com.liferay.portal.search.opensearch2.internal.script.ScriptTranslator;
import com.liferay.portal.search.opensearch2.internal.util.SetterUtil;
import com.liferay.portal.search.query.MultiValueMode;
import com.liferay.portal.search.query.function.score.ExponentialDecayScoreFunction;
import com.liferay.portal.search.query.function.score.FieldValueFactorScoreFunction;
import com.liferay.portal.search.query.function.score.GaussianDecayScoreFunction;
import com.liferay.portal.search.query.function.score.LinearDecayScoreFunction;
import com.liferay.portal.search.query.function.score.RandomScoreFunction;
import com.liferay.portal.search.query.function.score.ScoreFunction;
import com.liferay.portal.search.query.function.score.ScoreFunctionTranslator;
import com.liferay.portal.search.query.function.score.ScriptScoreFunction;
import com.liferay.portal.search.query.function.score.WeightScoreFunction;

import org.opensearch.client.json.JsonData;
import org.opensearch.client.opensearch._types.query_dsl.DecayFunction;
import org.opensearch.client.opensearch._types.query_dsl.DecayPlacement;
import org.opensearch.client.opensearch._types.query_dsl.FieldValueFactorModifier;
import org.opensearch.client.opensearch._types.query_dsl.FunctionScore;
import org.opensearch.client.opensearch._types.query_dsl.FunctionScore.Builder.ContainerBuilder;
import org.opensearch.client.opensearch._types.query_dsl.FunctionScoreBuilders;

/**
 * @author Michael C. Han
 * @author Petteri Karttunen
 */
public class OpenSearchScoreFunctionTranslator
	implements ScoreFunctionTranslator<ContainerBuilder> {

	@Override
	public ContainerBuilder translate(
		ExponentialDecayScoreFunction exponentialDecayScoreFunction) {

		FunctionScore.Builder functionScoreBuilder =
			new FunctionScore.Builder();

		DecayFunction.Builder decayFunctionBuilder =
			FunctionScoreBuilders.exp();

		decayFunctionBuilder.field(exponentialDecayScoreFunction.getField());

		decayFunctionBuilder.multiValueMode(
			_translateMultiValueMode(
				exponentialDecayScoreFunction.getMultiValueMode()));

		DecayPlacement.Builder decayPlacementBuilder =
			new DecayPlacement.Builder();

		SetterUtil.setNotNullDouble(
			decayPlacementBuilder::decay,
			exponentialDecayScoreFunction.getDecay());

		decayPlacementBuilder.offset(
			JsonData.of(exponentialDecayScoreFunction.getOffset()));
		decayPlacementBuilder.origin(
			JsonData.of(exponentialDecayScoreFunction.getOrigin()));
		decayPlacementBuilder.scale(
			JsonData.of(exponentialDecayScoreFunction.getScale()));

		decayFunctionBuilder.placement(decayPlacementBuilder.build());

		SetterUtil.setNotNullFloatAsDouble(
			decayFunctionBuilder::weight,
			exponentialDecayScoreFunction.getWeight());

		return functionScoreBuilder.exp(decayFunctionBuilder.build());
	}

	@Override
	public ContainerBuilder translate(
		FieldValueFactorScoreFunction fieldValueFactorScoreFunction) {

		FunctionScore.Builder functionScoreBuilder =
			new FunctionScore.Builder();

		org.opensearch.client.opensearch._types.query_dsl.
			FieldValueFactorScoreFunction.Builder
				fieldValueFactorScoreFunctionBuilder =
					FunctionScoreBuilders.fieldValueFactor();

		SetterUtil.setNotNullFloatAsDouble(
			fieldValueFactorScoreFunctionBuilder::factor,
			fieldValueFactorScoreFunction.getFactor());

		fieldValueFactorScoreFunctionBuilder.field(
			fieldValueFactorScoreFunction.getField());

		SetterUtil.setNotNullDouble(
			fieldValueFactorScoreFunctionBuilder::missing,
			fieldValueFactorScoreFunction.getMissing());

		if (fieldValueFactorScoreFunction.getModifier() != null) {
			fieldValueFactorScoreFunctionBuilder.modifier(
				_translateModifier(
					fieldValueFactorScoreFunction.getModifier()));
		}

		SetterUtil.setNotNullFloatAsDouble(
			fieldValueFactorScoreFunctionBuilder::weight,
			fieldValueFactorScoreFunction.getWeight());

		return functionScoreBuilder.fieldValueFactor(
			fieldValueFactorScoreFunctionBuilder.build());
	}

	@Override
	public ContainerBuilder translate(
		GaussianDecayScoreFunction gaussianDecayScoreFunction) {

		FunctionScore.Builder functionScoreBuilder =
			new FunctionScore.Builder();

		DecayFunction.Builder decayFunctionBuilder =
			FunctionScoreBuilders.gauss();

		decayFunctionBuilder.field(gaussianDecayScoreFunction.getField());

		decayFunctionBuilder.multiValueMode(
			_translateMultiValueMode(
				gaussianDecayScoreFunction.getMultiValueMode()));

		DecayPlacement.Builder decayPlacementBuilder =
			new DecayPlacement.Builder();

		SetterUtil.setNotNullDouble(
			decayPlacementBuilder::decay,
			gaussianDecayScoreFunction.getDecay());

		decayPlacementBuilder.offset(
			JsonData.of(gaussianDecayScoreFunction.getOffset()));
		decayPlacementBuilder.origin(
			JsonData.of(gaussianDecayScoreFunction.getOrigin()));
		decayPlacementBuilder.scale(
			JsonData.of(gaussianDecayScoreFunction.getScale()));

		decayFunctionBuilder.placement(decayPlacementBuilder.build());

		SetterUtil.setNotNullFloatAsDouble(
			decayFunctionBuilder::weight,
			gaussianDecayScoreFunction.getWeight());

		return functionScoreBuilder.gauss(decayFunctionBuilder.build());
	}

	@Override
	public ContainerBuilder translate(
		LinearDecayScoreFunction linearDecayScoreFunction) {

		FunctionScore.Builder functionScoreBuilder =
			new FunctionScore.Builder();

		DecayFunction.Builder decayFunctionBuilder =
			FunctionScoreBuilders.linear();

		decayFunctionBuilder.field(linearDecayScoreFunction.getField());

		decayFunctionBuilder.multiValueMode(
			_translateMultiValueMode(
				linearDecayScoreFunction.getMultiValueMode()));

		DecayPlacement.Builder decayPlacementBuilder =
			new DecayPlacement.Builder();

		SetterUtil.setNotNullDouble(
			decayPlacementBuilder::decay, linearDecayScoreFunction.getDecay());

		decayPlacementBuilder.offset(
			JsonData.of(linearDecayScoreFunction.getOffset()));
		decayPlacementBuilder.origin(
			JsonData.of(linearDecayScoreFunction.getOrigin()));
		decayPlacementBuilder.scale(
			JsonData.of(linearDecayScoreFunction.getScale()));

		decayFunctionBuilder.placement(decayPlacementBuilder.build());

		SetterUtil.setNotNullFloatAsDouble(
			decayFunctionBuilder::weight, linearDecayScoreFunction.getWeight());

		return functionScoreBuilder.linear(decayFunctionBuilder.build());
	}

	@Override
	public ContainerBuilder translate(RandomScoreFunction randomScoreFunction) {
		FunctionScore.Builder functionScoreBuilder =
			new FunctionScore.Builder();

		org.opensearch.client.opensearch._types.query_dsl.RandomScoreFunction.
			Builder randomScoreFunctionBuilder =
				FunctionScoreBuilders.randomScore();

		SetterUtil.setNotBlankString(
			randomScoreFunctionBuilder::field, randomScoreFunction.getField());
		SetterUtil.setNotBlankString(
			randomScoreFunctionBuilder::seed,
			String.valueOf(randomScoreFunction.getSeed()));
		SetterUtil.setNotNullFloatAsDouble(
			randomScoreFunctionBuilder::weight,
			randomScoreFunction.getWeight());

		return functionScoreBuilder.randomScore(
			randomScoreFunctionBuilder.build());
	}

	public ContainerBuilder translate(ScoreFunction scoreFunction) {
		if (scoreFunction == null) {
			return null;
		}

		return scoreFunction.accept(this);
	}

	@Override
	public ContainerBuilder translate(ScriptScoreFunction scriptScoreFunction) {
		FunctionScore.Builder functionScoreBuilder =
			new FunctionScore.Builder();

		org.opensearch.client.opensearch._types.query_dsl.ScriptScoreFunction.
			Builder scriptScoreFunctionBuilder =
				FunctionScoreBuilders.scriptScore();

		scriptScoreFunctionBuilder.script(
			_scriptTranslator.translate(scriptScoreFunction.getScript()));

		SetterUtil.setNotNullFloatAsDouble(
			scriptScoreFunctionBuilder::weight,
			scriptScoreFunction.getWeight());

		return functionScoreBuilder.scriptScore(
			scriptScoreFunctionBuilder.build());
	}

	@Override
	public ContainerBuilder translate(WeightScoreFunction weightScoreFunction) {
		throw new UnsupportedOperationException();
	}

	private FieldValueFactorModifier _translateModifier(
		FieldValueFactorScoreFunction.Modifier modifier) {

		if (modifier == FieldValueFactorScoreFunction.Modifier.LN) {
			return FieldValueFactorModifier.Ln;
		}
		else if (modifier == FieldValueFactorScoreFunction.Modifier.LN1P) {
			return FieldValueFactorModifier.Ln1p;
		}
		else if (modifier == FieldValueFactorScoreFunction.Modifier.LN2P) {
			return FieldValueFactorModifier.Ln2p;
		}
		else if (modifier == FieldValueFactorScoreFunction.Modifier.LOG) {
			return FieldValueFactorModifier.Log;
		}
		else if (modifier == FieldValueFactorScoreFunction.Modifier.LOG1P) {
			return FieldValueFactorModifier.Log1p;
		}
		else if (modifier == FieldValueFactorScoreFunction.Modifier.LOG2P) {
			return FieldValueFactorModifier.Log2p;
		}
		else if (modifier == FieldValueFactorScoreFunction.Modifier.NONE) {
			return FieldValueFactorModifier.None;
		}
		else if (modifier ==
					FieldValueFactorScoreFunction.Modifier.RECIPROCAL) {

			return FieldValueFactorModifier.Reciprocal;
		}
		else if (modifier == FieldValueFactorScoreFunction.Modifier.SQRT) {
			return FieldValueFactorModifier.Sqrt;
		}
		else if (modifier == FieldValueFactorScoreFunction.Modifier.SQUARE) {
			return FieldValueFactorModifier.Square;
		}

		throw new IllegalArgumentException("Invalid modifier " + modifier);
	}

	private org.opensearch.client.opensearch._types.query_dsl.MultiValueMode
		_translateMultiValueMode(MultiValueMode multiValueMode) {

		if (multiValueMode == MultiValueMode.AVG) {
			return org.opensearch.client.opensearch._types.query_dsl.
				MultiValueMode.Avg;
		}
		else if (multiValueMode == MultiValueMode.MAX) {
			return org.opensearch.client.opensearch._types.query_dsl.
				MultiValueMode.Max;
		}
		else if (multiValueMode == MultiValueMode.MIN) {
			return org.opensearch.client.opensearch._types.query_dsl.
				MultiValueMode.Min;
		}
		else if (multiValueMode == MultiValueMode.SUM) {
			return org.opensearch.client.opensearch._types.query_dsl.
				MultiValueMode.Sum;
		}

		throw new IllegalArgumentException(
			"Invalid multi value mode " + multiValueMode);
	}

	private final ScriptTranslator _scriptTranslator = new ScriptTranslator();

}