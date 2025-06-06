/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.exception;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.security.auth.FullNameValidator;
import com.liferay.portal.kernel.util.ClassUtil;

/**
 * @author Drew Brokke
 */
public class ContactNameException extends PortalException {

	public static class MustHaveFirstName extends ContactNameException {

		public MustHaveFirstName() {
			super("Contact must have a first name");
		}

	}

	public static class MustHaveLastName extends ContactNameException {

		public MustHaveLastName() {
			super("Contact must have a last name");
		}

	}

	public static class MustHaveMiddleName extends ContactNameException {

		public MustHaveMiddleName() {
			super("Contact must have a middle name");
		}

	}

	public static class MustHaveValidFullName extends ContactNameException {

		public MustHaveValidFullName(FullNameValidator fullNameValidator) {
			super(
				String.format(
					"Contact full name must validate with %s",
					ClassUtil.getClassName(fullNameValidator)));

			this.fullNameValidator = fullNameValidator;
		}

		public final FullNameValidator fullNameValidator;

	}

	public static class MustNotExceedMaximumLength
		extends ContactNameException {

		public MustNotExceedMaximumLength(int firstNameMaximumLength) {
			super(
				StringBundler.concat(
					"Contact first name must have fewer than ",
					firstNameMaximumLength, " characters"));
		}

	}

	private ContactNameException(String msg) {
		super(msg);
	}

}