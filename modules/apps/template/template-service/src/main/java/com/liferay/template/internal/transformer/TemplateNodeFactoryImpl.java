/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.template.internal.transformer;

import com.liferay.info.field.InfoField;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.field.type.InfoFieldType;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceComparator;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.templateparser.TemplateNode;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.template.info.field.transformer.TemplateNodeTransformer;
import com.liferay.template.transformer.TemplateNodeFactory;

import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Lourdes Fernández Besada
 */
@Component(service = TemplateNodeFactory.class)
public class TemplateNodeFactoryImpl implements TemplateNodeFactory {

	@Override
	public TemplateNode createTemplateNode(
		InfoFieldValue<Object> infoFieldValue, ThemeDisplay themeDisplay) {

		InfoField<?> infoField = infoFieldValue.getInfoField();

		InfoFieldType infoFieldType = infoField.getInfoFieldType();

		Class<?> infoFieldTypeClass = infoFieldType.getClass();

		TemplateNodeTransformer templateNodeTransformer =
			_getTemplateNodeTransformer(infoFieldTypeClass.getName());

		return templateNodeTransformer.transform(infoFieldValue, themeDisplay);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext,
			(Class<TemplateNodeTransformer>)
				(Class)TemplateNodeTransformer.class,
			null,
			(serviceReference, emitter) -> {
				try {
					List<String> classNames = StringUtil.asList(
						serviceReference.getProperty(
							"info.field.type.class.name"));

					for (String className : classNames) {
						emitter.emit(className);
					}

					if (classNames.isEmpty()) {
						emitter.emit(_CLASS_NAME_ANY);
					}
				}
				finally {
					bundleContext.ungetService(serviceReference);
				}
			},
			new PropertyServiceReferenceComparator<>("service.ranking"));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private TemplateNodeTransformer _getTemplateNodeTransformer(
		String className) {

		TemplateNodeTransformer templateNodeTransformer =
			_serviceTrackerMap.getService(className);

		if (templateNodeTransformer != null) {
			return templateNodeTransformer;
		}

		return _serviceTrackerMap.getService(_CLASS_NAME_ANY);
	}

	private static final String _CLASS_NAME_ANY = "<ANY>";

	private ServiceTrackerMap<String, TemplateNodeTransformer>
		_serviceTrackerMap;

}