/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the KaleoTaskAssignmentInstance service. Represents a row in the &quot;KaleoTaskAssignmentInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentInstance
 * @generated
 */
@ProviderType
public interface KaleoTaskAssignmentInstanceModel
	extends BaseModel<KaleoTaskAssignmentInstance>,
			CTModel<KaleoTaskAssignmentInstance>, GroupedModel, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kaleo task assignment instance model instance should use the {@link KaleoTaskAssignmentInstance} interface instead.
	 */

	/**
	 * Returns the primary key of this kaleo task assignment instance.
	 *
	 * @return the primary key of this kaleo task assignment instance
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kaleo task assignment instance.
	 *
	 * @param primaryKey the primary key of this kaleo task assignment instance
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this kaleo task assignment instance.
	 *
	 * @return the mvcc version of this kaleo task assignment instance
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this kaleo task assignment instance.
	 *
	 * @param mvccVersion the mvcc version of this kaleo task assignment instance
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this kaleo task assignment instance.
	 *
	 * @return the ct collection ID of this kaleo task assignment instance
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this kaleo task assignment instance.
	 *
	 * @param ctCollectionId the ct collection ID of this kaleo task assignment instance
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the kaleo task assignment instance ID of this kaleo task assignment instance.
	 *
	 * @return the kaleo task assignment instance ID of this kaleo task assignment instance
	 */
	public long getKaleoTaskAssignmentInstanceId();

	/**
	 * Sets the kaleo task assignment instance ID of this kaleo task assignment instance.
	 *
	 * @param kaleoTaskAssignmentInstanceId the kaleo task assignment instance ID of this kaleo task assignment instance
	 */
	public void setKaleoTaskAssignmentInstanceId(
		long kaleoTaskAssignmentInstanceId);

	/**
	 * Returns the group ID of this kaleo task assignment instance.
	 *
	 * @return the group ID of this kaleo task assignment instance
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kaleo task assignment instance.
	 *
	 * @param groupId the group ID of this kaleo task assignment instance
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kaleo task assignment instance.
	 *
	 * @return the company ID of this kaleo task assignment instance
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kaleo task assignment instance.
	 *
	 * @param companyId the company ID of this kaleo task assignment instance
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kaleo task assignment instance.
	 *
	 * @return the user ID of this kaleo task assignment instance
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kaleo task assignment instance.
	 *
	 * @param userId the user ID of this kaleo task assignment instance
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kaleo task assignment instance.
	 *
	 * @return the user uuid of this kaleo task assignment instance
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kaleo task assignment instance.
	 *
	 * @param userUuid the user uuid of this kaleo task assignment instance
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kaleo task assignment instance.
	 *
	 * @return the user name of this kaleo task assignment instance
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kaleo task assignment instance.
	 *
	 * @param userName the user name of this kaleo task assignment instance
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kaleo task assignment instance.
	 *
	 * @return the create date of this kaleo task assignment instance
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kaleo task assignment instance.
	 *
	 * @param createDate the create date of this kaleo task assignment instance
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kaleo task assignment instance.
	 *
	 * @return the modified date of this kaleo task assignment instance
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kaleo task assignment instance.
	 *
	 * @param modifiedDate the modified date of this kaleo task assignment instance
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the kaleo definition ID of this kaleo task assignment instance.
	 *
	 * @return the kaleo definition ID of this kaleo task assignment instance
	 */
	public long getKaleoDefinitionId();

	/**
	 * Sets the kaleo definition ID of this kaleo task assignment instance.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID of this kaleo task assignment instance
	 */
	public void setKaleoDefinitionId(long kaleoDefinitionId);

	/**
	 * Returns the kaleo definition version ID of this kaleo task assignment instance.
	 *
	 * @return the kaleo definition version ID of this kaleo task assignment instance
	 */
	public long getKaleoDefinitionVersionId();

	/**
	 * Sets the kaleo definition version ID of this kaleo task assignment instance.
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID of this kaleo task assignment instance
	 */
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId);

	/**
	 * Returns the kaleo instance ID of this kaleo task assignment instance.
	 *
	 * @return the kaleo instance ID of this kaleo task assignment instance
	 */
	public long getKaleoInstanceId();

	/**
	 * Sets the kaleo instance ID of this kaleo task assignment instance.
	 *
	 * @param kaleoInstanceId the kaleo instance ID of this kaleo task assignment instance
	 */
	public void setKaleoInstanceId(long kaleoInstanceId);

	/**
	 * Returns the kaleo instance token ID of this kaleo task assignment instance.
	 *
	 * @return the kaleo instance token ID of this kaleo task assignment instance
	 */
	public long getKaleoInstanceTokenId();

	/**
	 * Sets the kaleo instance token ID of this kaleo task assignment instance.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID of this kaleo task assignment instance
	 */
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId);

	/**
	 * Returns the kaleo task instance token ID of this kaleo task assignment instance.
	 *
	 * @return the kaleo task instance token ID of this kaleo task assignment instance
	 */
	public long getKaleoTaskInstanceTokenId();

	/**
	 * Sets the kaleo task instance token ID of this kaleo task assignment instance.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID of this kaleo task assignment instance
	 */
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId);

	/**
	 * Returns the kaleo task ID of this kaleo task assignment instance.
	 *
	 * @return the kaleo task ID of this kaleo task assignment instance
	 */
	public long getKaleoTaskId();

	/**
	 * Sets the kaleo task ID of this kaleo task assignment instance.
	 *
	 * @param kaleoTaskId the kaleo task ID of this kaleo task assignment instance
	 */
	public void setKaleoTaskId(long kaleoTaskId);

	/**
	 * Returns the kaleo task name of this kaleo task assignment instance.
	 *
	 * @return the kaleo task name of this kaleo task assignment instance
	 */
	@AutoEscape
	public String getKaleoTaskName();

	/**
	 * Sets the kaleo task name of this kaleo task assignment instance.
	 *
	 * @param kaleoTaskName the kaleo task name of this kaleo task assignment instance
	 */
	public void setKaleoTaskName(String kaleoTaskName);

	/**
	 * Returns the assignee class name of this kaleo task assignment instance.
	 *
	 * @return the assignee class name of this kaleo task assignment instance
	 */
	@AutoEscape
	public String getAssigneeClassName();

	/**
	 * Sets the assignee class name of this kaleo task assignment instance.
	 *
	 * @param assigneeClassName the assignee class name of this kaleo task assignment instance
	 */
	public void setAssigneeClassName(String assigneeClassName);

	/**
	 * Returns the assignee class pk of this kaleo task assignment instance.
	 *
	 * @return the assignee class pk of this kaleo task assignment instance
	 */
	public long getAssigneeClassPK();

	/**
	 * Sets the assignee class pk of this kaleo task assignment instance.
	 *
	 * @param assigneeClassPK the assignee class pk of this kaleo task assignment instance
	 */
	public void setAssigneeClassPK(long assigneeClassPK);

	/**
	 * Returns the completed of this kaleo task assignment instance.
	 *
	 * @return the completed of this kaleo task assignment instance
	 */
	public boolean getCompleted();

	/**
	 * Returns <code>true</code> if this kaleo task assignment instance is completed.
	 *
	 * @return <code>true</code> if this kaleo task assignment instance is completed; <code>false</code> otherwise
	 */
	public boolean isCompleted();

	/**
	 * Sets whether this kaleo task assignment instance is completed.
	 *
	 * @param completed the completed of this kaleo task assignment instance
	 */
	public void setCompleted(boolean completed);

	/**
	 * Returns the completion date of this kaleo task assignment instance.
	 *
	 * @return the completion date of this kaleo task assignment instance
	 */
	public Date getCompletionDate();

	/**
	 * Sets the completion date of this kaleo task assignment instance.
	 *
	 * @param completionDate the completion date of this kaleo task assignment instance
	 */
	public void setCompletionDate(Date completionDate);

	@Override
	public KaleoTaskAssignmentInstance cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}