/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.image;

import java.awt.image.RenderedImage;

import java.util.concurrent.Future;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Guilherme Camacho
 */
@ProviderType
public interface CMYKImageTool {

	public Future<RenderedImage> convertCMYKtoRGB(byte[] bytes, String type);

}