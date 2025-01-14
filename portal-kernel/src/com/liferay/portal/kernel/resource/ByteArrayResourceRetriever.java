/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resource;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;

import java.io.InputStream;

/**
 * @author Shuyang Zhou
 */
public class ByteArrayResourceRetriever implements ResourceRetriever {

	public ByteArrayResourceRetriever(byte[] bytes) {
		_inputStream = new UnsyncByteArrayInputStream(bytes);
	}

	@Override
	public InputStream getInputStream() {
		return _inputStream;
	}

	private final InputStream _inputStream;

}