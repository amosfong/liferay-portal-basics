/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.project.templates.service.builder.internal;

import com.liferay.project.templates.extensions.ProjectTemplateCustomizer;
import com.liferay.project.templates.extensions.ProjectTemplatesArgs;
import com.liferay.project.templates.extensions.util.FileUtil;
import com.liferay.project.templates.extensions.util.VersionUtil;
import com.liferay.project.templates.extensions.util.WorkspaceUtil;

import java.io.File;

import java.nio.file.Path;

import java.util.Properties;

import org.apache.maven.archetype.ArchetypeGenerationRequest;
import org.apache.maven.archetype.ArchetypeGenerationResult;

/**
 * @author Gregory Amerson
 */
public class ServiceBuilderProjectTemplateCustomizer
	implements ProjectTemplateCustomizer {

	@Override
	public String getTemplateName() {
		return "service-builder";
	}

	@Override
	public void onAfterGenerateProject(
			ProjectTemplatesArgs projectTemplatesArgs, File destinationDir,
			ArchetypeGenerationResult archetypeGenerationResult)
		throws Exception {

		ServiceBuilderProjectTemplatesArgs serviceBuilderProjectTemplatesArgs =
			(ServiceBuilderProjectTemplatesArgs)
				projectTemplatesArgs.getProjectTemplatesArgsExt();

		String addOns = serviceBuilderProjectTemplatesArgs.getAddOns();

		Path destinationDirPath = destinationDir.toPath();

		Path projectPath = destinationDirPath.resolve(
			projectTemplatesArgs.getName());

		File projectDir = projectPath.toFile();

		String artifactId = projectTemplatesArgs.getName();

		if (addOns.equals("false")) {
			File uadDir = new File(projectDir, artifactId + "-uad");

			FileUtil.deleteDir(uadDir.toPath());
		}

		File serviceDir = new File(projectDir, artifactId + "-service");

		File serviceXMLFile = new File(serviceDir, "service.xml");

		String liferayVersion = projectTemplatesArgs.getLiferayVersion();

		String minorVersionString = String.valueOf(
			VersionUtil.getMinorVersion(liferayVersion));

		if (VersionUtil.isLiferayQuarterlyVersion(liferayVersion)) {
			minorVersionString = "4";
		}

		FileUtil.replaceString(
			serviceXMLFile, "7.0", "7." + minorVersionString);
		FileUtil.replaceString(
			serviceXMLFile, "7_0", "7_" + minorVersionString);
	}

	@Override
	public void onBeforeGenerateProject(
			ProjectTemplatesArgs projectTemplatesArgs,
			ArchetypeGenerationRequest archetypeGenerationRequest)
		throws Exception {

		String artifactId = archetypeGenerationRequest.getArtifactId();

		String apiPath = ":" + artifactId + "-api";

		File destinationDir = new File(
			archetypeGenerationRequest.getOutputDirectory());

		File workspaceDir = WorkspaceUtil.getWorkspaceDir(destinationDir);

		if (workspaceDir != null) {
			Path destinationDirPath = destinationDir.toPath();
			Path workspaceDirPath = workspaceDir.toPath();

			destinationDirPath = destinationDirPath.toAbsolutePath();
			workspaceDirPath = workspaceDirPath.toAbsolutePath();

			String relativePath = String.valueOf(
				workspaceDirPath.relativize(destinationDirPath));

			relativePath = relativePath.replace(File.separatorChar, ':');

			if (relativePath.isEmpty()) {
				apiPath = ":" + artifactId + apiPath;
			}
			else {
				apiPath = ":" + relativePath + ":" + artifactId + apiPath;
			}
		}

		Properties properties = archetypeGenerationRequest.getProperties();

		setProperty(properties, "apiPath", apiPath);

		ServiceBuilderProjectTemplatesArgs serviceBuilderProjectTemplatesArgs =
			(ServiceBuilderProjectTemplatesArgs)
				projectTemplatesArgs.getProjectTemplatesArgsExt();

		String addOns = serviceBuilderProjectTemplatesArgs.getAddOns();

		String liferayVersion = projectTemplatesArgs.getLiferayVersion();

		if (addOns.equals("true") &&
			(liferayVersion.startsWith("7.0") ||
			 liferayVersion.startsWith("7.1"))) {

			throw new IllegalArgumentException(
				"Add Ons are not supported in 7.0 or 7.1");
		}

		setProperty(
			properties, "addOns",
			serviceBuilderProjectTemplatesArgs.getAddOns());
		setProperty(
			properties, "dependencyInjector",
			serviceBuilderProjectTemplatesArgs.getDependencyInjector());
	}

}