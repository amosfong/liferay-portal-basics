/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {SegmentsExperimentType} from '../types.es';
import {indexToPercentageString} from '../util/percentages.es';
import {STATUS_DRAFT} from '../util/statuses.es';

function SegmentsExperimentsDetails({segmentsExperiment}) {
	const {
		confidenceLevel,
		description,
		goal,
		segmentsEntryName,
		status,
		type,
	} = segmentsExperiment;

	return (
		<>
			<div className="mb-3 mt-4 sheet-subtitle">
				{Liferay.Language.get('details')}
			</div>

			<dl className="pl-0 segments-experiment-details">
				{description && (
					<div className="c-my-2">
						<dt className="d-inline-block">
							{`${Liferay.Language.get('description')}:`}
						</dt>

						<dd className="d-inline inline-item-after text-secondary">
							{description}
						</dd>
					</div>
				)}

				<div className="c-my-2">
					<dt className="d-inline-block">
						{`${Liferay.Language.get('segment')}:`}
					</dt>

					<dd className="d-inline inline-item-after text-secondary">
						{segmentsEntryName}
					</dd>
				</div>

				<div className="c-my-2">
					<dt className="d-inline-block">
						{`${Liferay.Language.get('goal')}:`}
					</dt>

					<dd className="d-inline inline-item-after text-secondary">
						{goal.label}
					</dd>
				</div>

				{status.value !== STATUS_DRAFT && type.value === 'AB' && (
					<div className="c-my-2">
						<dt className="d-inline-block">
							{`${Liferay.Language.get('confidence-level')}:`}
						</dt>

						<dd className="d-inline inline-item-after text-secondary">
							{indexToPercentageString(confidenceLevel)}
						</dd>

						{Liferay.FeatureFlags['LRAC-15017'] && (
							<div className="c-my-2">
								<dt className="d-inline-block">
									{`${Liferay.Language.get('test-type')}:`}
								</dt>

								<dd className="d-inline inline-item-after text-secondary">
									{Liferay.Language.get('standard')}
								</dd>
							</div>
						)}
					</div>
				)}

				{Liferay.FeatureFlags['LRAC-15017'] &&
					status.value !== STATUS_DRAFT &&
					type.value === 'MAB' && (
						<div className="c-my-2">
							<dt className="d-inline-block">
								{`${Liferay.Language.get('test-type')}:`}
							</dt>

							<dd className="d-inline inline-item-after text-secondary">
								{Liferay.Language.get('dynamic')}
							</dd>
						</div>
					)}
			</dl>
		</>
	);
}

SegmentsExperimentsDetails.propTypes = {
	segmentsExperiment: SegmentsExperimentType,
};

export default SegmentsExperimentsDetails;
