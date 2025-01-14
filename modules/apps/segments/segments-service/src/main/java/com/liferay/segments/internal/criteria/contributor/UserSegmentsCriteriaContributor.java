/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal.criteria.contributor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.segments.criteria.Criteria;
import com.liferay.segments.criteria.contributor.SegmentsCriteriaContributor;
import com.liferay.segments.criteria.mapper.SegmentsCriteriaJSONObjectMapper;
import com.liferay.segments.field.Field;
import com.liferay.segments.internal.odata.entity.EntityModelFieldMapper;
import com.liferay.segments.internal.odata.entity.UserEntityModel;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Eduardo García
 */
@Component(
	property = {
		"segments.criteria.contributor.key=" + UserSegmentsCriteriaContributor.KEY,
		"segments.criteria.contributor.model.class.name=com.liferay.portal.kernel.model.User",
		"segments.criteria.contributor.priority:Integer=10"
	},
	service = SegmentsCriteriaContributor.class
)
public class UserSegmentsCriteriaContributor
	implements SegmentsCriteriaContributor {

	public static final String KEY = "user";

	@Override
	public void contribute(
		Criteria criteria, String filterString,
		Criteria.Conjunction conjunction) {

		criteria.addCriterion(getKey(), getType(), filterString, conjunction);

		String newFilterString = filterString;

		Matcher matcher = _pattern.matcher(filterString);

		while (matcher.find()) {
			newFilterString = StringUtil.replace(
				newFilterString, matcher.group(),
				String.join(
					StringPool.BLANK, StringPool.OPEN_PARENTHESIS,
					matcher.group(), StringPool.SPACE,
					Criteria.Conjunction.OR.getValue(), StringPool.SPACE,
					"inheritedRoleIds eq ", StringPool.APOSTROPHE,
					String.valueOf(_getRoleId(matcher.group())),
					StringPool.APOSTROPHE, StringPool.CLOSE_PARENTHESIS));
		}

		criteria.addFilter(getType(), newFilterString, conjunction);
	}

	@Override
	public JSONObject getCriteriaJSONObject(Criteria criteria)
		throws Exception {

		return _segmentsCriteriaJSONObjectMapper.toJSONObject(criteria, this);
	}

	@Override
	public EntityModel getEntityModel() {
		return _entityModel;
	}

	@Override
	public String getEntityName() {
		return UserEntityModel.NAME;
	}

	@Override
	public List<Field> getFields(PortletRequest portletRequest) {
		return _entityModelFieldMapper.getFields(_entityModel, portletRequest);
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public Criteria.Type getType() {
		return Criteria.Type.MODEL;
	}

	private long _getRoleId(String criterionString) {
		int indexOf = criterionString.indexOf("'");
		int lastIndexOf = criterionString.lastIndexOf("'");

		if ((indexOf == -1) || (lastIndexOf == -1)) {
			return -1;
		}

		return GetterUtil.getLong(
			criterionString.substring(indexOf + 1, lastIndexOf));
	}

	private static final Pattern _pattern = Pattern.compile(
		"roleIds eq '\\d+'");

	@Reference(
		cardinality = ReferenceCardinality.MANDATORY,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(entity.model.name=" + UserEntityModel.NAME + ")"
	)
	private volatile EntityModel _entityModel;

	@Reference
	private EntityModelFieldMapper _entityModelFieldMapper;

	@Reference(target = "(segments.criteria.mapper.key=odata)")
	private SegmentsCriteriaJSONObjectMapper _segmentsCriteriaJSONObjectMapper;

}