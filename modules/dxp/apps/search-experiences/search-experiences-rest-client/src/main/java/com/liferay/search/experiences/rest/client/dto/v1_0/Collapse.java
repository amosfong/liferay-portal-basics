/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.rest.client.dto.v1_0;

import com.liferay.search.experiences.rest.client.function.UnsafeSupplier;
import com.liferay.search.experiences.rest.client.serdes.v1_0.CollapseSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@Generated("")
public class Collapse implements Cloneable, Serializable {

	public static Collapse toDTO(String json) {
		return CollapseSerDes.toDTO(json);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setField(
		UnsafeSupplier<String, Exception> fieldUnsafeSupplier) {

		try {
			field = fieldUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String field;

	public InnerHit[] getInnerHits() {
		return innerHits;
	}

	public void setInnerHits(InnerHit[] innerHits) {
		this.innerHits = innerHits;
	}

	public void setInnerHits(
		UnsafeSupplier<InnerHit[], Exception> innerHitsUnsafeSupplier) {

		try {
			innerHits = innerHitsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected InnerHit[] innerHits;

	public Integer getMaxConcurrentGroupRequests() {
		return maxConcurrentGroupRequests;
	}

	public void setMaxConcurrentGroupRequests(
		Integer maxConcurrentGroupRequests) {

		this.maxConcurrentGroupRequests = maxConcurrentGroupRequests;
	}

	public void setMaxConcurrentGroupRequests(
		UnsafeSupplier<Integer, Exception>
			maxConcurrentGroupRequestsUnsafeSupplier) {

		try {
			maxConcurrentGroupRequests =
				maxConcurrentGroupRequestsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer maxConcurrentGroupRequests;

	@Override
	public Collapse clone() throws CloneNotSupportedException {
		return (Collapse)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Collapse)) {
			return false;
		}

		Collapse collapse = (Collapse)object;

		return Objects.equals(toString(), collapse.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return CollapseSerDes.toJSON(this);
	}

}