/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the CommerceShippingMethod service. Represents a row in the &quot;CommerceShippingMethod&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingMethodModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.model.impl.CommerceShippingMethodImpl"
)
@ProviderType
public interface CommerceShippingMethod
	extends CommerceShippingMethodModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.model.impl.CommerceShippingMethodImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceShippingMethod, Long>
		COMMERCE_SHIPPING_METHOD_ID_ACCESSOR =
			new Accessor<CommerceShippingMethod, Long>() {

				@Override
				public Long get(CommerceShippingMethod commerceShippingMethod) {
					return commerceShippingMethod.getCommerceShippingMethodId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceShippingMethod> getTypeClass() {
					return CommerceShippingMethod.class;
				}

			};

	public String getImageURL(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay);

	public com.liferay.portal.kernel.util.UnicodeProperties
		getTypeSettingsUnicodeProperties();

	public void setTypeSettingsUnicodeProperties(
		com.liferay.portal.kernel.util.UnicodeProperties
			typeSettingsUnicodeProperties);

}