/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.message.body;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * @author Alejandro Hernández
 * @author Ivica Cardic
 */
@Produces(MediaType.APPLICATION_JSON)
@Provider
public class JSONMessageBodyWriter extends BaseMessageBodyWriter {

	public JSONMessageBodyWriter() {
		super(ObjectMapper.class, MediaType.APPLICATION_JSON_TYPE);
	}

}