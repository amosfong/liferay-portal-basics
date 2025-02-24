/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.service.http;

import com.liferay.asset.kernel.service.AssetCategoryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>AssetCategoryServiceUtil</code> service
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
public class AssetCategoryServiceHttp {

	public static com.liferay.asset.kernel.model.AssetCategory addCategory(
			HttpPrincipal httpPrincipal, long groupId, long parentCategoryId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			long vocabularyId, String[] categoryProperties,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "addCategory",
				_addCategoryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, parentCategoryId, titleMap, descriptionMap,
				vocabularyId, categoryProperties, serviceContext);

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

			return (com.liferay.asset.kernel.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategory addCategory(
			HttpPrincipal httpPrincipal, long groupId, String title,
			long vocabularyId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "addCategory",
				_addCategoryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, title, vocabularyId, serviceContext);

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

			return (com.liferay.asset.kernel.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategory addCategory(
			HttpPrincipal httpPrincipal, String externalReferenceCode,
			long groupId, long parentCategoryId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			long vocabularyId, String[] categoryProperties,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "addCategory",
				_addCategoryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, groupId, parentCategoryId,
				titleMap, descriptionMap, vocabularyId, categoryProperties,
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

			return (com.liferay.asset.kernel.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteCategories(
			HttpPrincipal httpPrincipal, long[] categoryIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "deleteCategories",
				_deleteCategoriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryIds);

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

	public static void deleteCategory(
			HttpPrincipal httpPrincipal, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "deleteCategory",
				_deleteCategoryParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryId);

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

	public static com.liferay.asset.kernel.model.AssetCategory fetchCategory(
			HttpPrincipal httpPrincipal, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "fetchCategory",
				_fetchCategoryParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryId);

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

			return (com.liferay.asset.kernel.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategory
			fetchCategoryByExternalReferenceCode(
				HttpPrincipal httpPrincipal, String externalReferenceCode,
				long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class,
				"fetchCategoryByExternalReferenceCode",
				_fetchCategoryByExternalReferenceCodeParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, groupId);

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

			return (com.liferay.asset.kernel.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategory
			getAssetCategoryByExternalReferenceCode(
				HttpPrincipal httpPrincipal, long groupId,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class,
				"getAssetCategoryByExternalReferenceCode",
				_getAssetCategoryByExternalReferenceCodeParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, externalReferenceCode);

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

			return (com.liferay.asset.kernel.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories(
			HttpPrincipal httpPrincipal, long classNameId, long classPK,
			int start, int end) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getCategories",
				_getCategoriesParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.asset.kernel.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategory>
			getCategories(
				HttpPrincipal httpPrincipal, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getCategories",
				_getCategoriesParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK);

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

			return (java.util.List
				<com.liferay.asset.kernel.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCategoriesCount(
		HttpPrincipal httpPrincipal, long classNameId, long classPK) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getCategoriesCount",
				_getCategoriesCountParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
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

	public static com.liferay.asset.kernel.model.AssetCategory getCategory(
			HttpPrincipal httpPrincipal, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getCategory",
				_getCategoryParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryId);

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

			return (com.liferay.asset.kernel.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static String getCategoryPath(
			HttpPrincipal httpPrincipal, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getCategoryPath",
				_getCategoryPathParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryId);

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

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategory>
			getChildCategories(
				HttpPrincipal httpPrincipal, long parentCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getChildCategories",
				_getChildCategoriesParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, parentCategoryId);

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

			return (java.util.List
				<com.liferay.asset.kernel.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategory>
			getChildCategories(
				HttpPrincipal httpPrincipal, long parentCategoryId, int start,
				int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.asset.kernel.model.AssetCategory>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getChildCategories",
				_getChildCategoriesParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, parentCategoryId, start, end, orderByComparator);

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

			return (java.util.List
				<com.liferay.asset.kernel.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getChildCategoriesCount(
			HttpPrincipal httpPrincipal, long parentCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getChildCategoriesCount",
				_getChildCategoriesCountParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, parentCategoryId);

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

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategory>
			getVocabularyCategories(
				HttpPrincipal httpPrincipal, long vocabularyId, int start,
				int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.asset.kernel.model.AssetCategory>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getVocabularyCategories",
				_getVocabularyCategoriesParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, vocabularyId, start, end, orderByComparator);

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

			return (java.util.List
				<com.liferay.asset.kernel.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategory>
			getVocabularyCategories(
				HttpPrincipal httpPrincipal, long parentCategoryId,
				long vocabularyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.asset.kernel.model.AssetCategory>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getVocabularyCategories",
				_getVocabularyCategoriesParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, parentCategoryId, vocabularyId, start, end,
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

			return (java.util.List
				<com.liferay.asset.kernel.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getVocabularyCategories(
			HttpPrincipal httpPrincipal, long groupId, long parentCategoryId,
			long vocabularyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.asset.kernel.model.AssetCategory>
					orderByComparator) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getVocabularyCategories",
				_getVocabularyCategoriesParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, parentCategoryId, vocabularyId, start, end,
				orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.asset.kernel.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getVocabularyCategories(
			HttpPrincipal httpPrincipal, long groupId, String name,
			long vocabularyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.asset.kernel.model.AssetCategory>
					orderByComparator) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getVocabularyCategories",
				_getVocabularyCategoriesParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, name, vocabularyId, start, end,
				orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.asset.kernel.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getVocabularyCategoriesCount(
		HttpPrincipal httpPrincipal, long groupId, long vocabularyId) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getVocabularyCategoriesCount",
				_getVocabularyCategoriesCountParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, vocabularyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
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

	public static int getVocabularyCategoriesCount(
		HttpPrincipal httpPrincipal, long groupId, long parentCategory,
		long vocabularyId) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getVocabularyCategoriesCount",
				_getVocabularyCategoriesCountParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, parentCategory, vocabularyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
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

	public static int getVocabularyCategoriesCount(
		HttpPrincipal httpPrincipal, long groupId, String name,
		long vocabularyId) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getVocabularyCategoriesCount",
				_getVocabularyCategoriesCountParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, name, vocabularyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
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

	public static com.liferay.asset.kernel.model.AssetCategoryDisplay
			getVocabularyCategoriesDisplay(
				HttpPrincipal httpPrincipal, long vocabularyId, int start,
				int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.asset.kernel.model.AssetCategory>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class,
				"getVocabularyCategoriesDisplay",
				_getVocabularyCategoriesDisplayParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, vocabularyId, start, end, orderByComparator);

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

			return (com.liferay.asset.kernel.model.AssetCategoryDisplay)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategoryDisplay
			getVocabularyCategoriesDisplay(
				HttpPrincipal httpPrincipal, long groupId, String name,
				long vocabularyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.asset.kernel.model.AssetCategory>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class,
				"getVocabularyCategoriesDisplay",
				_getVocabularyCategoriesDisplayParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, name, vocabularyId, start, end,
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

			return (com.liferay.asset.kernel.model.AssetCategoryDisplay)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getVocabularyRootCategories(
			HttpPrincipal httpPrincipal, long groupId, long vocabularyId,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.asset.kernel.model.AssetCategory>
					orderByComparator) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "getVocabularyRootCategories",
				_getVocabularyRootCategoriesParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, vocabularyId, start, end,
				orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.asset.kernel.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getVocabularyRootCategoriesCount(
		HttpPrincipal httpPrincipal, long groupId, long vocabularyId) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class,
				"getVocabularyRootCategoriesCount",
				_getVocabularyRootCategoriesCountParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, vocabularyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
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

	public static com.liferay.asset.kernel.model.AssetCategory moveCategory(
			HttpPrincipal httpPrincipal, long categoryId, long parentCategoryId,
			long vocabularyId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "moveCategory",
				_moveCategoryParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryId, parentCategoryId, vocabularyId,
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

			return (com.liferay.asset.kernel.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		search(
			HttpPrincipal httpPrincipal, long groupId, String keywords,
			long vocabularyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.asset.kernel.model.AssetCategory>
					orderByComparator) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "search",
				_searchParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, keywords, vocabularyId, start, end,
				orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.asset.kernel.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray search(
			HttpPrincipal httpPrincipal, long groupId, String name,
			String[] categoryProperties, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "search",
				_searchParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, name, categoryProperties, start, end);

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

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray search(
			HttpPrincipal httpPrincipal, long[] groupIds, String name,
			long[] vocabularyIds, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "search",
				_searchParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupIds, name, vocabularyIds, start, end);

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

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategoryDisplay
			searchCategoriesDisplay(
				HttpPrincipal httpPrincipal, long groupId, String title,
				long vocabularyId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "searchCategoriesDisplay",
				_searchCategoriesDisplayParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, title, vocabularyId, start, end);

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

			return (com.liferay.asset.kernel.model.AssetCategoryDisplay)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategoryDisplay
			searchCategoriesDisplay(
				HttpPrincipal httpPrincipal, long groupId, String title,
				long parentCategoryId, long vocabularyId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "searchCategoriesDisplay",
				_searchCategoriesDisplayParameterTypes32);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, title, parentCategoryId, vocabularyId,
				start, end);

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

			return (com.liferay.asset.kernel.model.AssetCategoryDisplay)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategoryDisplay
			searchCategoriesDisplay(
				HttpPrincipal httpPrincipal, long groupId, String title,
				long vocabularyId, long parentCategoryId, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "searchCategoriesDisplay",
				_searchCategoriesDisplayParameterTypes33);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, title, vocabularyId, parentCategoryId,
				start, end, sort);

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

			return (com.liferay.asset.kernel.model.AssetCategoryDisplay)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategoryDisplay
			searchCategoriesDisplay(
				HttpPrincipal httpPrincipal, long[] groupIds, String title,
				long[] vocabularyIds, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "searchCategoriesDisplay",
				_searchCategoriesDisplayParameterTypes34);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupIds, title, vocabularyIds, start, end);

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

			return (com.liferay.asset.kernel.model.AssetCategoryDisplay)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategoryDisplay
			searchCategoriesDisplay(
				HttpPrincipal httpPrincipal, long[] groupIds, String title,
				long[] parentCategoryIds, long[] vocabularyIds, int start,
				int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "searchCategoriesDisplay",
				_searchCategoriesDisplayParameterTypes35);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupIds, title, parentCategoryIds, vocabularyIds,
				start, end);

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

			return (com.liferay.asset.kernel.model.AssetCategoryDisplay)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategoryDisplay
			searchCategoriesDisplay(
				HttpPrincipal httpPrincipal, long[] groupIds, String title,
				long[] vocabularyIds, long[] parentCategoryIds, int start,
				int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "searchCategoriesDisplay",
				_searchCategoriesDisplayParameterTypes36);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupIds, title, vocabularyIds, parentCategoryIds,
				start, end, sort);

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

			return (com.liferay.asset.kernel.model.AssetCategoryDisplay)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.asset.kernel.model.AssetCategory updateCategory(
			HttpPrincipal httpPrincipal, long categoryId, long parentCategoryId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			long vocabularyId, String[] categoryProperties,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryServiceUtil.class, "updateCategory",
				_updateCategoryParameterTypes37);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryId, parentCategoryId, titleMap,
				descriptionMap, vocabularyId, categoryProperties,
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

			return (com.liferay.asset.kernel.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AssetCategoryServiceHttp.class);

	private static final Class<?>[] _addCategoryParameterTypes0 = new Class[] {
		long.class, long.class, java.util.Map.class, java.util.Map.class,
		long.class, String[].class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _addCategoryParameterTypes1 = new Class[] {
		long.class, String.class, long.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _addCategoryParameterTypes2 = new Class[] {
		String.class, long.class, long.class, java.util.Map.class,
		java.util.Map.class, long.class, String[].class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _deleteCategoriesParameterTypes3 =
		new Class[] {long[].class};
	private static final Class<?>[] _deleteCategoryParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _fetchCategoryParameterTypes5 =
		new Class[] {long.class};
	private static final Class<?>[]
		_fetchCategoryByExternalReferenceCodeParameterTypes6 = new Class[] {
			String.class, long.class
		};
	private static final Class<?>[]
		_getAssetCategoryByExternalReferenceCodeParameterTypes7 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getCategoriesParameterTypes8 =
		new Class[] {long.class, long.class, int.class, int.class};
	private static final Class<?>[] _getCategoriesParameterTypes9 =
		new Class[] {String.class, long.class};
	private static final Class<?>[] _getCategoriesCountParameterTypes10 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _getCategoryParameterTypes11 = new Class[] {
		long.class
	};
	private static final Class<?>[] _getCategoryPathParameterTypes12 =
		new Class[] {long.class};
	private static final Class<?>[] _getChildCategoriesParameterTypes13 =
		new Class[] {long.class};
	private static final Class<?>[] _getChildCategoriesParameterTypes14 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getChildCategoriesCountParameterTypes15 =
		new Class[] {long.class};
	private static final Class<?>[] _getVocabularyCategoriesParameterTypes16 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyCategoriesParameterTypes17 =
		new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyCategoriesParameterTypes18 =
		new Class[] {
			long.class, long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyCategoriesParameterTypes19 =
		new Class[] {
			long.class, String.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getVocabularyCategoriesCountParameterTypes20 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[]
		_getVocabularyCategoriesCountParameterTypes21 = new Class[] {
			long.class, long.class, long.class
		};
	private static final Class<?>[]
		_getVocabularyCategoriesCountParameterTypes22 = new Class[] {
			long.class, String.class, long.class
		};
	private static final Class<?>[]
		_getVocabularyCategoriesDisplayParameterTypes23 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getVocabularyCategoriesDisplayParameterTypes24 = new Class[] {
			long.class, String.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getVocabularyRootCategoriesParameterTypes25 = new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getVocabularyRootCategoriesCountParameterTypes26 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _moveCategoryParameterTypes27 =
		new Class[] {
			long.class, long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _searchParameterTypes28 = new Class[] {
		long.class, String.class, long.class, int.class, int.class,
		com.liferay.portal.kernel.util.OrderByComparator.class
	};
	private static final Class<?>[] _searchParameterTypes29 = new Class[] {
		long.class, String.class, String[].class, int.class, int.class
	};
	private static final Class<?>[] _searchParameterTypes30 = new Class[] {
		long[].class, String.class, long[].class, int.class, int.class
	};
	private static final Class<?>[] _searchCategoriesDisplayParameterTypes31 =
		new Class[] {
			long.class, String.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _searchCategoriesDisplayParameterTypes32 =
		new Class[] {
			long.class, String.class, long.class, long.class, int.class,
			int.class
		};
	private static final Class<?>[] _searchCategoriesDisplayParameterTypes33 =
		new Class[] {
			long.class, String.class, long.class, long.class, int.class,
			int.class, com.liferay.portal.kernel.search.Sort.class
		};
	private static final Class<?>[] _searchCategoriesDisplayParameterTypes34 =
		new Class[] {
			long[].class, String.class, long[].class, int.class, int.class
		};
	private static final Class<?>[] _searchCategoriesDisplayParameterTypes35 =
		new Class[] {
			long[].class, String.class, long[].class, long[].class, int.class,
			int.class
		};
	private static final Class<?>[] _searchCategoriesDisplayParameterTypes36 =
		new Class[] {
			long[].class, String.class, long[].class, long[].class, int.class,
			int.class, com.liferay.portal.kernel.search.Sort.class
		};
	private static final Class<?>[] _updateCategoryParameterTypes37 =
		new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			long.class, String[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}