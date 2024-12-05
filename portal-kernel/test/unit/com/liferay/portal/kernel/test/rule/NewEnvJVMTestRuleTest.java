/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.test.rule;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
@NewEnv(type = NewEnv.Type.JVM)
@NewEnv.Environment(variables = "ENV_KEY=ENV_VALUE")
@NewEnv.JVMArgsLine("-Dkey1=default1 -Dkey2=default2")
public class NewEnvJVMTestRuleTest {

	@BeforeClass
	public static void setUpClass() {
		System.setProperty(
			_SYSTEM_PROPERTY_KEY_ENVIRONMENT, _toString(_getEnvironment()));
	}

	@Before
	public void setUp() {
		Assert.assertEquals(0, _counter.getAndIncrement());
		Assert.assertNull(_processId);

		_processId = getProcessId();
	}

	@After
	public void tearDown() {
		Assert.assertEquals(2, _counter.getAndIncrement());

		assertProcessId();
	}

	@NewEnv.JVMArgsLine(
		"-Dparent.java.locale.providers=${java.locale.providers}"
	)
	@Test
	public void testDefaultJavaLocaleProviders() {
		_counter.getAndIncrement();

		Assert.assertEquals(
			System.getProperty("java.locale.providers"),
			System.getProperty("parent.java.locale.providers"));
	}

	@Test
	public void testNewJVM1() {
		Assert.assertEquals(1, _counter.getAndIncrement());

		assertProcessId();

		Assert.assertEquals("default1", System.getProperty("key1"));
		Assert.assertEquals("default2", System.getProperty("key2"));
		Assert.assertNull(System.getProperty("key3"));
	}

	@NewEnv.JVMArgsLine("-Dkey1=value1")
	@Test
	public void testNewJVM2() {
		Assert.assertEquals(1, _counter.getAndIncrement());

		assertProcessId();

		Assert.assertEquals("value1", System.getProperty("key1"));
		Assert.assertEquals("default2", System.getProperty("key2"));
		Assert.assertNull(System.getProperty("key3"));
	}

	@NewEnv.JVMArgsLine("-Dkey2=value2")
	@Test
	public void testNewJVM3() {
		Assert.assertEquals(1, _counter.getAndIncrement());

		assertProcessId();

		Assert.assertEquals("default1", System.getProperty("key1"));
		Assert.assertEquals("value2", System.getProperty("key2"));
		Assert.assertNull(System.getProperty("key3"));
	}

	@NewEnv.JVMArgsLine("-Dkey1=value1 -Dkey2=value2")
	@Test
	public void testNewJVM4() {
		Assert.assertEquals(1, _counter.getAndIncrement());

		assertProcessId();

		Assert.assertEquals("value1", System.getProperty("key1"));
		Assert.assertEquals("value2", System.getProperty("key2"));
		Assert.assertNull(System.getProperty("key3"));
	}

	@NewEnv.JVMArgsLine("-Dkey1=value1 -Dkey2=value2 -Dkey3=value3")
	@Test
	public void testNewJVM5() {
		Assert.assertEquals(1, _counter.getAndIncrement());

		assertProcessId();

		Assert.assertEquals("value1", System.getProperty("key1"));
		Assert.assertEquals("value2", System.getProperty("key2"));
		Assert.assertEquals("value3", System.getProperty("key3"));
	}

	@NewEnv.Environment(variables = {})
	@NewEnv.JVMArgsLine(
		"-D" + _SYSTEM_PROPERTY_KEY_ENVIRONMENT + "=${" +
			_SYSTEM_PROPERTY_KEY_ENVIRONMENT + "}"
	)
	@Test
	public void testNewJVM6() {
		Assert.assertEquals(1, _counter.getAndIncrement());

		assertProcessId();

		Map<String, String> environment = _getEnvironment();

		Assert.assertEquals("ENV_VALUE", environment.get("ENV_KEY"));

		Map<String, String> parentEnvironment = _fromString(
			System.getProperty(_SYSTEM_PROPERTY_KEY_ENVIRONMENT));

		parentEnvironment.put("ENV_KEY", "ENV_VALUE");

		Assert.assertEquals(parentEnvironment, environment);
	}

