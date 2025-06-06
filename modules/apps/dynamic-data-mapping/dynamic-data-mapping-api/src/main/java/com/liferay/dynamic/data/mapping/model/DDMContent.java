/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the DDMContent service. Represents a row in the &quot;DDMContent&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DDMContentModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.dynamic.data.mapping.model.impl.DDMContentImpl"
)
@ProviderType
public interface DDMContent extends DDMContentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.dynamic.data.mapping.model.impl.DDMContentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DDMContent, Long> CONTENT_ID_ACCESSOR =
		new Accessor<DDMContent, Long>() {

			@Override
			public Long get(DDMContent ddmContent) {
				return ddmContent.getContentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DDMContent> getTypeClass() {
				return DDMContent.class;
			}

		};

}