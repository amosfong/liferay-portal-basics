/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.gradle.plugins.jasper.jspc;

import com.liferay.gradle.util.FileUtil;
import com.liferay.gradle.util.GradleUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.JspC;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.Project;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.Classpath;
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.PathSensitive;
import org.gradle.api.tasks.PathSensitivity;
import org.gradle.api.tasks.SkipWhenEmpty;
import org.gradle.api.tasks.TaskAction;

/**
 * @author Andrea Di Giorgi
 */
@CacheableTask
public class CompileJSPTask extends DefaultTask {

	@TaskAction
	public void compileJSP() {
		FileCollection jspCClasspath = getJspCClasspath();

		JspC jspC = new JspC();

		try {
			jspC.setArgs(_getCompleteArgs());
			jspC.setClassPath(jspCClasspath.getAsPath());

			jspC.execute();
		}
		catch (Exception exception) {
			throw new GradleException(exception.getMessage(), exception);
		}
	}

	@OutputDirectory
	public File getDestinationDir() {
		return GradleUtil.toFile(getProject(), _destinationDir);
	}

	@Classpath
	public FileCollection getJspCClasspath() {
		return _jspCClasspath;
	}

	@InputFiles
	@PathSensitive(PathSensitivity.RELATIVE)
	@SkipWhenEmpty
	public FileCollection getJSPFiles() {
		Project project = getProject();

		Map<String, Object> args = new HashMap<>();

		args.put("dir", getWebAppDir());

		List<String> excludes = new ArrayList<>(2);

		excludes.add("**/custom_jsps/**/*");
		excludes.add("**/dependencies/**/*");

		args.put("excludes", excludes);

		args.put("include", "**/*.jsp");

		return project.fileTree(args);
	}

	@InputDirectory
	@PathSensitive(PathSensitivity.RELATIVE)
	public File getWebAppDir() {
		return GradleUtil.toFile(getProject(), _webAppDir);
	}

	public void setDestinationDir(Object destinationDir) {
		_destinationDir = destinationDir;
	}

	public void setJspCClasspath(FileCollection jspCClasspath) {
		_jspCClasspath = jspCClasspath;
	}

	public void setWebAppDir(Object webAppDir) {
		_webAppDir = webAppDir;
	}

	private String[] _getCompleteArgs() {
		return new String[] {
			"-d", FileUtil.getAbsolutePath(getDestinationDir()),
			"-disablePooling", "-webapp",
			FileUtil.getAbsolutePath(getWebAppDir())
		};
	}

	private Object _destinationDir;
	private FileCollection _jspCClasspath;
	private Object _webAppDir;

}