/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the LayoutClassedModelUsage service. Represents a row in the &quot;LayoutClassedModelUsage&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutClassedModelUsageModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.layout.model.impl.LayoutClassedModelUsageImpl"
)
@ProviderType
public interface LayoutClassedModelUsage
	extends LayoutClassedModelUsageModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.layout.model.impl.LayoutClassedModelUsageImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LayoutClassedModelUsage, Long>
		LAYOUT_CLASSED_MODEL_USAGE_ID_ACCESSOR =
			new Accessor<LayoutClassedModelUsage, Long>() {

				@Override
				public Long get(
					LayoutClassedModelUsage layoutClassedModelUsage) {

					return layoutClassedModelUsage.
						getLayoutClassedModelUsageId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<LayoutClassedModelUsage> getTypeClass() {
					return LayoutClassedModelUsage.class;
				}

			};

}