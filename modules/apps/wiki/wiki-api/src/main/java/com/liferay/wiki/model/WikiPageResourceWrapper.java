/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * <p>
 * This class is a wrapper for {@link WikiPageResource}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WikiPageResource
 * @generated
 */
public class WikiPageResourceWrapper
	extends BaseModelWrapper<WikiPageResource>
	implements ModelWrapper<WikiPageResource>, WikiPageResource {

	public WikiPageResourceWrapper(WikiPageResource wikiPageResource) {
		super(wikiPageResource);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("ctCollectionId", getCtCollectionId());
		attributes.put("uuid", getUuid());
		attributes.put("resourcePrimKey", getResourcePrimKey());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("nodeId", getNodeId());
		attributes.put("title", getTitle());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long ctCollectionId = (Long)attributes.get("ctCollectionId");

		if (ctCollectionId != null) {
			setCtCollectionId(ctCollectionId);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long resourcePrimKey = (Long)attributes.get("resourcePrimKey");

		if (resourcePrimKey != null) {
			setResourcePrimKey(resourcePrimKey);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long nodeId = (Long)attributes.get("nodeId");

		if (nodeId != null) {
			setNodeId(nodeId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}
	}

	@Override
	public WikiPageResource cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this wiki page resource.
	 *
	 * @return the company ID of this wiki page resource
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the ct collection ID of this wiki page resource.
	 *
	 * @return the ct collection ID of this wiki page resource
	 */
	@Override
	public long getCtCollectionId() {
		return model.getCtCollectionId();
	}

	/**
	 * Returns the group ID of this wiki page resource.
	 *
	 * @return the group ID of this wiki page resource
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the mvcc version of this wiki page resource.
	 *
	 * @return the mvcc version of this wiki page resource
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the node ID of this wiki page resource.
	 *
	 * @return the node ID of this wiki page resource
	 */
	@Override
	public long getNodeId() {
		return model.getNodeId();
	}

	/**
	 * Returns the primary key of this wiki page resource.
	 *
	 * @return the primary key of this wiki page resource
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the resource prim key of this wiki page resource.
	 *
	 * @return the resource prim key of this wiki page resource
	 */
	@Override
	public long getResourcePrimKey() {
		return model.getResourcePrimKey();
	}

	/**
	 * Returns the title of this wiki page resource.
	 *
	 * @return the title of this wiki page resource
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the uuid of this wiki page resource.
	 *
	 * @return the uuid of this wiki page resource
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this wiki page resource.
	 *
	 * @param companyId the company ID of this wiki page resource
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the ct collection ID of this wiki page resource.
	 *
	 * @param ctCollectionId the ct collection ID of this wiki page resource
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId) {
		model.setCtCollectionId(ctCollectionId);
	}

	/**
	 * Sets the group ID of this wiki page resource.
	 *
	 * @param groupId the group ID of this wiki page resource
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the mvcc version of this wiki page resource.
	 *
	 * @param mvccVersion the mvcc version of this wiki page resource
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the node ID of this wiki page resource.
	 *
	 * @param nodeId the node ID of this wiki page resource
	 */
	@Override
	public void setNodeId(long nodeId) {
		model.setNodeId(nodeId);
	}

	/**
	 * Sets the primary key of this wiki page resource.
	 *
	 * @param primaryKey the primary key of this wiki page resource
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the resource prim key of this wiki page resource.
	 *
	 * @param resourcePrimKey the resource prim key of this wiki page resource
	 */
	@Override
	public void setResourcePrimKey(long resourcePrimKey) {
		model.setResourcePrimKey(resourcePrimKey);
	}

	/**
	 * Sets the title of this wiki page resource.
	 *
	 * @param title the title of this wiki page resource
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the uuid of this wiki page resource.
	 *
	 * @param uuid the uuid of this wiki page resource
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public Map<String, Function<WikiPageResource, Object>>
		getAttributeGetterFunctions() {

		return model.getAttributeGetterFunctions();
	}

	@Override
	public Map<String, BiConsumer<WikiPageResource, Object>>
		getAttributeSetterBiConsumers() {

		return model.getAttributeSetterBiConsumers();
	}

	@Override
	protected WikiPageResourceWrapper wrap(WikiPageResource wikiPageResource) {
		return new WikiPageResourceWrapper(wikiPageResource);
	}

}