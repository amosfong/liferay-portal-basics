/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>UserServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserServiceHttp {

	public static void addGroupUsers(
			HttpPrincipal httpPrincipal, long groupId, long[] userIds,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addGroupUsers",
				_addGroupUsersParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, userIds, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void addOrganizationUsers(
			HttpPrincipal httpPrincipal, long organizationId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addOrganizationUsers",
				_addOrganizationUsersParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, organizationId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User addOrUpdateUser(
			HttpPrincipal httpPrincipal, String externalReferenceCode,
			long creatorUserId, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, java.util.Locale locale,
			String firstName, String middleName, String lastName,
			long prefixListTypeId, long suffixListTypeId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String jobTitle,
			java.util.List<com.liferay.portal.kernel.model.Address> addresses,
			java.util.List<com.liferay.portal.kernel.model.EmailAddress>
				emailAddresses,
			java.util.List<com.liferay.portal.kernel.model.Phone> phones,
			java.util.List<com.liferay.portal.kernel.model.Website> websites,
			boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addOrUpdateUser",
				_addOrUpdateUserParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, creatorUserId, companyId,
				autoPassword, password1, password2, autoScreenName, screenName,
				emailAddress, locale, firstName, middleName, lastName,
				prefixListTypeId, suffixListTypeId, male, birthdayMonth,
				birthdayDay, birthdayYear, jobTitle, addresses, emailAddresses,
				phones, websites, sendEmail, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void addPasswordPolicyUsers(
			HttpPrincipal httpPrincipal, long passwordPolicyId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addPasswordPolicyUsers",
				_addPasswordPolicyUsersParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, passwordPolicyId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void addRoleUsers(
			HttpPrincipal httpPrincipal, long roleId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addRoleUsers",
				_addRoleUsersParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, roleId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void addTeamUsers(
			HttpPrincipal httpPrincipal, long teamId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addTeamUsers",
				_addTeamUsersParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, teamId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User addUser(
			HttpPrincipal httpPrincipal, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, java.util.Locale locale,
			String firstName, String middleName, String lastName,
			long prefixListTypeId, long suffixListTypeId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String jobTitle, long[] groupIds, long[] organizationIds,
			long[] roleIds, long[] userGroupIds, boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addUser", _addUserParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, locale, firstName,
				middleName, lastName, prefixListTypeId, suffixListTypeId, male,
				birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
				organizationIds, roleIds, userGroupIds, sendEmail,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User addUser(
			HttpPrincipal httpPrincipal, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, java.util.Locale locale,
			String firstName, String middleName, String lastName,
			long prefixListTypeId, long suffixListTypeId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String jobTitle, long[] groupIds, long[] organizationIds,
			long[] roleIds, long[] userGroupIds,
			java.util.List<com.liferay.portal.kernel.model.Address> addresses,
			java.util.List<com.liferay.portal.kernel.model.EmailAddress>
				emailAddresses,
			java.util.List<com.liferay.portal.kernel.model.Phone> phones,
			java.util.List<com.liferay.portal.kernel.model.Website> websites,
			boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addUser", _addUserParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, locale, firstName,
				middleName, lastName, prefixListTypeId, suffixListTypeId, male,
				birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
				organizationIds, roleIds, userGroupIds, addresses,
				emailAddresses, phones, websites, sendEmail, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User addUser(
			HttpPrincipal httpPrincipal, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, long facebookId,
			String openId, java.util.Locale locale, String firstName,
			String middleName, String lastName, long prefixListTypeId,
			long suffixListTypeId, boolean male, int birthdayMonth,
			int birthdayDay, int birthdayYear, String jobTitle, long[] groupIds,
			long[] organizationIds, long[] roleIds, long[] userGroupIds,
			boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addUser", _addUserParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, facebookId, openId,
				locale, firstName, middleName, lastName, prefixListTypeId,
				suffixListTypeId, male, birthdayMonth, birthdayDay,
				birthdayYear, jobTitle, groupIds, organizationIds, roleIds,
				userGroupIds, sendEmail, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User addUser(
			HttpPrincipal httpPrincipal, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, long facebookId,
			String openId, java.util.Locale locale, String firstName,
			String middleName, String lastName, long prefixListTypeId,
			long suffixListTypeId, boolean male, int birthdayMonth,
			int birthdayDay, int birthdayYear, String jobTitle, long[] groupIds,
			long[] organizationIds, long[] roleIds, long[] userGroupIds,
			java.util.List<com.liferay.portal.kernel.model.Address> addresses,
			java.util.List<com.liferay.portal.kernel.model.EmailAddress>
				emailAddresses,
			java.util.List<com.liferay.portal.kernel.model.Phone> phones,
			java.util.List<com.liferay.portal.kernel.model.Website> websites,
			boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addUser", _addUserParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, facebookId, openId,
				locale, firstName, middleName, lastName, prefixListTypeId,
				suffixListTypeId, male, birthdayMonth, birthdayDay,
				birthdayYear, jobTitle, groupIds, organizationIds, roleIds,
				userGroupIds, addresses, emailAddresses, phones, websites,
				sendEmail, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void addUserGroupUsers(
			HttpPrincipal httpPrincipal, long userGroupId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addUserGroupUsers",
				_addUserGroupUsersParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userGroupId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User addUserWithWorkflow(
			HttpPrincipal httpPrincipal, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, java.util.Locale locale,
			String firstName, String middleName, String lastName,
			long prefixListTypeId, long suffixListTypeId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String jobTitle, long[] groupIds, long[] organizationIds,
			long[] roleIds, long[] userGroupIds, boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addUserWithWorkflow",
				_addUserWithWorkflowParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, locale, firstName,
				middleName, lastName, prefixListTypeId, suffixListTypeId, male,
				birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
				organizationIds, roleIds, userGroupIds, sendEmail,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User addUserWithWorkflow(
			HttpPrincipal httpPrincipal, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, java.util.Locale locale,
			String firstName, String middleName, String lastName,
			long prefixListTypeId, long suffixListTypeId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String jobTitle, long[] groupIds, long[] organizationIds,
			long[] roleIds, long[] userGroupIds,
			java.util.List<com.liferay.portal.kernel.model.Address> addresses,
			java.util.List<com.liferay.portal.kernel.model.EmailAddress>
				emailAddresses,
			java.util.List<com.liferay.portal.kernel.model.Phone> phones,
			java.util.List<com.liferay.portal.kernel.model.Website> websites,
			boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addUserWithWorkflow",
				_addUserWithWorkflowParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, locale, firstName,
				middleName, lastName, prefixListTypeId, suffixListTypeId, male,
				birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
				organizationIds, roleIds, userGroupIds, addresses,
				emailAddresses, phones, websites, sendEmail, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User addUserWithWorkflow(
			HttpPrincipal httpPrincipal, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, long facebookId,
			String openId, java.util.Locale locale, String firstName,
			String middleName, String lastName, long prefixListTypeId,
			long suffixListTypeId, boolean male, int birthdayMonth,
			int birthdayDay, int birthdayYear, String jobTitle, long[] groupIds,
			long[] organizationIds, long[] roleIds, long[] userGroupIds,
			boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addUserWithWorkflow",
				_addUserWithWorkflowParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, facebookId, openId,
				locale, firstName, middleName, lastName, prefixListTypeId,
				suffixListTypeId, male, birthdayMonth, birthdayDay,
				birthdayYear, jobTitle, groupIds, organizationIds, roleIds,
				userGroupIds, sendEmail, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User addUserWithWorkflow(
			HttpPrincipal httpPrincipal, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, long facebookId,
			String openId, java.util.Locale locale, String firstName,
			String middleName, String lastName, long prefixListTypeId,
			long suffixListTypeId, boolean male, int birthdayMonth,
			int birthdayDay, int birthdayYear, String jobTitle, long[] groupIds,
			long[] organizationIds, long[] roleIds, long[] userGroupIds,
			java.util.List<com.liferay.portal.kernel.model.Address> addresses,
			java.util.List<com.liferay.portal.kernel.model.EmailAddress>
				emailAddresses,
			java.util.List<com.liferay.portal.kernel.model.Phone> phones,
			java.util.List<com.liferay.portal.kernel.model.Website> websites,
			boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "addUserWithWorkflow",
				_addUserWithWorkflowParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, facebookId, openId,
				locale, firstName, middleName, lastName, prefixListTypeId,
				suffixListTypeId, male, birthdayMonth, birthdayDay,
				birthdayYear, jobTitle, groupIds, organizationIds, roleIds,
				userGroupIds, addresses, emailAddresses, phones, websites,
				sendEmail, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deletePortrait(HttpPrincipal httpPrincipal, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "deletePortrait",
				_deletePortraitParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteRoleUser(
			HttpPrincipal httpPrincipal, long roleId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "deleteRoleUser",
				_deleteRoleUserParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, roleId, userId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteUser(HttpPrincipal httpPrincipal, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "deleteUser",
				_deleteUserParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User
			fetchUserByExternalReferenceCode(
				HttpPrincipal httpPrincipal, String externalReferenceCode,
				long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "fetchUserByExternalReferenceCode",
				_fetchUserByExternalReferenceCodeParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, companyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getCompanyUsers(
				HttpPrincipal httpPrincipal, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getCompanyUsers",
				_getCompanyUsersParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCompanyUsersCount(
			HttpPrincipal httpPrincipal, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getCompanyUsersCount",
				_getCompanyUsersCountParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User getCurrentUser(
			HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getCurrentUser",
				_getCurrentUserParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static long[] getGroupUserIds(
			HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getGroupUserIds",
				_getGroupUserIdsParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (long[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getGroupUsers(HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getGroupUsers",
				_getGroupUsersParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getGroupUsers(
				HttpPrincipal httpPrincipal, long groupId, int status,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.portal.kernel.model.User> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getGroupUsers",
				_getGroupUsersParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, status, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getGroupUsers(
				HttpPrincipal httpPrincipal, long groupId, int status,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.portal.kernel.model.User> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getGroupUsers",
				_getGroupUsersParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, status, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getGroupUsersCount(
			HttpPrincipal httpPrincipal, long groupId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getGroupUsersCount",
				_getGroupUsersCountParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getGtCompanyUsers(
				HttpPrincipal httpPrincipal, long gtUserId, long companyId,
				int size)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getGtCompanyUsers",
				_getGtCompanyUsersParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, gtUserId, companyId, size);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getGtOrganizationUsers(
				HttpPrincipal httpPrincipal, long gtUserId, long organizationId,
				int size)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getGtOrganizationUsers",
				_getGtOrganizationUsersParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, gtUserId, organizationId, size);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getGtUserGroupUsers(
				HttpPrincipal httpPrincipal, long gtUserId, long userGroupId,
				int size)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getGtUserGroupUsers",
				_getGtUserGroupUsersParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, gtUserId, userGroupId, size);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getOrganizationsAndUserGroupsUsersCount(
			HttpPrincipal httpPrincipal, long[] organizationIds,
			long[] userGroupIds)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class,
				"getOrganizationsAndUserGroupsUsersCount",
				_getOrganizationsAndUserGroupsUsersCountParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, organizationIds, userGroupIds);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.security.auth.
							PrincipalException) {

					throw (com.liferay.portal.kernel.security.auth.
						PrincipalException)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static long[] getOrganizationUserIds(
			HttpPrincipal httpPrincipal, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getOrganizationUserIds",
				_getOrganizationUserIdsParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, organizationId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (long[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getOrganizationUsers(
				HttpPrincipal httpPrincipal, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getOrganizationUsers",
				_getOrganizationUsersParameterTypes32);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, organizationId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getOrganizationUsers(
				HttpPrincipal httpPrincipal, long organizationId, int status,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.portal.kernel.model.User> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getOrganizationUsers",
				_getOrganizationUsersParameterTypes33);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, organizationId, status, start, end,
				orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getOrganizationUsers(
				HttpPrincipal httpPrincipal, long organizationId, int status,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.portal.kernel.model.User> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getOrganizationUsers",
				_getOrganizationUsersParameterTypes34);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, organizationId, status, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getOrganizationUsersCount(
			HttpPrincipal httpPrincipal, long organizationId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getOrganizationUsersCount",
				_getOrganizationUsersCountParameterTypes35);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, organizationId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static long[] getRoleUserIds(
			HttpPrincipal httpPrincipal, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getRoleUserIds",
				_getRoleUserIdsParameterTypes36);

			MethodHandler methodHandler = new MethodHandler(methodKey, roleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (long[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User getUserByEmailAddress(
			HttpPrincipal httpPrincipal, long companyId, String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getUserByEmailAddress",
				_getUserByEmailAddressParameterTypes37);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, emailAddress);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User
			getUserByExternalReferenceCode(
				HttpPrincipal httpPrincipal, long companyId,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getUserByExternalReferenceCode",
				_getUserByExternalReferenceCodeParameterTypes38);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, externalReferenceCode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User
			getUserByExternalReferenceCode(
				HttpPrincipal httpPrincipal, String externalReferenceCode,
				long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getUserByExternalReferenceCode",
				_getUserByExternalReferenceCodeParameterTypes39);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, companyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User getUserById(
			HttpPrincipal httpPrincipal, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getUserById",
				_getUserByIdParameterTypes40);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User getUserByScreenName(
			HttpPrincipal httpPrincipal, long companyId, String screenName)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getUserByScreenName",
				_getUserByScreenNameParameterTypes41);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, screenName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getUserGroupUsers(HttpPrincipal httpPrincipal, long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getUserGroupUsers",
				_getUserGroupUsersParameterTypes42);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userGroupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getUserGroupUsers(
				HttpPrincipal httpPrincipal, long userGroupId, int start,
				int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getUserGroupUsers",
				_getUserGroupUsersParameterTypes43);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userGroupId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static long getUserIdByEmailAddress(
			HttpPrincipal httpPrincipal, long companyId, String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getUserIdByEmailAddress",
				_getUserIdByEmailAddressParameterTypes44);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, emailAddress);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static long getUserIdByScreenName(
			HttpPrincipal httpPrincipal, long companyId, String screenName)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "getUserIdByScreenName",
				_getUserIdByScreenNameParameterTypes45);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, screenName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static boolean hasGroupUser(
			HttpPrincipal httpPrincipal, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "hasGroupUser",
				_hasGroupUserParameterTypes46);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, userId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static boolean hasRoleUser(
			HttpPrincipal httpPrincipal, long roleId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "hasRoleUser",
				_hasRoleUserParameterTypes47);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, roleId, userId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static boolean hasRoleUser(
			HttpPrincipal httpPrincipal, long companyId, String name,
			long userId, boolean inherited)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "hasRoleUser",
				_hasRoleUserParameterTypes48);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, name, userId, inherited);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static boolean sendPasswordByEmailAddress(
			HttpPrincipal httpPrincipal, long companyId, String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "sendPasswordByEmailAddress",
				_sendPasswordByEmailAddressParameterTypes49);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, emailAddress);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static boolean sendPasswordByScreenName(
			HttpPrincipal httpPrincipal, long companyId, String screenName)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "sendPasswordByScreenName",
				_sendPasswordByScreenNameParameterTypes50);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, screenName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static boolean sendPasswordByUserId(
			HttpPrincipal httpPrincipal, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "sendPasswordByUserId",
				_sendPasswordByUserIdParameterTypes51);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void setRoleUsers(
			HttpPrincipal httpPrincipal, long roleId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "setRoleUsers",
				_setRoleUsersParameterTypes52);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, roleId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void setUserGroupUsers(
			HttpPrincipal httpPrincipal, long userGroupId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "setUserGroupUsers",
				_setUserGroupUsersParameterTypes53);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userGroupId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void unsetGroupTeamsUsers(
			HttpPrincipal httpPrincipal, long groupId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "unsetGroupTeamsUsers",
				_unsetGroupTeamsUsersParameterTypes54);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void unsetGroupUsers(
			HttpPrincipal httpPrincipal, long groupId, long[] userIds,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "unsetGroupUsers",
				_unsetGroupUsersParameterTypes55);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, userIds, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void unsetOrganizationUsers(
			HttpPrincipal httpPrincipal, long organizationId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "unsetOrganizationUsers",
				_unsetOrganizationUsersParameterTypes56);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, organizationId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void unsetPasswordPolicyUsers(
			HttpPrincipal httpPrincipal, long passwordPolicyId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "unsetPasswordPolicyUsers",
				_unsetPasswordPolicyUsersParameterTypes57);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, passwordPolicyId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void unsetRoleUsers(
			HttpPrincipal httpPrincipal, long roleId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "unsetRoleUsers",
				_unsetRoleUsersParameterTypes58);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, roleId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void unsetTeamUsers(
			HttpPrincipal httpPrincipal, long teamId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "unsetTeamUsers",
				_unsetTeamUsersParameterTypes59);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, teamId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void unsetUserGroupUsers(
			HttpPrincipal httpPrincipal, long userGroupId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "unsetUserGroupUsers",
				_unsetUserGroupUsersParameterTypes60);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userGroupId, userIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateAgreedToTermsOfUse(
			HttpPrincipal httpPrincipal, long userId,
			boolean agreedToTermsOfUse)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateAgreedToTermsOfUse",
				_updateAgreedToTermsOfUseParameterTypes61);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, agreedToTermsOfUse);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateEmailAddress(
			HttpPrincipal httpPrincipal, long userId, String password,
			String emailAddress1, String emailAddress2,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateEmailAddress",
				_updateEmailAddressParameterTypes62);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, password, emailAddress1, emailAddress2,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User
			updateExternalReferenceCode(
				HttpPrincipal httpPrincipal, long userId,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateExternalReferenceCode",
				_updateExternalReferenceCodeParameterTypes63);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, externalReferenceCode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User
			updateExternalReferenceCode(
				HttpPrincipal httpPrincipal,
				com.liferay.portal.kernel.model.User user,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateExternalReferenceCode",
				_updateExternalReferenceCodeParameterTypes64);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, user, externalReferenceCode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateIncompleteUser(
			HttpPrincipal httpPrincipal, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, java.util.Locale locale,
			String firstName, String middleName, String lastName,
			long prefixListTypeId, long suffixListTypeId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String jobTitle, boolean updateUserInformation, boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateIncompleteUser",
				_updateIncompleteUserParameterTypes65);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, locale, firstName,
				middleName, lastName, prefixListTypeId, suffixListTypeId, male,
				birthdayMonth, birthdayDay, birthdayYear, jobTitle,
				updateUserInformation, sendEmail, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateIncompleteUser(
			HttpPrincipal httpPrincipal, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, long facebookId,
			String openId, java.util.Locale locale, String firstName,
			String middleName, String lastName, long prefixListTypeId,
			long suffixListTypeId, boolean male, int birthdayMonth,
			int birthdayDay, int birthdayYear, String jobTitle,
			boolean updateUserInformation, boolean sendEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateIncompleteUser",
				_updateIncompleteUserParameterTypes66);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, facebookId, openId,
				locale, firstName, middleName, lastName, prefixListTypeId,
				suffixListTypeId, male, birthdayMonth, birthdayDay,
				birthdayYear, jobTitle, updateUserInformation, sendEmail,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateLanguageId(
			HttpPrincipal httpPrincipal, long userId, String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateLanguageId",
				_updateLanguageIdParameterTypes67);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, languageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateLockoutById(
			HttpPrincipal httpPrincipal, long userId, boolean lockout)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateLockoutById",
				_updateLockoutByIdParameterTypes68);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, lockout);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateOpenId(
			HttpPrincipal httpPrincipal, long userId, String openId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateOpenId",
				_updateOpenIdParameterTypes69);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, openId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void updateOrganizations(
			HttpPrincipal httpPrincipal, long userId, long[] organizationIds,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateOrganizations",
				_updateOrganizationsParameterTypes70);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, organizationIds, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updatePassword(
			HttpPrincipal httpPrincipal, long userId, String password1,
			String password2, boolean passwordReset)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updatePassword",
				_updatePasswordParameterTypes71);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, password1, password2, passwordReset);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updatePortrait(
			HttpPrincipal httpPrincipal, long userId, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updatePortrait",
				_updatePortraitParameterTypes72);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, bytes);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateReminderQuery(
			HttpPrincipal httpPrincipal, long userId, String question,
			String answer)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateReminderQuery",
				_updateReminderQueryParameterTypes73);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, question, answer);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateScreenName(
			HttpPrincipal httpPrincipal, long userId, String screenName)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateScreenName",
				_updateScreenNameParameterTypes74);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, screenName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateStatus(
			HttpPrincipal httpPrincipal, long userId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateStatus",
				_updateStatusParameterTypes75);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, status, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateStatus(
			HttpPrincipal httpPrincipal,
			com.liferay.portal.kernel.model.User user, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateStatus",
				_updateStatusParameterTypes76);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, user, status, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateUser(
			HttpPrincipal httpPrincipal, long userId, String oldPassword,
			String newPassword1, String newPassword2, boolean passwordReset,
			String reminderQueryQuestion, String reminderQueryAnswer,
			String screenName, String emailAddress, boolean hasPortrait,
			byte[] portraitBytes, String languageId, String timeZoneId,
			String greeting, String comments, String firstName,
			String middleName, String lastName, long prefixListTypeId,
			long suffixListTypeId, boolean male, int birthdayMonth,
			int birthdayDay, int birthdayYear, String smsSn, String facebookSn,
			String jabberSn, String skypeSn, String twitterSn, String jobTitle,
			long[] groupIds, long[] organizationIds, long[] roleIds,
			java.util.List<com.liferay.portal.kernel.model.UserGroupRole>
				userGroupRoles,
			long[] userGroupIds,
			java.util.List<com.liferay.portal.kernel.model.Address> addresses,
			java.util.List<com.liferay.portal.kernel.model.EmailAddress>
				emailAddresses,
			java.util.List<com.liferay.portal.kernel.model.Phone> phones,
			java.util.List<com.liferay.portal.kernel.model.Website> websites,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateUser",
				_updateUserParameterTypes77);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, oldPassword, newPassword1, newPassword2,
				passwordReset, reminderQueryQuestion, reminderQueryAnswer,
				screenName, emailAddress, hasPortrait, portraitBytes,
				languageId, timeZoneId, greeting, comments, firstName,
				middleName, lastName, prefixListTypeId, suffixListTypeId, male,
				birthdayMonth, birthdayDay, birthdayYear, smsSn, facebookSn,
				jabberSn, skypeSn, twitterSn, jobTitle, groupIds,
				organizationIds, roleIds, userGroupRoles, userGroupIds,
				addresses, emailAddresses, phones, websites, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateUser(
			HttpPrincipal httpPrincipal, long userId, String oldPassword,
			String newPassword1, String newPassword2, boolean passwordReset,
			String reminderQueryQuestion, String reminderQueryAnswer,
			String screenName, String emailAddress, long facebookId,
			String openId, boolean hasPortrait, byte[] portraitBytes,
			String languageId, String timeZoneId, String greeting,
			String comments, String firstName, String middleName,
			String lastName, long prefixListTypeId, long suffixListTypeId,
			boolean male, int birthdayMonth, int birthdayDay, int birthdayYear,
			String smsSn, String facebookSn, String jabberSn, String skypeSn,
			String twitterSn, String jobTitle, long[] groupIds,
			long[] organizationIds, long[] roleIds,
			java.util.List<com.liferay.portal.kernel.model.UserGroupRole>
				userGroupRoles,
			long[] userGroupIds,
			java.util.List<com.liferay.portal.kernel.model.Address> addresses,
			java.util.List<com.liferay.portal.kernel.model.EmailAddress>
				emailAddresses,
			java.util.List<com.liferay.portal.kernel.model.Phone> phones,
			java.util.List<com.liferay.portal.kernel.model.Website> websites,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateUser",
				_updateUserParameterTypes78);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, oldPassword, newPassword1, newPassword2,
				passwordReset, reminderQueryQuestion, reminderQueryAnswer,
				screenName, emailAddress, facebookId, openId, hasPortrait,
				portraitBytes, languageId, timeZoneId, greeting, comments,
				firstName, middleName, lastName, prefixListTypeId,
				suffixListTypeId, male, birthdayMonth, birthdayDay,
				birthdayYear, smsSn, facebookSn, jabberSn, skypeSn, twitterSn,
				jobTitle, groupIds, organizationIds, roleIds, userGroupRoles,
				userGroupIds, addresses, emailAddresses, phones, websites,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateUser(
			HttpPrincipal httpPrincipal, long userId, String oldPassword,
			String newPassword1, String newPassword2, boolean passwordReset,
			String reminderQueryQuestion, String reminderQueryAnswer,
			String screenName, String emailAddress, long facebookId,
			String openId, String languageId, String timeZoneId,
			String greeting, String comments, String firstName,
			String middleName, String lastName, long prefixListTypeId,
			long suffixListTypeId, boolean male, int birthdayMonth,
			int birthdayDay, int birthdayYear, String smsSn, String facebookSn,
			String jabberSn, String skypeSn, String twitterSn, String jobTitle,
			long[] groupIds, long[] organizationIds, long[] roleIds,
			java.util.List<com.liferay.portal.kernel.model.UserGroupRole>
				userGroupRoles,
			long[] userGroupIds,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateUser",
				_updateUserParameterTypes79);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, oldPassword, newPassword1, newPassword2,
				passwordReset, reminderQueryQuestion, reminderQueryAnswer,
				screenName, emailAddress, facebookId, openId, languageId,
				timeZoneId, greeting, comments, firstName, middleName, lastName,
				prefixListTypeId, suffixListTypeId, male, birthdayMonth,
				birthdayDay, birthdayYear, smsSn, facebookSn, jabberSn, skypeSn,
				twitterSn, jobTitle, groupIds, organizationIds, roleIds,
				userGroupRoles, userGroupIds, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.User updateUser(
			HttpPrincipal httpPrincipal, long userId, String oldPassword,
			String newPassword1, String newPassword2, boolean passwordReset,
			String reminderQueryQuestion, String reminderQueryAnswer,
			String screenName, String emailAddress, String languageId,
			String timeZoneId, String greeting, String comments,
			String firstName, String middleName, String lastName,
			long prefixListTypeId, long suffixListTypeId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear, String smsSn,
			String facebookSn, String jabberSn, String skypeSn,
			String twitterSn, String jobTitle, long[] groupIds,
			long[] organizationIds, long[] roleIds,
			java.util.List<com.liferay.portal.kernel.model.UserGroupRole>
				userGroupRoles,
			long[] userGroupIds,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				UserServiceUtil.class, "updateUser",
				_updateUserParameterTypes80);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, oldPassword, newPassword1, newPassword2,
				passwordReset, reminderQueryQuestion, reminderQueryAnswer,
				screenName, emailAddress, languageId, timeZoneId, greeting,
				comments, firstName, middleName, lastName, prefixListTypeId,
				suffixListTypeId, male, birthdayMonth, birthdayDay,
				birthdayYear, smsSn, facebookSn, jabberSn, skypeSn, twitterSn,
				jobTitle, groupIds, organizationIds, roleIds, userGroupRoles,
				userGroupIds, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.model.User)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UserServiceHttp.class);

	private static final Class<?>[] _addGroupUsersParameterTypes0 =
		new Class[] {
			long.class, long[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addOrganizationUsersParameterTypes1 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _addOrUpdateUserParameterTypes2 =
		new Class[] {
			String.class, long.class, long.class, boolean.class, String.class,
			String.class, boolean.class, String.class, String.class,
			java.util.Locale.class, String.class, String.class, String.class,
			long.class, long.class, boolean.class, int.class, int.class,
			int.class, String.class, java.util.List.class, java.util.List.class,
			java.util.List.class, java.util.List.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addPasswordPolicyUsersParameterTypes3 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _addRoleUsersParameterTypes4 = new Class[] {
		long.class, long[].class
	};
	private static final Class<?>[] _addTeamUsersParameterTypes5 = new Class[] {
		long.class, long[].class
	};
	private static final Class<?>[] _addUserParameterTypes6 = new Class[] {
		long.class, boolean.class, String.class, String.class, boolean.class,
		String.class, String.class, java.util.Locale.class, String.class,
		String.class, String.class, long.class, long.class, boolean.class,
		int.class, int.class, int.class, String.class, long[].class,
		long[].class, long[].class, long[].class, boolean.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _addUserParameterTypes7 = new Class[] {
		long.class, boolean.class, String.class, String.class, boolean.class,
		String.class, String.class, java.util.Locale.class, String.class,
		String.class, String.class, long.class, long.class, boolean.class,
		int.class, int.class, int.class, String.class, long[].class,
		long[].class, long[].class, long[].class, java.util.List.class,
		java.util.List.class, java.util.List.class, java.util.List.class,
		boolean.class, com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _addUserParameterTypes8 = new Class[] {
		long.class, boolean.class, String.class, String.class, boolean.class,
		String.class, String.class, long.class, String.class,
		java.util.Locale.class, String.class, String.class, String.class,
		long.class, long.class, boolean.class, int.class, int.class, int.class,
		String.class, long[].class, long[].class, long[].class, long[].class,
		boolean.class, com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _addUserParameterTypes9 = new Class[] {
		long.class, boolean.class, String.class, String.class, boolean.class,
		String.class, String.class, long.class, String.class,
		java.util.Locale.class, String.class, String.class, String.class,
		long.class, long.class, boolean.class, int.class, int.class, int.class,
		String.class, long[].class, long[].class, long[].class, long[].class,
		java.util.List.class, java.util.List.class, java.util.List.class,
		java.util.List.class, boolean.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _addUserGroupUsersParameterTypes10 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _addUserWithWorkflowParameterTypes11 =
		new Class[] {
			long.class, boolean.class, String.class, String.class,
			boolean.class, String.class, String.class, java.util.Locale.class,
			String.class, String.class, String.class, long.class, long.class,
			boolean.class, int.class, int.class, int.class, String.class,
			long[].class, long[].class, long[].class, long[].class,
			boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addUserWithWorkflowParameterTypes12 =
		new Class[] {
			long.class, boolean.class, String.class, String.class,
			boolean.class, String.class, String.class, java.util.Locale.class,
			String.class, String.class, String.class, long.class, long.class,
			boolean.class, int.class, int.class, int.class, String.class,
			long[].class, long[].class, long[].class, long[].class,
			java.util.List.class, java.util.List.class, java.util.List.class,
			java.util.List.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addUserWithWorkflowParameterTypes13 =
		new Class[] {
			long.class, boolean.class, String.class, String.class,
			boolean.class, String.class, String.class, long.class, String.class,
			java.util.Locale.class, String.class, String.class, String.class,
			long.class, long.class, boolean.class, int.class, int.class,
			int.class, String.class, long[].class, long[].class, long[].class,
			long[].class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addUserWithWorkflowParameterTypes14 =
		new Class[] {
			long.class, boolean.class, String.class, String.class,
			boolean.class, String.class, String.class, long.class, String.class,
			java.util.Locale.class, String.class, String.class, String.class,
			long.class, long.class, boolean.class, int.class, int.class,
			int.class, String.class, long[].class, long[].class, long[].class,
			long[].class, java.util.List.class, java.util.List.class,
			java.util.List.class, java.util.List.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deletePortraitParameterTypes15 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteRoleUserParameterTypes16 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _deleteUserParameterTypes17 = new Class[] {
		long.class
	};
	private static final Class<?>[]
		_fetchUserByExternalReferenceCodeParameterTypes18 = new Class[] {
			String.class, long.class
		};
	private static final Class<?>[] _getCompanyUsersParameterTypes19 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[] _getCompanyUsersCountParameterTypes20 =
		new Class[] {long.class};
	private static final Class<?>[] _getCurrentUserParameterTypes21 =
		new Class[] {};
	private static final Class<?>[] _getGroupUserIdsParameterTypes22 =
		new Class[] {long.class};
	private static final Class<?>[] _getGroupUsersParameterTypes23 =
		new Class[] {long.class};
	private static final Class<?>[] _getGroupUsersParameterTypes24 =
		new Class[] {
			long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupUsersParameterTypes25 =
		new Class[] {
			long.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupUsersCountParameterTypes26 =
		new Class[] {long.class, int.class};
	private static final Class<?>[] _getGtCompanyUsersParameterTypes27 =
		new Class[] {long.class, long.class, int.class};
	private static final Class<?>[] _getGtOrganizationUsersParameterTypes28 =
		new Class[] {long.class, long.class, int.class};
	private static final Class<?>[] _getGtUserGroupUsersParameterTypes29 =
		new Class[] {long.class, long.class, int.class};
	private static final Class<?>[]
		_getOrganizationsAndUserGroupsUsersCountParameterTypes30 = new Class[] {
			long[].class, long[].class
		};
	private static final Class<?>[] _getOrganizationUserIdsParameterTypes31 =
		new Class[] {long.class};
	private static final Class<?>[] _getOrganizationUsersParameterTypes32 =
		new Class[] {long.class};
	private static final Class<?>[] _getOrganizationUsersParameterTypes33 =
		new Class[] {
			long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getOrganizationUsersParameterTypes34 =
		new Class[] {
			long.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getOrganizationUsersCountParameterTypes35 =
		new Class[] {long.class, int.class};
	private static final Class<?>[] _getRoleUserIdsParameterTypes36 =
		new Class[] {long.class};
	private static final Class<?>[] _getUserByEmailAddressParameterTypes37 =
		new Class[] {long.class, String.class};
	private static final Class<?>[]
		_getUserByExternalReferenceCodeParameterTypes38 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[]
		_getUserByExternalReferenceCodeParameterTypes39 = new Class[] {
			String.class, long.class
		};
	private static final Class<?>[] _getUserByIdParameterTypes40 = new Class[] {
		long.class
	};
	private static final Class<?>[] _getUserByScreenNameParameterTypes41 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _getUserGroupUsersParameterTypes42 =
		new Class[] {long.class};
	private static final Class<?>[] _getUserGroupUsersParameterTypes43 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[] _getUserIdByEmailAddressParameterTypes44 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _getUserIdByScreenNameParameterTypes45 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _hasGroupUserParameterTypes46 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _hasRoleUserParameterTypes47 = new Class[] {
		long.class, long.class
	};
	private static final Class<?>[] _hasRoleUserParameterTypes48 = new Class[] {
		long.class, String.class, long.class, boolean.class
	};
	private static final Class<?>[]
		_sendPasswordByEmailAddressParameterTypes49 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _sendPasswordByScreenNameParameterTypes50 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _sendPasswordByUserIdParameterTypes51 =
		new Class[] {long.class};
	private static final Class<?>[] _setRoleUsersParameterTypes52 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _setUserGroupUsersParameterTypes53 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _unsetGroupTeamsUsersParameterTypes54 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _unsetGroupUsersParameterTypes55 =
		new Class[] {
			long.class, long[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _unsetOrganizationUsersParameterTypes56 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _unsetPasswordPolicyUsersParameterTypes57 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _unsetRoleUsersParameterTypes58 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _unsetTeamUsersParameterTypes59 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _unsetUserGroupUsersParameterTypes60 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _updateAgreedToTermsOfUseParameterTypes61 =
		new Class[] {long.class, boolean.class};
	private static final Class<?>[] _updateEmailAddressParameterTypes62 =
		new Class[] {
			long.class, String.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_updateExternalReferenceCodeParameterTypes63 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[]
		_updateExternalReferenceCodeParameterTypes64 = new Class[] {
			com.liferay.portal.kernel.model.User.class, String.class
		};
	private static final Class<?>[] _updateIncompleteUserParameterTypes65 =
		new Class[] {
			long.class, boolean.class, String.class, String.class,
			boolean.class, String.class, String.class, java.util.Locale.class,
			String.class, String.class, String.class, long.class, long.class,
			boolean.class, int.class, int.class, int.class, String.class,
			boolean.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateIncompleteUserParameterTypes66 =
		new Class[] {
			long.class, boolean.class, String.class, String.class,
			boolean.class, String.class, String.class, long.class, String.class,
			java.util.Locale.class, String.class, String.class, String.class,
			long.class, long.class, boolean.class, int.class, int.class,
			int.class, String.class, boolean.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateLanguageIdParameterTypes67 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _updateLockoutByIdParameterTypes68 =
		new Class[] {long.class, boolean.class};
	private static final Class<?>[] _updateOpenIdParameterTypes69 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _updateOrganizationsParameterTypes70 =
		new Class[] {
			long.class, long[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updatePasswordParameterTypes71 =
		new Class[] {long.class, String.class, String.class, boolean.class};
	private static final Class<?>[] _updatePortraitParameterTypes72 =
		new Class[] {long.class, byte[].class};
	private static final Class<?>[] _updateReminderQueryParameterTypes73 =
		new Class[] {long.class, String.class, String.class};
	private static final Class<?>[] _updateScreenNameParameterTypes74 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _updateStatusParameterTypes75 =
		new Class[] {
			long.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateStatusParameterTypes76 =
		new Class[] {
			com.liferay.portal.kernel.model.User.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateUserParameterTypes77 = new Class[] {
		long.class, String.class, String.class, String.class, boolean.class,
		String.class, String.class, String.class, String.class, boolean.class,
		byte[].class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, long.class, long.class,
		boolean.class, int.class, int.class, int.class, String.class,
		String.class, String.class, String.class, String.class, String.class,
		long[].class, long[].class, long[].class, java.util.List.class,
		long[].class, java.util.List.class, java.util.List.class,
		java.util.List.class, java.util.List.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _updateUserParameterTypes78 = new Class[] {
		long.class, String.class, String.class, String.class, boolean.class,
		String.class, String.class, String.class, String.class, long.class,
		String.class, boolean.class, byte[].class, String.class, String.class,
		String.class, String.class, String.class, String.class, String.class,
		long.class, long.class, boolean.class, int.class, int.class, int.class,
		String.class, String.class, String.class, String.class, String.class,
		String.class, long[].class, long[].class, long[].class,
		java.util.List.class, long[].class, java.util.List.class,
		java.util.List.class, java.util.List.class, java.util.List.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _updateUserParameterTypes79 = new Class[] {
		long.class, String.class, String.class, String.class, boolean.class,
		String.class, String.class, String.class, String.class, long.class,
		String.class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, long.class, long.class,
		boolean.class, int.class, int.class, int.class, String.class,
		String.class, String.class, String.class, String.class, String.class,
		long[].class, long[].class, long[].class, java.util.List.class,
		long[].class, com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _updateUserParameterTypes80 = new Class[] {
		long.class, String.class, String.class, String.class, boolean.class,
		String.class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, String.class, String.class,
		String.class, long.class, long.class, boolean.class, int.class,
		int.class, int.class, String.class, String.class, String.class,
		String.class, String.class, String.class, long[].class, long[].class,
		long[].class, java.util.List.class, long[].class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};

}