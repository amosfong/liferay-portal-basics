/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.filter;

import com.liferay.portal.search.filter.ComplexQueryPart;
import com.liferay.portal.search.filter.ComplexQueryPartBuilder;
import com.liferay.portal.search.query.Query;

/**
 * @author André de Oliveira
 */
public class ComplexQueryPartImpl implements ComplexQueryPart {

	public ComplexQueryPartImpl() {
	}

	public ComplexQueryPartImpl(ComplexQueryPartImpl complexQueryPartImpl) {
		_additive = complexQueryPartImpl._additive;
		_boost = complexQueryPartImpl._boost;
		_disabled = complexQueryPartImpl._disabled;
		_field = complexQueryPartImpl._field;
		_rootClause = complexQueryPartImpl._rootClause;
		_name = complexQueryPartImpl._name;
		_occur = complexQueryPartImpl._occur;
		_parent = complexQueryPartImpl._parent;
		_query = complexQueryPartImpl._query;
		_type = complexQueryPartImpl._type;
		_value = complexQueryPartImpl._value;
	}

	@Override
	public Float getBoost() {
		return _boost;
	}

	@Override
	public String getField() {
		return _field;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getOccur() {
		return _occur;
	}

	@Override
	public String getParent() {
		return _parent;
	}

	@Override
	public Query getQuery() {
		return _query;
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public String getValue() {
		return _value;
	}

	@Override
	public boolean isAdditive() {
		return _additive;
	}

	@Override
	public boolean isDisabled() {
		return _disabled;
	}

	@Override
	public boolean isRootClause() {
		return _rootClause;
	}

	public static class Builder implements ComplexQueryPartBuilder {

		@Override
		public ComplexQueryPartBuilder additive(boolean additive) {
			_complexQueryPartImpl._additive = additive;

			return this;
		}

		@Override
		public ComplexQueryPartBuilder boost(Float boost) {
			_complexQueryPartImpl._boost = boost;

			return this;
		}

		@Override
		public ComplexQueryPart build() {
			return new ComplexQueryPartImpl(_complexQueryPartImpl);
		}

		@Override
		public ComplexQueryPartBuilder disabled(boolean disabled) {
			_complexQueryPartImpl._disabled = disabled;

			return this;
		}

		@Override
		public Builder field(String field) {
			_complexQueryPartImpl._field = field;

			return this;
		}

		@Override
		public ComplexQueryPartBuilder name(String name) {
			_complexQueryPartImpl._name = name;

			return this;
		}

		@Override
		public ComplexQueryPartBuilder occur(String occur) {
			_complexQueryPartImpl._occur = occur;

			return this;
		}

		@Override
		public ComplexQueryPartBuilder parent(String parent) {
			_complexQueryPartImpl._parent = parent;

			return this;
		}

		@Override
		public ComplexQueryPartBuilder query(Query query) {
			_complexQueryPartImpl._query = query;

			return this;
		}

		@Override
		public Builder rootClause(boolean rootClause) {
			_complexQueryPartImpl._rootClause = rootClause;

			return this;
		}

		@Override
		public Builder type(String type) {
			_complexQueryPartImpl._type = type;

			return this;
		}

		@Override
		public Builder value(String value) {
			_complexQueryPartImpl._value = value;

			return this;
		}

		private final ComplexQueryPartImpl _complexQueryPartImpl =
			new ComplexQueryPartImpl();

	}

	private boolean _additive;
	private Float _boost;
	private boolean _disabled;
	private String _field;
	private String _name;
	private String _occur;
	private String _parent;
	private Query _query;
	private boolean _rootClause;
	private String _type;
	private String _value;

}