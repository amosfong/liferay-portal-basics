/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.evp;

import com.liferay.client.extension.util.spring.boot.BaseRestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Elvison Victor
 */
@RequestMapping("/ready")
@RestController
public class ReadyRestController extends BaseRestController {

	@GetMapping
	public String get() {
		return "READY";
	}

}