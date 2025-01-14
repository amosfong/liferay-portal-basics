/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the AccountGroup service. Represents a row in the &quot;AccountGroup&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AccountGroupModel
 * @generated
 */
@ImplementationClassName("com.liferay.account.model.impl.AccountGroupImpl")
@ProviderType
public interface AccountGroup extends AccountGroupModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.account.model.impl.AccountGroupImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AccountGroup, Long> ACCOUNT_GROUP_ID_ACCESSOR =
		new Accessor<AccountGroup, Long>() {

			@Override
			public Long get(AccountGroup accountGroup) {
				return accountGroup.getAccountGroupId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AccountGroup> getTypeClass() {
				return AccountGroup.class;
			}

		};

}