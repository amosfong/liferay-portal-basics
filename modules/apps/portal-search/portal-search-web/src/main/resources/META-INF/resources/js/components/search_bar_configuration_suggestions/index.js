/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayDropDown from '@clayui/drop-down';
import getCN from 'classnames';
import {LearnMessage, LearnResourcesContext} from 'frontend-js-components-web';
import React, {useMemo} from 'react';

import InputSets, {useInputSets} from '../../shared/input_sets/index';
import {ITEM_ID_PROPERTY} from '../../shared/input_sets/useInputSets';
import cleanSuggestionsContributorConfiguration from '../../utils/clean_suggestions_contributor_configuration';
import {
	CONTRIBUTOR_TYPES,
	CONTRIBUTOR_TYPES_ASAH_DEFAULT_DISPLAY_GROUP_NAMES,
	CONTRIBUTOR_TYPES_DEFAULT_ATTRIBUTES,
} from '../../utils/types/contributorTypes';
import SuggestionContributorAddButton from './SuggestionContributorAddButton';
import ContributorInputSetItem from './contributor_input_set_item/index';

/**
 * Cleans up the fields array by removing those that do not have the required
 * fields (contributorName, displayGroupName, size). If blueprint, check
 * for sxpBlueprintExternalReferenceCode as well.
 * @param {Array} fields The list of fields.
 * @return {Array} The cleaned up list of fields.
 */
const removeEmptyFields = (fields) =>
	fields.filter(({attributes, contributorName, displayGroupName, size}) => {
		if (contributorName === CONTRIBUTOR_TYPES.SXP_BLUEPRINT) {
			return (
				contributorName &&
				displayGroupName &&
				size &&
				attributes?.sxpBlueprintExternalReferenceCode
			);
		}

		return contributorName && displayGroupName && size;
	});

