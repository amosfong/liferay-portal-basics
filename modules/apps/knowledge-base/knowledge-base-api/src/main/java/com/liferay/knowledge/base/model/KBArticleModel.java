/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ExternalReferenceCodeModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ResourcedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the KBArticle service. Represents a row in the &quot;KBArticle&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.knowledge.base.model.impl.KBArticleModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.knowledge.base.model.impl.KBArticleImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBArticle
 * @generated
 */
@ProviderType
public interface KBArticleModel
	extends BaseModel<KBArticle>, CTModel<KBArticle>,
			ExternalReferenceCodeModel, MVCCModel, ResourcedModel, ShardedModel,
			StagedGroupedModel, TrashedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kb article model instance should use the {@link KBArticle} interface instead.
	 */

	/**
	 * Returns the primary key of this kb article.
	 *
	 * @return the primary key of this kb article
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kb article.
	 *
	 * @param primaryKey the primary key of this kb article
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this kb article.
	 *
	 * @return the mvcc version of this kb article
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this kb article.
	 *
	 * @param mvccVersion the mvcc version of this kb article
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this kb article.
	 *
	 * @return the ct collection ID of this kb article
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this kb article.
	 *
	 * @param ctCollectionId the ct collection ID of this kb article
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this kb article.
	 *
	 * @return the uuid of this kb article
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this kb article.
	 *
	 * @param uuid the uuid of this kb article
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the kb article ID of this kb article.
	 *
	 * @return the kb article ID of this kb article
	 */
	public long getKbArticleId();

	/**
	 * Sets the kb article ID of this kb article.
	 *
	 * @param kbArticleId the kb article ID of this kb article
	 */
	public void setKbArticleId(long kbArticleId);

	/**
	 * Returns the resource prim key of this kb article.
	 *
	 * @return the resource prim key of this kb article
	 */
	@Override
	public long getResourcePrimKey();

	/**
	 * Sets the resource prim key of this kb article.
	 *
	 * @param resourcePrimKey the resource prim key of this kb article
	 */
	@Override
	public void setResourcePrimKey(long resourcePrimKey);

	@Override
	public boolean isResourceMain();

	/**
	 * Returns the group ID of this kb article.
	 *
	 * @return the group ID of this kb article
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kb article.
	 *
	 * @param groupId the group ID of this kb article
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kb article.
	 *
	 * @return the company ID of this kb article
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kb article.
	 *
	 * @param companyId the company ID of this kb article
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kb article.
	 *
	 * @return the user ID of this kb article
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kb article.
	 *
	 * @param userId the user ID of this kb article
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kb article.
	 *
	 * @return the user uuid of this kb article
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kb article.
	 *
	 * @param userUuid the user uuid of this kb article
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kb article.
	 *
	 * @return the user name of this kb article
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kb article.
	 *
	 * @param userName the user name of this kb article
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kb article.
	 *
	 * @return the create date of this kb article
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kb article.
	 *
	 * @param createDate the create date of this kb article
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kb article.
	 *
	 * @return the modified date of this kb article
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kb article.
	 *
	 * @param modifiedDate the modified date of this kb article
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the external reference code of this kb article.
	 *
	 * @return the external reference code of this kb article
	 */
	@AutoEscape
	@Override
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this kb article.
	 *
	 * @param externalReferenceCode the external reference code of this kb article
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the root resource prim key of this kb article.
	 *
	 * @return the root resource prim key of this kb article
	 */
	public long getRootResourcePrimKey();

	/**
	 * Sets the root resource prim key of this kb article.
	 *
	 * @param rootResourcePrimKey the root resource prim key of this kb article
	 */
	public void setRootResourcePrimKey(long rootResourcePrimKey);

	/**
	 * Returns the parent resource class name ID of this kb article.
	 *
	 * @return the parent resource class name ID of this kb article
	 */
	public long getParentResourceClassNameId();

	/**
	 * Sets the parent resource class name ID of this kb article.
	 *
	 * @param parentResourceClassNameId the parent resource class name ID of this kb article
	 */
	public void setParentResourceClassNameId(long parentResourceClassNameId);

	/**
	 * Returns the parent resource prim key of this kb article.
	 *
	 * @return the parent resource prim key of this kb article
	 */
	public long getParentResourcePrimKey();

	/**
	 * Sets the parent resource prim key of this kb article.
	 *
	 * @param parentResourcePrimKey the parent resource prim key of this kb article
	 */
	public void setParentResourcePrimKey(long parentResourcePrimKey);

	/**
	 * Returns the kb folder ID of this kb article.
	 *
	 * @return the kb folder ID of this kb article
	 */
	public long getKbFolderId();

	/**
	 * Sets the kb folder ID of this kb article.
	 *
	 * @param kbFolderId the kb folder ID of this kb article
	 */
	public void setKbFolderId(long kbFolderId);

	/**
	 * Returns the version of this kb article.
	 *
	 * @return the version of this kb article
	 */
	public int getVersion();

	/**
	 * Sets the version of this kb article.
	 *
	 * @param version the version of this kb article
	 */
	public void setVersion(int version);

	/**
	 * Returns the title of this kb article.
	 *
	 * @return the title of this kb article
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this kb article.
	 *
	 * @param title the title of this kb article
	 */
	public void setTitle(String title);

	/**
	 * Returns the url title of this kb article.
	 *
	 * @return the url title of this kb article
	 */
	@AutoEscape
	public String getUrlTitle();

	/**
	 * Sets the url title of this kb article.
	 *
	 * @param urlTitle the url title of this kb article
	 */
	public void setUrlTitle(String urlTitle);

	/**
	 * Returns the content of this kb article.
	 *
	 * @return the content of this kb article
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this kb article.
	 *
	 * @param content the content of this kb article
	 */
	public void setContent(String content);

	/**
	 * Returns the description of this kb article.
	 *
	 * @return the description of this kb article
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this kb article.
	 *
	 * @param description the description of this kb article
	 */
	public void setDescription(String description);

	/**
	 * Returns the priority of this kb article.
	 *
	 * @return the priority of this kb article
	 */
	public double getPriority();

	/**
	 * Sets the priority of this kb article.
	 *
	 * @param priority the priority of this kb article
	 */
	public void setPriority(double priority);

	/**
	 * Returns the sections of this kb article.
	 *
	 * @return the sections of this kb article
	 */
	@AutoEscape
	public String getSections();

	/**
	 * Sets the sections of this kb article.
	 *
	 * @param sections the sections of this kb article
	 */
	public void setSections(String sections);

	/**
	 * Returns the latest of this kb article.
	 *
	 * @return the latest of this kb article
	 */
	public boolean getLatest();

	/**
	 * Returns <code>true</code> if this kb article is latest.
	 *
	 * @return <code>true</code> if this kb article is latest; <code>false</code> otherwise
	 */
	public boolean isLatest();

	/**
	 * Sets whether this kb article is latest.
	 *
	 * @param latest the latest of this kb article
	 */
	public void setLatest(boolean latest);

	/**
	 * Returns the main of this kb article.
	 *
	 * @return the main of this kb article
	 */
	public boolean getMain();

	/**
	 * Returns <code>true</code> if this kb article is main.
	 *
	 * @return <code>true</code> if this kb article is main; <code>false</code> otherwise
	 */
	public boolean isMain();

	/**
	 * Sets whether this kb article is main.
	 *
	 * @param main the main of this kb article
	 */
	public void setMain(boolean main);

	/**
	 * Returns the source url of this kb article.
	 *
	 * @return the source url of this kb article
	 */
	@AutoEscape
	public String getSourceURL();

	/**
	 * Sets the source url of this kb article.
	 *
	 * @param sourceURL the source url of this kb article
	 */
	public void setSourceURL(String sourceURL);

	/**
	 * Returns the display date of this kb article.
	 *
	 * @return the display date of this kb article
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this kb article.
	 *
	 * @param displayDate the display date of this kb article
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Returns the expiration date of this kb article.
	 *
	 * @return the expiration date of this kb article
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this kb article.
	 *
	 * @param expirationDate the expiration date of this kb article
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the review date of this kb article.
	 *
	 * @return the review date of this kb article
	 */
	public Date getReviewDate();

	/**
	 * Sets the review date of this kb article.
	 *
	 * @param reviewDate the review date of this kb article
	 */
	public void setReviewDate(Date reviewDate);

	/**
	 * Returns the last publish date of this kb article.
	 *
	 * @return the last publish date of this kb article
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this kb article.
	 *
	 * @param lastPublishDate the last publish date of this kb article
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this kb article.
	 *
	 * @return the status of this kb article
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this kb article.
	 *
	 * @param status the status of this kb article
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this kb article.
	 *
	 * @return the status by user ID of this kb article
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this kb article.
	 *
	 * @param statusByUserId the status by user ID of this kb article
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this kb article.
	 *
	 * @return the status by user uuid of this kb article
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this kb article.
	 *
	 * @param statusByUserUuid the status by user uuid of this kb article
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this kb article.
	 *
	 * @return the status by user name of this kb article
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this kb article.
	 *
	 * @param statusByUserName the status by user name of this kb article
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this kb article.
	 *
	 * @return the status date of this kb article
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this kb article.
	 *
	 * @param statusDate the status date of this kb article
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the class primary key of the trash entry for this kb article.
	 *
	 * @return the class primary key of the trash entry for this kb article
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns <code>true</code> if this kb article is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this kb article is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if this kb article is approved.
	 *
	 * @return <code>true</code> if this kb article is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this kb article is denied.
	 *
	 * @return <code>true</code> if this kb article is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this kb article is a draft.
	 *
	 * @return <code>true</code> if this kb article is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this kb article is expired.
	 *
	 * @return <code>true</code> if this kb article is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this kb article is inactive.
	 *
	 * @return <code>true</code> if this kb article is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this kb article is incomplete.
	 *
	 * @return <code>true</code> if this kb article is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this kb article is pending.
	 *
	 * @return <code>true</code> if this kb article is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this kb article is scheduled.
	 *
	 * @return <code>true</code> if this kb article is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public KBArticle cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}