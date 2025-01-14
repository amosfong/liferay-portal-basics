/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.css.builder.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.Assert;

/**
 * @author Andrea Di Giorgi
 */
public class FileTestUtil {

	public static void changeContentInPath(
			Path path, String s, String replacement)
		throws IOException {

		Charset charset = StandardCharsets.UTF_8;

		String content = new String(Files.readAllBytes(path), charset);

		content = content.replace(s, replacement);

		Files.write(path, content.getBytes(charset));
	}

	public static void copyDir(Path rootDirPath, Path rootDestinationDirPath)
		throws IOException {

		Files.createDirectories(rootDestinationDirPath);

		Files.walkFileTree(
			rootDirPath,
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(
						Path dirPath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					Path relativePath = rootDirPath.relativize(dirPath);

					Files.createDirectories(
						rootDestinationDirPath.resolve(relativePath));

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(
						Path path, BasicFileAttributes basicFileAttributes)
					throws IOException {

					Path relativePath = rootDirPath.relativize(path);

					Files.copy(
						path, rootDestinationDirPath.resolve(relativePath));

					return FileVisitResult.CONTINUE;
				}

			});
	}

	public static File createFile(File dir, String fileName)
		throws IOException {

		File file = new File(dir, fileName);

		File parentDir = file.getParentFile();

		Assert.assertTrue(
			"Unable to create " + parentDir,
			parentDir.exists() || parentDir.mkdirs());

		Assert.assertTrue("Unable to create " + file, file.createNewFile());

		return file;
	}

	public static void createFiles(File dir, String... fileNames)
		throws IOException {

		for (String fileName : fileNames) {
			createFile(dir, fileName);
		}
	}

	public static String read(Class<?> clazz, String name) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		try (InputStream inputStream = clazz.getResourceAsStream(name)) {
			byte[] bytes = new byte[1024];
			int length = 0;

			while ((length = inputStream.read(bytes)) > 0) {
				byteArrayOutputStream.write(bytes, 0, length);
			}
		}

		return byteArrayOutputStream.toString(StandardCharsets.UTF_8.name());
	}

	public static String read(Path path) throws IOException {
		String s = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

		return s.replace("\r\n", "\n");
	}

}