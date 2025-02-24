/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.gradle.plugins.go.internal;

import com.liferay.gradle.util.GUtil;
import com.liferay.gradle.util.GradleUtil;
import com.liferay.gradle.util.OSDetector;
import com.liferay.gradle.util.Validator;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.process.ExecSpec;
import org.gradle.util.CollectionUtils;

/**
 * @author Peter Shin
 */
public class GoExecutor {

	public GoExecutor(Project project) {
		_project = project;
	}

	public GoExecutor args(Iterable<?> args) {
		GUtil.addToCollection(_args, args);

		return this;
	}

	public GoExecutor args(Object... args) {
		return args(Arrays.asList(args));
	}

	public GoExecutor environment(Map<?, ?> environment) {
		_environment.putAll(environment);

		return this;
	}

	public GoExecutor environment(Object key, Object value) {
		_environment.put(key, value);

		return this;
	}

	public void execute() throws Exception {
		File workingDir = getWorkingDir();

		workingDir.mkdirs();

		if (isUseGradleExec()) {
			_executeGradleExec();
		}
		else {
			_executeProcessBuilder();
		}
	}

	public List<Object> getArgs() {
		return _args;
	}

	public String getCommand() {
		return GradleUtil.toString(_command);
	}

	public Map<?, ?> getEnvironment() {
		return _environment;
	}

	public File getGoDir() {
		return GradleUtil.toFile(_project, _goDir);
	}

	public File getWorkingDir() {
		return GradleUtil.toFile(_project, _workingDir);
	}

	public boolean isInheritProxy() {
		return _inheritProxy;
	}

	public boolean isUseGradleExec() {
		return _useGradleExec;
	}

	public void setArgs(Iterable<?> args) {
		_args.clear();

		args(args);
	}

	public void setArgs(Object... args) {
		setArgs(Arrays.asList(args));
	}

	public void setCommand(Object command) {
		_command = command;
	}

	public void setEnvironment(Map<?, ?> environment) {
		_environment.clear();

		environment(environment);
	}

	public void setGoDir(Object goDir) {
		_goDir = goDir;
	}

	public void setInheritProxy(boolean inheritProxy) {
		_inheritProxy = inheritProxy;
	}

	public void setUseGradleExec(boolean useGradleExec) {
		_useGradleExec = useGradleExec;
	}

	public void setWorkingDir(Object workingDir) {
		_workingDir = workingDir;
	}

	private void _executeGradleExec() {
		_project.exec(
			new Action<ExecSpec>() {

				@Override
				public void execute(ExecSpec execSpec) {
					execSpec.setCommandLine(_getCommandLine());
					execSpec.setEnvironment(
						_getEnvironment(execSpec.getEnvironment()));
					execSpec.setWorkingDir(getWorkingDir());
				}

			});
	}

	private void _executeProcessBuilder() throws Exception {
		ProcessBuilder processBuilder = new ProcessBuilder(_getCommandLine());

		processBuilder.directory(getWorkingDir());
		processBuilder.inheritIO();

		_updateEnvironment(processBuilder.environment());

		if (_logger.isInfoEnabled()) {
			_logger.info(
				"Running {} from {} with environment variables {}",
				processBuilder.command(), processBuilder.directory(),
				processBuilder.environment());
		}

		Process process = processBuilder.start();

		int exitValue = process.waitFor();

		if (exitValue != 0) {
			throw new IOException(
				"Process '" + processBuilder.command() +
					"' finished with non-zero exit value " + exitValue);
		}
	}

	private List<String> _getCommandLine() {
		List<String> commandLine = new ArrayList<>();

		if (OSDetector.isWindows()) {
			commandLine.add("cmd");
			commandLine.addAll(_getWindowsArgs());
		}
		else {
			commandLine.add(_getExecutable());
			commandLine.addAll(GradleUtil.toStringList(getArgs()));
		}

		return commandLine;
	}

	private Map<String, String> _getEnvironment(Map<?, ?> environment) {
		Map<String, String> newEnvironment = new HashMap<>();

		GUtil.addToMap(newEnvironment, environment);

		_updateEnvironment(newEnvironment);

		return newEnvironment;
	}

	private String _getExecutable() {
		String executable = GradleUtil.toString(_command);

		File executableDir = _getExecutableDir();

		if (executableDir != null) {
			File executableFile = new File(executableDir, executable);

			executable = executableFile.getAbsolutePath();
		}

		return executable;
	}

	private File _getExecutableDir() {
		File goDir = getGoDir();

		if (goDir == null) {
			return null;
		}

		return new File(goDir, "bin");
	}

