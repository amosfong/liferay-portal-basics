/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.internal.portlet.action;

import com.liferay.blogs.configuration.BlogsFileUploadsConfiguration;
import com.liferay.blogs.constants.BlogsConstants;
import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.blogs.web.internal.upload.ImageBlogsUploadResponseHandler;
import com.liferay.blogs.web.internal.upload.TempImageBlogsUploadFileEntryHandler;
import com.liferay.item.selector.ItemSelectorUploadResponseHandler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.upload.UniqueFileNameProvider;
import com.liferay.upload.UploadHandler;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	configurationPid = "com.liferay.blogs.configuration.BlogsFileUploadsConfiguration",
	property = {
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
		"mvc.command.name=/blogs/upload_cover_image",
		"mvc.command.name=/blogs/upload_small_image",
		"mvc.command.name=/blogs/upload_temp_image"
	},
	service = MVCActionCommand.class
)
public class UploadTempImageMVCActionCommand extends BaseMVCActionCommand {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		BlogsFileUploadsConfiguration blogsFileUploadsConfiguration =
			ConfigurableUtil.createConfigurable(
				BlogsFileUploadsConfiguration.class, properties);

		_imageBlogsUploadResponseHandler = new ImageBlogsUploadResponseHandler(
			blogsFileUploadsConfiguration, _itemSelectorUploadResponseHandler);

		_tempImageBlogsUploadFileEntryHandler =
			new TempImageBlogsUploadFileEntryHandler(
				_blogsLocalService, blogsFileUploadsConfiguration,
				_portletFileRepository, _portletResourcePermission,
				_uniqueFileNameProvider);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		_uploadHandler.upload(
			_tempImageBlogsUploadFileEntryHandler,
			_imageBlogsUploadResponseHandler, actionRequest, actionResponse);
	}

	@Reference
	private BlogsEntryLocalService _blogsLocalService;

	private volatile ImageBlogsUploadResponseHandler
		_imageBlogsUploadResponseHandler;

	@Reference
	private ItemSelectorUploadResponseHandler
		_itemSelectorUploadResponseHandler;

	@Reference
	private PortletFileRepository _portletFileRepository;

	@Reference(target = "(resource.name=" + BlogsConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	private volatile TempImageBlogsUploadFileEntryHandler
		_tempImageBlogsUploadFileEntryHandler;

	@Reference
	private UniqueFileNameProvider _uniqueFileNameProvider;

	@Reference
	private UploadHandler _uploadHandler;

}