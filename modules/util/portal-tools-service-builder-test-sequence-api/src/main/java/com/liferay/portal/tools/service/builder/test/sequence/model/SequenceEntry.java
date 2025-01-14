/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.sequence.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the SequenceEntry service. Represents a row in the &quot;SequenceEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SequenceEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.portal.tools.service.builder.test.sequence.model.impl.SequenceEntryImpl"
)
@ProviderType
public interface SequenceEntry extends PersistedModel, SequenceEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.tools.service.builder.test.sequence.model.impl.SequenceEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SequenceEntry, Long>
		SEQUENCE_ENTRY_ID_ACCESSOR = new Accessor<SequenceEntry, Long>() {

			@Override
			public Long get(SequenceEntry sequenceEntry) {
				return sequenceEntry.getSequenceEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SequenceEntry> getTypeClass() {
				return SequenceEntry.class;
			}

		};

}