/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.permission.internal;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.ast.ASTNode;
import com.liferay.petra.sql.dsl.expression.Expression;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.petra.sql.dsl.query.WhereStep;
import com.liferay.petra.sql.dsl.spi.ast.BaseASTNode;
import com.liferay.petra.sql.dsl.spi.query.Where;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermissionTable;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.InlineSQLHelper;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.contributor.PermissionSQLContributor;
import com.liferay.portal.security.permission.internal.configuration.InlinePermissionConfiguration;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Raymond Augé
 * @author Connor McKay
 * @author Sergio González
 */
@Component(
	configurationPid = "com.liferay.portal.security.permission.internal.configuration.InlinePermissionConfiguration",
	service = InlineSQLHelper.class
)
public class InlineSQLHelperImpl implements InlineSQLHelper {

	public static final String FIND_BY_RESOURCE_PERMISSION =
		InlineSQLHelper.class.getName() + ".findByResourcePermission";

	@Override
	public <T extends Table<T>> Predicate getPermissionWherePredicate(
		Class<?> modelClass, Column<T, Long> classPKColumn, long... groupIds) {

		return getPermissionWherePredicate(
			modelClass.getName(), classPKColumn, groupIds);
	}

	@Override
	public <T extends Table<T>> Predicate getPermissionWherePredicate(
		String modelClassName, Column<T, Long> classPKColumn,
		long... groupIds) {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (ArrayUtil.isEmpty(groupIds)) {
			groupIds = new long[] {0};
		}

		if (_skipReplace(
				permissionChecker, modelClassName, classPKColumn, groupIds)) {

			return null;
		}

		return _getPermissionWherePredicate(
			permissionChecker, modelClassName, classPKColumn, groupIds);
	}

	@Override
	public boolean isEnabled() {
		return isEnabled(0);
	}

	@Override
	public boolean isEnabled(long groupId) {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker == null) {
			throw new IllegalStateException("Permission checker is null");
		}