function SearchBarConfigurationSuggestions({
	initialSuggestionsContributorConfiguration = '[]',
	isDXP = false,
	isSearchExperiencesSupported = true,
	learnResources,
	namespace = '',
	suggestionsContributorConfigurationName = '',
}) {
	const preparedSuggestionsContributorConfiguration = useMemo(
		() =>
			cleanSuggestionsContributorConfiguration(
				initialSuggestionsContributorConfiguration,
				isDXP,
				isSearchExperiencesSupported
			),
		[
			initialSuggestionsContributorConfiguration,
			isDXP,
			isSearchExperiencesSupported,
		]
	);

	const {
		getInputSetItemProps,
		onInputSetItemChange,
		onInputSetsAdd,
		value: suggestionsContributorConfiguration,
	} = useInputSets(preparedSuggestionsContributorConfiguration);

	const contributorOptions = useMemo(() => {
		const BASIC_OPTION = {
			contributorName: CONTRIBUTOR_TYPES.BASIC,
			description: Liferay.Language.get(
				'basic-suggestions-contributor-help'
			),
			title: Liferay.Language.get('basic'),
		};

		const BLUEPRINT_OPTION = {
			contributorName: CONTRIBUTOR_TYPES.SXP_BLUEPRINT,
			description: (
				<>
					{Liferay.Language.get(
						'blueprint-suggestions-contributor-help'
					)}

					<LearnMessage
						className="c-ml-1"
						resource="portal-search-web"
						resourceKey="search-bar-suggestions-blueprints"
					/>
				</>
			),
			title: Liferay.Language.get('blueprint'),
		};

		const SITE_ACTIVITIES_OPTION = {
			contributorName: CONTRIBUTOR_TYPES.ASAH_TOP_SEARCH_SITE_ACTIVITY,
			description: (
				<>
					{Liferay.Language.get(
						'site-activities-suggestions-contributor-help'
					)}

					<LearnMessage
						className="c-ml-1"
						resource="portal-search-web"
						resourceKey="search-bar-suggestions-site-activities"
					/>
				</>
			),
			title: Liferay.Language.get('site-activities'),
		};

		const options = [];

		const basicContributorExists =
			suggestionsContributorConfiguration.findIndex(
				(value) => value.contributorName === CONTRIBUTOR_TYPES.BASIC
			) > -1;

		if (!basicContributorExists) {
			options.push(BASIC_OPTION);
		}

		if (isDXP && isSearchExperiencesSupported) {
			options.push(BLUEPRINT_OPTION);
		}

		if (isDXP && Liferay.FeatureFlags['LPS-159643']) {
			options.push(SITE_ACTIVITIES_OPTION);
		}

		return options;
	}, [suggestionsContributorConfiguration.length]); // eslint-disable-line react-hooks/exhaustive-deps

	const _handleInputSetAdd = (contributorName) => () => {
		if (contributorName === CONTRIBUTOR_TYPES.BASIC) {
			onInputSetsAdd({
				attributes:
					CONTRIBUTOR_TYPES_DEFAULT_ATTRIBUTES[contributorName],
				contributorName,
				displayGroupName: 'suggestions',
				size: '5',
			});
		}
		else if (contributorName === CONTRIBUTOR_TYPES.SXP_BLUEPRINT) {
			onInputSetsAdd({
				attributes:
					CONTRIBUTOR_TYPES_DEFAULT_ATTRIBUTES[contributorName],
				contributorName,
				displayGroupName: '',
				size: '',
			});
		}
		else if (
			[
				CONTRIBUTOR_TYPES.ASAH_RECENT_ASSETS_USER_ACTIVITY,
				CONTRIBUTOR_TYPES.ASAH_RECENT_PAGES_USER_ACTIVITY,
				CONTRIBUTOR_TYPES.ASAH_RECENT_SEARCH_SITE_ACTIVITY,
				CONTRIBUTOR_TYPES.ASAH_RECENT_SEARCHES_USER_ACTIVITY,
				CONTRIBUTOR_TYPES.ASAH_RECENT_SITES_USER_ACTIVITY,
				CONTRIBUTOR_TYPES.ASAH_TOP_SEARCH_SITE_ACTIVITY,
			].includes(contributorName)
		) {
			onInputSetsAdd({
				attributes:
					CONTRIBUTOR_TYPES_DEFAULT_ATTRIBUTES[contributorName],
				contributorName,
				displayGroupName:
					CONTRIBUTOR_TYPES_ASAH_DEFAULT_DISPLAY_GROUP_NAMES[
						contributorName
					] || '',

				size: '3',
			});
		}
	};

	return (
		<LearnResourcesContext.Provider value={learnResources}>
			<div className="search-bar-configuration-suggestions-root">
				{removeEmptyFields(suggestionsContributorConfiguration)
					.length ? (
					removeEmptyFields(suggestionsContributorConfiguration).map(
						({[ITEM_ID_PROPERTY]: key, ...item}) => (
							<input
								hidden
								key={key}
								name={`${namespace}${suggestionsContributorConfigurationName}`}
								readOnly
								value={JSON.stringify(item)}
							/>
						)
					)
				) : (
					<input
						hidden
						name={`${namespace}${suggestionsContributorConfigurationName}`}
						readOnly
						value=""
					/>
				)}

				<InputSets>
					{suggestionsContributorConfiguration.map(
						(valueItem, valueIndex) => (

							// eslint-disable-next-line react/jsx-key
							<InputSets.Item
								{...getInputSetItemProps(valueItem, valueIndex)}
							>
								<ContributorInputSetItem
									index={valueIndex}
									onInputSetItemChange={onInputSetItemChange}
									value={valueItem}
								/>
							</InputSets.Item>
						)
					)}

					{!!contributorOptions.length && (
						<div
							className={getCN({
								'c-mt-4':
									!suggestionsContributorConfiguration.length,
							})}
						>
							<SuggestionContributorAddButton>
								{contributorOptions.map((option, index) => (
									<ClayDropDown.Item
										key={index}
										onClick={_handleInputSetAdd(
											option.contributorName
										)}
									>
										<div>{option.title}</div>

										<div className="text-2">
											{option.description}
										</div>
									</ClayDropDown.Item>
								))}
							</SuggestionContributorAddButton>
						</div>
					)}
				</InputSets>
			</div>
		</LearnResourcesContext.Provider>
	);
}

export default SearchBarConfigurationSuggestions;