	private List<String> _getWindowsArgs() {
		List<String> windowsArgs = new ArrayList<>(2);

		windowsArgs.add("/c");

		StringBuilder sb = new StringBuilder();

		sb.append('"');

		String executable = _getExecutable();

		if (executable.indexOf(File.separatorChar) == -1) {
			sb.append(executable);
		}
		else {
			sb.append('"');
			sb.append(executable);
			sb.append('"');
		}

		List<String> args = GradleUtil.toStringList(getArgs());

		for (String arg : args) {
			sb.append(" \"");

			if (Validator.isNotNull(arg)) {
				sb.append(arg);
			}

			sb.append('"');
		}

		sb.append('"');

		windowsArgs.add(sb.toString());

		return windowsArgs;
	}

	private void _setNonproxyHosts(Map<String, String> environment) {
		if (environment.containsKey(_NO_PROXY_KEY) ||
			environment.containsKey(_NO_PROXY_KEY.toUpperCase())) {

			if (_logger.isInfoEnabled()) {
				_logger.info("Non-proxy hosts are already set");
			}

			return;
		}

		Set<String> nonProxyHosts = new LinkedHashSet<>();

		String hosts = System.getProperty("http.nonProxyHosts");

		if (Validator.isNotNull(hosts)) {
			Collections.addAll(nonProxyHosts, hosts.split("\\|"));
		}

		hosts = System.getProperty("https.nonProxyHosts");

		if (Validator.isNotNull(hosts)) {
			Collections.addAll(nonProxyHosts, hosts.split("\\|"));
		}

		if (nonProxyHosts.isEmpty()) {
			return;
		}

		hosts = CollectionUtils.join(",", nonProxyHosts);

		environment.put(_NO_PROXY_KEY, hosts);

		if (_logger.isInfoEnabled()) {
			_logger.info("Non-proxy hosts set to {}", hosts);
		}
	}

	private void _setProxy(Map<String, String> environment, String protocol) {
		String key = protocol + "_proxy";

		if (environment.containsKey(key) ||
			environment.containsKey(key.toUpperCase())) {

			if (_logger.isInfoEnabled()) {
				_logger.info("{} proxy is already set", protocol.toUpperCase());
			}

			return;
		}

		String host = System.getProperty(protocol + ".proxyHost");
		String port = System.getProperty(protocol + ".proxyPort");

		if (Validator.isNull(host) || Validator.isNull(port)) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		sb.append(protocol);
		sb.append("://");

		String user = System.getProperty(protocol + ".proxyUser");

		if (Validator.isNotNull(user)) {
			sb.append(user);

			String password = System.getProperty(protocol + ".proxyPassword");

			if (Validator.isNotNull(password)) {
				sb.append(':');
				sb.append(password);
				sb.append('@');
			}
		}

		sb.append(host);
		sb.append(':');
		sb.append(port);

		String url = sb.toString();

		if (_logger.isInfoEnabled()) {
			_logger.info("{} proxy set to {}", protocol.toUpperCase(), url);
		}

		environment.put(key, sb.toString());
	}

	private void _updateEnvironment(Map<String, String> environment) {
		GUtil.addToMap(environment, getEnvironment());

		if (isInheritProxy()) {
			_setNonproxyHosts(environment);
			_setProxy(environment, "http");
			_setProxy(environment, "https");
		}

		File executableDir = _getExecutableDir();

		if (executableDir == null) {
			return;
		}

		for (String pathKey : _PATH_KEYS) {
			String path = environment.get(pathKey);

			if (Validator.isNull(path)) {
				continue;
			}

			path = executableDir.getAbsolutePath() + File.pathSeparator + path;

			environment.put(pathKey, path);
		}

		environment.put("GOBIN", executableDir.getAbsolutePath());
		environment.put("GOROOT", getGoDir().getAbsolutePath());

		File goDir = getGoDir();

		File goParentDir = goDir.getParentFile();

		if (goParentDir == null) {
			goParentDir = _project.getProjectDir();
		}

		File dir = new File(goParentDir, "go-cache");

		environment.put("GOCACHE", dir.getAbsolutePath());

		dir = new File(goParentDir, "go-work");

		environment.put("GOPATH", dir.getAbsolutePath());
	}

	private static final String _NO_PROXY_KEY = "no_proxy";

	private static final String[] _PATH_KEYS = {"Path", "PATH"};

	private static final Logger _logger = Logging.getLogger(GoExecutor.class);

	private final List<Object> _args = new ArrayList<>();
	private Object _command = "go";
	private final Map<Object, Object> _environment = new LinkedHashMap<>();
	private Object _goDir;
	private boolean _inheritProxy = true;
	private final Project _project;
	private boolean _useGradleExec;
	private Object _workingDir;

}