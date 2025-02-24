/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.action.executor;

import com.liferay.object.scope.CompanyScoped;
import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapperFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.workflow.kaleo.definition.ActionType;
import com.liferay.portal.workflow.kaleo.definition.ScriptLanguage;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorManager;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Leonardo Barros
 */
@Component(service = ActionExecutorManager.class)
public class ActionExecutorManagerImpl implements ActionExecutorManager {

	@Override
	public void executeKaleoAction(
			KaleoAction kaleoAction, ExecutionContext executionContext)
		throws PortalException {

		String actionExecutorKey = _getActionExecutorKey(kaleoAction);

		ActionExecutor actionExecutor = null;

		List<ActionExecutor> actionExecutors = _getActionExecutors(
			actionExecutorKey, CompanyThreadLocal.getCompanyId());

		if (actionExecutors != null) {
			if (Objects.equals(
					String.valueOf(ScriptLanguage.JAVA),
					kaleoAction.getScriptLanguage())) {

				String className = kaleoAction.getScript();

				for (ActionExecutor innerActionExecutor : actionExecutors) {
					if (Objects.equals(
							ClassUtil.getClassName(innerActionExecutor),
							className)) {

						actionExecutor = innerActionExecutor;

						break;
					}
				}
			}
			else {
				actionExecutor = actionExecutors.get(0);
			}
		}

		if (actionExecutor == null) {
			throw new PortalException(
				"No action executor for " + actionExecutorKey);
		}

		actionExecutor.execute(kaleoAction, executionContext);
	}

	@Override
	public String[] getFunctionActionExecutorKeys() {
		List<String> actionExecutorKeys = new ArrayList<>();

		for (String actionExecutorKey : _serviceTrackerMap.keySet()) {
			if (ListUtil.isEmpty(
					_getActionExecutors(
						actionExecutorKey,
						CompanyThreadLocal.getCompanyId()))) {

				continue;
			}

			actionExecutorKeys.add(actionExecutorKey);
		}

		return TransformUtil.transformToArray(
			actionExecutorKeys,
			actionExecutorKey -> {
				if (actionExecutorKey.startsWith("function")) {
					return actionExecutorKey;
				}

				return null;
			},
			String.class);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, ActionExecutor.class, null,
			ServiceReferenceMapperFactory.create(
				bundleContext,
				(actionExecutor, emitter) -> emitter.emit(
					actionExecutor.getActionExecutorKey())));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private String _getActionExecutorKey(KaleoAction kaleoAction) {
		ActionType actionType = ActionType.valueOf(kaleoAction.getType());

		if (Objects.equals(actionType, ActionType.UPDATE_STATUS)) {
			return actionType.name();
		}

		return kaleoAction.getScriptLanguage();
	}

	private List<ActionExecutor> _getActionExecutors(
		String actionExecutorKey, long companyId) {

		return ListUtil.filter(
			ListUtil.fromCollection(
				_serviceTrackerMap.getService(actionExecutorKey)),
			actionExecutor -> {
				if (actionExecutor instanceof CompanyScoped) {
					CompanyScoped companyScoped = (CompanyScoped)actionExecutor;

					return companyScoped.isAllowedCompany(companyId);
				}

				return true;
			});
	}

	private ServiceTrackerMap<String, List<ActionExecutor>> _serviceTrackerMap;

}