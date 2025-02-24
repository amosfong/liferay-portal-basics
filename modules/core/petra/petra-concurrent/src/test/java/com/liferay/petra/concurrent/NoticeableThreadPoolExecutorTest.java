/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.concurrent;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.test.util.ThreadTestUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class NoticeableThreadPoolExecutorTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new CodeCoverageAssertor() {

				@Override
				public void appendAssertClasses(List<Class<?>> assertClasses) {
					assertClasses.add(BaseNoticeableExecutorService.class);
				}

			},
			LiferayUnitTestRule.INSTANCE);

	@Test
	public void testAdjustSize() throws InterruptedException {
		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
				},
				new ThreadPoolHandlerAdapter());

		Assert.assertEquals(1, noticeableThreadPoolExecutor.getCorePoolSize());
		Assert.assertEquals(
			1, noticeableThreadPoolExecutor.getMaximumPoolSize());

		try {
			noticeableThreadPoolExecutor.setCorePoolSize(0);

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
			Assert.assertEquals(
				"To ensure FIFO, core pool size must be 1 or greater",
				illegalArgumentException.getMessage());
		}

		noticeableThreadPoolExecutor.setMaximumPoolSize(3);

		Assert.assertEquals(
			3, noticeableThreadPoolExecutor.getMaximumPoolSize());

		noticeableThreadPoolExecutor.setCorePoolSize(2);

		Assert.assertEquals(2, noticeableThreadPoolExecutor.getCorePoolSize());

		noticeableThreadPoolExecutor.shutdown();

		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));
	}

	@Test
	public void testAwaitTermination() throws InterruptedException {
		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
				},
				new ThreadPoolHandlerAdapter());

		Assert.assertFalse(
			noticeableThreadPoolExecutor.awaitTermination(
				1, TimeUnit.NANOSECONDS));

		ThreadPoolExecutor dispatcherThreadPoolExecutor =
			ReflectionTestUtil.getFieldValue(
				noticeableThreadPoolExecutor, "_dispatcherThreadPoolExecutor");

		dispatcherThreadPoolExecutor.shutdown();

		Assert.assertFalse(
			noticeableThreadPoolExecutor.awaitTermination(
				1, TimeUnit.NANOSECONDS));

		noticeableThreadPoolExecutor.shutdown();

		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));
	}

	@Test
	public void testConstructor() {
		try {
			new NoticeableThreadPoolExecutor(
				0, 1, 1, TimeUnit.NANOSECONDS, new SynchronousQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
				},
				new ThreadPoolHandlerAdapter());

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
			Assert.assertEquals(
				"To ensure FIFO, core pool size must be 1 or greater",
				illegalArgumentException.getMessage());
		}

		new NoticeableThreadPoolExecutor(
			1, 1, 1, TimeUnit.NANOSECONDS, new SynchronousQueue<>(),
			new MethodNameThreadFactory(),
			(runnable, threadPoolExecutor) -> {
			},
			new ThreadPoolHandlerAdapter());
	}

	@Test
	public void testMisc() throws InterruptedException {
		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
				},
				new ThreadPoolHandlerAdapter());

		try {
			noticeableThreadPoolExecutor.execute(null);

			Assert.fail();
		}
		catch (NullPointerException nullPointerException) {
		}

		Assert.assertFalse(noticeableThreadPoolExecutor.isShutdown());
		Assert.assertFalse(noticeableThreadPoolExecutor.isTerminated());

		ThreadPoolExecutor workerThreadPoolExecutor =
			ReflectionTestUtil.getFieldValue(
				noticeableThreadPoolExecutor, "_workerThreadPoolExecutor");

		workerThreadPoolExecutor.shutdown();

		Assert.assertTrue(
			workerThreadPoolExecutor.awaitTermination(10, TimeUnit.MINUTES));

		Assert.assertTrue(noticeableThreadPoolExecutor.isTerminated());

		noticeableThreadPoolExecutor.shutdown();

		Assert.assertTrue(noticeableThreadPoolExecutor.isShutdown());
		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));
		Assert.assertTrue(noticeableThreadPoolExecutor.isTerminated());
	}

	@Test
	public void testRejectedByFullQueueAndExecuted()
		throws InterruptedException {

		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
				},
				new ThreadPoolHandlerAdapter());

		Semaphore semaphore = new Semaphore(0);

		Runnable slowRunnable = () -> {
			try {
				semaphore.acquire();
			}
			catch (InterruptedException interruptedException) {
				ReflectionUtil.throwException(interruptedException);
			}
		};

		noticeableThreadPoolExecutor.execute(slowRunnable);

		noticeableThreadPoolExecutor.execute(slowRunnable);

		BlockingQueue<Runnable> workerTaskQueue = _getWorkerTaskQueue(
			noticeableThreadPoolExecutor);

		Assert.assertSame(slowRunnable, workerTaskQueue.take());

		semaphore.release();

		workerTaskQueue.put(slowRunnable);

		semaphore.release();

		noticeableThreadPoolExecutor.shutdown();

		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));
	}

	@Test
	public void testRejectedByFullQueueAndInterruptted()
		throws InterruptedException {

		BlockingQueue<Runnable> dispatchTaskQueue = new LinkedBlockingQueue<>();

		BlockingQueue<Runnable> rejectedTaskQueue = new LinkedBlockingQueue<>();

		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, dispatchTaskQueue,
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> rejectedTaskQueue.add(
					runnable),
				new ThreadPoolHandlerAdapter());

		Semaphore semaphore = new Semaphore(0);

		Runnable slowRunnable = () -> {
			try {
				semaphore.acquire();
			}
			catch (InterruptedException interruptedException) {
				ReflectionUtil.throwException(interruptedException);
			}
		};

		noticeableThreadPoolExecutor.execute(slowRunnable);

		noticeableThreadPoolExecutor.execute(slowRunnable);

		while (!dispatchTaskQueue.isEmpty());

		ThreadPoolExecutor dispatcherThreadPoolExecutor =
			ReflectionTestUtil.getFieldValue(
				noticeableThreadPoolExecutor, "_dispatcherThreadPoolExecutor");

		dispatcherThreadPoolExecutor.shutdownNow();

		Assert.assertSame(slowRunnable, rejectedTaskQueue.take());

		semaphore.release();

		noticeableThreadPoolExecutor.shutdown();

		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));
	}

	@Test
	public void testRejectedByShutdownNoticeableThreadPoolExecutor()
		throws InterruptedException {

		BlockingQueue<Runnable> rejectedTasks = new LinkedBlockingQueue<>();

		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, new SynchronousQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> rejectedTasks.add(runnable),
				new ThreadPoolHandlerAdapter());

		noticeableThreadPoolExecutor.shutdown();

		Runnable runnable = () -> {
		};

		noticeableThreadPoolExecutor.execute(runnable);

		Assert.assertSame(runnable, rejectedTasks.take());
	}

	@Test
	public void testRejectedByShutdownWorkerThreadPoolExecutor()
		throws InterruptedException {

		BlockingQueue<Runnable> rejectedTasks = new LinkedBlockingQueue<>();

		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, new SynchronousQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> rejectedTasks.add(runnable),
				new ThreadPoolHandlerAdapter());

		ThreadPoolExecutor workerThreadPoolExecutor =
			ReflectionTestUtil.getFieldValue(
				noticeableThreadPoolExecutor, "_workerThreadPoolExecutor");

		workerThreadPoolExecutor.shutdown();

		Runnable runnable = () -> {
		};

		noticeableThreadPoolExecutor.execute(runnable);

		Assert.assertSame(runnable, rejectedTasks.take());
	}

	@Test
	public void testRejectedWithDiscardOldestStylePolicy()
		throws InterruptedException {

		List<Runnable> rejectedRunnables = new ArrayList<>();

		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, new SynchronousQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
					rejectedRunnables.add(runnable);

					if (rejectedRunnables.size() > 1) {
						return;
					}

					Queue<Runnable> queue = threadPoolExecutor.getQueue();

					queue.poll();

					threadPoolExecutor.execute(runnable);
				},
				new ThreadPoolHandlerAdapter());

		Runnable slowRunnable = () -> {
			try {
				Thread.sleep(Long.MAX_VALUE);
			}
			catch (InterruptedException interruptedException) {
			}
		};

		noticeableThreadPoolExecutor.execute(slowRunnable);
		noticeableThreadPoolExecutor.execute(slowRunnable);

		Assert.assertEquals(
			rejectedRunnables.toString(), 2, rejectedRunnables.size());

		Runnable runnable = rejectedRunnables.get(0);

		Assert.assertSame(slowRunnable, rejectedRunnables.get(0));
		Assert.assertSame(slowRunnable, rejectedRunnables.get(1));

		noticeableThreadPoolExecutor.shutdownNow();

		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));
	}

	@Test
	public void testShutdownBeforeShutdowNow() throws InterruptedException {
		_testShutdownNow(true);
	}

	@Test
	public void testShutdownNow() throws InterruptedException {
		_testShutdownNow(false);
	}

	@Test
	public void testStatisticMethods() throws InterruptedException {
		BlockingQueue<Runnable> taskBlockingQueue = new LinkedBlockingQueue<>();

		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 2, 1, TimeUnit.NANOSECONDS, taskBlockingQueue,
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
				},
				new ThreadPoolHandlerAdapter());

		Assert.assertEquals(0, noticeableThreadPoolExecutor.getActiveCount());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getCompletedTaskCount());
		Assert.assertEquals(1, noticeableThreadPoolExecutor.getCorePoolSize());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getLargestPoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getMaximumPoolSize());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getPendingTaskCount());
		Assert.assertEquals(0, noticeableThreadPoolExecutor.getPoolSize());

		BlockingQueue<Object> runningBlockingTaskQueue =
			new LinkedBlockingQueue<>();

		Semaphore semaphore = new Semaphore(0);

		Runnable slowRunnable = () -> {
			runningBlockingTaskQueue.add(this);

			try {
				semaphore.acquire();
			}
			catch (InterruptedException interruptedException) {
				ReflectionUtil.throwException(interruptedException);
			}
		};

		noticeableThreadPoolExecutor.execute(slowRunnable);

		runningBlockingTaskQueue.take();

		Assert.assertEquals(1, noticeableThreadPoolExecutor.getActiveCount());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getCompletedTaskCount());
		Assert.assertEquals(1, noticeableThreadPoolExecutor.getCorePoolSize());
		Assert.assertEquals(
			1, noticeableThreadPoolExecutor.getLargestPoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getMaximumPoolSize());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getPendingTaskCount());
		Assert.assertEquals(1, noticeableThreadPoolExecutor.getPoolSize());

		noticeableThreadPoolExecutor.execute(slowRunnable);

		runningBlockingTaskQueue.take();

		Assert.assertEquals(2, noticeableThreadPoolExecutor.getActiveCount());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getCompletedTaskCount());
		Assert.assertEquals(1, noticeableThreadPoolExecutor.getCorePoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getLargestPoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getMaximumPoolSize());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getPendingTaskCount());
		Assert.assertEquals(2, noticeableThreadPoolExecutor.getPoolSize());

		noticeableThreadPoolExecutor.execute(slowRunnable);

		Thread dispatcherThread = null;

		for (Thread thread : ThreadTestUtil.getThreads()) {
			if (thread == null) {
				continue;
			}

			String name = thread.getName();

			if (name.startsWith("testStatisticMethods-") &&
				name.endsWith("-dispatcher")) {

				dispatcherThread = thread;

				break;
			}
		}

		Assert.assertNotNull(
			"Dispatcher thread is not started", dispatcherThread);

		while (dispatcherThread.getState() != Thread.State.WAITING);

		while (!taskBlockingQueue.isEmpty());

		Assert.assertEquals(2, noticeableThreadPoolExecutor.getActiveCount());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getCompletedTaskCount());
		Assert.assertEquals(1, noticeableThreadPoolExecutor.getCorePoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getLargestPoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getMaximumPoolSize());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getPendingTaskCount());
		Assert.assertEquals(2, noticeableThreadPoolExecutor.getPoolSize());

		noticeableThreadPoolExecutor.execute(slowRunnable);

		Assert.assertEquals(2, noticeableThreadPoolExecutor.getActiveCount());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getCompletedTaskCount());
		Assert.assertEquals(1, noticeableThreadPoolExecutor.getCorePoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getLargestPoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getMaximumPoolSize());
		Assert.assertEquals(
			1, noticeableThreadPoolExecutor.getPendingTaskCount());
		Assert.assertEquals(2, noticeableThreadPoolExecutor.getPoolSize());

		semaphore.release();

		runningBlockingTaskQueue.take();

		while (!taskBlockingQueue.isEmpty());

		Assert.assertEquals(2, noticeableThreadPoolExecutor.getActiveCount());
		Assert.assertEquals(
			1, noticeableThreadPoolExecutor.getCompletedTaskCount());
		Assert.assertEquals(1, noticeableThreadPoolExecutor.getCorePoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getLargestPoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getMaximumPoolSize());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getPendingTaskCount());
		Assert.assertEquals(2, noticeableThreadPoolExecutor.getPoolSize());

		semaphore.release();

		runningBlockingTaskQueue.take();

		semaphore.release();

		semaphore.release();

		noticeableThreadPoolExecutor.shutdown();

		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));

		Assert.assertEquals(0, noticeableThreadPoolExecutor.getActiveCount());
		Assert.assertEquals(
			4, noticeableThreadPoolExecutor.getCompletedTaskCount());
		Assert.assertEquals(1, noticeableThreadPoolExecutor.getCorePoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getLargestPoolSize());
		Assert.assertEquals(
			2, noticeableThreadPoolExecutor.getMaximumPoolSize());
		Assert.assertEquals(
			0, noticeableThreadPoolExecutor.getPendingTaskCount());
		Assert.assertEquals(0, noticeableThreadPoolExecutor.getPoolSize());
	}

	@Test
	public void testSubmit() throws Exception {
		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
				},
				new ThreadPoolHandlerAdapter());

		try {
			noticeableThreadPoolExecutor.submit((Callable<?>)null);

			Assert.fail();
		}
		catch (NullPointerException nullPointerException) {
			Assert.assertEquals(
				"Callable is null", nullPointerException.getMessage());
		}

		NoticeableFuture<String> noticeableFuture1 =
			noticeableThreadPoolExecutor.submit(() -> "test");

		Assert.assertEquals("test", noticeableFuture1.get());

		AtomicBoolean flag = new AtomicBoolean();

		NoticeableFuture<?> noticeableFuture2 =
			noticeableThreadPoolExecutor.submit(() -> flag.set(true));

		Assert.assertNull(noticeableFuture2.get());

		Assert.assertTrue(flag.get());

		try {
			noticeableThreadPoolExecutor.submit(null, null);

			Assert.fail();
		}
		catch (NullPointerException nullPointerException) {
			Assert.assertEquals(
				"Runnable is null", nullPointerException.getMessage());
		}

		NoticeableFuture<String> noticeableFuture3 =
			noticeableThreadPoolExecutor.submit(() -> flag.set(false), "test");

		Assert.assertEquals("test", noticeableFuture3.get());

		Assert.assertFalse(flag.get());

		noticeableThreadPoolExecutor.shutdown();

		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));
	}

	@Test
	public void testTerminationNoticeableFuture() throws InterruptedException {
		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
				},
				new ThreadPoolHandlerAdapter());

		NoticeableFuture<Void> terminationNoticeableFuture =
			noticeableThreadPoolExecutor.terminationNoticeableFuture();

		Assert.assertFalse(terminationNoticeableFuture.isDone());

		AtomicBoolean terminated = new AtomicBoolean();

		terminationNoticeableFuture.addFutureListener(
			future -> terminated.set(true));

		noticeableThreadPoolExecutor.shutdown();

		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));

		Assert.assertTrue(terminated.get());
	}

	@Test
	public void testThreadPoolHandler() throws Exception {
		BlockingQueue<String> executionPaths = new LinkedBlockingQueue<>();

		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<>(),
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
				},
				new ThreadPoolHandler() {

					@Override
					public void afterExecute(
						Runnable runnable, Throwable throwable) {

						executionPaths.add("afterExecute");
					}

					@Override
					public void beforeExecute(
						Thread thread, Runnable runnable) {

						executionPaths.add("beforeExecute");
					}

					@Override
					public void terminated() {
						executionPaths.add("terminated");
					}

				});

		NoticeableFuture<Void> terminationNoticeableFuture =
			noticeableThreadPoolExecutor.terminationNoticeableFuture();

		terminationNoticeableFuture.addFutureListener(
			future -> executionPaths.add("terminationNoticeableFuture"));

		Future<?> future = noticeableThreadPoolExecutor.submit(
			() -> executionPaths.add("runnable"));

		future.get();

		noticeableThreadPoolExecutor.shutdown();

		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));

		Assert.assertEquals(
			executionPaths.toString(), 5, executionPaths.size());
		Assert.assertEquals("beforeExecute", executionPaths.take());
		Assert.assertEquals("runnable", executionPaths.take());
		Assert.assertEquals("afterExecute", executionPaths.take());
		Assert.assertEquals("terminated", executionPaths.take());
		Assert.assertEquals(
			"terminationNoticeableFuture", executionPaths.take());
	}

	private BlockingQueue<Runnable> _getWorkerTaskQueue(
		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor) {

		ThreadPoolExecutor workerThreadPoolExecutor =
			ReflectionTestUtil.getFieldValue(
				noticeableThreadPoolExecutor, "_workerThreadPoolExecutor");

		return workerThreadPoolExecutor.getQueue();
	}

	private void _testShutdownNow(boolean shutdownBeforeShutdownNow)
		throws InterruptedException {

		BlockingQueue<Runnable> taskBlockingQueue = new LinkedBlockingQueue<>();

		NoticeableThreadPoolExecutor noticeableThreadPoolExecutor =
			new NoticeableThreadPoolExecutor(
				1, 1, 1, TimeUnit.NANOSECONDS, taskBlockingQueue,
				new MethodNameThreadFactory(),
				(runnable, threadPoolExecutor) -> {
				},
				new ThreadPoolHandlerAdapter());

		Semaphore semaphore = new Semaphore(0);

		Runnable slowRunnable = () -> {
			try {
				semaphore.acquire();
			}
			catch (InterruptedException interruptedException) {
			}
		};

		noticeableThreadPoolExecutor.execute(slowRunnable);

		noticeableThreadPoolExecutor.execute(slowRunnable);

		while (!taskBlockingQueue.isEmpty());

		noticeableThreadPoolExecutor.execute(slowRunnable);

		if (shutdownBeforeShutdownNow) {
			noticeableThreadPoolExecutor.shutdown();
		}

		List<Runnable> shutdownTasks =
			noticeableThreadPoolExecutor.shutdownNow();

		Assert.assertEquals(shutdownTasks.toString(), 1, shutdownTasks.size());

		shutdownTasks = noticeableThreadPoolExecutor.shutdownNow();

		Assert.assertTrue(shutdownTasks.toString(), shutdownTasks.isEmpty());

		Assert.assertTrue(
			noticeableThreadPoolExecutor.awaitTermination(
				10, TimeUnit.MINUTES));
	}

	private static class MethodNameThreadFactory implements ThreadFactory {

		@Override
		public Thread newThread(Runnable runnable) {
			Thread thread = new Thread(
				runnable, _prefix + _counter.getAndIncrement());

			thread.setDaemon(true);

			return thread;
		}

		private MethodNameThreadFactory() {
			Exception exception = new Exception();

			StackTraceElement[] stackTraceElements = exception.getStackTrace();

			_prefix = stackTraceElements[2].getMethodName() + "-";
		}

		private final AtomicInteger _counter = new AtomicInteger();
		private final String _prefix;

	}

}