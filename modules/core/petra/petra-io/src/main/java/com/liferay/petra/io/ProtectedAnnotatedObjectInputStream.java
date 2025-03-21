/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.io;

import com.liferay.petra.lang.ClassLoaderPool;
import com.liferay.petra.lang.ClassResolverUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamClass;

/**
 * @author Shuyang Zhou
 */
public class ProtectedAnnotatedObjectInputStream
	extends ProtectedObjectInputStream {

	public ProtectedAnnotatedObjectInputStream(InputStream inputStream)
		throws IOException {

		super(inputStream);
	}

	@Override
	protected Class<?> doResolveClass(ObjectStreamClass objectStreamClass)
		throws ClassNotFoundException, IOException {

		String contextName = readUTF();

		ClassLoader classLoader = ClassLoaderPool.getClassLoader(contextName);

		String className = objectStreamClass.getName();

		return ClassResolverUtil.resolve(className, classLoader);
	}

}