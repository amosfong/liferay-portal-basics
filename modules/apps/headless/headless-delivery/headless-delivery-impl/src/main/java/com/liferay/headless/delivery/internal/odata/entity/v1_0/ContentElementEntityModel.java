/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.internal.odata.entity.v1_0;

import com.liferay.headless.common.spi.odata.entity.EntityFieldsMapFactory;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.odata.entity.CollectionEntityField;
import com.liferay.portal.odata.entity.DateTimeEntityField;
import com.liferay.portal.odata.entity.DoubleEntityField;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.IntegerEntityField;
import com.liferay.portal.odata.entity.StringEntityField;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;

import java.util.Map;

/**
 * @author Javier Gamarra
 */
public class ContentElementEntityModel implements EntityModel {

	public ContentElementEntityModel(
		DTOConverterRegistry dtoConverterRegistry) {

		_entityFieldsMap = EntityFieldsMapFactory.create(
			new CollectionEntityField(
				new IntegerEntityField(
					"taxonomyCategoryIds", locale -> "assetCategoryIds")),
			new CollectionEntityField(
				new StringEntityField(
					"keywords", locale -> "assetTagNames.lowercase")),
			new DateTimeEntityField(
				"dateCreated",
				locale -> Field.getSortableFieldName(Field.CREATE_DATE),
				locale -> Field.CREATE_DATE),
			new DateTimeEntityField(
				"dateModified",
				locale -> Field.getSortableFieldName(Field.MODIFIED_DATE),
				locale -> Field.MODIFIED_DATE),
			new DoubleEntityField(
				"priority",
				locale -> Field.getSortableFieldName(Field.PRIORITY)),
			new EntityField(
				"contentType", EntityField.Type.STRING,
				locale -> Field.ENTRY_CLASS_NAME,
				locale -> Field.ENTRY_CLASS_NAME,
				object -> _getFilterableFieldFunction(
					dtoConverterRegistry, object)),
			new IntegerEntityField("creatorId", locale -> Field.USER_ID),
			new StringEntityField(
				"title",
				locale -> Field.getSortableFieldName(
					"localized_title_".concat(LocaleUtil.toLanguageId(locale))),
				locale -> {
					String sortableFieldName = Field.getSortableFieldName(
						"localized_title_".concat(
							LocaleUtil.toLanguageId(locale)));

					return sortableFieldName.concat(".keyword_lowercase");
				}));
	}

	@Override
	public Map<String, EntityField> getEntityFieldsMap() {
		return _entityFieldsMap;
	}

	private String _getFilterableFieldFunction(
		DTOConverterRegistry dtoConverterRegistry, Object object) {

		for (String dtoClassName : dtoConverterRegistry.getDTOClassNames()) {
			DTOConverter<?, ?> dtoConverter =
				dtoConverterRegistry.getDTOConverter(dtoClassName);

			if (object.equals(dtoConverter.getContentType())) {
				return dtoConverter.getDTOClassName();
			}
		}

		return String.valueOf(object);
	}

	private final Map<String, EntityField> _entityFieldsMap;

}