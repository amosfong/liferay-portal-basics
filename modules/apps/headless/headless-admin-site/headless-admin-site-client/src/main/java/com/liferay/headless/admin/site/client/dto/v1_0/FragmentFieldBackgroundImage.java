/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.dto.v1_0;

import com.liferay.headless.admin.site.client.function.UnsafeSupplier;
import com.liferay.headless.admin.site.client.serdes.v1_0.FragmentFieldBackgroundImageSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Rubén Pulido
 * @generated
 */
@Generated("")
public class FragmentFieldBackgroundImage implements Cloneable, Serializable {

	public static FragmentFieldBackgroundImage toDTO(String json) {
		return FragmentFieldBackgroundImageSerDes.toDTO(json);
	}

	public FragmentImage getBackgroundFragmentImage() {
		return backgroundFragmentImage;
	}

	public void setBackgroundFragmentImage(
		FragmentImage backgroundFragmentImage) {

		this.backgroundFragmentImage = backgroundFragmentImage;
	}

	public void setBackgroundFragmentImage(
		UnsafeSupplier<FragmentImage, Exception>
			backgroundFragmentImageUnsafeSupplier) {

		try {
			backgroundFragmentImage =
				backgroundFragmentImageUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected FragmentImage backgroundFragmentImage;

	@Override
	public FragmentFieldBackgroundImage clone()
		throws CloneNotSupportedException {

		return (FragmentFieldBackgroundImage)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FragmentFieldBackgroundImage)) {
			return false;
		}

		FragmentFieldBackgroundImage fragmentFieldBackgroundImage =
			(FragmentFieldBackgroundImage)object;

		return Objects.equals(
			toString(), fragmentFieldBackgroundImage.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return FragmentFieldBackgroundImageSerDes.toJSON(this);
	}

}