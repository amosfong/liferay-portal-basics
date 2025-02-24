/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.customer;

import com.liferay.client.extension.util.spring.boot.ClientExtensionUtilSpringBootComponentScan;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

/**
 * @author Amos Fong
 */
@Import(ClientExtensionUtilSpringBootComponentScan.class)
@SpringBootApplication
public class CustomerSpringBootApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(
			CustomerSpringBootApplication.class
		).web(
			WebApplicationType.NONE
		).run(
			args
		);
	}

}