/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class DefaultWorkflowDefinition
	implements Serializable, WorkflowDefinition {

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public String getContent() {
		return _content;
	}

	@Override
	public String getContentAsXML() {
		return _contentAsXML;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}

		return _description;
	}

	@Override
	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	@Override
	public InputStream getInputStream() {
		return _inputStream;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public Map<String, Object> getOptionalAttributes() {
		return _optionalAttributes;
	}

	@Override
	public String getScope() {
		return _scope;
	}

	@Override
	public String getTitle() {
		if (_title == null) {
			return StringPool.BLANK;
		}

		return _title;
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public int getVersion() {
		return _version;
	}

	@Override
	public long getWorkflowDefinitionId() {
		return _workflowDefinitionId;
	}

	@Override
	public List<WorkflowNode> getWorkflowNodes() {
		return _workflowNodes;
	}

	@Override
	public List<WorkflowTransition> getWorkflowTransitions() {
		return _workflowTransitions;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setContentAsXML(String contentAsXML) {
		_contentAsXML = contentAsXML;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setInputStream(InputStream inputStream) {
		_inputStream = inputStream;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOptionalAttributes(Map<String, Object> optionalAttributes) {
		_optionalAttributes = optionalAttributes;
	}

	public void setScope(String scope) {
		_scope = scope;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowDefinitionId = workflowDefinitionId;
	}

	public void setWorkflowNodes(List<WorkflowNode> workflowNodes) {
		_workflowNodes = workflowNodes;
	}

	public void setWorkflowTransitions(
		List<WorkflowTransition> workflowTransitions) {

		_workflowTransitions = workflowTransitions;
	}

	private boolean _active;
	private long _companyId;
	private String _content;
	private String _contentAsXML;
	private Date _createDate;
	private String _description;
	private String _externalReferenceCode;
	private InputStream _inputStream;
	private Date _modifiedDate;
	private String _name;
	private Map<String, Object> _optionalAttributes;
	private String _scope;
	private String _title;
	private long _userId;
	private int _version;
	private long _workflowDefinitionId;
	private List<WorkflowNode> _workflowNodes;
	private List<WorkflowTransition> _workflowTransitions;

}