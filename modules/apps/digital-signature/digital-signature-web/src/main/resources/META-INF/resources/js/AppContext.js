/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {createContext} from 'react';

const AppContext = createContext();

const AppContextProvider = ({
	allowedFileExtensions,
	baseResourceURL,
	children,
	portletNamespace,
	...otherProps
}) => (
	<AppContext.Provider
		value={{
			allowedFileExtensions: allowedFileExtensions.split(','),
			baseResourceURL,
			portletNamespace,
			...otherProps,
		}}
	>
		{children}
	</AppContext.Provider>
);

export {AppContext, AppContextProvider};