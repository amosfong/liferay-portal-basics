/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.test;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.Arrays;

/**
 * @author Shuyang Zhou
 */
public class ReflectionTestUtil {

	public static <T> T getAndSetFieldValue(
		Class<?> clazz, String fieldName, T newValue) {

		Field field = getField(clazz, fieldName);

		try {
			T t = (T)field.get(null);

			setFieldValue(field, (Object)null, newValue);

			return t;
		}
		catch (Throwable throwable) {
			return ReflectionUtil.throwException(throwable);
		}
	}

	public static <T> T getAndSetFieldValue(
		Object instance, String fieldName, T newValue) {

		Field field = getField(instance.getClass(), fieldName);

		try {
			T t = (T)field.get(instance);

			setFieldValue(field, instance, newValue);

			return t;
		}
		catch (Throwable throwable) {
			return ReflectionUtil.throwException(throwable);
		}
	}

	public static Method getBridgeMethod(
		Class<?> clazz, String methodName, Class<?>... parameterTypes) {

		Method method = getMethod(clazz, methodName, parameterTypes);

		if (method.isBridge()) {
			return method;
		}

		Method bridgeMethod = _findBridgeMethod(clazz.getMethods(), method);

		if (bridgeMethod != null) {
			return bridgeMethod;
		}

		while (clazz != null) {
			bridgeMethod = _findBridgeMethod(
				clazz.getDeclaredMethods(), method);

			if (bridgeMethod != null) {
				return bridgeMethod;
			}

			clazz = clazz.getSuperclass();
		}

		return ReflectionUtil.throwException(
			new NoSuchMethodException(
				StringBundler.concat(
					"No bridge method on ", clazz, " with name ", methodName,
					" and parameter types ", Arrays.toString(parameterTypes))));
	}

	public static Field getField(Class<?> clazz, String fieldName) {
		try {
			Field field = clazz.getField(fieldName);

			field.setAccessible(true);

			return field;
		}
		catch (NoSuchFieldException noSuchFieldException) {
		}
		catch (Exception exception) {
			return ReflectionUtil.throwException(exception);
		}

		while (clazz != null) {
			try {
				Field field = clazz.getDeclaredField(fieldName);

				field.setAccessible(true);

				return field;
			}
			catch (NoSuchFieldException noSuchFieldException) {
				clazz = clazz.getSuperclass();
			}
			catch (Exception exception) {
				return ReflectionUtil.throwException(exception);
			}
		}

		return ReflectionUtil.throwException(
			new NoSuchFieldException(
				StringBundler.concat(
					"No field on ", clazz, " with name ", fieldName)));
	}

	public static <T> T getFieldValue(Class<?> clazz, String fieldName) {
		Field field = getField(clazz, fieldName);

		try {
			return (T)field.get(null);
		}
		catch (Exception exception) {
			return ReflectionUtil.throwException(exception);
		}
	}

	public static <T> T getFieldValue(Object instance, String fieldName) {
		Field field = getField(instance.getClass(), fieldName);

		try {
			return (T)field.get(instance);
		}
		catch (Exception exception) {
			return ReflectionUtil.throwException(exception);
		}
	}

	public static Method getMethod(
		Class<?> clazz, String methodName, Class<?>... parameterTypes) {

		try {
			Method method = clazz.getMethod(methodName, parameterTypes);

			method.setAccessible(true);

			return method;
		}
		catch (NoSuchMethodException noSuchMethodException) {
		}

		while (clazz != null) {
			try {
				Method method = clazz.getDeclaredMethod(
					methodName, parameterTypes);

				method.setAccessible(true);

				return method;
			}
			catch (NoSuchMethodException noSuchMethodException) {
				clazz = clazz.getSuperclass();
			}
		}

		return ReflectionUtil.throwException(
			new NoSuchMethodException(
				StringBundler.concat(
					"No method on ", clazz, " with name ", methodName,
					" and parameter types ", Arrays.toString(parameterTypes))));
	}

	public static <T> T invoke(
		Class<?> clazz, String methodName, Class<?>[] parameterTypes,
		Object... parameters) {

		Method method = getMethod(clazz, methodName, parameterTypes);

		try {
			return (T)method.invoke(null, parameters);
		}
		catch (InvocationTargetException invocationTargetException) {
			return ReflectionUtil.throwException(
				invocationTargetException.getCause());
		}
		catch (Exception exception) {
			return ReflectionUtil.throwException(exception);
		}
	}

	public static <T> T invoke(
		Object instance, String methodName, Class<?>[] parameterTypes,
		Object... parameters) {

		Method method = getMethod(
			instance.getClass(), methodName, parameterTypes);

		try {
			return (T)method.invoke(instance, parameters);
		}
		catch (InvocationTargetException invocationTargetException) {
			return ReflectionUtil.throwException(
				invocationTargetException.getCause());
		}
		catch (Exception exception) {
			return ReflectionUtil.throwException(exception);
		}
	}

	public static <T> T invokeBridge(
		Object instance, String methodName, Class<?>[] parameterTypes,
		Object... parameters) {

		Method method = getBridgeMethod(
			instance.getClass(), methodName, parameterTypes);

		try {
			return (T)method.invoke(instance, parameters);
		}
		catch (InvocationTargetException invocationTargetException) {
			return ReflectionUtil.throwException(
				invocationTargetException.getCause());
		}
		catch (Exception exception) {
			return ReflectionUtil.throwException(exception);
		}
	}

