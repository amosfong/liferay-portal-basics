/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.bean.BeanPropertiesImpl;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.model.impl.EmailAddressImpl;
import com.liferay.portal.model.impl.EmailAddressModelImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Wesley Gong
 */
public class OrderByComparatorFactoryUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() {
		BeanPropertiesUtil beanPropertiesUtil = new BeanPropertiesUtil();

		beanPropertiesUtil.setBeanProperties(new BeanPropertiesImpl());
	}

	@Test
	public void testCollectionsSortMultipleColumnsAscending() throws Exception {
		EmailAddress emailAddress1 = newEmailAddress(
			1, newDate(0, 1, 2012), "abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress(
			2, newDate(0, 2, 2012), "abc@liferay.com");

		List<EmailAddress> expectedList = new ArrayList<>();

		expectedList.add(emailAddress1);
		expectedList.add(emailAddress2);

		List<EmailAddress> actualList = new ArrayList<>();

		actualList.add(emailAddress2);
		actualList.add(emailAddress1);

		OrderByComparator<EmailAddress> orderByComparator =
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME, "address", false,
				"createDate", true);

		Collections.sort(actualList, orderByComparator);

		Assert.assertEquals(expectedList.toString(), actualList.toString());
	}

	@Test
	public void testCollectionsSortMultipleColumnsDescending()
		throws Exception {

		EmailAddress emailAddress1 = newEmailAddress(
			1, newDate(0, 1, 2012), "abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress(
			2, newDate(0, 2, 2012), "abc@liferay.com");

		List<EmailAddress> expectedList = new ArrayList<>();

		expectedList.add(emailAddress2);
		expectedList.add(emailAddress1);

		List<EmailAddress> actualList = new ArrayList<>();

		actualList.add(emailAddress1);
		actualList.add(emailAddress2);

		OrderByComparator<EmailAddress> orderByComparator =
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME, "address", false,
				"createDate", false);

		Collections.sort(actualList, orderByComparator);

		Assert.assertEquals(expectedList.toString(), actualList.toString());
	}

	@Test
	public void testCollectionsSortSingleColumnAscending() throws Exception {
		EmailAddress emailAddress1 = newEmailAddress(
			1, newDate(0, 1, 2012), "abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress(
			2, newDate(0, 2, 2012), "def@liferay.com");

		List<EmailAddress> expectedList = new ArrayList<>();

		expectedList.add(emailAddress1);
		expectedList.add(emailAddress2);

		List<EmailAddress> actualList = new ArrayList<>();

		actualList.add(emailAddress2);
		actualList.add(emailAddress1);

		OrderByComparator<EmailAddress> orderByComparator =
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME, "address", true);

		Collections.sort(actualList, orderByComparator);

		Assert.assertEquals(expectedList.toString(), actualList.toString());
	}

	@Test
	public void testCollectionsSortSingleColumnDescending() throws Exception {
		EmailAddress emailAddress1 = newEmailAddress(
			1, newDate(0, 1, 2012), "abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress(
			2, newDate(0, 2, 2012), "def@liferay.com");

		List<EmailAddress> expectedList = new ArrayList<>();

		expectedList.add(emailAddress2);
		expectedList.add(emailAddress1);

		List<EmailAddress> actualList = new ArrayList<>();

		actualList.add(emailAddress1);
		actualList.add(emailAddress2);

		OrderByComparator<EmailAddress> orderByComparator =
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME, "address", false);

		Collections.sort(actualList, orderByComparator);

		Assert.assertEquals(expectedList.toString(), actualList.toString());
	}

	@Test
	public void testCollectionsSortSingleColumnPrimitiveAscending()
		throws Exception {

		EmailAddress emailAddress1 = newEmailAddress(
			1, newDate(0, 1, 2012), "abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress(
			2, newDate(0, 2, 2012), "abc@liferay.com");

		List<EmailAddress> expectedList = new ArrayList<>();

		expectedList.add(emailAddress1);
		expectedList.add(emailAddress2);

		List<EmailAddress> actualList = new ArrayList<>();

		actualList.add(emailAddress2);
		actualList.add(emailAddress1);

		OrderByComparator<EmailAddress> orderByComparator =
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME, "companyId", true);

		Collections.sort(actualList, orderByComparator);

		Assert.assertEquals(expectedList.toString(), actualList.toString());
	}

	@Test
	public void testCollectionsSortSingleColumnPrimitiveDescending()
		throws Exception {

		EmailAddress emailAddress1 = newEmailAddress(
			1, newDate(0, 1, 2012), "abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress(
			2, newDate(0, 2, 2012), "abc@liferay.com");

		List<EmailAddress> expectedList = new ArrayList<>();

		expectedList.add(emailAddress2);
		expectedList.add(emailAddress1);

		List<EmailAddress> actualList = new ArrayList<>();

		actualList.add(emailAddress1);
		actualList.add(emailAddress2);

		OrderByComparator<EmailAddress> orderByComparator =
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME, "companyId", false);

		Collections.sort(actualList, orderByComparator);

		Assert.assertEquals(expectedList.toString(), actualList.toString());
	}

	@Test
	public void testGetOrderByMultipleColumns() throws Exception {
		OrderByComparator<EmailAddress> orderByComparator =
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME, "address", true, "createDate",
				false);

		Assert.assertEquals(
			"EmailAddress.address ASC,EmailAddress.createDate DESC",
			orderByComparator.getOrderBy());

		orderByComparator = OrderByComparatorFactoryUtil.create(
			EmailAddressModelImpl.TABLE_NAME, "address", false, "createDate",
			true);

		Assert.assertEquals(
			"EmailAddress.address DESC,EmailAddress.createDate ASC",
			orderByComparator.getOrderBy());
	}

	@Test
	public void testGetOrderBySingleColumn() throws Exception {
		OrderByComparator<EmailAddress> orderByComparator =
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME, "address", true);

		Assert.assertEquals(
			"EmailAddress.address ASC", orderByComparator.getOrderBy());

		orderByComparator = OrderByComparatorFactoryUtil.create(
			EmailAddressModelImpl.TABLE_NAME, "address", false);

		Assert.assertEquals(
			"EmailAddress.address DESC", orderByComparator.getOrderBy());
	}

	@Test
	public void testInvalidColumns() throws Exception {
		try {
			OrderByComparatorFactoryUtil.<EmailAddress>create(
				EmailAddressModelImpl.TABLE_NAME);

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
		}

		try {
			OrderByComparatorFactoryUtil.<EmailAddress>create(
				EmailAddressModelImpl.TABLE_NAME, "address");

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
		}

		try {
			OrderByComparatorFactoryUtil.<EmailAddress>create(
				EmailAddressModelImpl.TABLE_NAME, "address", true,
				"createDate");

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
		}
	}

	protected Date newDate(int month, int day, int year) throws Exception {
		Calendar calendar = new GregorianCalendar();

		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, day);
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime();
	}

	protected EmailAddress newEmailAddress(
		long companyId, Date createDate, String address) {

		EmailAddress emailAddress = new EmailAddressImpl();

		emailAddress.setCompanyId(companyId);
		emailAddress.setCreateDate(createDate);
		emailAddress.setAddress(address);

		return emailAddress;
	}

}