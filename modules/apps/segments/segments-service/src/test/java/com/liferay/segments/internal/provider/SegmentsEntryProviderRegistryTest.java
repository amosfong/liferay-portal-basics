/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal.provider;

import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.segments.context.Context;
import com.liferay.segments.exception.NoSuchEntryException;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.provider.SegmentsEntryProvider;
import com.liferay.segments.provider.SegmentsEntryProviderRegistry;
import com.liferay.segments.service.SegmentsEntryLocalService;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author David Arques
 */
public class SegmentsEntryProviderRegistryTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws PortalException {
		ReflectionTestUtil.setFieldValue(
			_segmentsEntryProviderRegistry, "_segmentsEntryLocalService",
			_segmentsEntryLocalService);

		ReflectionTestUtil.setFieldValue(
			_segmentsEntryProviderRegistry, "_serviceTrackerList",
			_serviceTrackerList);

		ReflectionTestUtil.setFieldValue(
			_segmentsEntryProviderRegistry, "_serviceTrackerMap",
			_serviceTrackerMap);

		Mockito.doThrow(
			new NoSuchEntryException()
		).when(
			_segmentsEntryLocalService
		).getSegmentsEntry(
			Mockito.anyLong()
		);
	}

	@Test
	public void testGetSegmentsEntryClassPKs() throws PortalException {
		long segmentsEntryId = RandomTestUtil.randomLong();

		String source = RandomTestUtil.randomString();

		Mockito.doReturn(
			_createSegmentsEntry(segmentsEntryId, source)
		).when(
			_segmentsEntryLocalService
		).fetchSegmentsEntry(
			segmentsEntryId
		);

		long[] segmentsEntryClassPKs = {
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong(),
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong()
		};

		Mockito.doReturn(
			_createSegmentsEntryProvider(segmentsEntryId, segmentsEntryClassPKs)
		).when(
			_serviceTrackerMap
		).getService(
			source
		);

		Assert.assertArrayEquals(
			segmentsEntryClassPKs,
			_segmentsEntryProviderRegistry.getSegmentsEntryClassPKs(
				segmentsEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		Mockito.verify(
			_segmentsEntryLocalService, Mockito.times(1)
		).fetchSegmentsEntry(
			segmentsEntryId
		);

		Mockito.verify(
			_serviceTrackerMap, Mockito.times(1)
		).getService(
			source
		);
	}

	@Test
	public void testGetSegmentsEntryClassPKsCount() throws PortalException {
		long segmentsEntryId = RandomTestUtil.randomLong();

		String source = RandomTestUtil.randomString();

		Mockito.doReturn(
			_createSegmentsEntry(segmentsEntryId, source)
		).when(
			_segmentsEntryLocalService
		).fetchSegmentsEntry(
			segmentsEntryId
		);

		int segmentsEntryClassPKsCount = RandomTestUtil.randomInt();

		Mockito.doReturn(
			_createSegmentsEntryProvider(
				segmentsEntryId, segmentsEntryClassPKsCount)
		).when(
			_serviceTrackerMap
		).getService(
			source
		);

		Assert.assertEquals(
			segmentsEntryClassPKsCount,
			_segmentsEntryProviderRegistry.getSegmentsEntryClassPKsCount(
				segmentsEntryId));

		Mockito.verify(
			_segmentsEntryLocalService, Mockito.times(1)
		).fetchSegmentsEntry(
			segmentsEntryId
		);

		Mockito.verify(
			_serviceTrackerMap, Mockito.times(1)
		).getService(
			source
		);
	}

	@Test
	public void testGetSegmentsEntryIds() throws PortalException {
		String className = RandomTestUtil.randomString();
		long classPK = RandomTestUtil.randomLong();
		Context context = new Context();
		long groupId = RandomTestUtil.randomLong();

		long[] segmentsEntryIds1 = {
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong(),
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong()
		};

		long[] segmentsEntryIds2 = {
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong(),
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong()
		};

		SegmentsEntryProvider segmentsEntryProvider1 =
			_createSegmentsEntryProvider(segmentsEntryIds1);

		SegmentsEntryProvider segmentsEntryProvider2 =
			_createSegmentsEntryProvider(segmentsEntryIds2);

		List<SegmentsEntryProvider> segmentsEntryProviders = Arrays.asList(
			segmentsEntryProvider1, segmentsEntryProvider2);

		Mockito.doReturn(
			segmentsEntryProviders.iterator()
		).when(
			_serviceTrackerList
		).iterator();

		long[] actualSegmentsEntryIds =
			_segmentsEntryProviderRegistry.getSegmentsEntryIds(
				groupId, className, classPK, context, new long[0]);

		Arrays.sort(actualSegmentsEntryIds);

		long[] segmentsEntryIds = ArrayUtil.append(
			segmentsEntryIds1, segmentsEntryIds2);

		Arrays.sort(segmentsEntryIds);

		Assert.assertArrayEquals(segmentsEntryIds, actualSegmentsEntryIds);
	}

	private SegmentsEntry _createSegmentsEntry(
		long segmentsEntryId, String source) {

		SegmentsEntry segmentsEntry = Mockito.mock(SegmentsEntry.class);

		Mockito.doReturn(
			segmentsEntryId
		).when(
			segmentsEntry
		).getSegmentsEntryId();

		Mockito.doReturn(
			source
		).when(
			segmentsEntry
		).getSource();

		return segmentsEntry;
	}

	private SegmentsEntryProvider _createSegmentsEntryProvider(
			long segmentsEntryId, int segmentsEntryClassPKsCount)
		throws PortalException {

		SegmentsEntryProvider segmentsEntryProvider = Mockito.mock(
			SegmentsEntryProvider.class);

		Mockito.doReturn(
			segmentsEntryClassPKsCount
		).when(
			segmentsEntryProvider
		).getSegmentsEntryClassPKsCount(
			segmentsEntryId
		);

		return segmentsEntryProvider;
	}

	private SegmentsEntryProvider _createSegmentsEntryProvider(
			long segmentsEntryId, long[] segmentsEntryClassPKs)
		throws PortalException {

		SegmentsEntryProvider segmentsEntryProvider = Mockito.mock(
			SegmentsEntryProvider.class);

		Mockito.doReturn(
			segmentsEntryClassPKs
		).when(
			segmentsEntryProvider
		).getSegmentsEntryClassPKs(
			segmentsEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS
		);

		return segmentsEntryProvider;
	}

	private SegmentsEntryProvider _createSegmentsEntryProvider(
			long[] segmentsEntryIds)
		throws PortalException {

		SegmentsEntryProvider segmentsEntryProvider = Mockito.mock(
			SegmentsEntryProvider.class);

		Mockito.doReturn(
			segmentsEntryIds
		).when(
			segmentsEntryProvider
		).getSegmentsEntryIds(
			Mockito.anyLong(), Mockito.anyString(), Mockito.anyLong(),
			Mockito.any(Context.class), Mockito.any(long[].class),
			Mockito.any(long[].class)
		);

		return segmentsEntryProvider;
	}

	private final SegmentsEntryLocalService _segmentsEntryLocalService =
		Mockito.mock(SegmentsEntryLocalService.class);
	private final SegmentsEntryProviderRegistry _segmentsEntryProviderRegistry =
		new SegmentsEntryProviderRegistryImpl();
	private final ServiceTrackerList<SegmentsEntryProvider>
		_serviceTrackerList = Mockito.mock(ServiceTrackerList.class);
	private final ServiceTrackerMap<String, SegmentsEntryProvider>
		_serviceTrackerMap = Mockito.mock(ServiceTrackerMap.class);

}