/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.internal.jaxrs.param.converter.provider;

import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.scope.util.GroupUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.StringUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.utils.AnnotationUtils;

/**
 * @author Jorge García Jiménez
 */
@Provider
public class ScopeKeyParamConverterProvider
	implements ParamConverter<String>, ParamConverterProvider {

	public ScopeKeyParamConverterProvider(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Override
	public String fromString(String parameter) {
		if (parameter == null) {
			return null;
		}

		if (StringUtil.equals(
				_objectDefinition.getScope(),
				ObjectDefinitionConstants.SCOPE_SITE)) {

			String groupId = _getGroupId(_company.getCompanyId(), parameter);

			if (groupId != null) {
				return groupId;
			}

			throw new NotFoundException(
				"Unable to get a valid site with ID " + parameter);
		}

		throw new InternalServerErrorException("Unexpected scopeKey parameter");
	}

	@Override
	public <T> ParamConverter<T> getConverter(
		Class<T> clazz, Type type, Annotation[] annotations) {

		if (String.class.equals(clazz) && _hasScopeKeyAnnotation(annotations)) {
			return (ParamConverter<T>)this;
		}

		return null;
	}

	@Override
	public String toString(String parameter) {
		return String.valueOf(parameter);
	}

	private String _getGroupId(long companyId, String scopeKey) {
		Long groupId = GroupUtil.getGroupId(
			companyId, scopeKey, _groupLocalService);

		if (groupId == null) {
			return null;
		}

		return String.valueOf(groupId);
	}

	private boolean _hasScopeKeyAnnotation(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			if ((annotation.annotationType() == PathParam.class) &&
				StringUtil.equals(
					AnnotationUtils.getAnnotationValue(annotation),
					"scopeKey")) {

				return true;
			}
		}

		return false;
	}

	@Context
	private Company _company;

	private final GroupLocalService _groupLocalService;

	@Context
	private ObjectDefinition _objectDefinition;

	@Context
	private UriInfo _uriInfo;

}