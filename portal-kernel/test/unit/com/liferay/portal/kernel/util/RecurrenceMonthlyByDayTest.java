/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.cal.DayAndPosition;
import com.liferay.portal.kernel.cal.Duration;
import com.liferay.portal.kernel.cal.Recurrence;

import java.util.Calendar;

import org.junit.Test;

/**
 * @author Douglas Wong
 */
public class RecurrenceMonthlyByDayTest extends RecurrenceTestCase {

	@Test
	public void testRecurrence() {
		Recurrence recurrence = getRecurrence(
			dtStart, durationOneHour, FRIDAY, 3, 1);

		Calendar beforeRecurrence = getCalendar(2008, FEBRUARY, 15, 22, 9);

		assertRecurrenceEquals(false, recurrence, beforeRecurrence);

		Calendar duringRecurrence1 = getCalendar(2008, FEBRUARY, 15, 22, 10);
		Calendar duringRecurrence2 = getCalendar(2008, MARCH, 21, 22, 15);

		assertRecurrenceEquals(true, recurrence, duringRecurrence1);
		assertRecurrenceEquals(true, recurrence, duringRecurrence2);

		Calendar afterRecurrence = getCalendar(2008, FEBRUARY, 15, 23, 10);

		assertRecurrenceEquals(false, recurrence, afterRecurrence);
	}

	@Test
	public void testRecurrenceCrossDates() {
		Recurrence recurrence = getRecurrence(
			dtStart, durationTwoHours, FRIDAY, 3, 1);

		Calendar duringRecurrence = getCalendar(2008, FEBRUARY, 16, 0, 9);

		assertRecurrenceEquals(true, recurrence, duringRecurrence);

		Calendar afterRecurrence = getCalendar(2008, FEBRUARY, 16, 0, 10);

		assertRecurrenceEquals(false, recurrence, afterRecurrence);
	}

	@Test
	public void testRecurrenceCrossWeeks() {
		Recurrence recurrence = getRecurrence(
			dtStart, durationCrossWeek, FRIDAY, 3, 1);

		Calendar duringRecurrence = getCalendar(2008, FEBRUARY, 23, 22, 9);

		assertRecurrenceEquals(true, recurrence, duringRecurrence);

		Calendar afterRecurrence = getCalendar(2008, FEBRUARY, 23, 22, 10);

		assertRecurrenceEquals(false, recurrence, afterRecurrence);
	}

	@Test
	public void testRecurrenceCrossYears() {
		Recurrence recurrence = getRecurrence(
			dtStart, durationCrossWeek, FRIDAY, 4, 1);

		Calendar duringRecurrence = getCalendar(2009, JANUARY, 3, 22, 9);

		assertRecurrenceEquals(true, recurrence, duringRecurrence);

		Calendar afterRecurrence = getCalendar(2009, JANUARY, 3, 22, 10);

		assertRecurrenceEquals(false, recurrence, afterRecurrence);
	}

	@Test
	public void testRecurrenceWithInterval() {
		Recurrence recurrence = getRecurrence(
			dtStart, durationOneHour, FRIDAY, 3, 2);

		Calendar duringRecurrence1 = getCalendar(2008, FEBRUARY, 15, 22, 15);
		Calendar duringRecurrence2 = getCalendar(2008, MARCH, 21, 22, 15);
		Calendar duringRecurrence3 = getCalendar(2008, APRIL, 18, 22, 15);
		Calendar duringRecurrence4 = getCalendar(2008, MAY, 16, 22, 15);

		assertRecurrenceEquals(true, recurrence, duringRecurrence1);
		assertRecurrenceEquals(false, recurrence, duringRecurrence2);
		assertRecurrenceEquals(true, recurrence, duringRecurrence3);
		assertRecurrenceEquals(false, recurrence, duringRecurrence4);
	}

	@Test
	public void testRecurrenceWithLeapYear() {
		Recurrence recurrence = getRecurrence(
			dtStart, durationTwoHours, FRIDAY, -1, 1);

		Calendar duringRecurrence1 = getCalendar(2008, FEBRUARY, 29, 22, 10);
		Calendar duringRecurrence2 = getCalendar(2008, MARCH, 1, 0, 9);

		assertRecurrenceEquals(true, recurrence, duringRecurrence1);
		assertRecurrenceEquals(true, recurrence, duringRecurrence2);

		Calendar afterRecurrence = getCalendar(2008, MARCH, 1, 0, 10);

		assertRecurrenceEquals(false, recurrence, afterRecurrence);
	}

	@Test
	public void testRecurrenceWithUntilDate() {
		Recurrence recurrence = getRecurrence(
			dtStart, durationOneHour, FRIDAY, 3, 1);

		recurrence.setUntil(getCalendar(2008, MARCH, 15, 22, 10));

		Calendar beforeUntil = getCalendar(2008, FEBRUARY, 15, 22, 15);

		assertRecurrenceEquals(true, recurrence, beforeUntil);

		Calendar afterUntil = getCalendar(2008, MARCH, 21, 22, 15);

		assertRecurrenceEquals(false, recurrence, afterUntil);
	}

	protected Recurrence getRecurrence(
		Calendar dtStart, Duration duration, int day, int position,
		int interval) {

		Recurrence recurrence = new Recurrence(
			dtStart, duration, Recurrence.MONTHLY);

		recurrence.setByDay(
			new DayAndPosition[] {new DayAndPosition(day, position)});
		recurrence.setInterval(interval);

		return recurrence;
	}

}