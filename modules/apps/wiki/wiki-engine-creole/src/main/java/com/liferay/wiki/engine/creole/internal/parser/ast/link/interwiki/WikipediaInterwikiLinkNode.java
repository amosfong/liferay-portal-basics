/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.creole.internal.parser.ast.link.interwiki;

import com.liferay.wiki.engine.creole.internal.parser.visitor.ASTVisitor;

/**
 * @author Miguel Pastor
 */
public class WikipediaInterwikiLinkNode extends InterwikiLinkNode {

	@Override
	public void accept(ASTVisitor astVisitor) {
		astVisitor.visit(this);
	}

	@Override
	public String getBaseURL() {
		return "http://wikipedia.org/wiki/";
	}

}