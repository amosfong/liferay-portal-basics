/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.dto.v1_0;

import com.liferay.headless.admin.site.client.function.UnsafeSupplier;
import com.liferay.headless.admin.site.client.serdes.v1_0.ClassSubtypeReferenceSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Rubén Pulido
 * @generated
 */
@Generated("")
public class ClassSubtypeReference implements Cloneable, Serializable {

	public static ClassSubtypeReference toDTO(String json) {
		return ClassSubtypeReferenceSerDes.toDTO(json);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setClassName(
		UnsafeSupplier<String, Exception> classNameUnsafeSupplier) {

		try {
			className = classNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String className;

	public ItemExternalReference getSubTypeExternalReference() {
		return subTypeExternalReference;
	}

	public void setSubTypeExternalReference(
		ItemExternalReference subTypeExternalReference) {

		this.subTypeExternalReference = subTypeExternalReference;
	}

	public void setSubTypeExternalReference(
		UnsafeSupplier<ItemExternalReference, Exception>
			subTypeExternalReferenceUnsafeSupplier) {

		try {
			subTypeExternalReference =
				subTypeExternalReferenceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ItemExternalReference subTypeExternalReference;

	@Override
	public ClassSubtypeReference clone() throws CloneNotSupportedException {
		return (ClassSubtypeReference)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ClassSubtypeReference)) {
			return false;
		}

		ClassSubtypeReference classSubtypeReference =
			(ClassSubtypeReference)object;

		return Objects.equals(toString(), classSubtypeReference.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ClassSubtypeReferenceSerDes.toJSON(this);
	}

}