/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.repository.registry.test.util;

import com.liferay.portal.kernel.repository.event.RepositoryEventListener;
import com.liferay.portal.kernel.repository.event.RepositoryEventType;
import com.liferay.portal.kernel.repository.registry.RepositoryEventRegistry;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Adolfo Pérez
 */
public class RepositoryEventTestUtil {

	public static <S extends RepositoryEventType, T> AtomicInteger
		registerCounterRepositoryEventListener(
			RepositoryEventRegistry repositoryEventRegistry,
			Class<S> eventClass, Class<T> modelClass) {

		AtomicInteger count = new AtomicInteger();

		CounterRepositoryEventListener<S, T> counterRepositoryEventListener =
			new CounterRepositoryEventListener<>(count);

		repositoryEventRegistry.registerRepositoryEventListener(
			eventClass, modelClass, counterRepositoryEventListener);

		return count;
	}

	public static <S extends RepositoryEventType, T> AtomicInteger
		registerCounterRepositoryEventListener(
			RepositoryEventRegistry repositoryEventRegistry,
			Class<S> eventClass, Class<T> modelClass, AtomicInteger count) {

		CounterRepositoryEventListener<S, T> counterRepositoryEventListener =
			new CounterRepositoryEventListener<>(count);

		repositoryEventRegistry.registerRepositoryEventListener(
			eventClass, modelClass, counterRepositoryEventListener);

		return count;
	}

	public static class AlwaysFailingRepositoryEventListener
		<S extends RepositoryEventType, T>
			implements RepositoryEventListener<S, T> {

		@Override
		public void execute(T model) {
			throw new IllegalStateException();
		}

	}

	public static class CounterRepositoryEventListener
		<S extends RepositoryEventType, T>
			implements RepositoryEventListener<S, T> {

		public CounterRepositoryEventListener(AtomicInteger count) {
			_count = count;
		}

		@Override
		public void execute(T model) {
			_count.incrementAndGet();
		}

		private final AtomicInteger _count;

	}

	public static class NoOpRepositoryEventListener
		<S extends RepositoryEventType, T>
			implements RepositoryEventListener<S, T> {

		@Override
		public void execute(T model) {
		}

	}

}