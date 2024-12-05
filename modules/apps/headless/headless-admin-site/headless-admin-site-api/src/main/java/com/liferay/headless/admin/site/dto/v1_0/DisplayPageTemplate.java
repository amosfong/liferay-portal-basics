/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.headless.admin.user.dto.v1_0.Creator;
import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Rubén Pulido
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "A display page template.", value = "DisplayPageTemplate"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "DisplayPageTemplate")
public class DisplayPageTemplate implements Serializable {

	public static DisplayPageTemplate toDTO(String json) {
		return ObjectMapperUtil.readValue(DisplayPageTemplate.class, json);
	}

	public static DisplayPageTemplate unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			DisplayPageTemplate.class, json);
	}

	@Schema(
		description = "The content type (and optionally subtype) associated to the display page template."
	)
	@Valid
	public ClassSubtypeReference getContentTypeReference() {
		if (_contentTypeReferenceSupplier != null) {
			contentTypeReference = _contentTypeReferenceSupplier.get();

			_contentTypeReferenceSupplier = null;
		}

		return contentTypeReference;
	}

	public void setContentTypeReference(
		ClassSubtypeReference contentTypeReference) {

		this.contentTypeReference = contentTypeReference;

		_contentTypeReferenceSupplier = null;
	}

	@JsonIgnore
	public void setContentTypeReference(
		UnsafeSupplier<ClassSubtypeReference, Exception>
			contentTypeReferenceUnsafeSupplier) {

		_contentTypeReferenceSupplier = () -> {
			try {
				return contentTypeReferenceUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "The content type (and optionally subtype) associated to the display page template."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected ClassSubtypeReference contentTypeReference;

	@JsonIgnore
	private Supplier<ClassSubtypeReference> _contentTypeReferenceSupplier;

	@Schema(
		description = "The display page template's creator. It is not returned by default. It can be embedded via nestedFields."
	)
	@Valid
	public Creator getCreator() {
		if (_creatorSupplier != null) {
			creator = _creatorSupplier.get();

			_creatorSupplier = null;
		}

		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;

		_creatorSupplier = null;
	}

	@JsonIgnore
	public void setCreator(
		UnsafeSupplier<Creator, Exception> creatorUnsafeSupplier) {

		_creatorSupplier = () -> {
			try {
				return creatorUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "The display page template's creator. It is not returned by default. It can be embedded via nestedFields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Creator creator;

	@JsonIgnore
	private Supplier<Creator> _creatorSupplier;

	@Schema(
		description = "The display page template's creator external reference code."
	)
	public String getCreatorExternalReferenceCode() {
		if (_creatorExternalReferenceCodeSupplier != null) {
			creatorExternalReferenceCode =
				_creatorExternalReferenceCodeSupplier.get();

			_creatorExternalReferenceCodeSupplier = null;
		}

		return creatorExternalReferenceCode;
	}

	public void setCreatorExternalReferenceCode(
		String creatorExternalReferenceCode) {

		this.creatorExternalReferenceCode = creatorExternalReferenceCode;

		_creatorExternalReferenceCodeSupplier = null;
	}

	@JsonIgnore
	public void setCreatorExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			creatorExternalReferenceCodeUnsafeSupplier) {

		_creatorExternalReferenceCodeSupplier = () -> {
			try {
				return creatorExternalReferenceCodeUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "The display page template's creator external reference code."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String creatorExternalReferenceCode;

	@JsonIgnore
	private Supplier<String> _creatorExternalReferenceCodeSupplier;

	@Schema(description = "The display page template's creation date.")
	public Date getDateCreated() {
		if (_dateCreatedSupplier != null) {
			dateCreated = _dateCreatedSupplier.get();

			_dateCreatedSupplier = null;
		}

		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;

		_dateCreatedSupplier = null;
	}

	@JsonIgnore
	public void setDateCreated(
		UnsafeSupplier<Date, Exception> dateCreatedUnsafeSupplier) {

		_dateCreatedSupplier = () -> {
			try {
				return dateCreatedUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "The display page template's creation date.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date dateCreated;

	@JsonIgnore
	private Supplier<Date> _dateCreatedSupplier;

	@Schema(
		description = "The last time any field of the display page template was changed."
	)
	public Date getDateModified() {
		if (_dateModifiedSupplier != null) {
			dateModified = _dateModifiedSupplier.get();

			_dateModifiedSupplier = null;
		}

		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;

		_dateModifiedSupplier = null;
	}

	@JsonIgnore
	public void setDateModified(
		UnsafeSupplier<Date, Exception> dateModifiedUnsafeSupplier) {

		_dateModifiedSupplier = () -> {
			try {
				return dateModifiedUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "The last time any field of the display page template was changed."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date dateModified;

	@JsonIgnore
	private Supplier<Date> _dateModifiedSupplier;

	@Schema(
		description = "The display page template's most recent publication date."
	)
	public Date getDatePublished() {
		if (_datePublishedSupplier != null) {
			datePublished = _datePublishedSupplier.get();

			_datePublishedSupplier = null;
		}

		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;

		_datePublishedSupplier = null;
	}

	@JsonIgnore
	public void setDatePublished(
		UnsafeSupplier<Date, Exception> datePublishedUnsafeSupplier) {

		_datePublishedSupplier = () -> {
			try {
				return datePublishedUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "The display page template's most recent publication date."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date datePublished;

	@JsonIgnore
	private Supplier<Date> _datePublishedSupplier;

	@Schema(
		description = "Settings of the display page template, such as SEO or OpenGraph."
	)
	@Valid
	public DisplayPageTemplateSettings getDisplayPageTemplateSettings() {
		if (_displayPageTemplateSettingsSupplier != null) {
			displayPageTemplateSettings =
				_displayPageTemplateSettingsSupplier.get();

			_displayPageTemplateSettingsSupplier = null;
		}

		return displayPageTemplateSettings;
	}

	public void setDisplayPageTemplateSettings(
		DisplayPageTemplateSettings displayPageTemplateSettings) {

		this.displayPageTemplateSettings = displayPageTemplateSettings;

		_displayPageTemplateSettingsSupplier = null;
	}

	@JsonIgnore
	public void setDisplayPageTemplateSettings(
		UnsafeSupplier<DisplayPageTemplateSettings, Exception>
			displayPageTemplateSettingsUnsafeSupplier) {

		_displayPageTemplateSettingsSupplier = () -> {
			try {
				return displayPageTemplateSettingsUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "Settings of the display page template, such as SEO or OpenGraph."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected DisplayPageTemplateSettings displayPageTemplateSettings;

	@JsonIgnore
	private Supplier<DisplayPageTemplateSettings>
		_displayPageTemplateSettingsSupplier;

	@Schema(
		description = "The display page template's external reference code."
	)
	public String getExternalReferenceCode() {
		if (_externalReferenceCodeSupplier != null) {
			externalReferenceCode = _externalReferenceCodeSupplier.get();

			_externalReferenceCodeSupplier = null;
		}

		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;

		_externalReferenceCodeSupplier = null;
	}

	@JsonIgnore
	public void setExternalReferenceCode(
		UnsafeSupplier<String, Exception> externalReferenceCodeUnsafeSupplier) {

		_externalReferenceCodeSupplier = () -> {
			try {
				return externalReferenceCodeUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "The display page template's external reference code."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String externalReferenceCode;

	@JsonIgnore
	private Supplier<String> _externalReferenceCodeSupplier;

	@Schema(
		description = "The history of previously used URLs to the display page template's rendered content. This field is not returned by default. It can be requested via nestedFields."
	)
	@Valid
	public FriendlyUrlHistory getFriendlyUrlHistory() {
		if (_friendlyUrlHistorySupplier != null) {
			friendlyUrlHistory = _friendlyUrlHistorySupplier.get();

			_friendlyUrlHistorySupplier = null;
		}

		return friendlyUrlHistory;
	}

	public void setFriendlyUrlHistory(FriendlyUrlHistory friendlyUrlHistory) {
		this.friendlyUrlHistory = friendlyUrlHistory;

		_friendlyUrlHistorySupplier = null;
	}

	@JsonIgnore
	public void setFriendlyUrlHistory(
		UnsafeSupplier<FriendlyUrlHistory, Exception>
			friendlyUrlHistoryUnsafeSupplier) {

		_friendlyUrlHistorySupplier = () -> {
			try {
				return friendlyUrlHistoryUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "The history of previously used URLs to the display page template's rendered content. This field is not returned by default. It can be requested via nestedFields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected FriendlyUrlHistory friendlyUrlHistory;

	@JsonIgnore
	private Supplier<FriendlyUrlHistory> _friendlyUrlHistorySupplier;

	@Schema(
		description = "The localized relative URLs to the display page template's rendered content."
	)
	@Valid
	public Map<String, String> getFriendlyUrlPath_i18n() {
		if (_friendlyUrlPath_i18nSupplier != null) {
			friendlyUrlPath_i18n = _friendlyUrlPath_i18nSupplier.get();

			_friendlyUrlPath_i18nSupplier = null;
		}

		return friendlyUrlPath_i18n;
	}

	public void setFriendlyUrlPath_i18n(
		Map<String, String> friendlyUrlPath_i18n) {

		this.friendlyUrlPath_i18n = friendlyUrlPath_i18n;

		_friendlyUrlPath_i18nSupplier = null;
	}

	@JsonIgnore
	public void setFriendlyUrlPath_i18n(
		UnsafeSupplier<Map<String, String>, Exception>
			friendlyUrlPath_i18nUnsafeSupplier) {

		_friendlyUrlPath_i18nSupplier = () -> {
			try {
				return friendlyUrlPath_i18nUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "The localized relative URLs to the display page template's rendered content."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, String> friendlyUrlPath_i18n;

	@JsonIgnore
	private Supplier<Map<String, String>> _friendlyUrlPath_i18nSupplier;

	@Schema(description = "The display page template's key.")
	public String getKey() {
		if (_keySupplier != null) {
			key = _keySupplier.get();

			_keySupplier = null;
		}

		return key;
	}

	public void setKey(String key) {
		this.key = key;

		_keySupplier = null;
	}

	@JsonIgnore
	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		_keySupplier = () -> {
			try {
				return keyUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "The display page template's key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String key;

	@JsonIgnore
	private Supplier<String> _keySupplier;

	@Schema(
		description = "Whether the display page template is the default one for the given content type/subtype."
	)
	public Boolean getMarkedAsDefault() {
		if (_markedAsDefaultSupplier != null) {
			markedAsDefault = _markedAsDefaultSupplier.get();

			_markedAsDefaultSupplier = null;
		}

		return markedAsDefault;
	}

	public void setMarkedAsDefault(Boolean markedAsDefault) {
		this.markedAsDefault = markedAsDefault;

		_markedAsDefaultSupplier = null;
	}

	@JsonIgnore
	public void setMarkedAsDefault(
		UnsafeSupplier<Boolean, Exception> markedAsDefaultUnsafeSupplier) {

		_markedAsDefaultSupplier = () -> {
			try {
				return markedAsDefaultUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "Whether the display page template is the default one for the given content type/subtype."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean markedAsDefault;

	@JsonIgnore
	private Supplier<Boolean> _markedAsDefaultSupplier;

	@Schema(description = "The display page template's name.")
	public String getName() {
		if (_nameSupplier != null) {
			name = _nameSupplier.get();

			_nameSupplier = null;
		}

		return name;
	}

	public void setName(String name) {
		this.name = name;

		_nameSupplier = null;
	}

	@JsonIgnore
	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		_nameSupplier = () -> {
			try {
				return nameUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "The display page template's name.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String name;

	@JsonIgnore
	private Supplier<String> _nameSupplier;

	@Schema(
		description = "The display page template's specifications. A display page template may contain 0 or 1 page specifications in draft status and 0 or 1 page specifications in published status. This field is not returned by default. It can be requested via nestedFields."
	)
	@Valid
	public PageSpecification[] getPageSpecifications() {
		if (_pageSpecificationsSupplier != null) {
			pageSpecifications = _pageSpecificationsSupplier.get();

			_pageSpecificationsSupplier = null;
		}

		return pageSpecifications;
	}

	public void setPageSpecifications(PageSpecification[] pageSpecifications) {
		this.pageSpecifications = pageSpecifications;

		_pageSpecificationsSupplier = null;
	}

	@JsonIgnore
	public void setPageSpecifications(
		UnsafeSupplier<PageSpecification[], Exception>
			pageSpecificationsUnsafeSupplier) {

		_pageSpecificationsSupplier = () -> {
			try {
				return pageSpecificationsUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "The display page template's specifications. A display page template may contain 0 or 1 page specifications in draft status and 0 or 1 page specifications in published status. This field is not returned by default. It can be requested via nestedFields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected PageSpecification[] pageSpecifications;

	@JsonIgnore
	private Supplier<PageSpecification[]> _pageSpecificationsSupplier;

	@Schema(description = "The display page template's parent folder.")
	@Valid
	public DisplayPageTemplateFolder getParentFolder() {
		if (_parentFolderSupplier != null) {
			parentFolder = _parentFolderSupplier.get();

			_parentFolderSupplier = null;
		}

		return parentFolder;
	}

	public void setParentFolder(DisplayPageTemplateFolder parentFolder) {
		this.parentFolder = parentFolder;

		_parentFolderSupplier = null;
	}

	@JsonIgnore
	public void setParentFolder(
		UnsafeSupplier<DisplayPageTemplateFolder, Exception>
			parentFolderUnsafeSupplier) {

		_parentFolderSupplier = () -> {
			try {
				return parentFolderUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "The display page template's parent folder.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected DisplayPageTemplateFolder parentFolder;

	@JsonIgnore
	private Supplier<DisplayPageTemplateFolder> _parentFolderSupplier;

	@Schema(description = "The display page template's thumbnail.")
	@Valid
	public ItemExternalReference getThumbnail() {
		if (_thumbnailSupplier != null) {
			thumbnail = _thumbnailSupplier.get();

			_thumbnailSupplier = null;
		}

		return thumbnail;
	}

	public void setThumbnail(ItemExternalReference thumbnail) {
		this.thumbnail = thumbnail;

		_thumbnailSupplier = null;
	}

	@JsonIgnore
	public void setThumbnail(
		UnsafeSupplier<ItemExternalReference, Exception>
			thumbnailUnsafeSupplier) {

		_thumbnailSupplier = () -> {
			try {
				return thumbnailUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "The display page template's thumbnail.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected ItemExternalReference thumbnail;

	@JsonIgnore
	private Supplier<ItemExternalReference> _thumbnailSupplier;

	@Schema(
		description = "A valid external identifier to reference this display page template."
	)
	public String getUuid() {
		if (_uuidSupplier != null) {
			uuid = _uuidSupplier.get();

			_uuidSupplier = null;
		}

		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;

		_uuidSupplier = null;
	}

	@JsonIgnore
	public void setUuid(UnsafeSupplier<String, Exception> uuidUnsafeSupplier) {
		_uuidSupplier = () -> {
			try {
				return uuidUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "A valid external identifier to reference this display page template."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String uuid;

	@JsonIgnore
	private Supplier<String> _uuidSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DisplayPageTemplate)) {
			return false;
		}

		DisplayPageTemplate displayPageTemplate = (DisplayPageTemplate)object;

		return Objects.equals(toString(), displayPageTemplate.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		ClassSubtypeReference contentTypeReference = getContentTypeReference();

		if (contentTypeReference != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contentTypeReference\": ");

			sb.append(String.valueOf(contentTypeReference));
		}

		Creator creator = getCreator();

		if (creator != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creator\": ");

			sb.append(creator);
		}

		String creatorExternalReferenceCode = getCreatorExternalReferenceCode();

		if (creatorExternalReferenceCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creatorExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(creatorExternalReferenceCode));

			sb.append("\"");
		}

		Date dateCreated = getDateCreated();

		if (dateCreated != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateCreated));

			sb.append("\"");
		}

		Date dateModified = getDateModified();

		if (dateModified != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateModified\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateModified));

			sb.append("\"");
		}

		Date datePublished = getDatePublished();

		if (datePublished != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"datePublished\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(datePublished));

			sb.append("\"");
		}

		DisplayPageTemplateSettings displayPageTemplateSettings =
			getDisplayPageTemplateSettings();

		if (displayPageTemplateSettings != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"displayPageTemplateSettings\": ");

			sb.append(String.valueOf(displayPageTemplateSettings));
		}

		String externalReferenceCode = getExternalReferenceCode();

		if (externalReferenceCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(externalReferenceCode));

			sb.append("\"");
		}

		FriendlyUrlHistory friendlyUrlHistory = getFriendlyUrlHistory();

		if (friendlyUrlHistory != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"friendlyUrlHistory\": ");

			sb.append(String.valueOf(friendlyUrlHistory));
		}

		Map<String, String> friendlyUrlPath_i18n = getFriendlyUrlPath_i18n();

		if (friendlyUrlPath_i18n != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"friendlyUrlPath_i18n\": ");

			sb.append(_toJSON(friendlyUrlPath_i18n));
		}

		String key = getKey();

		if (key != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(key));

			sb.append("\"");
		}

		Boolean markedAsDefault = getMarkedAsDefault();

		if (markedAsDefault != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"markedAsDefault\": ");

			sb.append(markedAsDefault);
		}

		String name = getName();

		if (name != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(name));

			sb.append("\"");
		}

		PageSpecification[] pageSpecifications = getPageSpecifications();

		if (pageSpecifications != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pageSpecifications\": ");

			sb.append("[");

			for (int i = 0; i < pageSpecifications.length; i++) {
				sb.append(String.valueOf(pageSpecifications[i]));

				if ((i + 1) < pageSpecifications.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		DisplayPageTemplateFolder parentFolder = getParentFolder();

		if (parentFolder != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"parentFolder\": ");

			sb.append(String.valueOf(parentFolder));
		}

		ItemExternalReference thumbnail = getThumbnail();

		if (thumbnail != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"thumbnail\": ");

			sb.append(String.valueOf(thumbnail));
		}

		String uuid = getUuid();

		if (uuid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"uuid\": ");

			sb.append("\"");

			sb.append(_escape(uuid));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.admin.site.dto.v1_0.DisplayPageTemplate",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof Map) {
						sb.append(_toJSON((Map<String, ?>)valueArray[i]));
					}
					else if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

	private Map<String, Serializable> _extendedProperties;

}