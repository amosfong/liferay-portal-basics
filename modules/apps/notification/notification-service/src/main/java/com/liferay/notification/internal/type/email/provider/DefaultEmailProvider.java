/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notification.internal.type.email.provider;

import com.liferay.notification.context.NotificationContext;
import com.liferay.notification.term.evaluator.NotificationTermEvaluatorTracker;
import com.liferay.notification.type.util.NotificationTypeUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Carolina Barbosa
 */
public class DefaultEmailProvider implements EmailProvider {

	public DefaultEmailProvider(
		NotificationTermEvaluatorTracker notificationTermEvaluatorTracker) {

		_notificationTermEvaluatorTracker = notificationTermEvaluatorTracker;
	}

	@Override
	public String provide(NotificationContext notificationContext, Object value)
		throws PortalException {

		if (value == null) {
			return StringPool.BLANK;
		}

		if (!(value instanceof Map)) {
			return NotificationTypeUtil.evaluateTerms(
				(String)value, notificationContext,
				_notificationTermEvaluatorTracker);
		}

		Map<Locale, String> valueMap = (Map<Locale, String>)value;

		String valueString = valueMap.get(notificationContext.getUserLocale());

		if (Validator.isNull(valueString)) {
			valueString = valueMap.get(
				notificationContext.getSiteDefaultLocale());
		}

		String content = NotificationTypeUtil.evaluateTerms(
			valueString, notificationContext,
			_notificationTermEvaluatorTracker);

		if (Validator.isNull(content)) {
			return StringPool.BLANK;
		}

		Set<String> emailAddresses = new HashSet<>();

		Matcher matcher = _emailAddressPattern.matcher(content);

		while (matcher.find()) {
			emailAddresses.add(matcher.group());
		}

		return StringUtil.merge(emailAddresses);
	}

	private static final Pattern _emailAddressPattern = Pattern.compile(
		"[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@" +
			"(?:\\w(?:[\\w-]*\\w)?\\.)+(\\w(?:[\\w-]*\\w))");

	private final NotificationTermEvaluatorTracker
		_notificationTermEvaluatorTracker;

}