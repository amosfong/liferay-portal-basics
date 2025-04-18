/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.param.converter.provider;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.vulcan.util.GroupUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.utils.AnnotationUtils;

/**
 * @author Javier Gamarra
 */
@Provider
public class SiteParamConverterProvider
	implements ParamConverter<Long>, ParamConverterProvider {

	public SiteParamConverterProvider(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Override
	public Long fromString(String parameter) {
		MultivaluedMap<String, String> multivaluedMap =
			_uriInfo.getPathParameters();

		Long siteId = null;

		siteId = getGroupId(
			_company.getCompanyId(), multivaluedMap.getFirst("siteId"));

		if (siteId != null) {
			return siteId;
		}

		StringBundler sb = new StringBundler(4);

		sb.append("Unable to get a valid ");

		if (multivaluedMap.containsKey("assetLibraryId")) {
			sb.append("asset library");
		}
		else {
			sb.append("site");
		}

		sb.append(" with ID ");
		sb.append(parameter);

		throw new NotFoundException(sb.toString());
	}

	@Override
	public <T> ParamConverter<T> getConverter(
		Class<T> clazz, Type type, Annotation[] annotations) {

		if (Long.class.equals(clazz) && _hasSiteIdAnnotation(annotations)) {
			return (ParamConverter<T>)this;
		}

		return null;
	}

	public Long getGroupId(long companyId, String siteKey) {
		if (siteKey == null) {
			return null;
		}

		return GroupUtil.getGroupId(companyId, siteKey, _groupLocalService);
	}

	@Override
	public String toString(Long parameter) {
		return String.valueOf(parameter);
	}

	private boolean _hasSiteIdAnnotation(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			if ((annotation.annotationType() == PathParam.class) &&
				StringUtils.equalsAny(
					AnnotationUtils.getAnnotationValue(annotation),
					"assetLibraryId", "siteId")) {

				return true;
			}
		}

		return false;
	}

	@Context
	private Company _company;

	private final GroupLocalService _groupLocalService;

	@Context
	private UriInfo _uriInfo;

}