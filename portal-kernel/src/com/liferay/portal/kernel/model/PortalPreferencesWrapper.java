/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PortalPreferences}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortalPreferences
 * @generated
 */
public class PortalPreferencesWrapper
	extends BaseModelWrapper<PortalPreferences>
	implements ModelWrapper<PortalPreferences>, PortalPreferences {

	public PortalPreferencesWrapper(PortalPreferences portalPreferences) {
		super(portalPreferences);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("portalPreferencesId", getPortalPreferencesId());
		attributes.put("companyId", getCompanyId());
		attributes.put("ownerId", getOwnerId());
		attributes.put("ownerType", getOwnerType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long portalPreferencesId = (Long)attributes.get("portalPreferencesId");

		if (portalPreferencesId != null) {
			setPortalPreferencesId(portalPreferencesId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long ownerId = (Long)attributes.get("ownerId");

		if (ownerId != null) {
			setOwnerId(ownerId);
		}

		Integer ownerType = (Integer)attributes.get("ownerType");

		if (ownerType != null) {
			setOwnerType(ownerType);
		}
	}

	@Override
	public PortalPreferences cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this portal preferences.
	 *
	 * @return the company ID of this portal preferences
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the mvcc version of this portal preferences.
	 *
	 * @return the mvcc version of this portal preferences
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the owner ID of this portal preferences.
	 *
	 * @return the owner ID of this portal preferences
	 */
	@Override
	public long getOwnerId() {
		return model.getOwnerId();
	}

	/**
	 * Returns the owner type of this portal preferences.
	 *
	 * @return the owner type of this portal preferences
	 */
	@Override
	public int getOwnerType() {
		return model.getOwnerType();
	}

	/**
	 * Returns the portal preferences ID of this portal preferences.
	 *
	 * @return the portal preferences ID of this portal preferences
	 */
	@Override
	public long getPortalPreferencesId() {
		return model.getPortalPreferencesId();
	}

	/**
	 * Returns the primary key of this portal preferences.
	 *
	 * @return the primary key of this portal preferences
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this portal preferences.
	 *
	 * @param companyId the company ID of this portal preferences
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the mvcc version of this portal preferences.
	 *
	 * @param mvccVersion the mvcc version of this portal preferences
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the owner ID of this portal preferences.
	 *
	 * @param ownerId the owner ID of this portal preferences
	 */
	@Override
	public void setOwnerId(long ownerId) {
		model.setOwnerId(ownerId);
	}

	/**
	 * Sets the owner type of this portal preferences.
	 *
	 * @param ownerType the owner type of this portal preferences
	 */
	@Override
	public void setOwnerType(int ownerType) {
		model.setOwnerType(ownerType);
	}

	/**
	 * Sets the portal preferences ID of this portal preferences.
	 *
	 * @param portalPreferencesId the portal preferences ID of this portal preferences
	 */
	@Override
	public void setPortalPreferencesId(long portalPreferencesId) {
		model.setPortalPreferencesId(portalPreferencesId);
	}

	/**
	 * Sets the primary key of this portal preferences.
	 *
	 * @param primaryKey the primary key of this portal preferences
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected PortalPreferencesWrapper wrap(
		PortalPreferences portalPreferences) {

		return new PortalPreferencesWrapper(portalPreferences);
	}

}