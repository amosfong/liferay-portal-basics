/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.gradle.plugins.workspace;

import com.liferay.gradle.plugins.NodeDefaultsPlugin;
import com.liferay.gradle.plugins.node.NodeExtension;
import com.liferay.gradle.plugins.node.YarnPlugin;
import com.liferay.gradle.plugins.node.task.NpmInstallTask;
import com.liferay.gradle.plugins.node.task.PackageRunTask;
import com.liferay.gradle.plugins.node.task.PackageRunTestTask;
import com.liferay.gradle.plugins.node.task.YarnInstallTask;
import com.liferay.gradle.plugins.workspace.internal.util.GradleUtil;
import com.liferay.gradle.plugins.workspace.task.SetUpYarnTask;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.TaskProvider;

/**
 * @author David Truong
 */
public class LiferayWorkspaceYarnPlugin extends YarnPlugin {

	public static final String SET_UP_YARN_TASK_NAME = "setUpYarn";

	public static final String YARN_INSTALL_TASK_NAME = "yarnInstall";

	@Override
	public void apply(Project project) {
		super.apply(project);

		GradleUtil.applyPlugin(project, NodeDefaultsPlugin.class);

		TaskProvider<SetUpYarnTask> setUpYarnTaskProvider =
			GradleUtil.addTaskProvider(
				project, SET_UP_YARN_TASK_NAME, SetUpYarnTask.class);

		TaskProvider<YarnInstallTask> yarnInstallTaskProvider =
			GradleUtil.getTaskProvider(
				project, YARN_INSTALL_TASK_NAME, YarnInstallTask.class);

		_configureTaskYarnInstallProvider(
			project, yarnInstallTaskProvider, setUpYarnTaskProvider);

		project.allprojects(
			project1 -> _configureNodeProject(
				project1, yarnInstallTaskProvider));
	}

	private void _configureNodeProject(
		Project project,
		TaskProvider<YarnInstallTask> yarnInstallTaskProvider) {

		project.afterEvaluate(
			project1 -> {
				TaskContainer taskContainer = project1.getTasks();

				taskContainer.withType(
					NpmInstallTask.class,
					npmInstallTask -> {
						NodeExtension nodeExtension = GradleUtil.getExtension(
							npmInstallTask.getProject(), NodeExtension.class);

						nodeExtension.setUseNpm(false);

						npmInstallTask.finalizedBy(yarnInstallTaskProvider);
					});
				taskContainer.withType(
					PackageRunTask.class,
					packageRunTask -> {
						if (packageRunTask instanceof PackageRunTestTask) {
							return;
						}

						packageRunTask.mustRunAfter(yarnInstallTaskProvider);
					});
			});
	}

	private void _configureTaskYarnInstallProvider(
		Project project, TaskProvider<YarnInstallTask> yarnInstallTaskProvider,
		TaskProvider<SetUpYarnTask> setUpYarnTaskProvider) {

		yarnInstallTaskProvider.configure(
			yarnInstallTask -> {
				yarnInstallTask.dependsOn(setUpYarnTaskProvider);

				try {
					File file = new File(project.getProjectDir(), "yarn.lock");

					if (file.exists()) {
						String contents = new String(
							Files.readAllBytes(file.toPath()));

						yarnInstallTask.setFrozenLockFile(!contents.equals(""));
					}
					else {
						yarnInstallTask.setFrozenLockFile(false);
					}
				}
				catch (IOException ioException) {
					Logger logger = project.getLogger();

					if (logger.isWarnEnabled()) {
						StringBuilder sb = new StringBuilder();

						sb.append("Unable to read yarn.lock.");

						logger.warn(sb.toString());
					}
				}
			});
	}

}