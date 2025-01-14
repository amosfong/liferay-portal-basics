/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.dto.v1_0;

import com.liferay.headless.admin.site.client.function.UnsafeSupplier;
import com.liferay.headless.admin.site.client.serdes.v1_0.SEOSettingsSerDes;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Rubén Pulido
 * @generated
 */
@Generated("")
public class SEOSettings implements Cloneable, Serializable {

	public static SEOSettings toDTO(String json) {
		return SEOSettingsSerDes.toDTO(json);
	}

	public Map<String, String> getCustomCanonicalURL_i18n() {
		return customCanonicalURL_i18n;
	}

	public void setCustomCanonicalURL_i18n(
		Map<String, String> customCanonicalURL_i18n) {

		this.customCanonicalURL_i18n = customCanonicalURL_i18n;
	}

	public void setCustomCanonicalURL_i18n(
		UnsafeSupplier<Map<String, String>, Exception>
			customCanonicalURL_i18nUnsafeSupplier) {

		try {
			customCanonicalURL_i18n =
				customCanonicalURL_i18nUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, String> customCanonicalURL_i18n;

	public Map<String, String> getDescription_i18n() {
		return description_i18n;
	}

	public void setDescription_i18n(Map<String, String> description_i18n) {
		this.description_i18n = description_i18n;
	}

	public void setDescription_i18n(
		UnsafeSupplier<Map<String, String>, Exception>
			description_i18nUnsafeSupplier) {

		try {
			description_i18n = description_i18nUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, String> description_i18n;

	public Map<String, String> getHtmlTitle_i18n() {
		return htmlTitle_i18n;
	}

	public void setHtmlTitle_i18n(Map<String, String> htmlTitle_i18n) {
		this.htmlTitle_i18n = htmlTitle_i18n;
	}

	public void setHtmlTitle_i18n(
		UnsafeSupplier<Map<String, String>, Exception>
			htmlTitle_i18nUnsafeSupplier) {

		try {
			htmlTitle_i18n = htmlTitle_i18nUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, String> htmlTitle_i18n;

	public Map<String, String> getRobots_i18n() {
		return robots_i18n;
	}

	public void setRobots_i18n(Map<String, String> robots_i18n) {
		this.robots_i18n = robots_i18n;
	}

	public void setRobots_i18n(
		UnsafeSupplier<Map<String, String>, Exception>
			robots_i18nUnsafeSupplier) {

		try {
			robots_i18n = robots_i18nUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, String> robots_i18n;

	public Map<String, String> getSeoKeywords_i18n() {
		return seoKeywords_i18n;
	}

	public void setSeoKeywords_i18n(Map<String, String> seoKeywords_i18n) {
		this.seoKeywords_i18n = seoKeywords_i18n;
	}

	public void setSeoKeywords_i18n(
		UnsafeSupplier<Map<String, String>, Exception>
			seoKeywords_i18nUnsafeSupplier) {

		try {
			seoKeywords_i18n = seoKeywords_i18nUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, String> seoKeywords_i18n;

	public SiteMapSettings getSiteMapSettings() {
		return siteMapSettings;
	}

	public void setSiteMapSettings(SiteMapSettings siteMapSettings) {
		this.siteMapSettings = siteMapSettings;
	}

	public void setSiteMapSettings(
		UnsafeSupplier<SiteMapSettings, Exception>
			siteMapSettingsUnsafeSupplier) {

		try {
			siteMapSettings = siteMapSettingsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected SiteMapSettings siteMapSettings;

	@Override
	public SEOSettings clone() throws CloneNotSupportedException {
		return (SEOSettings)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SEOSettings)) {
			return false;
		}

		SEOSettings seoSettings = (SEOSettings)object;

		return Objects.equals(toString(), seoSettings.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return SEOSettingsSerDes.toJSON(this);
	}

}