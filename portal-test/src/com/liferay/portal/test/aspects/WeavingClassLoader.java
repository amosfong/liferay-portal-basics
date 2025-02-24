/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.aspects;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLClassLoader;

import java.security.ProtectionDomain;

import java.util.Arrays;

import org.aspectj.bridge.AbortException;

/**
 * @author Shuyang Zhou
 */
public class WeavingClassLoader extends URLClassLoader {

	public WeavingClassLoader(
		URL[] urls, Class<?>[] aspectClasses, File dumpDir) {

		super(urls, null);

		_dumpDir = dumpDir;

		_urlWeavingAdapter = new URLWeavingAdapter(urls, aspectClasses);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String resourcePath = StringUtil.replace(name, '.', '/') + ".class";

		InputStream inputStream = getResourceAsStream(resourcePath);

		byte[] data = null;

		try {
			if (inputStream == null) {

				// It may be a generated inner class

				data = _urlWeavingAdapter.removeGeneratedClassDate(name);
			}
			else {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
					new UnsyncByteArrayOutputStream();

				StreamUtil.transfer(inputStream, unsyncByteArrayOutputStream);

				data = unsyncByteArrayOutputStream.toByteArray();
			}

			if (data == null) {
				throw new ClassNotFoundException(name);
			}

			byte[] oldData = data;

			try {
				data = _urlWeavingAdapter.weaveClass(name, data, false);
			}
			catch (AbortException abortException) {
				if (_log.isWarnEnabled()) {
					_log.warn("Abort weaving class " + name, abortException);
				}
			}

			if (Arrays.equals(oldData, data)) {
				return _generateClass(name, data);
			}

			if (_dumpDir != null) {
				File dumpFile = new File(_dumpDir, resourcePath);

				File dumpDir = dumpFile.getParentFile();

				dumpDir.mkdirs();

				try (FileOutputStream fileOutputStream = new FileOutputStream(
						dumpFile)) {

					fileOutputStream.write(data);
				}

				if (_log.isInfoEnabled()) {
					_log.info(
						StringBundler.concat(
							"Woven class ", name, " result in ",
							dumpFile.getCanonicalPath()));
				}
			}
			else {
				if (_log.isInfoEnabled()) {
					_log.info("Woven class " + name);
				}
			}

			return _generateClass(name, data);
		}
		catch (IOException ioException) {
			throw new ClassNotFoundException(name, ioException);
		}
	}

	private Class<?> _generateClass(String name, byte[] data) {
		Class<?> clazz = defineClass(
			name, data, 0, data.length, (ProtectionDomain)null);

		String packageName = null;

		int index = name.lastIndexOf('.');

		if (index != -1) {
			packageName = name.substring(0, index);
		}

		if (packageName != null) {
			Package pkg = getPackage(packageName);

			if (pkg == null) {
				definePackage(
					packageName, null, null, null, null, null, null, null);
			}
		}

		return clazz;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WeavingClassLoader.class);

	private final File _dumpDir;
	private final URLWeavingAdapter _urlWeavingAdapter;

}