/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import classNames from 'classnames';

type CodeProps = React.HTMLAttributes<HTMLElement>;

const Code: React.FC<CodeProps> = ({children, className, title}) => {
	if (!children) {
		return null;
	}

	return (
		<code className={classNames('tr-code', className)} title={title}>
			{children}
		</code>
	);
};

export default Code;
