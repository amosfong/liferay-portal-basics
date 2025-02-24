/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.multiple.internal.cluster.link;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class CoalescedPipeTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testBlockingTake() throws InterruptedException {
		final CoalescedPipe<String> coalescedPipe = new CoalescedPipe<>();

		ScheduledExecutorService scheduledExecutorService =
			Executors.newScheduledThreadPool(1);

		scheduledExecutorService.schedule(
			new Runnable() {

				@Override
				public void run() {
					try {
						coalescedPipe.put("test1");
					}
					catch (InterruptedException interruptedException) {
						Assert.fail(interruptedException.getMessage());
					}
				}

			},
			500, TimeUnit.MILLISECONDS);

		long startTime = System.currentTimeMillis();

		Assert.assertEquals("test1", coalescedPipe.take());
		Assert.assertTrue((System.currentTimeMillis() - startTime) > 250L);

		scheduledExecutorService.shutdownNow();
		scheduledExecutorService.awaitTermination(120, TimeUnit.SECONDS);
	}

	@Test
	public void testNonblockingTake() throws InterruptedException {
		CoalescedPipe<String> coalescedPipe = new CoalescedPipe<>();

		coalescedPipe.put("test2");
		coalescedPipe.put("test3");

		long startTime = System.currentTimeMillis();

		Assert.assertEquals("test2", coalescedPipe.take());
		Assert.assertTrue((System.currentTimeMillis() - startTime) < 100);

		startTime = System.currentTimeMillis();

		Assert.assertEquals("test3", coalescedPipe.take());
		Assert.assertTrue((System.currentTimeMillis() - startTime) < 100);
	}

	@Test
	public void testPut() throws InterruptedException {

		// Without comparator

		CoalescedPipe<String> coalescedPipe = new CoalescedPipe<>();

		// Null

		try {
			coalescedPipe.put(null);

			Assert.fail();
		}
		catch (NullPointerException nullPointerException) {
		}

		// Normal

		coalescedPipe.put("test1");

		Assert.assertEquals(1, coalescedPipe.pendingCount());
		Assert.assertEquals(0, coalescedPipe.coalescedCount());

		coalescedPipe.put("test2");

		Assert.assertEquals(2, coalescedPipe.pendingCount());
		Assert.assertEquals(0, coalescedPipe.coalescedCount());

		// Coalesce

		coalescedPipe.put("test1");

		Assert.assertEquals(2, coalescedPipe.pendingCount());
		Assert.assertEquals(1, coalescedPipe.coalescedCount());

		coalescedPipe.put("test2");

		Assert.assertEquals(2, coalescedPipe.pendingCount());
		Assert.assertEquals(2, coalescedPipe.coalescedCount());

		// With comparator

		coalescedPipe = new CoalescedPipe<String>(
			new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.length() - o2.length();
				}

			});

		// Null

		try {
			coalescedPipe.put(null);

			Assert.fail();
		}
		catch (NullPointerException nullPointerException) {
		}

		// Normal

		coalescedPipe.put("a");

		Assert.assertEquals(1, coalescedPipe.pendingCount());
		Assert.assertEquals(0, coalescedPipe.coalescedCount());

		coalescedPipe.put("ab");

		Assert.assertEquals(2, coalescedPipe.pendingCount());
		Assert.assertEquals(0, coalescedPipe.coalescedCount());

		// Coalesce

		coalescedPipe.put("c");

		Assert.assertEquals(2, coalescedPipe.pendingCount());
		Assert.assertEquals(1, coalescedPipe.coalescedCount());

		coalescedPipe.put("cd");

		Assert.assertEquals(2, coalescedPipe.pendingCount());
		Assert.assertEquals(2, coalescedPipe.coalescedCount());
	}

	@Test
	public void testTakeSnapshot() throws InterruptedException {
		CoalescedPipe<String> coalescedPipe = new CoalescedPipe<>();

		Object[] snapShot = coalescedPipe.takeSnapshot();

		Assert.assertEquals(Arrays.toString(snapShot), 0, snapShot.length);

		coalescedPipe.put("test1");

		snapShot = coalescedPipe.takeSnapshot();

		Assert.assertEquals(Arrays.toString(snapShot), 1, snapShot.length);
		Assert.assertEquals("test1", snapShot[0]);

		coalescedPipe.put("test2");

		snapShot = coalescedPipe.takeSnapshot();

		Assert.assertEquals(Arrays.toString(snapShot), 2, snapShot.length);
		Assert.assertEquals("test1", snapShot[0]);
		Assert.assertEquals("test2", snapShot[1]);
	}

}