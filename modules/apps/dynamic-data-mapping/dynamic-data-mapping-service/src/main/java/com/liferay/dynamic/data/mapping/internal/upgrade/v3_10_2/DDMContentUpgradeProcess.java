/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v3_10_2;

import com.liferay.dynamic.data.mapping.internal.upgrade.v3_10_2.util.DDMFormFieldUpgradeProcessUtil;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.util.DDMFormDeserializeUtil;
import com.liferay.dynamic.data.mapping.util.DDMFormValuesDeserializeUtil;
import com.liferay.dynamic.data.mapping.util.DDMFormValuesSerializeUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Carolina Barbosa
 */
public class DDMContentUpgradeProcess extends UpgradeProcess {

	public DDMContentUpgradeProcess(
		DDMFormDeserializer ddmFormDeserializer,
		DDMFormValuesDeserializer ddmFormValuesDeserializer,
		DDMFormValuesSerializer ddmFormValuesSerializer,
		JSONFactory jsonFactory) {

		_ddmFormDeserializer = ddmFormDeserializer;
		_ddmFormValuesDeserializer = ddmFormValuesDeserializer;
		_ddmFormValuesSerializer = ddmFormValuesSerializer;
		_jsonFactory = jsonFactory;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement selectPreparedStatement =
				connection.prepareStatement(
					StringBundler.concat(
						"select DDMContent.ctCollectionId, ",
						"DDMContent.contentId, DDMContent.data_, ",
						"DDMStructureVersion.definition from DDMContent inner ",
						"join DDMFormInstanceRecordVersion on ",
						"DDMContent.contentId = ",
						"DDMFormInstanceRecordVersion.storageId inner join ",
						"DDMFormInstanceVersion on ",
						"DDMFormInstanceRecordVersion.formInstanceId = ",
						"DDMFormInstanceVersion.formInstanceId and ",
						"DDMFormInstanceRecordVersion.formInstanceVersion = ",
						"DDMFormInstanceVersion.version inner join ",
						"DDMStructureVersion on ",
						"DDMFormInstanceVersion.structureVersionId = ",
						"DDMStructureVersion.structureVersionId"));
			PreparedStatement updatePreparedStatement =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMContent set data_ = ? where ctCollectionId = " +
						"? and contentId = ?")) {

			try (ResultSet resultSet = selectPreparedStatement.executeQuery()) {
				while (resultSet.next()) {
					String data = resultSet.getString("data_");

					String newData = upgradeDDMContentData(
						data, resultSet.getString("definition"));

					if (StringUtil.equals(data, newData)) {
						continue;
					}

					updatePreparedStatement.setString(1, newData);
					updatePreparedStatement.setLong(
						2, resultSet.getLong("ctCollectionId"));
					updatePreparedStatement.setLong(
						3, resultSet.getLong("contentId"));

					updatePreparedStatement.addBatch();
				}

				updatePreparedStatement.executeBatch();
			}
		}
	}

	protected String upgradeDDMContentData(String data, String definition)
		throws Exception {

		DDMForm ddmForm = DDMFormDeserializeUtil.deserialize(
			_ddmFormDeserializer, definition);

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(true);

		DDMFormValues ddmFormValues = DDMFormValuesDeserializeUtil.deserialize(
			data, ddmForm, _ddmFormValuesDeserializer);

		_normalizeDDMFormFieldValues(
			ddmFormFieldsMap, ddmFormValues.getDDMFormFieldValues());

		return DDMFormValuesSerializeUtil.serialize(
			ddmFormValues, _ddmFormValuesSerializer);
	}

	private void _normalizeDDMFormFieldValue(Value value) throws Exception {
		if (value == null) {
			return;
		}

		Set<Locale> availableLocales = new HashSet<>(
			value.getAvailableLocales());

		for (Locale availableLocale : availableLocales) {
			String valueString = value.getString(availableLocale);

			if (valueString.startsWith(StringPool.OPEN_BRACKET) &&
				valueString.endsWith(StringPool.CLOSE_BRACKET)) {

				value.addString(
					availableLocale,
					String.valueOf(
						DDMFormFieldUpgradeProcessUtil.getNormalizedJSONArray(
							_jsonFactory.createJSONArray(valueString))));
			}
			else {
				value.addString(
					availableLocale,
					DDMFormFieldUpgradeProcessUtil.getNormalizedName(
						valueString));
			}
		}
	}

	private void _normalizeDDMFormFieldValues(
			Map<String, DDMFormField> ddmFormFieldsMap,
			List<DDMFormFieldValue> ddmFormFieldValues)
		throws Exception {

		for (DDMFormFieldValue ddmFormFieldValue : ddmFormFieldValues) {
			ddmFormFieldValue.setFieldReference(
				DDMFormFieldUpgradeProcessUtil.getNormalizedName(
					ddmFormFieldValue.getFieldReference()));

			String ddmFormFieldValueName = ddmFormFieldValue.getName();

			ddmFormFieldValue.setName(
				DDMFormFieldUpgradeProcessUtil.getNormalizedName(
					ddmFormFieldValueName));

			DDMFormField ddmFormField = ddmFormFieldsMap.get(
				ddmFormFieldValueName);

			if ((ddmFormField != null) &&
				DDMFormFieldUpgradeProcessUtil.isDDMFormFieldWithOptions(
					ddmFormField.getType())) {

				_normalizeDDMFormFieldValue(ddmFormFieldValue.getValue());
			}

			_normalizeDDMFormFieldValues(
				ddmFormFieldsMap,
				ddmFormFieldValue.getNestedDDMFormFieldValues());
		}
	}

	private final DDMFormDeserializer _ddmFormDeserializer;
	private final DDMFormValuesDeserializer _ddmFormValuesDeserializer;
	private final DDMFormValuesSerializer _ddmFormValuesSerializer;
	private final JSONFactory _jsonFactory;

}