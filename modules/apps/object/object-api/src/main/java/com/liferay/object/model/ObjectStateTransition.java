/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ObjectStateTransition service. Represents a row in the &quot;ObjectStateTransition&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see ObjectStateTransitionModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.object.model.impl.ObjectStateTransitionImpl"
)
@ProviderType
public interface ObjectStateTransition
	extends ObjectStateTransitionModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.object.model.impl.ObjectStateTransitionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ObjectStateTransition, Long>
		OBJECT_STATE_TRANSITION_ID_ACCESSOR =
			new Accessor<ObjectStateTransition, Long>() {

				@Override
				public Long get(ObjectStateTransition objectStateTransition) {
					return objectStateTransition.getObjectStateTransitionId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<ObjectStateTransition> getTypeClass() {
					return ObjectStateTransition.class;
				}

			};

	public long getTargetObjectStateListTypeEntryId();

	public void setTargetObjectStateListTypeEntryId(
		long targetObjectStateListTypeEntryId);

}