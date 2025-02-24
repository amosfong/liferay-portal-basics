/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ai.creator.openai.configuration.manager;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;

/**
 * @author Lourdes Fernández Besada
 */
public interface AICreatorOpenAIConfigurationManager {

	public String getAICreatorOpenAICompanyAPIKey(long companyId)
		throws ConfigurationException;

	public String getAICreatorOpenAIGroupAPIKey(long groupId)
		throws ConfigurationException;

	public String getAICreatorOpenAIGroupAPIKey(long companyId, long groupId)
		throws ConfigurationException;

	public boolean isAICreatorChatGPTCompanyEnabled(long companyId)
		throws ConfigurationException;

	public boolean isAICreatorChatGPTGroupEnabled(long companyId, long groupId)
		throws ConfigurationException;

	public boolean isAICreatorDALLECompanyEnabled(long companyId)
		throws ConfigurationException;

	public boolean isAICreatorDALLEGroupEnabled(long companyId, long groupId)
		throws ConfigurationException;

	public void saveAICreatorOpenAICompanyConfiguration(
			long companyId, String apiKey, boolean enableChatGPT,
			boolean enableDALLE)
		throws ConfigurationException;

	public void saveAICreatorOpenAIGroupConfiguration(
			long groupId, String apiKey, boolean enableChatGPT,
			boolean enableDALLE)
		throws ConfigurationException;

}