/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.document.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Bryan Engler
 */
@ExtendedObjectClassDefinition(generateUI = false)
@Meta.OCD(
	id = "com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.document.configuration.BulkDocumentRequestRetryConfiguration"
)
public interface BulkDocumentRequestRetryConfiguration {

	@Meta.AD(deflt = "0", required = false)
	public int numberOfTries();

	@Meta.AD(deflt = "60", required = false)
	public int waitInSeconds();

}