	@NewEnv.Environment(variables = {"USER=UNIT_TEST", "ENV_KEY=NEW_VALUE"})
	@NewEnv.JVMArgsLine(
		"-D" + _SYSTEM_PROPERTY_KEY_ENVIRONMENT + "=${" +
			_SYSTEM_PROPERTY_KEY_ENVIRONMENT + "}"
	)
	@Test
	public void testNewJVM7() {
		Assert.assertEquals(1, _counter.getAndIncrement());

		assertProcessId();

		Map<String, String> environment = _getEnvironment();

		Assert.assertEquals(
			"UNIT_TEST", environment.get(_ENVIRONMENT_KEY_USER));
		Assert.assertEquals("NEW_VALUE", environment.get("ENV_KEY"));

		Map<String, String> parentEnvironment = _fromString(
			System.getProperty(_SYSTEM_PROPERTY_KEY_ENVIRONMENT));

		parentEnvironment.put(_ENVIRONMENT_KEY_USER, "UNIT_TEST");
		parentEnvironment.put("ENV_KEY", "NEW_VALUE");

		Assert.assertEquals(parentEnvironment, environment);
	}

	@NewEnv.Environment(append = false, variables = "KEY1=VALUE1")
	@NewEnv.JVMArgsLine(
		"-D" + _SYSTEM_PROPERTY_KEY_ENVIRONMENT + "=${" +
			_SYSTEM_PROPERTY_KEY_ENVIRONMENT + "}"
	)
	@Test
	public void testNewJVM8() {
		Assert.assertEquals(1, _counter.getAndIncrement());

		assertProcessId();

		Map<String, String> environment = _getEnvironment();

		Assert.assertEquals("VALUE1", environment.get("KEY1"));
		Assert.assertNull(environment.get("ENV_KEY"));
		Assert.assertNull(environment.get(_ENVIRONMENT_KEY_USER));

		Map<String, String> parentEnvironment = _fromString(
			System.getProperty(_SYSTEM_PROPERTY_KEY_ENVIRONMENT));

		parentEnvironment.put("KEY1", "VALUE1");

		Assert.assertNotEquals(parentEnvironment, environment);
	}

	@NewEnv.JVMArgsLine("-Dparent.java.home=${java.home}")
	@Test
	public void testNewJVM9() {
		Assert.assertEquals(1, _counter.getAndIncrement());

		assertProcessId();

		Assert.assertEquals(
			System.getProperty("parent.java.home"),
			System.getProperty("java.home"));
	}

	@Rule
	public final NewEnvTestRule newEnvTestRule = NewEnvTestRule.INSTANCE;

	protected void assertProcessId() {
		Assert.assertNotNull(_processId);

		Assert.assertEquals(_processId.intValue(), getProcessId());
	}

	protected int getProcessId() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

		String name = runtimeMXBean.getName();

		int index = name.indexOf(CharPool.AT);

		if (index == -1) {
			throw new RuntimeException("Unable to parse process name " + name);
		}

		int pid = GetterUtil.getInteger(name.substring(0, index));

		if (pid == 0) {
			throw new RuntimeException("Unable to parse process name " + name);
		}

		return pid;
	}

	private static Map<String, String> _getEnvironment() {
		Map<String, String> environment = new HashMap<>(System.getenv());

		environment.remove("CACHE_DIR");
		environment.remove("MODULES_BASE_DIR_NAMES_WITH_CHANGES");
		environment.remove("PROJECT_NAMES");
		environment.remove("PORTAL_BUNDLES_DIST_URL");
		environment.remove("SUBREPOSITORY_PACKAGE_NAMES");
		environment.remove("TERMCAP");

		return environment;
	}

	private static String _toString(Map<String, String> map) {
		StringBundler sb = new StringBundler();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey());
			sb.append(_SEPARATOR_KEY_VALUE);
			sb.append(entry.getValue());
			sb.append(_SEPARATOR_VARIABLE);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	private Map<String, String> _fromString(String s) {
		Map<String, String> map = new HashMap<>();

		for (String entry : s.split(_SEPARATOR_VARIABLE)) {
			String[] parts = entry.split(_SEPARATOR_KEY_VALUE);

			if (parts.length == 1) {
				map.put(parts[0], StringPool.BLANK);
			}
			else {
				map.put(parts[0], parts[1]);
			}
		}

		return map;
	}

	private static final String _ENVIRONMENT_KEY_USER = "USER";

	private static final String _SEPARATOR_KEY_VALUE = "_SEPARATOR_KEY_VALUE_";

	private static final String _SEPARATOR_VARIABLE = "_SEPARATOR_VARIABLE_";

	private static final String _SYSTEM_PROPERTY_KEY_ENVIRONMENT =
		"KEY_ENVIRONMENT";

	private final AtomicInteger _counter = new AtomicInteger();
	private Integer _processId;

}