	public static <T extends Enum<T>> T newEnumElement(
		Class<T> enumClass, Class<?>[] constructorParameterTypes, String name,
		int ordinal, Object... constructorParameters) {

		Class<?>[] parameterTypes = null;

		if ((constructorParameterTypes != null) &&
			(constructorParameterTypes.length != 0)) {

			parameterTypes = new Class<?>[constructorParameterTypes.length + 2];

			parameterTypes[0] = String.class;
			parameterTypes[1] = int.class;

			System.arraycopy(
				constructorParameterTypes, 0, parameterTypes, 2,
				constructorParameterTypes.length);
		}
		else {
			parameterTypes = new Class<?>[2];

			parameterTypes[0] = String.class;
			parameterTypes[1] = int.class;
		}

		try {
			Constructor<T> constructor = enumClass.getDeclaredConstructor(
				parameterTypes);

			Method acquireConstructorAccessorMethod =
				ReflectionUtil.getDeclaredMethod(
					Constructor.class, "acquireConstructorAccessor");

			acquireConstructorAccessorMethod.invoke(constructor);

			Field constructorAccessorField = ReflectionUtil.getDeclaredField(
				Constructor.class, "constructorAccessor");

			Object constructorAccessor = constructorAccessorField.get(
				constructor);

			Method newInstanceMethod = ReflectionUtil.getDeclaredMethod(
				constructorAccessor.getClass(), "newInstance", Object[].class);

			Object[] parameters = null;

			if ((constructorParameters != null) &&
				(constructorParameters.length != 0)) {

				parameters = new Object[constructorParameters.length + 2];

				parameters[0] = name;
				parameters[1] = ordinal;

				System.arraycopy(
					constructorParameters, 0, parameters, 2,
					constructorParameters.length);
			}
			else {
				parameters = new Object[2];

				parameters[0] = name;
				parameters[1] = ordinal;
			}

			return (T)newInstanceMethod.invoke(
				constructorAccessor, new Object[] {parameters});
		}
		catch (Exception exception) {
			return ReflectionUtil.throwException(exception);
		}
	}

	public static <T extends Enum<T>> T newEnumElement(
		Class<T> enumClass, String name, int ordinal) {

		return newEnumElement(enumClass, null, name, ordinal, (Object[])null);
	}

	public static void setFieldValue(
		Class<?> clazz, String fieldName, Object value) {

		setFieldValue(getField(clazz, fieldName), (Object)null, value);
	}

	public static void setFieldValue(
		Field field, Object instance, Object value) {

		try {
			int modifiers = field.getModifiers();

			if (!Modifier.isFinal(modifiers) || !Modifier.isStatic(modifiers)) {
				field.set(instance, value);

				return;
			}

			MethodHandle methodHandle = _lookup.findStaticSetter(
				field.getDeclaringClass(), field.getName(), field.getType());

			methodHandle.invoke(value);
		}
		catch (Throwable throwable) {
			ReflectionUtil.throwException(throwable);
		}
	}

	public static void setFieldValue(
		Object instance, String fieldName, Object value) {

		setFieldValue(
			getField(instance.getClass(), fieldName), instance, value);
	}

	public static AutoCloseable setFieldValueWithAutoCloseable(
		Class<?> clazz, String fieldName, Object newValue) {

		Field field = getField(clazz, fieldName);

		try {
			Object value = field.get(null);

			setFieldValue(field, (Object)null, newValue);

			return () -> setFieldValue(field, (Object)null, value);
		}
		catch (Throwable throwable) {
			return ReflectionUtil.throwException(throwable);
		}
	}

	public static AutoCloseable setFieldValueWithAutoCloseable(
		Object instance, String fieldName, Object newValue) {

		Field field = getField(instance.getClass(), fieldName);

		try {
			Object value = field.get(instance);

			setFieldValue(field, instance, newValue);

			return () -> setFieldValue(field, instance, value);
		}
		catch (Throwable throwable) {
			return ReflectionUtil.throwException(throwable);
		}
	}

	private static Method _findBridgeMethod(Method[] methods, Method method) {
		String name = method.getName();
		Class<?>[] parameterTypes = method.getParameterTypes();

		bridge:
		for (Method currentMethod : methods) {
			if (!currentMethod.isBridge() ||
				!name.equals(currentMethod.getName())) {

				continue;
			}

			Class<?>[] currentParameterTypes =
				currentMethod.getParameterTypes();

			if (currentParameterTypes.length != parameterTypes.length) {
				continue;
			}

			for (int i = 0; i < currentParameterTypes.length; i++) {
				if (!currentParameterTypes[i].isAssignableFrom(
						parameterTypes[i])) {

					continue bridge;
				}
			}

			currentMethod.setAccessible(true);

			return currentMethod;
		}

		return null;
	}

	private static final MethodHandles.Lookup _lookup;

	static {
		try {
			Field field = MethodHandles.Lookup.class.getDeclaredField(
				"IMPL_LOOKUP");

			field.setAccessible(true);

			_lookup = (MethodHandles.Lookup)field.get(null);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new ExceptionInInitializerError(reflectiveOperationException);
		}
	}

}