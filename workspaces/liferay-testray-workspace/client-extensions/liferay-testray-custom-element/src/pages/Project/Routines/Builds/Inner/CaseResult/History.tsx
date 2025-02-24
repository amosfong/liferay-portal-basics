/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useOutletContext, useParams} from 'react-router-dom';

import Container from '../../../../../../components/Layout/Container';
import {TestrayCaseResult} from '../../../../../../services/rest';
import CaseResultHistory from '../../../../Cases/CaseResultHistory';

type OutletContext = {
	caseResult: TestrayCaseResult;
};

const History = () => {
	const {caseResult} = useOutletContext<OutletContext>();

	const {projectId} = useParams();

	return (
		<Container>
			<CaseResultHistory
				caseId={String(caseResult.case?.id)}
				tableProps={{
					navigateTo: ({
						testrayBuildId,
						testrayCaseResultId,
						testrayRoutineId,
					}) =>
						`/project/${projectId}/routines/${testrayRoutineId}/build/${testrayBuildId}/case-result/${testrayCaseResultId}`,
				}}
			/>
		</Container>
	);
};

export default History;
