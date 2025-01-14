/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.preview.pdf.internal.background.task;

import com.liferay.document.library.kernel.model.DLProcessorConstants;
import com.liferay.document.library.kernel.processor.DLProcessor;
import com.liferay.document.library.kernel.processor.PDFProcessor;
import com.liferay.document.library.preview.background.task.BasePreviewBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.ContentTypes;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	property = "background.task.executor.class.name=com.liferay.document.library.preview.pdf.internal.background.task.PDFPreviewBackgroundTaskExecutor",
	service = BackgroundTaskExecutor.class
)
public class PDFPreviewBackgroundTaskExecutor
	extends BasePreviewBackgroundTaskExecutor {

	@Override
	protected void generatePreview(FileVersion fileVersion) throws Exception {
		PDFProcessor pdfProcessor = (PDFProcessor)_dlProcessor;

		pdfProcessor.generateImages(null, fileVersion);
	}

	@Override
	protected String[] getMimeTypes() {
		return new String[] {ContentTypes.APPLICATION_PDF};
	}

	@Reference(target = "(type=" + DLProcessorConstants.PDF_PROCESSOR + ")")
	private DLProcessor _dlProcessor;

}