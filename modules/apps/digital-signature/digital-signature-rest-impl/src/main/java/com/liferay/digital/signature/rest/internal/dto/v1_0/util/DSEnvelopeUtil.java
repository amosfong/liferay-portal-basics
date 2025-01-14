/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.digital.signature.rest.internal.dto.v1_0.util;

import com.liferay.digital.signature.rest.dto.v1_0.DSDocument;
import com.liferay.digital.signature.rest.dto.v1_0.DSEnvelope;
import com.liferay.digital.signature.rest.dto.v1_0.DSRecipient;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

/**
 * @author José Abelenda
 */
public class DSEnvelopeUtil {

	public static DSEnvelope toDSEnvelope(
		com.liferay.digital.signature.model.DSEnvelope dsEnvelope) {

		return new DSEnvelope() {
			{
				setDsDocument(
					() -> TransformUtil.transformToArray(
						dsEnvelope.getDSDocuments(),
						dsDocument -> _toDSDocument(dsDocument),
						DSDocument.class));
				setDsRecipient(
					() -> TransformUtil.transformToArray(
						dsEnvelope.getDSRecipients(),
						dsRecipient -> _toDSRecipient(dsRecipient),
						DSRecipient.class));
				setEmailBlurb(dsEnvelope::getEmailBlurb);
				setEmailSubject(dsEnvelope::getEmailSubject);
				setId(dsEnvelope::getDSEnvelopeId);
				setName(dsEnvelope::getName);
				setSenderEmailAddress(dsEnvelope::getSenderEmailAddress);
				setStatus(dsEnvelope::getStatus);
			}
		};
	}

	public static com.liferay.digital.signature.model.DSEnvelope toDSEnvelope(
		DSEnvelope dsEnvelope) {

		return new com.liferay.digital.signature.model.DSEnvelope() {
			{
				dsDocuments = TransformUtil.transformToList(
					dsEnvelope.getDsDocument(),
					dsDocument -> _toDSDocument(dsDocument));
				dsEnvelopeId = dsEnvelope.getId();
				dsRecipients = TransformUtil.transformToList(
					dsEnvelope.getDsRecipient(),
					dsRecipient -> _toDSRecipient(dsRecipient));
				emailBlurb = dsEnvelope.getEmailBlurb();
				emailSubject = dsEnvelope.getEmailSubject();
				name = dsEnvelope.getName();
				senderEmailAddress = dsEnvelope.getSenderEmailAddress();
				status = dsEnvelope.getStatus();
			}
		};
	}

	private static DSDocument _toDSDocument(
		com.liferay.digital.signature.model.DSDocument dsDocument) {

		return new DSDocument() {
			{
				setAssignTabsToDSRecipientId(
					dsDocument::getAssignTabsToDSRecipientId);
				setData(dsDocument::getData);
				setFileExtension(dsDocument::getFileExtension);
				setId(dsDocument::getDSDocumentId);
				setName(dsDocument::getName);
				setTransformPDFFields(dsDocument::isTransformPDFFields);
				setUri(dsDocument::getURI);
			}
		};
	}

	private static com.liferay.digital.signature.model.DSDocument _toDSDocument(
		DSDocument dsDocument) {

		return new com.liferay.digital.signature.model.DSDocument() {
			{
				assignTabsToDSRecipientId =
					dsDocument.getAssignTabsToDSRecipientId();
				data = dsDocument.getData();
				dsDocumentId = dsDocument.getId();
				fileExtension = dsDocument.getFileExtension();
				name = dsDocument.getName();
				transformPDFFields = GetterUtil.getBoolean(
					dsDocument.getTransformPDFFields());
				uri = dsDocument.getUri();
			}
		};
	}

	private static DSRecipient _toDSRecipient(
		com.liferay.digital.signature.model.DSRecipient dsRecipient) {

		return new DSRecipient() {
			{
				setDsClientUserId(dsRecipient::getDSClientUserId);
				setEmailAddress(dsRecipient::getEmailAddress);
				setId(dsRecipient::getDSRecipientId);
				setName(dsRecipient::getName);
				setStatus(dsRecipient::getStatus);
			}
		};
	}

	private static com.liferay.digital.signature.model.DSRecipient
		_toDSRecipient(DSRecipient dsRecipient) {

		return new com.liferay.digital.signature.model.DSRecipient() {
			{
				dsClientUserId = dsRecipient.getDsClientUserId();
				dsRecipientId = dsRecipient.getId();
				emailAddress = dsRecipient.getEmailAddress();
				name = dsRecipient.getName();
				status = dsRecipient.getStatus();

				if (dsRecipient.getTabs() != null) {
					tabsJSONObject = JSONFactoryUtil.createJSONObject(
						(Map<?, ?>)dsRecipient.getTabs());
				}
			}
		};
	}

}