/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

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
import javax.validation.constraints.NotEmpty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "Represents a folder for organizing Knowledge Base articles.",
	value = "KnowledgeBaseFolder"
)
@JsonFilter("Liferay.Vulcan")
@Schema(
	description = "Represents a folder for organizing Knowledge Base articles.",
	requiredProperties = {"name"}
)
@XmlRootElement(name = "KnowledgeBaseFolder")
public class KnowledgeBaseFolder implements Serializable {

	public static KnowledgeBaseFolder toDTO(String json) {
		return ObjectMapperUtil.readValue(KnowledgeBaseFolder.class, json);
	}

	public static KnowledgeBaseFolder unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			KnowledgeBaseFolder.class, json);
	}

	@Schema(
		description = "Block of actions allowed by the user making the request."
	)
	@Valid
	public Map<String, Map<String, String>> getActions() {
		if (_actionsSupplier != null) {
			actions = _actionsSupplier.get();

			_actionsSupplier = null;
		}

		return actions;
	}

	public void setActions(Map<String, Map<String, String>> actions) {
		this.actions = actions;

		_actionsSupplier = null;
	}

	@JsonIgnore
	public void setActions(
		UnsafeSupplier<Map<String, Map<String, String>>, Exception>
			actionsUnsafeSupplier) {

		_actionsSupplier = () -> {
			try {
				return actionsUnsafeSupplier.get();
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
		description = "Block of actions allowed by the user making the request."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Map<String, Map<String, String>> actions;

	@JsonIgnore
	private Supplier<Map<String, Map<String, String>>> _actionsSupplier;

	@Schema(description = "The folder's creator.")
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

	@GraphQLField(description = "The folder's creator.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Creator creator;

	@JsonIgnore
	private Supplier<Creator> _creatorSupplier;

	@Schema(
		description = "A list of the custom fields associated with the folder."
	)
	@Valid
	public CustomField[] getCustomFields() {
		if (_customFieldsSupplier != null) {
			customFields = _customFieldsSupplier.get();

			_customFieldsSupplier = null;
		}

		return customFields;
	}

	public void setCustomFields(CustomField[] customFields) {
		this.customFields = customFields;

		_customFieldsSupplier = null;
	}

	@JsonIgnore
	public void setCustomFields(
		UnsafeSupplier<CustomField[], Exception> customFieldsUnsafeSupplier) {

		_customFieldsSupplier = () -> {
			try {
				return customFieldsUnsafeSupplier.get();
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
		description = "A list of the custom fields associated with the folder."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected CustomField[] customFields;

	@JsonIgnore
	private Supplier<CustomField[]> _customFieldsSupplier;

	@Schema(description = "The date the folder was created.")
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

	@GraphQLField(description = "The date the folder was created.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date dateCreated;

	@JsonIgnore
	private Supplier<Date> _dateCreatedSupplier;

	@Schema(description = "The last time the folder was modified.")
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

	@GraphQLField(description = "The last time the folder was modified.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date dateModified;

	@JsonIgnore
	private Supplier<Date> _dateModifiedSupplier;

	@Schema(description = "The folder's description.")
	public String getDescription() {
		if (_descriptionSupplier != null) {
			description = _descriptionSupplier.get();

			_descriptionSupplier = null;
		}

		return description;
	}

	public void setDescription(String description) {
		this.description = description;

		_descriptionSupplier = null;
	}

	@JsonIgnore
	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		_descriptionSupplier = () -> {
			try {
				return descriptionUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "The folder's description.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String description;

	@JsonIgnore
	private Supplier<String> _descriptionSupplier;

	@Schema(description = "The folder's external reference code.")
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

	@GraphQLField(description = "The folder's external reference code.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String externalReferenceCode;

	@JsonIgnore
	private Supplier<String> _externalReferenceCodeSupplier;

	@Schema(description = "The folder's ID.")
	public Long getId() {
		if (_idSupplier != null) {
			id = _idSupplier.get();

			_idSupplier = null;
		}

		return id;
	}

	public void setId(Long id) {
		this.id = id;

		_idSupplier = null;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		_idSupplier = () -> {
			try {
				return idUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "The folder's ID.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long id;

	@JsonIgnore
	private Supplier<Long> _idSupplier;

	@Schema(description = "The folder's main title/name.")
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

	@GraphQLField(description = "The folder's main title/name.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String name;

	@JsonIgnore
	private Supplier<String> _nameSupplier;

	@Schema(
		description = "The number of Knowledge Base articles in this folder."
	)
	public Integer getNumberOfKnowledgeBaseArticles() {
		if (_numberOfKnowledgeBaseArticlesSupplier != null) {
			numberOfKnowledgeBaseArticles =
				_numberOfKnowledgeBaseArticlesSupplier.get();

			_numberOfKnowledgeBaseArticlesSupplier = null;
		}

		return numberOfKnowledgeBaseArticles;
	}

	public void setNumberOfKnowledgeBaseArticles(
		Integer numberOfKnowledgeBaseArticles) {

		this.numberOfKnowledgeBaseArticles = numberOfKnowledgeBaseArticles;

		_numberOfKnowledgeBaseArticlesSupplier = null;
	}

	@JsonIgnore
	public void setNumberOfKnowledgeBaseArticles(
		UnsafeSupplier<Integer, Exception>
			numberOfKnowledgeBaseArticlesUnsafeSupplier) {

		_numberOfKnowledgeBaseArticlesSupplier = () -> {
			try {
				return numberOfKnowledgeBaseArticlesUnsafeSupplier.get();
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
		description = "The number of Knowledge Base articles in this folder."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Integer numberOfKnowledgeBaseArticles;

	@JsonIgnore
	private Supplier<Integer> _numberOfKnowledgeBaseArticlesSupplier;

	@Schema(
		description = "The number of Knowledge Base folders in this folder."
	)
	public Integer getNumberOfKnowledgeBaseFolders() {
		if (_numberOfKnowledgeBaseFoldersSupplier != null) {
			numberOfKnowledgeBaseFolders =
				_numberOfKnowledgeBaseFoldersSupplier.get();

			_numberOfKnowledgeBaseFoldersSupplier = null;
		}

		return numberOfKnowledgeBaseFolders;
	}

	public void setNumberOfKnowledgeBaseFolders(
		Integer numberOfKnowledgeBaseFolders) {

		this.numberOfKnowledgeBaseFolders = numberOfKnowledgeBaseFolders;

		_numberOfKnowledgeBaseFoldersSupplier = null;
	}

	@JsonIgnore
	public void setNumberOfKnowledgeBaseFolders(
		UnsafeSupplier<Integer, Exception>
			numberOfKnowledgeBaseFoldersUnsafeSupplier) {

		_numberOfKnowledgeBaseFoldersSupplier = () -> {
			try {
				return numberOfKnowledgeBaseFoldersUnsafeSupplier.get();
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
		description = "The number of Knowledge Base folders in this folder."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Integer numberOfKnowledgeBaseFolders;

	@JsonIgnore
	private Supplier<Integer> _numberOfKnowledgeBaseFoldersSupplier;

	@Schema(
		description = "The folder's parent Knowledge Base folder, if it exists."
	)
	@Valid
	public ParentKnowledgeBaseFolder getParentKnowledgeBaseFolder() {
		if (_parentKnowledgeBaseFolderSupplier != null) {
			parentKnowledgeBaseFolder =
				_parentKnowledgeBaseFolderSupplier.get();

			_parentKnowledgeBaseFolderSupplier = null;
		}

		return parentKnowledgeBaseFolder;
	}

	public void setParentKnowledgeBaseFolder(
		ParentKnowledgeBaseFolder parentKnowledgeBaseFolder) {

		this.parentKnowledgeBaseFolder = parentKnowledgeBaseFolder;

		_parentKnowledgeBaseFolderSupplier = null;
	}

	@JsonIgnore
	public void setParentKnowledgeBaseFolder(
		UnsafeSupplier<ParentKnowledgeBaseFolder, Exception>
			parentKnowledgeBaseFolderUnsafeSupplier) {

		_parentKnowledgeBaseFolderSupplier = () -> {
			try {
				return parentKnowledgeBaseFolderUnsafeSupplier.get();
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
		description = "The folder's parent Knowledge Base folder, if it exists."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected ParentKnowledgeBaseFolder parentKnowledgeBaseFolder;

	@JsonIgnore
	private Supplier<ParentKnowledgeBaseFolder>
		_parentKnowledgeBaseFolderSupplier;

	@Schema(
		description = "The ID of the folder's parent Knowledge Base folder, if such a parent folder exists."
	)
	public Long getParentKnowledgeBaseFolderId() {
		if (_parentKnowledgeBaseFolderIdSupplier != null) {
			parentKnowledgeBaseFolderId =
				_parentKnowledgeBaseFolderIdSupplier.get();

			_parentKnowledgeBaseFolderIdSupplier = null;
		}

		return parentKnowledgeBaseFolderId;
	}

	public void setParentKnowledgeBaseFolderId(
		Long parentKnowledgeBaseFolderId) {

		this.parentKnowledgeBaseFolderId = parentKnowledgeBaseFolderId;

		_parentKnowledgeBaseFolderIdSupplier = null;
	}

	@JsonIgnore
	public void setParentKnowledgeBaseFolderId(
		UnsafeSupplier<Long, Exception>
			parentKnowledgeBaseFolderIdUnsafeSupplier) {

		_parentKnowledgeBaseFolderIdSupplier = () -> {
			try {
				return parentKnowledgeBaseFolderIdUnsafeSupplier.get();
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
		description = "The ID of the folder's parent Knowledge Base folder, if such a parent folder exists."
	)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	protected Long parentKnowledgeBaseFolderId;

	@JsonIgnore
	private Supplier<Long> _parentKnowledgeBaseFolderIdSupplier;

	@Schema(description = "The ID of the site to which this folder is scoped.")
	public Long getSiteId() {
		if (_siteIdSupplier != null) {
			siteId = _siteIdSupplier.get();

			_siteIdSupplier = null;
		}

		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;

		_siteIdSupplier = null;
	}

	@JsonIgnore
	public void setSiteId(
		UnsafeSupplier<Long, Exception> siteIdUnsafeSupplier) {

		_siteIdSupplier = () -> {
			try {
				return siteIdUnsafeSupplier.get();
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
		description = "The ID of the site to which this folder is scoped."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long siteId;

	@JsonIgnore
	private Supplier<Long> _siteIdSupplier;

	@JsonGetter("viewableBy")
	@Schema(
		description = "A write-only property that specifies the folder's default permissions."
	)
	@Valid
	public ViewableBy getViewableBy() {
		if (_viewableBySupplier != null) {
			viewableBy = _viewableBySupplier.get();

			_viewableBySupplier = null;
		}

		return viewableBy;
	}

	@JsonIgnore
	public String getViewableByAsString() {
		ViewableBy viewableBy = getViewableBy();

		if (viewableBy == null) {
			return null;
		}

		return viewableBy.toString();
	}

	public void setViewableBy(ViewableBy viewableBy) {
		this.viewableBy = viewableBy;

		_viewableBySupplier = null;
	}

	@JsonIgnore
	public void setViewableBy(
		UnsafeSupplier<ViewableBy, Exception> viewableByUnsafeSupplier) {

		_viewableBySupplier = () -> {
			try {
				return viewableByUnsafeSupplier.get();
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
		description = "A write-only property that specifies the folder's default permissions."
	)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	protected ViewableBy viewableBy;

	@JsonIgnore
	private Supplier<ViewableBy> _viewableBySupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof KnowledgeBaseFolder)) {
			return false;
		}

		KnowledgeBaseFolder knowledgeBaseFolder = (KnowledgeBaseFolder)object;

		return Objects.equals(toString(), knowledgeBaseFolder.toString());
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

		Map<String, Map<String, String>> actions = getActions();

		if (actions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(actions));
		}

		Creator creator = getCreator();

		if (creator != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creator\": ");

			sb.append(String.valueOf(creator));
		}

		CustomField[] customFields = getCustomFields();

		if (customFields != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customFields\": ");

			sb.append("[");

			for (int i = 0; i < customFields.length; i++) {
				sb.append(String.valueOf(customFields[i]));

				if ((i + 1) < customFields.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
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

		String description = getDescription();

		if (description != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(description));

			sb.append("\"");
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

		Long id = getId();

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
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

		Integer numberOfKnowledgeBaseArticles =
			getNumberOfKnowledgeBaseArticles();

		if (numberOfKnowledgeBaseArticles != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"numberOfKnowledgeBaseArticles\": ");

			sb.append(numberOfKnowledgeBaseArticles);
		}

		Integer numberOfKnowledgeBaseFolders =
			getNumberOfKnowledgeBaseFolders();

		if (numberOfKnowledgeBaseFolders != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"numberOfKnowledgeBaseFolders\": ");

			sb.append(numberOfKnowledgeBaseFolders);
		}

		ParentKnowledgeBaseFolder parentKnowledgeBaseFolder =
			getParentKnowledgeBaseFolder();

		if (parentKnowledgeBaseFolder != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"parentKnowledgeBaseFolder\": ");

			sb.append(String.valueOf(parentKnowledgeBaseFolder));
		}

		Long parentKnowledgeBaseFolderId = getParentKnowledgeBaseFolderId();

		if (parentKnowledgeBaseFolderId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"parentKnowledgeBaseFolderId\": ");

			sb.append(parentKnowledgeBaseFolderId);
		}

		Long siteId = getSiteId();

		if (siteId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteId\": ");

			sb.append(siteId);
		}

		ViewableBy viewableBy = getViewableBy();

		if (viewableBy != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"viewableBy\": ");

			sb.append("\"");

			sb.append(viewableBy);

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.delivery.dto.v1_0.KnowledgeBaseFolder",
		name = "x-class-name"
	)
	public String xClassName;

	@GraphQLName("ViewableBy")
	public static enum ViewableBy {

		ANYONE("Anyone"), MEMBERS("Members"), OWNER("Owner");

		@JsonCreator
		public static ViewableBy create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (ViewableBy viewableBy : values()) {
				if (Objects.equals(viewableBy.getValue(), value)) {
					return viewableBy;
				}
			}

			throw new IllegalArgumentException("Invalid enum value: " + value);
		}

		@JsonValue
		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private ViewableBy(String value) {
			_value = value;
		}

		private final String _value;

	}

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