/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.util;

import java.io.File;
import java.io.InputStream;

/**
 * @author Adolfo Pérez
 */
public interface AudioConverter {

	public InputStream generateAudioPreview(File file, String format)
		throws Exception;

	public boolean isEnabled();

}