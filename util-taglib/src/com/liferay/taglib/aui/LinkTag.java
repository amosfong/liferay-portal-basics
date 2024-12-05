/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.aui;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.content.security.policy.ContentSecurityPolicyNonceProviderUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.aui.base.BaseLinkTag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * @author Iván Zaera Avellón
 */
public class LinkTag extends BaseLinkTag {

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter jspWriter = pageContext.getOut();

			jspWriter.write("<link");

			String cssClass = getCssClass();

			if (Validator.isNotNull(cssClass)) {
				_write(jspWriter, "class", cssClass);
			}

			String crossOrigin = getCrossOrigin();

			if (Validator.isNotNull(crossOrigin)) {
				_write(jspWriter, "crossorigin", crossOrigin);
			}

			String senna = getSenna();

			if (Validator.isNotNull(senna)) {
				_write(jspWriter, "data-senna-track", senna);
			}

			String href = getHref();

			if (Validator.isNotNull(href)) {
				_write(jspWriter, "href", href);
			}

			String integrity = getIntegrity();

			if (Validator.isNotNull(integrity)) {
				_write(jspWriter, "integrity", integrity);
			}

			String id = getId();

			if (Validator.isNotNull(id)) {
				_write(jspWriter, "id", id);
			}

			jspWriter.write(
				ContentSecurityPolicyNonceProviderUtil.getNonceAttribute(
					getRequest()));

			String rel = getRel();

			if (Validator.isNotNull(rel)) {
				_write(jspWriter, "rel", rel);
			}

			String type = getType();

			if (Validator.isNotNull(type)) {
				_write(jspWriter, "type", type);
			}

			jspWriter.write(" />");

			return EVAL_PAGE;
		}
		catch (IOException ioException) {
			throw new JspException(ioException);
		}
	}

	private void _write(JspWriter jspWriter, String name, String value)
		throws IOException {

		if (Validator.isNotNull(value)) {
			jspWriter.write(StringPool.SPACE);
			jspWriter.write(name);
			jspWriter.write("=\"");
			jspWriter.write(value);
			jspWriter.write(StringPool.QUOTE);
		}
	}

}