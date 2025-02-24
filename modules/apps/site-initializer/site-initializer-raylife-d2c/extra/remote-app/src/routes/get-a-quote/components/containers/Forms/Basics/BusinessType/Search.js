/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import classNames from 'classnames';
import React, {useEffect, useState} from 'react';
import {useFormContext} from 'react-hook-form';
import {WarningBadge} from '../../../../../../../common/components/fragments/Badges/Warning';
import {MoreInfoButton} from '../../../../../../../common/components/fragments/Buttons/MoreInfo';
import {SearchInput} from '../../../../../../../common/components/fragments/Forms/Input/Search';
import {useDebounce} from '../../../../../../../common/hooks/useDebounce';
import {calculatePercentage} from '../../../../../../../common/utils';
import {TIP_EVENT} from '../../../../../../../common/utils/events';
import {useStepWizard} from '../../../../../hooks/useStepWizard';
import {useTriggerContext} from '../../../../../hooks/useTriggerContext';
import {getTaxonomyCategories} from '../../../../../services/TaxonomyVolucabularies';
import {AVAILABLE_STEPS, TOTAL_OF_FIELD} from '../../../../../utils/constants';
import {getLoadedContentFlag, truncateSearch} from '../../../../../utils/util';
import BusinessTypeRadioGroup from './BusinessTypeRadioGroup';

const templateName = 'i-am-unable-to-find-my-industry';

const MAX_LENGTH_TO_TRUNCATE = 18;

export function BusinessTypeSearch({
	form,
	isMobileDevice = false,
	setNewSelectedProduct,
	taxonomyVocabularyId,
}) {
	const [taxonomyCategories, setTaxonomyCategories] = useState([]);
	const [error, setError] = useState();
	const {register, setValue} = useFormContext();
	const {isSelected, updateState} = useTriggerContext();

	const {setPercentage} = useStepWizard();
	const [isLoading, setIsLoading] = useState(false);
	const {applicationId, backToEdit} = getLoadedContentFlag();

	const businessSearchDebounced = useDebounce(
		form?.basics?.businessSearch,
		500
	);

	const _getTaxonomyCategories = async (search = '') => {
		if (!search) {
			return setTaxonomyCategories([]);
		}

		try {
			const taxonomyCategoriesResponse = await getTaxonomyCategories(
				taxonomyVocabularyId,
				search
			);

			const taxonomyCategories =
				taxonomyCategoriesResponse.data.items || [];

			setError('');

			setTaxonomyCategories(taxonomyCategories);
		}
		catch (error) {
			setError('Unable to make the request. Please try again later.');
		}
	};

	const getTaxonomyCategoriesBySearchName = async (searchTerm) => {
		setIsLoading(true);

		if (!searchTerm) {
			if (applicationId || backToEdit) {
				setPercentage(
					calculatePercentage(
						TOTAL_OF_FIELD.BASICS - 1,
						TOTAL_OF_FIELD.BASICS
					),
					AVAILABLE_STEPS.BASICS_BUSINESS_TYPE.section
				);
			}

			setValue('basics.businessCategoryId', '');
			setValue('basics.properties.businessClassCode', '');
			setValue('basics.properties.naics', '');
			setValue('basics.properties.segment', '');
		}

		await _getTaxonomyCategories(searchTerm);

		setIsLoading(false);
	};

	useEffect(() => {
		getTaxonomyCategoriesBySearchName(businessSearchDebounced);

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [businessSearchDebounced]);

	const noResults =
		!taxonomyCategories.length && !isLoading && businessSearchDebounced;

	return (
		<>
			<div className="mb-5">
				<SearchInput
					className={classNames(
						'bg-neutral-1 font-weight-bold px-4 py-0 search text-neutral-10 text-paragraph-lg',
						{'pr-7': isMobileDevice}
					)}
					defaultValue=""
					isMobileDevice={isMobileDevice}
					label="Search for your primary industry and then select it from the list."
					placeholder="Begin typing to show options..."
					required
					{...register('basics.businessSearch', {
						required:
							'Please, search for a business type in order to proceed.',
					})}
				>
					{!isMobileDevice && (
						<ClayButton
							className="font-weight-bolder ml-3 search text-paragraph text-small-caps"
							displayType="primary"
						>
							Search
						</ClayButton>
					)}
				</SearchInput>

				<p className="mt-1 paragraph">
					i.e. Apartments, Coffee, Medical, Pet Stores, etc
				</p>
			</div>

			<BusinessTypeRadioGroup
				businessTypes={taxonomyCategories}
				form={form}
				setNewSelectedProduct={setNewSelectedProduct}
			/>

			{noResults && (
				<WarningBadge>
					There are no results for &quot;
					{truncateSearch(
						form?.basics?.businessSearch,
						MAX_LENGTH_TO_TRUNCATE
					)}
					&quot;. Please try a different search.
				</WarningBadge>
			)}

			{error && <WarningBadge>{error}</WarningBadge>}

			{businessSearchDebounced && !isLoading && (
				<MoreInfoButton
					callback={() => updateState(templateName)}
					event={TIP_EVENT}
					label="I am unable to find my industry"
					selected={isSelected(templateName)}
					value={{templateName}}
				/>
			)}
		</>
	);
}
