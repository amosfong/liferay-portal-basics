/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the KBTemplate service. Represents a row in the &quot;KBTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplateModel
 * @generated
 */
@ImplementationClassName("com.liferay.knowledge.base.model.impl.KBTemplateImpl")
@ProviderType
public interface KBTemplate extends KBTemplateModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.knowledge.base.model.impl.KBTemplateImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<KBTemplate, Long> KB_TEMPLATE_ID_ACCESSOR =
		new Accessor<KBTemplate, Long>() {

			@Override
			public Long get(KBTemplate kbTemplate) {
				return kbTemplate.getKbTemplateId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<KBTemplate> getTypeClass() {
				return KBTemplate.class;
			}

		};

}