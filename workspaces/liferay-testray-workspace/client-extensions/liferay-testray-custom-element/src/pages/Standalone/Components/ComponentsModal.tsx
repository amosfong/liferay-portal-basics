/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ListView from '../../../components/ListView';
import SearchBuilder from '../../../core/SearchBuilder';
import i18n from '../../../i18n';
import {TestrayTeam, testrayComponentImpl} from '../../../services/rest';
import TeamFormModal from './ComponentsFormModal';
import useComponentActions from './useComponentActions';

type ComponentsModalProps = {
	projectId: number;
};

const ComponentsModal: React.FC<ComponentsModalProps> = ({projectId}) => {
	const {actions, formModal} = useComponentActions();

	return (
		<>
			<ListView
				forceRefetch={formModal.forceRefetch}
				managementToolbarProps={{
					addButton: () => formModal.modal.open(),
					applyFilters: false,
					display: {columns: false},
					filterSchema: 'components',
				}}
				resource={testrayComponentImpl.resource}
				tableProps={{
					actions,
					columns: [
						{
							key: 'team',
							render: (testrayTeam: TestrayTeam) =>
								testrayTeam?.name,
							value: i18n.translate('team'),
						},
						{
							key: 'name',
							value: i18n.translate('name'),
						},
					],
				}}
				transformData={(response) =>
					testrayComponentImpl.transformDataFromList(response)
				}
				variables={{filter: SearchBuilder.eq('projectId', projectId)}}
			/>

			<TeamFormModal modal={formModal.modal} projectId={projectId} />
		</>
	);
};

export default ComponentsModal;
