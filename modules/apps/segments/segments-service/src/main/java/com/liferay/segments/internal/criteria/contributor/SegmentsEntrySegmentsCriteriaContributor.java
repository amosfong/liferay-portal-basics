/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal.criteria.contributor;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.segments.criteria.Criteria;
import com.liferay.segments.criteria.contributor.SegmentsCriteriaContributor;
import com.liferay.segments.criteria.mapper.SegmentsCriteriaJSONObjectMapper;
import com.liferay.segments.field.Field;
import com.liferay.segments.internal.odata.entity.EntityModelFieldMapper;
import com.liferay.segments.internal.odata.entity.SegmentsEntryEntityModel;

import java.util.List;

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
		"segments.criteria.contributor.key=" + SegmentsEntrySegmentsCriteriaContributor.KEY,
		"segments.criteria.contributor.model.class.name=*",
		"segments.criteria.contributor.priority:Integer=40"
	},
	service = SegmentsCriteriaContributor.class
)
public class SegmentsEntrySegmentsCriteriaContributor
	implements SegmentsCriteriaContributor {

	public static final String KEY = "segments";

	@Override
	public void contribute(
		Criteria criteria, String filterString,
		Criteria.Conjunction conjunction) {

		criteria.addCriterion(getKey(), getType(), filterString, conjunction);
		criteria.addFilter(getType(), filterString, conjunction);

		// Contribute to model filter to support preview

		criteria.addFilter(Criteria.Type.MODEL, filterString, conjunction);
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
		return SegmentsEntryEntityModel.NAME;
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
		return Criteria.Type.REFERRED;
	}

	@Reference(
		cardinality = ReferenceCardinality.MANDATORY,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(entity.model.name=" + SegmentsEntryEntityModel.NAME + ")"
	)
	private volatile EntityModel _entityModel;

	@Reference
	private EntityModelFieldMapper _entityModelFieldMapper;

	@Reference(target = "(segments.criteria.mapper.key=odata)")
	private SegmentsCriteriaJSONObjectMapper _segmentsCriteriaJSONObjectMapper;

}