		return isEnabled(permissionChecker.getCompanyId(), groupId);
	}

	@Override
	public boolean isEnabled(long companyId, long groupId) {
		if (!_inlinePermissionConfiguration.sqlCheckEnabled()) {
			return false;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker == null) {
			throw new IllegalStateException("Permission checker is null");
		}

		if (groupId > 0) {
			if (permissionChecker.isGroupAdmin(groupId) ||
				permissionChecker.isGroupOwner(groupId)) {

				return false;
			}
		}
		else if (companyId > 0) {
			if (permissionChecker.isCompanyAdmin(companyId)) {
				return false;
			}
		}
		else {
			if (permissionChecker.isOmniadmin()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isEnabled(long[] groupIds) {
		if (!_inlinePermissionConfiguration.sqlCheckEnabled()) {
			return false;
		}

		for (long groupId : groupIds) {
			if (isEnabled(groupId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public <T extends Table<T>> DSLQuery replacePermissionCheck(
		DSLQuery dslQuery, Class<?> modelClass, Column<T, Long> classPKColumn,
		long... groupIds) {

		Predicate permissionWherePredicate = getPermissionWherePredicate(
			modelClass, classPKColumn, groupIds);

		if (permissionWherePredicate == null) {
			return dslQuery;
		}

		return _insertResourcePermissionQuery(
			dslQuery, permissionWherePredicate);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField) {

		return replacePermissionCheck(
			sql, className, classPKField, null, new long[] {0}, null);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId) {

		return replacePermissionCheck(
			sql, className, classPKField, null, new long[] {groupId}, null);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId,
		String bridgeJoin) {

		return replacePermissionCheck(
			sql, className, classPKField, null, new long[] {groupId},
			bridgeJoin);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds) {

		return replacePermissionCheck(
			sql, className, classPKField, null, groupIds, null);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds,
		String bridgeJoin) {

		return replacePermissionCheck(
			sql, className, classPKField, null, groupIds, bridgeJoin);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField) {

		return replacePermissionCheck(
			sql, className, classPKField, userIdField, new long[] {0}, null);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId) {

		return replacePermissionCheck(
			sql, className, classPKField, userIdField, new long[] {groupId},
			null);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId, String bridgeJoin) {

		return replacePermissionCheck(
			sql, className, classPKField, userIdField, new long[] {groupId},
			bridgeJoin);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds) {

		return replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIds, null);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds, String bridgeJoin) {

		String groupIdField = classPKField.substring(
			0, classPKField.lastIndexOf(CharPool.PERIOD));

		return replacePermissionCheck(
			sql, className, classPKField, userIdField,
			groupIdField.concat(".groupId"), groupIds, bridgeJoin);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String bridgeJoin) {

		return replacePermissionCheck(
			sql, className, classPKField, userIdField, 0, bridgeJoin);
	}

	@Override
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String groupIdField, long[] groupIds, String bridgeJoin) {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if ((sql == null) ||
			_skipReplace(
				permissionChecker, className, classPKField, groupIds)) {

			return sql;
		}

		String resourcePermissionSQL = _getResourcePermissionSQL(
			permissionChecker, className, userIdField, groupIds, bridgeJoin);

		return _insertResourcePermissionSQL(
			sql, className, classPKField, userIdField, groupIdField, groupIds,
			resourcePermissionSQL);
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		modified(properties);

		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, PermissionSQLContributor.class, "model.class.name");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	@Modified
	protected void modified(Map<String, Object> properties) {
		_inlinePermissionConfiguration = ConfigurableUtil.createConfigurable(
			InlinePermissionConfiguration.class, properties);
	}

	private void _appendPermissionSQL(
		StringBundler sb, String className, String classPKField,
		String userIdField, String groupIdField, long[] groupIds,
		String permissionSQL) {

		List<PermissionSQLContributor> permissionSQLContributors =
			_serviceTrackerMap.getService(className);

		StringBundler permissionSQLContributorsSQLSB = null;

		if ((permissionSQLContributors != null) &&
			!permissionSQLContributors.isEmpty()) {

			permissionSQLContributorsSQLSB = new StringBundler(
				permissionSQLContributors.size() * 3);

			for (PermissionSQLContributor permissionSQLContributor :
					permissionSQLContributors) {

				String contributorPermissionSQL =
					permissionSQLContributor.getPermissionSQL(
						className, classPKField, userIdField, groupIdField,
						groupIds);

				if (Validator.isNull(contributorPermissionSQL)) {
					continue;
				}

				permissionSQLContributorsSQLSB.append("OR (");
				permissionSQLContributorsSQLSB.append(contributorPermissionSQL);
				permissionSQLContributorsSQLSB.append(") ");
			}
		}

		StringBundler groupAdminResourcePermissionSB = null;

		for (long groupId : groupIds) {
			if (!isEnabled(groupId)) {
				if (groupAdminResourcePermissionSB == null) {
					groupAdminResourcePermissionSB = new StringBundler(
						(groupIds.length * 2) - 1);
				}
				else {
					groupAdminResourcePermissionSB.append(", ");
				}

				groupAdminResourcePermissionSB.append(groupId);
			}
		}

		if ((permissionSQLContributorsSQLSB != null) ||
			(groupAdminResourcePermissionSB != null)) {

			sb.append("(");
		}

		sb.append("(");
		sb.append(classPKField);
		sb.append(" IN (");
		sb.append(permissionSQL);
		sb.append(")) ");

		if (permissionSQLContributorsSQLSB != null) {
			sb.append(permissionSQLContributorsSQLSB);
		}

		if (groupAdminResourcePermissionSB != null) {
			sb.append(" OR (");
			sb.append(groupIdField);
			sb.append(" IN (");
			sb.append(groupAdminResourcePermissionSB);
			sb.append(")) ");
		}

		if ((permissionSQLContributorsSQLSB != null) ||
			(groupAdminResourcePermissionSB != null)) {

			sb.append(") ");
		}
	}

	private <T extends Table<T>> Predicate _getPermissionWherePredicate(
		PermissionChecker permissionChecker, String modelClassName,
		Column<T, Long> classPKColumn, long[] groupIds) {

		DSLQuery resourcePermissionDSLQuery = _getResourcePermissionQuery(
			permissionChecker, modelClassName, groupIds);

		Predicate permissionWherePredicate = classPKColumn.in(
			resourcePermissionDSLQuery);

		List<PermissionSQLContributor> permissionSQLContributors =
			_serviceTrackerMap.getService(modelClassName);

		if ((permissionSQLContributors != null) &&
			!permissionSQLContributors.isEmpty()) {

			for (PermissionSQLContributor permissionSQLContributor :
					permissionSQLContributors) {

				Predicate contributorPermissionWherePredicate =
					permissionSQLContributor.getPermissionPredicate(
						permissionChecker, modelClassName, classPKColumn,
						groupIds);

				permissionWherePredicate =
					permissionWherePredicate = permissionWherePredicate.or(
						() -> {
							if (contributorPermissionWherePredicate != null) {
								return contributorPermissionWherePredicate.
									withParentheses();
							}

							return null;
						});
			}
		}

		Set<Long> groupIdSet = null;

		for (long groupId : groupIds) {
			if (!isEnabled(groupId)) {
				if (groupIdSet == null) {
					groupIdSet = new LinkedHashSet<>();
				}

				groupIdSet.add(groupId);
			}
		}

		if (groupIdSet != null) {
			T table = classPKColumn.getTable();

			Column<T, Long> groupIdColumn = table.getColumn(
				"groupId", Long.class);

			if (groupIdColumn == null) {
				throw new IllegalArgumentException(
					"No groupId column for table " + table.getTableName());
			}

			permissionWherePredicate = permissionWherePredicate.or(
				groupIdColumn.in(groupIdSet.toArray(new Long[0])));

			permissionWherePredicate =
				permissionWherePredicate.withParentheses();
		}

		return permissionWherePredicate;
	}

	private DSLQuery _getResourcePermissionQuery(
		PermissionChecker permissionChecker, String modelClassName,
		long[] groupIds) {

		Predicate roleIdsOrOwnerIdsPredicate = null;

		long[] roleIds = _getRoleIds(groupIds);

		if (roleIds.length > 0) {
			roleIdsOrOwnerIdsPredicate =
				ResourcePermissionTable.INSTANCE.roleId.in(
					ArrayUtil.toLongArray(roleIds));
		}

		if (permissionChecker.isSignedIn()) {
			Expression<Long> ownerIdExpression =
				ResourcePermissionTable.INSTANCE.ownerId;

			Predicate ownerIdPredicate = ownerIdExpression.eq(
				permissionChecker.getUserId());

			if (roleIdsOrOwnerIdsPredicate == null) {
				roleIdsOrOwnerIdsPredicate = ownerIdPredicate;
			}
			else {
				roleIdsOrOwnerIdsPredicate = roleIdsOrOwnerIdsPredicate.or(
					ownerIdPredicate);
			}
		}

		Predicate predicate = ResourcePermissionTable.INSTANCE.companyId.eq(
			permissionChecker.getCompanyId()
		).and(
			ResourcePermissionTable.INSTANCE.name.eq(modelClassName)
		).and(
			ResourcePermissionTable.INSTANCE.scope.eq(
				ResourceConstants.SCOPE_INDIVIDUAL)
		).and(
			ResourcePermissionTable.INSTANCE.viewActionId.eq(true)
		);

		if (roleIdsOrOwnerIdsPredicate != null) {
			predicate = predicate.and(
				roleIdsOrOwnerIdsPredicate.withParentheses());
		}

		return DSLQueryFactoryUtil.selectDistinct(
			ResourcePermissionTable.INSTANCE.primKeyId
		).from(
			ResourcePermissionTable.INSTANCE
		).where(
			predicate
		);
	}

	private String _getResourcePermissionSQL(
		PermissionChecker permissionChecker, String className,
		String userIdField, long[] groupIds, String bridgeJoin) {

		String resourcePermissionSQL = _customSQL.get(
			getClass(), FIND_BY_RESOURCE_PERMISSION);

		if (Validator.isNotNull(bridgeJoin)) {
			resourcePermissionSQL = bridgeJoin.concat(resourcePermissionSQL);
		}

		StringBundler sb = new StringBundler(8);

		long[] roleIds = _getRoleIds(groupIds);

		if (roleIds.length > 0) {
			sb.append("(ResourcePermission.roleId IN (");
			sb.append(StringUtil.merge(roleIds));
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		if (permissionChecker.isSignedIn()) {
			if (roleIds.length > 0) {
				sb.append(" OR ");
			}
			else {
				sb.append(StringPool.OPEN_PARENTHESIS);
			}

			long userId = permissionChecker.getUserId();

			if (Validator.isNull(userIdField)) {
				sb.append("ResourcePermission.ownerId = ");
				sb.append(userId);
			}
			else {
				sb.append(userIdField);
				sb.append(" = ");
				sb.append(userId);
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);
		}
		else if (roleIds.length > 0) {
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		String roleIdsOrOwnerIdSQL = sb.toString();

		int scope = ResourceConstants.SCOPE_INDIVIDUAL;

		String selectOptimizer = StringPool.BLANK;

		if (DBManagerUtil.getDBType() == DBType.MYSQL) {
			selectOptimizer = "/*+ SUBQUERY(MATERIALIZATION) */";
		}

		return StringUtil.replace(
			resourcePermissionSQL,
			new String[] {
				"[$CLASS_NAME$]", "[$COMPANY_ID$]",
				"[$RESOURCE_SCOPE_INDIVIDUAL$]", "[$ROLE_IDS_OR_OWNER_ID$]",
				"[$SELECT_OPTIMIZER$]"
			},
			new String[] {
				className, String.valueOf(permissionChecker.getCompanyId()),
				String.valueOf(scope), roleIdsOrOwnerIdSQL, selectOptimizer
			});
	}

	private long[] _getRoleIds(long groupId) {
		long[] roleIds = PermissionChecker.DEFAULT_ROLE_IDS;

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker != null) {
			roleIds = permissionChecker.getRoleIds(
				permissionChecker.getUserId(), groupId);
		}

		return roleIds;
	}

	private long[] _getRoleIds(long[] groupIds) {
		if (groupIds.length == 1) {
			return _getRoleIds(groupIds[0]);
		}

		Set<Long> roleIds = new HashSet<>();

		for (long groupId : groupIds) {
			for (long roleId : _getRoleIds(groupId)) {
				roleIds.add(roleId);
			}
		}

		return ArrayUtil.toLongArray(roleIds);
	}

	private DSLQuery _insertResourcePermissionQuery(
		DSLQuery dslQuery, Predicate permissionWherePredicate) {

		if (dslQuery instanceof WhereStep) {
			WhereStep whereStep = (WhereStep)dslQuery;

			return whereStep.where(permissionWherePredicate);
		}

		WhereStep whereStep = null;

		Where where = null;

		Deque<BaseASTNode> baseASTNodes = new LinkedList<>();

		ASTNode astNode = dslQuery;

		while (astNode instanceof BaseASTNode) {
			BaseASTNode baseASTNode = (BaseASTNode)astNode;

			if (baseASTNode instanceof WhereStep) {
				whereStep = (WhereStep)baseASTNode;

				break;
			}

			if (baseASTNode instanceof Where) {
				where = (Where)baseASTNode;
			}
			else {
				baseASTNodes.push(baseASTNode);
			}

			astNode = baseASTNode.getChild();
		}

		if (whereStep == null) {
			throw new IllegalArgumentException(
				StringBundler.concat(
					"Unable to replace permission check for \"", dslQuery,
					"\", if this is a union pass in the left or right queries ",
					"separately"));
		}

		ASTNode childASTNode = null;

		if (where == null) {
			childASTNode = whereStep.where(permissionWherePredicate);
		}
		else {
			Predicate predicate = where.getPredicate();

			childASTNode = new Where(
				whereStep, predicate.and(permissionWherePredicate));
		}

		for (BaseASTNode baseASTNode : baseASTNodes) {
			childASTNode = baseASTNode.withNewChild(childASTNode);
		}

		return (DSLQuery)childASTNode;
	}

	private String _insertResourcePermissionSQL(
		String sql, String className, String classPKField, String userIdField,
		String groupIdField, long[] groupIds, String permissionSQL) {

		StringBundler sb = new StringBundler(11);

		int pos = sql.lastIndexOf(_WHERE_CLAUSE);

		if (pos == -1) {
			pos = sql.indexOf(_GROUP_BY_CLAUSE);

			if (pos == -1) {
				pos = sql.indexOf(_ORDER_BY_CLAUSE);
			}

			if (pos == -1) {
				sb.append(sql);
			}
			else {
				sb.append(sql.substring(0, pos));
			}

			sb.append(_WHERE_CLAUSE);

			_appendPermissionSQL(
				sb, className, classPKField, userIdField, groupIdField,
				groupIds, permissionSQL);

			if (pos != -1) {
				sb.append(sql.substring(pos));
			}
		}
		else {
			pos += _WHERE_CLAUSE.length();

			sb.append(sql.substring(0, pos));

			_appendPermissionSQL(
				sb, className, classPKField, userIdField, groupIdField,
				groupIds, permissionSQL);

			sb.append("AND ");

			sb.append(sql.substring(pos));
		}

		return sb.toString();
	}

	private boolean _skipReplace(
		PermissionChecker permissionChecker, String className,
		Object classPKField, long[] groupIds) {

		if (!isEnabled(groupIds)) {
			return true;
		}

		if (Validator.isNull(className)) {
			throw new IllegalArgumentException("className is null");
		}

		if (Validator.isNull(classPKField)) {
			throw new IllegalArgumentException("classPKField is null");
		}

		long companyId = permissionChecker.getCompanyId();

		if (groupIds.length == 1) {
			long groupId = groupIds[0];

			Group group = _groupLocalService.fetchGroup(groupId);

			if (group != null) {
				long[] roleIds = _getRoleIds(groupId);

				try {
					if (_resourcePermissionLocalService.hasResourcePermission(
							companyId, className, ResourceConstants.SCOPE_GROUP,
							String.valueOf(groupId), roleIds,
							ActionKeys.VIEW) ||
						_resourcePermissionLocalService.hasResourcePermission(
							companyId, className,
							ResourceConstants.SCOPE_GROUP_TEMPLATE,
							String.valueOf(
								GroupConstants.DEFAULT_PARENT_GROUP_ID),
							roleIds, ActionKeys.VIEW)) {

						return true;
					}
				}
				catch (PortalException portalException) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							StringBundler.concat(
								"Unable to get resource permissions for ",
								className, " with group ", groupId),
							portalException);
					}
				}
			}
		}
		else {
			for (long groupId : groupIds) {
				Group group = _groupLocalService.fetchGroup(groupId);

				if ((group != null) && (group.getCompanyId() != companyId)) {
					throw new IllegalArgumentException(
						"Permission queries across multiple portal instances " +
							"are not supported");
				}
			}
		}

		try {
			if (_resourcePermissionLocalService.hasResourcePermission(
					companyId, className, ResourceConstants.SCOPE_COMPANY,
					String.valueOf(companyId),
					_getRoleIds(ArrayUtil.append(groupIds, 0)),
					ActionKeys.VIEW)) {

				return true;
			}
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Unable to get resource permissions for ", className,
						" with company ", companyId),
					portalException);
			}
		}

		return false;
	}

	private static final String _GROUP_BY_CLAUSE = " GROUP BY ";

	private static final String _ORDER_BY_CLAUSE = " ORDER BY ";

	private static final String _WHERE_CLAUSE = " WHERE ";

	private static final Log _log = LogFactoryUtil.getLog(
		InlineSQLHelperImpl.class);

	@Reference
	private CustomSQL _customSQL;

	@Reference
	private GroupLocalService _groupLocalService;

	private volatile InlinePermissionConfiguration
		_inlinePermissionConfiguration;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	private ServiceTrackerMap<String, List<PermissionSQLContributor>>
		_serviceTrackerMap;

}