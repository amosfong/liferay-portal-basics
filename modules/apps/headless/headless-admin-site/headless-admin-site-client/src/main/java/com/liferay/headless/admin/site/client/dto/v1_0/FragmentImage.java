/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.dto.v1_0;

import com.liferay.headless.admin.site.client.function.UnsafeSupplier;
import com.liferay.headless.admin.site.client.serdes.v1_0.FragmentImageSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Rubén Pulido
 * @generated
 */
@Generated("")
public class FragmentImage implements Cloneable, Serializable {

	public static FragmentImage toDTO(String json) {
		return FragmentImageSerDes.toDTO(json);
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public void setConfig(
		UnsafeSupplier<Config, Exception> configUnsafeSupplier) {

		try {
			config = configUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Config config;

	public Object getDescription() {
		return description;
	}

	public void setDescription(Object description) {
		this.description = description;
	}

	public void setDescription(
		UnsafeSupplier<Object, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object description;

	public ItemExternalReference getItemExternalReference() {
		return itemExternalReference;
	}

	public void setItemExternalReference(
		ItemExternalReference itemExternalReference) {

		this.itemExternalReference = itemExternalReference;
	}

	public void setItemExternalReference(
		UnsafeSupplier<ItemExternalReference, Exception>
			itemExternalReferenceUnsafeSupplier) {

		try {
			itemExternalReference = itemExternalReferenceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ItemExternalReference itemExternalReference;

	public Object getTitle() {
		return title;
	}

	public void setTitle(Object title) {
		this.title = title;
	}

	public void setTitle(
		UnsafeSupplier<Object, Exception> titleUnsafeSupplier) {

		try {
			title = titleUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object title;

	public Object getUrl() {
		return url;
	}

	public void setUrl(Object url) {
		this.url = url;
	}

	public void setUrl(UnsafeSupplier<Object, Exception> urlUnsafeSupplier) {
		try {
			url = urlUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object url;

	@Override
	public FragmentImage clone() throws CloneNotSupportedException {
		return (FragmentImage)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FragmentImage)) {
			return false;
		}

		FragmentImage fragmentImage = (FragmentImage)object;

		return Objects.equals(toString(), fragmentImage.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return FragmentImageSerDes.toJSON(this);
	}

}