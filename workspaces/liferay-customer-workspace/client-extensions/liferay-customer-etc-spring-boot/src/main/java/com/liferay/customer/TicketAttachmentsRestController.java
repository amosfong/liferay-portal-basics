/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.customer;

import com.liferay.client.extension.util.spring.boot.BaseRestController;
import com.liferay.customer.model.TicketAttachment;
import com.liferay.customer.service.GoogleCloudStorageService;
import com.liferay.customer.service.TicketAttachmentService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Amos Fong
 */
@RequestMapping("/ticket-attachments/{ticketAttachmentId}")
@RestController
public class TicketAttachmentsRestController extends BaseRestController {

	@DeleteMapping
	public ResponseEntity<String> delete(
		@AuthenticationPrincipal Jwt jwt,
		@PathVariable("ticketAttachmentId") long ticketAttachmentId) {

		try {
			TicketAttachment ticketAttachment =
				_ticketAttachmentService.fetchTicketAttachment(
					jwt, ticketAttachmentId);

			if (ticketAttachment == null) {
				return new ResponseEntity<>(
					"Ticket attachment " + ticketAttachmentId +
						" does not exist",
					HttpStatus.NOT_FOUND);
			}

			_ticketAttachmentService.deleteTicketAttachment(
				jwt, ticketAttachmentId);

			_googleCloudStorageService.deleteObject(
				ticketAttachment.getGCSBucketName(),
				ticketAttachment.getGCSObjectName());

			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return new ResponseEntity(
				exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private static final Log _log = LogFactory.getLog(
		TicketAttachmentsRestController.class);

	@Autowired
	private GoogleCloudStorageService _googleCloudStorageService;

	@Autowired
	private TicketAttachmentService _ticketAttachmentService;

}