/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.writer;

import com.liferay.batch.engine.BatchEngineTaskContentType;
import com.liferay.batch.engine.csv.ColumnDescriptorProvider;
import com.liferay.batch.engine.unit.BatchEngineUnitConfiguration;
import com.liferay.portal.kernel.util.ObjectValuePair;

import java.io.OutputStream;
import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class BatchEngineExportTaskItemWriterBuilder {

	public BatchEngineExportTaskItemWriterBuilder batchEngineTaskContentType(
		BatchEngineTaskContentType batchEngineTaskContentType) {

		_batchEngineTaskContentType = batchEngineTaskContentType;

		return this;
	}

	public BatchEngineExportTaskItemWriter build() throws Exception {
		Map<String, ObjectValuePair<Field, Method>> fieldNameObjectValuePairs =
			ItemClassIndexUtil.index(_itemClass);

		if (_batchEngineTaskContentType == BatchEngineTaskContentType.CSV) {
			return new CSVBatchEngineExportTaskItemWriterImpl(
				_columnDescriptorProvider, _companyId, _csvFileColumnDelimiter,
				fieldNameObjectValuePairs, _fieldNames, _outputStream,
				_parameters, _taskItemDelegateName);
		}

		if (_batchEngineTaskContentType == BatchEngineTaskContentType.JSON) {
			return new JSONBatchEngineExportTaskItemWriterImpl(
				_fieldNames, _outputStream);
		}

		if (_batchEngineTaskContentType == BatchEngineTaskContentType.JSONL) {
			return new JSONLBatchEngineExportTaskItemWriterImpl(
				_fieldNames, _outputStream);
		}

		if ((_batchEngineTaskContentType == BatchEngineTaskContentType.XLS) ||
			(_batchEngineTaskContentType == BatchEngineTaskContentType.XLSX)) {

			return new XLSBatchEngineExportTaskItemWriterImpl(
				_columnDescriptorProvider, _companyId,
				fieldNameObjectValuePairs, _fieldNames, _outputStream,
				_taskItemDelegateName);
		}

		if (_batchEngineTaskContentType == BatchEngineTaskContentType.JSONT) {
			BatchEngineUnitConfiguration batchEngineUnitConfiguration =
				new BatchEngineUnitConfiguration();

			batchEngineUnitConfiguration.setClassName(_itemClass.getName());
			batchEngineUnitConfiguration.setCompanyId(_companyId);

			if (_parameters == null) {
				_parameters = new HashMap<>();
			}

			_parameters.computeIfAbsent("createStrategy", key -> "INSERT");
			_parameters.computeIfAbsent(
				"importStrategy", key -> "ON_ERROR_FAIL");
			_parameters.computeIfAbsent("updateStrategy", key -> "UPDATE");

			batchEngineUnitConfiguration.setParameters(_parameters);
			batchEngineUnitConfiguration.setTaskItemDelegateName(
				_taskItemDelegateName);
			batchEngineUnitConfiguration.setUserId(_userId);
			batchEngineUnitConfiguration.setVersion("v1.0");

			return new JSONTBatchEngineExportTaskItemWriterImpl(
				batchEngineUnitConfiguration, _fieldNames, _outputStream);
		}

		throw new IllegalArgumentException(
			"Unknown batch engine task content type " +
				_batchEngineTaskContentType);
	}

	public BatchEngineExportTaskItemWriterBuilder columnDescriptorProvider(
		ColumnDescriptorProvider columnDescriptorProvider) {

		_columnDescriptorProvider = columnDescriptorProvider;

		return this;
	}

	public BatchEngineExportTaskItemWriterBuilder companyId(long companyId) {
		_companyId = companyId;

		return this;
	}

	public BatchEngineExportTaskItemWriterBuilder csvFileColumnDelimiter(
		String csvFileColumnDelimiter) {

		_csvFileColumnDelimiter = csvFileColumnDelimiter;

		return this;
	}

	public BatchEngineExportTaskItemWriterBuilder fieldNames(
		List<String> fieldNames) {

		_fieldNames = fieldNames;

		return this;
	}

	public BatchEngineExportTaskItemWriterBuilder itemClass(
		Class<?> itemClass) {

		_itemClass = itemClass;

		return this;
	}

	public BatchEngineExportTaskItemWriterBuilder outputStream(
		OutputStream outputStream) {

		_outputStream = outputStream;

		return this;
	}

	public BatchEngineExportTaskItemWriterBuilder parameters(
		Map<String, Serializable> parameters) {

		_parameters = parameters;

		return this;
	}

	public BatchEngineExportTaskItemWriterBuilder taskItemDelegateName(
		String taskItemDelegateName) {

		_taskItemDelegateName = taskItemDelegateName;

		return this;
	}

	public BatchEngineExportTaskItemWriterBuilder userId(long userId) {
		_userId = userId;

		return this;
	}

	private BatchEngineTaskContentType _batchEngineTaskContentType;
	private ColumnDescriptorProvider _columnDescriptorProvider;
	private long _companyId;
	private String _csvFileColumnDelimiter;
	private List<String> _fieldNames;
	private Class<?> _itemClass;
	private OutputStream _outputStream;
	private Map<String, Serializable> _parameters;
	private String _taskItemDelegateName;
	private long _userId;

}