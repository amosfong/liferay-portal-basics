/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommercePriceModifierRel service. Represents a row in the &quot;CommercePriceModifierRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.pricing.model.impl.CommercePriceModifierRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.pricing.model.impl.CommercePriceModifierRelImpl</code>.
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommercePriceModifierRel
 * @generated
 */
@ProviderType
public interface CommercePriceModifierRelModel
	extends AttachedModel, AuditedModel, BaseModel<CommercePriceModifierRel>,
			CTModel<CommercePriceModifierRel>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce price modifier rel model instance should use the {@link CommercePriceModifierRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce price modifier rel.
	 *
	 * @return the primary key of this commerce price modifier rel
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce price modifier rel.
	 *
	 * @param primaryKey the primary key of this commerce price modifier rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce price modifier rel.
	 *
	 * @return the mvcc version of this commerce price modifier rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce price modifier rel.
	 *
	 * @param mvccVersion the mvcc version of this commerce price modifier rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this commerce price modifier rel.
	 *
	 * @return the ct collection ID of this commerce price modifier rel
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this commerce price modifier rel.
	 *
	 * @param ctCollectionId the ct collection ID of this commerce price modifier rel
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the commerce price modifier rel ID of this commerce price modifier rel.
	 *
	 * @return the commerce price modifier rel ID of this commerce price modifier rel
	 */
	public long getCommercePriceModifierRelId();

	/**
	 * Sets the commerce price modifier rel ID of this commerce price modifier rel.
	 *
	 * @param commercePriceModifierRelId the commerce price modifier rel ID of this commerce price modifier rel
	 */
	public void setCommercePriceModifierRelId(long commercePriceModifierRelId);

	/**
	 * Returns the company ID of this commerce price modifier rel.
	 *
	 * @return the company ID of this commerce price modifier rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce price modifier rel.
	 *
	 * @param companyId the company ID of this commerce price modifier rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce price modifier rel.
	 *
	 * @return the user ID of this commerce price modifier rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce price modifier rel.
	 *
	 * @param userId the user ID of this commerce price modifier rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce price modifier rel.
	 *
	 * @return the user uuid of this commerce price modifier rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce price modifier rel.
	 *
	 * @param userUuid the user uuid of this commerce price modifier rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce price modifier rel.
	 *
	 * @return the user name of this commerce price modifier rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce price modifier rel.
	 *
	 * @param userName the user name of this commerce price modifier rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce price modifier rel.
	 *
	 * @return the create date of this commerce price modifier rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce price modifier rel.
	 *
	 * @param createDate the create date of this commerce price modifier rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce price modifier rel.
	 *
	 * @return the modified date of this commerce price modifier rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce price modifier rel.
	 *
	 * @param modifiedDate the modified date of this commerce price modifier rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce price modifier ID of this commerce price modifier rel.
	 *
	 * @return the commerce price modifier ID of this commerce price modifier rel
	 */
	public long getCommercePriceModifierId();

	/**
	 * Sets the commerce price modifier ID of this commerce price modifier rel.
	 *
	 * @param commercePriceModifierId the commerce price modifier ID of this commerce price modifier rel
	 */
	public void setCommercePriceModifierId(long commercePriceModifierId);

	/**
	 * Returns the fully qualified class name of this commerce price modifier rel.
	 *
	 * @return the fully qualified class name of this commerce price modifier rel
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this commerce price modifier rel.
	 *
	 * @return the class name ID of this commerce price modifier rel
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this commerce price modifier rel.
	 *
	 * @param classNameId the class name ID of this commerce price modifier rel
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this commerce price modifier rel.
	 *
	 * @return the class pk of this commerce price modifier rel
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this commerce price modifier rel.
	 *
	 * @param classPK the class pk of this commerce price modifier rel
	 */
	@Override
	public void setClassPK(long classPK);

	@Override
	public CommercePriceModifierRel cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}