/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.internal.validator.util;

import java.util.Collections;

import org.apache.commons.validator.routines.DomainValidator;

/**
 * @author Drew Brokke
 */
public class DomainValidatorFactoryUtil {

	public static DomainValidator create(String[] customTLDs) {
		return DomainValidator.getInstance(
			false,
			Collections.singletonList(
				new DomainValidator.Item(
					DomainValidator.ArrayType.GENERIC_PLUS, customTLDs)));
	}

}