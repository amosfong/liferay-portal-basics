/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.io;

/**
 * @author Leonardo Barros
 */
public final class DDMFormFieldTypesSerializerSerializeResponse {

	public String getContent() {
		return _content;
	}

	public static class Builder {

		public static Builder newBuilder(String content) {
			return new Builder(content);
		}

		public DDMFormFieldTypesSerializerSerializeResponse build() {
			return _ddmFormFieldTypesSerializerSerializeResponse;
		}

		private Builder(String content) {
			_ddmFormFieldTypesSerializerSerializeResponse._content = content;
		}

		private final DDMFormFieldTypesSerializerSerializeResponse
			_ddmFormFieldTypesSerializerSerializeResponse =
				new DDMFormFieldTypesSerializerSerializeResponse();

	}

	private DDMFormFieldTypesSerializerSerializeResponse() {
	}

	private String _content;

}