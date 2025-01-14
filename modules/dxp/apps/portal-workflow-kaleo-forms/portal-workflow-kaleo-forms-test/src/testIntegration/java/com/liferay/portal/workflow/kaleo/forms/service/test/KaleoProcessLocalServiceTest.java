/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.forms.test.util.KaleoProcessTestUtil;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Inácio Nery
 */
@RunWith(Arquillian.class)
@Sync
public class KaleoProcessLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAddKaleoProcess() throws Exception {
		int initialCount =
			KaleoProcessLocalServiceUtil.getKaleoProcessesCount();

		KaleoProcessTestUtil.addKaleoProcess(KaleoProcess.class.getName());

		int actualCount = KaleoProcessLocalServiceUtil.getKaleoProcessesCount();

		Assert.assertEquals(initialCount + 1, actualCount);
	}

	@Test
	public void testUpdateKaleoProcess() throws Exception {
		int initialCount =
			KaleoProcessLocalServiceUtil.getKaleoProcessesCount();

		KaleoProcess kaleoProcess = KaleoProcessTestUtil.addKaleoProcess(
			KaleoProcess.class.getName());

		KaleoProcessTestUtil.updateKaleoProcess(
			kaleoProcess, KaleoProcess.class.getName());

		int actualCount = KaleoProcessLocalServiceUtil.getKaleoProcessesCount();

		Assert.assertEquals(initialCount + 1, actualCount);
	}

}