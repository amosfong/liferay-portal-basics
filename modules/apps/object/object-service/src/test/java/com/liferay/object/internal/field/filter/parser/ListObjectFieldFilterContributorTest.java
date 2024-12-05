/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.field.filter.parser;

import com.liferay.object.constants.ObjectViewFilterColumnConstants;
import com.liferay.object.exception.ObjectViewFilterColumnException;
import com.liferay.object.field.filter.parser.StatusSystemObjectFieldFilterStrategy;
import com.liferay.object.model.ObjectViewFilterColumn;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Feliphe Marinho
 */
public class ListObjectFieldFilterContributorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testValidate() throws PortalException {
		Language language = Mockito.mock(Language.class);

		Mockito.when(
			language.get(LocaleUtil.getDefault(), "approved")
		).thenReturn(
			"approved"
		);

		ObjectViewFilterColumn objectViewFilterColumn = Mockito.mock(
			ObjectViewFilterColumn.class);

		Mockito.when(
			objectViewFilterColumn.getFilterType()
		).thenReturn(
			ObjectViewFilterColumnConstants.FILTER_TYPE_EXCLUDES
		);

		Mockito.when(
			objectViewFilterColumn.getJSON()
		).thenReturn(
			"{\"includes\": [0, 1]}"
		);

		ReflectionTestUtil.setFieldValue(
			_listObjectFieldFilterContributor, "_objectFieldFilterStrategy",
			new StatusSystemObjectFieldFilterStrategy(
				language, LocaleUtil.getDefault(), objectViewFilterColumn));

		try {
			_listObjectFieldFilterContributor.validate();

			Assert.fail();
		}
		catch (ObjectViewFilterColumnException
					objectViewFilterColumnException) {

			Assert.assertEquals(
				"JSON array is null for filter type excludes",
				objectViewFilterColumnException.getMessage());
		}

		Mockito.when(
			objectViewFilterColumn.getJSON()
		).thenReturn(
			"{\"excludes\": [\"brazil\"]}"
		);

		Mockito.when(
			objectViewFilterColumn.getObjectFieldName()
		).thenReturn(
			"status"
		);

		try {
			_listObjectFieldFilterContributor.validate();

			Assert.fail();
		}
		catch (ObjectViewFilterColumnException
					objectViewFilterColumnException) {

			Assert.assertEquals(
				"JSON array is invalid for filter type excludes",
				objectViewFilterColumnException.getMessage());
		}

		Mockito.when(
			objectViewFilterColumn.getJSON()
		).thenReturn(
			"{\"excludes\": [0, 1]}"
		);

		_listObjectFieldFilterContributor.validate();
	}

	private final ListObjectFieldFilterContributor
		_listObjectFieldFilterContributor =
			new ListObjectFieldFilterContributor();

}