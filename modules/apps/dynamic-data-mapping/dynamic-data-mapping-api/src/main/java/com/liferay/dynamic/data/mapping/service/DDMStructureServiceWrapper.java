/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DDMStructureService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureService
 * @generated
 */
public class DDMStructureServiceWrapper
	implements DDMStructureService, ServiceWrapper<DDMStructureService> {

	public DDMStructureServiceWrapper() {
		this(null);
	}

	public DDMStructureServiceWrapper(DDMStructureService ddmStructureService) {
		_ddmStructureService = ddmStructureService;
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure addStructure(
			long groupId, long parentStructureId, long classNameId,
			String structureKey,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			com.liferay.dynamic.data.mapping.model.DDMForm ddmForm,
			com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout,
			String storageType, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.addStructure(
			groupId, parentStructureId, classNameId, structureKey, nameMap,
			descriptionMap, ddmForm, ddmFormLayout, storageType, type,
			serviceContext);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure addStructure(
			long groupId, long classNameId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			com.liferay.dynamic.data.mapping.model.DDMForm ddmForm,
			com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout,
			String storageType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.addStructure(
			groupId, classNameId, nameMap, descriptionMap, ddmForm,
			ddmFormLayout, storageType, serviceContext);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure addStructure(
			long groupId, String parentStructureKey, long classNameId,
			String structureKey,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			com.liferay.dynamic.data.mapping.model.DDMForm ddmForm,
			com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout,
			String storageType, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.addStructure(
			groupId, parentStructureKey, classNameId, structureKey, nameMap,
			descriptionMap, ddmForm, ddmFormLayout, storageType, type,
			serviceContext);
	}

	/**
	 * Copies a structure, creating a new structure with all the values
	 * extracted from the original one. The new structure supports a new name
	 * and description.
	 *
	 * @param sourceStructureId the primary key of the structure to be copied
	 * @param nameMap the new structure's locales and localized names
	 * @param descriptionMap the new structure's locales and localized
	 descriptions
	 * @param serviceContext the service context to be applied. Can set the
	 UUID, creation date, modification date, guest permissions, and
	 group permissions for the structure.
	 * @return the new structure
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure copyStructure(
			long sourceStructureId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.copyStructure(
			sourceStructureId, nameMap, descriptionMap, serviceContext);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure copyStructure(
			long sourceStructureId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.copyStructure(
			sourceStructureId, serviceContext);
	}

	/**
	 * Deletes the structure and its resources.
	 *
	 * <p>
	 * Before deleting the structure, the system verifies whether the structure
	 * is required by another entity. If it is needed, an exception is thrown.
	 * </p>
	 *
	 * @param structureId the primary key of the structure to be deleted
	 */
	@Override
	public void deleteStructure(long structureId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_ddmStructureService.deleteStructure(structureId);
	}

	/**
	 * Returns the structure matching the class name ID, structure key, and
	 * group.
	 *
	 * @param groupId the primary key of the group
	 * @param classNameId the primary key of the class name for the structure's
	 related model
	 * @param structureKey the unique string identifying the structure
	 * @return the matching structure, or <code>null</code> if a matching
	 structure could not be found
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure fetchStructure(
			long groupId, long classNameId, String structureKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.fetchStructure(
			groupId, classNameId, structureKey);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure fetchStructure(
			long groupId, long classNameId, String structureKey,
			boolean includeAncestorStructures)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.fetchStructure(
			groupId, classNameId, structureKey, includeAncestorStructures);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure
			fetchStructureByExternalReferenceCode(
				String externalReferenceCode, long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.fetchStructureByExternalReferenceCode(
			externalReferenceCode, groupId, classNameId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ddmStructureService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns the structure with the ID.
	 *
	 * @param structureId the primary key of the structure
	 * @return the structure with the ID
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure getStructure(
			long structureId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.getStructure(structureId);
	}

	/**
	 * Returns the structure matching the class name ID, structure key, and
	 * group.
	 *
	 * @param groupId the primary key of the structure's group
	 * @param classNameId the primary key of the class name for the structure's
	 related model
	 * @param structureKey the unique string identifying the structure
	 * @return the matching structure
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure getStructure(
			long groupId, long classNameId, String structureKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.getStructure(
			groupId, classNameId, structureKey);
	}

	/**
	 * Returns the structure matching the class name ID, structure key, and
	 * group, optionally searching ancestor sites (that have sharing enabled)
	 * and global scoped sites.
	 *
	 * <p>
	 * This method first searches in the group. If the structure is still not
	 * found and <code>includeAncestorStructures</code> is set to
	 * <code>true</code>, this method searches the group's ancestor sites (that
	 * have sharing enabled) and lastly searches global scoped sites.
	 * </p>
	 *
	 * @param groupId the primary key of the structure's group
	 * @param classNameId the primary key of the class name for the structure's
	 related model
	 * @param structureKey the unique string identifying the structure
	 * @param includeAncestorStructures whether to include ancestor sites (that
	 have sharing enabled) and include global scoped sites in the
	 search
	 * @return the matching structure
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure getStructure(
			long groupId, long classNameId, String structureKey,
			boolean includeAncestorStructures)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.getStructure(
			groupId, classNameId, structureKey, includeAncestorStructures);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure
			getStructureByExternalReferenceCode(
				String externalReferenceCode, long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.getStructureByExternalReferenceCode(
			externalReferenceCode, groupId, classNameId);
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		getStructures(
			long companyId, long[] groupIds, long classNameId, int status) {

		return _ddmStructureService.getStructures(
			companyId, groupIds, classNameId, status);
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		getStructures(
			long companyId, long[] groupIds, long classNameId, int status,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator) {

		return _ddmStructureService.getStructures(
			companyId, groupIds, classNameId, status, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		getStructures(
			long companyId, long[] groupIds, long classNameId, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator) {

		return _ddmStructureService.getStructures(
			companyId, groupIds, classNameId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		getStructures(
			long companyId, long[] groupIds, long classNameId, String keywords,
			int status, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator) {

		return _ddmStructureService.getStructures(
			companyId, groupIds, classNameId, keywords, status, start, end,
			orderByComparator);
	}

	@Override
	public int getStructuresCount(
		long companyId, long[] groupIds, long classNameId) {

		return _ddmStructureService.getStructuresCount(
			companyId, groupIds, classNameId);
	}

	@Override
	public int getStructuresCount(
		long companyId, long[] groupIds, long classNameId, String keywords,
		int status) {

		return _ddmStructureService.getStructuresCount(
			companyId, groupIds, classNameId, keywords, status);
	}

	@Override
	public void revertStructure(
			long structureId, String version,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_ddmStructureService.revertStructure(
			structureId, version, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
			search(
				long companyId, long[] groupIds, long classNameId, long classPK,
				String keywords, int status, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.dynamic.data.mapping.model.DDMStructure>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.search(
			companyId, groupIds, classNameId, classPK, keywords, status, start,
			end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the structures matching the groups and
	 * class name IDs, and matching the keywords in the structure names and
	 * descriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param companyId the primary key of the structure's company
	 * @param groupIds the primary keys of the groups
	 * @param classNameId the primary key of the class name of the model the
	 structure is related to
	 * @param keywords the keywords (space separated), which may occur in the
	 structure's name or description (optionally <code>null</code>)
	 * @param type the structure's type. For more information, see {@link
	 com.liferay.dynamic.data.mapping.constants.DDMStructureConstants}.
	 * @param status the workflow's status.
	 * @param start the lower bound of the range of structures to return
	 * @param end the upper bound of the range of structures to return (not
	 inclusive)
	 * @param orderByComparator the comparator to order the structures
	 (optionally <code>null</code>)
	 * @return the range of matching structures ordered by the comparator
	 */
	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		search(
			long companyId, long[] groupIds, long classNameId, String keywords,
			int type, int status, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator) {

		return _ddmStructureService.search(
			companyId, groupIds, classNameId, keywords, type, status, start,
			end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the structures matching the groups and
	 * class name IDs, and matching the keywords in the structure names and
	 * descriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param companyId the primary key of the structure's company
	 * @param groupIds the primary keys of the groups
	 * @param classNameId the primary key of the class name of the model the
	 structure is related to
	 * @param keywords the keywords (space separated), which may occur in the
	 structure's name or description (optionally <code>null</code>)
	 * @param status the workflow's status.
	 * @param start the lower bound of the range of structures to return
	 * @param end the upper bound of the range of structures to return (not
	 inclusive)
	 * @param orderByComparator the comparator to order the structures
	 (optionally <code>null</code>)
	 * @return the range of matching structures ordered by the comparator
	 */
	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		search(
			long companyId, long[] groupIds, long classNameId, String keywords,
			int status, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator) {

		return _ddmStructureService.search(
			companyId, groupIds, classNameId, keywords, status, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the structures matching the groups, class
	 * name IDs, name keyword, description keyword, storage type, and type.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param companyId the primary key of the structure's company
	 * @param groupIds the primary keys of the groups
	 * @param classNameId the primary key of the class name of the model the
	 structure is related to
	 * @param name the name keywords
	 * @param description the description keywords
	 * @param storageType the structure's storage type. It can be "xml" or
	 "expando". For more information, see {@link
	 com.liferay.dynamic.data.mapping.storage.StorageType}.
	 * @param type the structure's type. For more information, see {@link
	 com.liferay.dynamic.data.mapping.constants.DDMStructureConstants}.
	 * @param status the workflow's status.
	 * @param andOperator whether every field must match its keywords, or just
	 one field
	 * @param start the lower bound of the range of structures to return
	 * @param end the upper bound of the range of structures to return (not
	 inclusive)
	 * @param orderByComparator the comparator to order the structures
	 (optionally <code>null</code>)
	 * @return the range of matching structures ordered by the comparator
	 */
	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		search(
			long companyId, long[] groupIds, long classNameId, String name,
			String description, String storageType, int type, int status,
			boolean andOperator, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator) {

		return _ddmStructureService.search(
			companyId, groupIds, classNameId, name, description, storageType,
			type, status, andOperator, start, end, orderByComparator);
	}

	@Override
	public int searchCount(
			long companyId, long[] groupIds, long classNameId, long classPK,
			String keywords, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.searchCount(
			companyId, groupIds, classNameId, classPK, keywords, status);
	}

	/**
	 * Returns the number of structures matching the groups and class name IDs,
	 * and matching the keywords in the structure names and descriptions.
	 *
	 * @param companyId the primary key of the structure's company
	 * @param groupIds the primary keys of the groups
	 * @param classNameId the primary key of the class name of the model the
	 structure is related to
	 * @param keywords the keywords (space separated), which may occur in the
	 structure's name or description (optionally <code>null</code>)
	 * @param status the workflow's status.
	 * @return the number of matching structures
	 */
	@Override
	public int searchCount(
		long companyId, long[] groupIds, long classNameId, String keywords,
		int status) {

		return _ddmStructureService.searchCount(
			companyId, groupIds, classNameId, keywords, status);
	}

	/**
	 * Returns the number of structures matching the groups and class name IDs,
	 * and matching the keywords in the structure names and descriptions.
	 *
	 * @param companyId the primary key of the structure's company
	 * @param groupIds the primary keys of the groups
	 * @param classNameId the primary key of the class name of the model the
	 structure is related to
	 * @param keywords the keywords (space separated), which may occur in the
	 structure's name or description (optionally <code>null</code>)
	 * @param type the structure's type. For more information, see {@link
	 com.liferay.dynamic.data.mapping.constants.DDMStructureConstants}.
	 * @param status the workflow's status.
	 * @return the number of matching structures
	 */
	@Override
	public int searchCount(
		long companyId, long[] groupIds, long classNameId, String keywords,
		int type, int status) {

		return _ddmStructureService.searchCount(
			companyId, groupIds, classNameId, keywords, type, status);
	}

	/**
	 * Returns the number of structures matching the groups, class name IDs,
	 * name keyword, description keyword, storage type, and type
	 *
	 * @param companyId the primary key of the structure's company
	 * @param groupIds the primary keys of the groups
	 * @param classNameId the primary key of the class name of the model the
	 structure is related to
	 * @param name the name keywords
	 * @param description the description keywords
	 * @param storageType the structure's storage type. It can be "xml" or
	 "expando". For more information, see {@link
	 com.liferay.dynamic.data.mapping.storage.StorageType}.
	 * @param type the structure's type. For more information, see {@link
	 com.liferay.dynamic.data.mapping.constants.DDMStructureConstants}.
	 * @param andOperator whether every field must match its keywords, or just
	 one field
	 * @return the number of matching structures
	 */
	@Override
	public int searchCount(
		long companyId, long[] groupIds, long classNameId, String name,
		String description, String storageType, int type, int status,
		boolean andOperator) {

		return _ddmStructureService.searchCount(
			companyId, groupIds, classNameId, name, description, storageType,
			type, status, andOperator);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure updateStructure(
			long groupId, long parentStructureId, long classNameId,
			String structureKey,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			com.liferay.dynamic.data.mapping.model.DDMForm ddmForm,
			com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.updateStructure(
			groupId, parentStructureId, classNameId, structureKey, nameMap,
			descriptionMap, ddmForm, ddmFormLayout, serviceContext);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure updateStructure(
			long structureId, long parentStructureId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			com.liferay.dynamic.data.mapping.model.DDMForm ddmForm,
			com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmStructureService.updateStructure(
			structureId, parentStructureId, nameMap, descriptionMap, ddmForm,
			ddmFormLayout, serviceContext);
	}

	@Override
	public DDMStructureService getWrappedService() {
		return _ddmStructureService;
	}

	@Override
	public void setWrappedService(DDMStructureService ddmStructureService) {
		_ddmStructureService = ddmStructureService;
	}

	private DDMStructureService _ddmStructureService;

}