/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.constants.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.test.rule.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Rafael Praxedes
 */
@RunWith(Arquillian.class)
public class DDMTemplateServiceTest extends BaseDDMServiceTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_group = GroupTestUtil.addGroup();
		_recordSetClassNameId = PortalUtil.getClassNameId(
			DDL_RECORD_SET_CLASS_NAME);
		_structureClassNameId = PortalUtil.getClassNameId(DDMStructure.class);
		_siteAdminUser = UserTestUtil.addGroupAdminUser(group);

		setUpPermissionThreadLocal();
		setUpPrincipalThreadLocal();
	}

	@After
	public void tearDown() throws Exception {
		PermissionThreadLocal.setPermissionChecker(_originalPermissionChecker);

		PrincipalThreadLocal.setName(_originalName);
	}

	@Test
	public void testAddTemplateWithoutAddPermission() throws Exception {
		try {
			UserTestUtil.setUser(
				UserTestUtil.addGroupUser(_group, RoleConstants.SITE_MEMBER));

			_ddmTemplateService.addTemplate(
				RandomTestUtil.randomString(), group.getGroupId(),
				PortalUtil.getClassNameId(DDL_RECORD_CLASS_NAME), 0,
				PortalUtil.getClassNameId(DDL_RECORD_SET_CLASS_NAME),
				Collections.singletonMap(
					LocaleUtil.getSiteDefault(), RandomTestUtil.randomString()),
				null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
				DDMTemplateConstants.TEMPLATE_MODE_CREATE,
				TemplateConstants.LANG_TYPE_VM,
				getTestTemplateScript(TemplateConstants.LANG_TYPE_VM),
				ServiceContextTestUtil.getServiceContext());

			Assert.fail();
		}
		catch (PrincipalException principalException) {
		}
		finally {
			UserTestUtil.setUser(TestPropsValues.getUser());
		}
	}

	@Test
	public void testDeleteTemplateByExternalReferenceCode() throws Exception {
		DDMTemplate template = _ddmTemplateService.addTemplate(
			RandomTestUtil.randomString(), group.getGroupId(),
			PortalUtil.getClassNameId(DDL_RECORD_CLASS_NAME), 0,
			PortalUtil.getClassNameId(DDL_RECORD_SET_CLASS_NAME),
			Collections.singletonMap(
				LocaleUtil.getSiteDefault(), RandomTestUtil.randomString()),
			null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			DDMTemplateConstants.TEMPLATE_MODE_CREATE,
			TemplateConstants.LANG_TYPE_VM,
			getTestTemplateScript(TemplateConstants.LANG_TYPE_VM),
			ServiceContextTestUtil.getServiceContext());

		_ddmTemplateService.deleteTemplate(
			template.getExternalReferenceCode(), template.getGroupId());

		Assert.assertNull(
			_ddmTemplateLocalService.fetchTemplate(template.getTemplateId()));
	}

	@Test
	public void testDeleteTemplateByExternalReferenceCodeWithoutDeletePermission()
		throws Exception {

		DDMTemplate template = _ddmTemplateService.addTemplate(
			RandomTestUtil.randomString(), group.getGroupId(),
			PortalUtil.getClassNameId(DDL_RECORD_CLASS_NAME), 0,
			PortalUtil.getClassNameId(DDL_RECORD_SET_CLASS_NAME),
			Collections.singletonMap(
				LocaleUtil.getSiteDefault(), RandomTestUtil.randomString()),
			null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			DDMTemplateConstants.TEMPLATE_MODE_CREATE,
			TemplateConstants.LANG_TYPE_VM,
			getTestTemplateScript(TemplateConstants.LANG_TYPE_VM),
			ServiceContextTestUtil.getServiceContext());

		try {
			UserTestUtil.setUser(
				UserTestUtil.addGroupUser(_group, RoleConstants.SITE_MEMBER));

			_ddmTemplateService.deleteTemplate(
				template.getExternalReferenceCode(), template.getGroupId());

			Assert.fail();
		}
		catch (PrincipalException principalException) {
		}
		finally {
			UserTestUtil.setUser(TestPropsValues.getUser());
		}
	}

	@Test
	public void testGetTemplateByExternalReferenceCode() throws Exception {
		DDMTemplate template = _ddmTemplateService.addTemplate(
			RandomTestUtil.randomString(), group.getGroupId(),
			PortalUtil.getClassNameId(DDL_RECORD_CLASS_NAME), 0,
			PortalUtil.getClassNameId(DDL_RECORD_SET_CLASS_NAME),
			Collections.singletonMap(
				LocaleUtil.getSiteDefault(), RandomTestUtil.randomString()),
			null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			DDMTemplateConstants.TEMPLATE_MODE_CREATE,
			TemplateConstants.LANG_TYPE_VM,
			getTestTemplateScript(TemplateConstants.LANG_TYPE_VM),
			ServiceContextTestUtil.getServiceContext());

		DDMTemplate curTemplate =
			_ddmTemplateService.getTemplateByExternalReferenceCode(
				template.getExternalReferenceCode(), template.getGroupId());

		Assert.assertEquals(
			template.getTemplateId(), curTemplate.getTemplateId());
	}

	@Test
	public void testGetTemplateByExternalReferenceCodeWithoutViewPermission()
		throws Exception {

		DDMTemplate template = _ddmTemplateService.addTemplate(
			RandomTestUtil.randomString(), group.getGroupId(),
			PortalUtil.getClassNameId(DDL_RECORD_CLASS_NAME), 0,
			PortalUtil.getClassNameId(DDL_RECORD_SET_CLASS_NAME),
			Collections.singletonMap(
				LocaleUtil.getSiteDefault(), RandomTestUtil.randomString()),
			null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			DDMTemplateConstants.TEMPLATE_MODE_CREATE,
			TemplateConstants.LANG_TYPE_VM,
			getTestTemplateScript(TemplateConstants.LANG_TYPE_VM),
			ServiceContextTestUtil.getServiceContext());

		RoleTestUtil.removeResourcePermission(
			RoleConstants.GUEST, DDMTemplate.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(template.getTemplateId()), ActionKeys.VIEW);
		RoleTestUtil.removeResourcePermission(
			RoleConstants.SITE_MEMBER, DDMTemplate.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(template.getTemplateId()), ActionKeys.VIEW);

		try {
			UserTestUtil.setUser(
				UserTestUtil.addGroupUser(_group, RoleConstants.SITE_MEMBER));

			_ddmTemplateService.getTemplateByExternalReferenceCode(
				template.getExternalReferenceCode(), template.getGroupId());

			Assert.fail();
		}
		catch (PrincipalException principalException) {
		}
		finally {
			UserTestUtil.setUser(TestPropsValues.getUser());
		}
	}

	@Test
	public void testGetTemplates() throws Exception {
		addFormTemplate(
			0, StringUtil.randomString(), WorkflowConstants.STATUS_ANY);
		addFormTemplate(
			0, StringUtil.randomString(), WorkflowConstants.STATUS_ANY);
		addFormTemplate(
			0, StringUtil.randomString(), WorkflowConstants.STATUS_ANY);

		List<DDMTemplate> templates = DDMTemplateServiceUtil.getTemplates(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, _recordSetClassNameId,
			WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(templates.toString(), 3, templates.size());
	}

	@Test
	public void testGetTemplatesByClassPK() throws Exception {
		DDMStructure ddmStructure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		String language = TemplateConstants.LANG_TYPE_FTL;

		String script = getTestTemplateScript(language);

		addTemplate(
			0, ddmStructure.getStructureId(), StringUtil.randomString(), null,
			null, language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			0, ddmStructure.getStructureId(), StringUtil.randomString(), null,
			null, language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			0, ddmStructure.getStructureId(), StringUtil.randomString(), null,
			null, language, script, WorkflowConstants.STATUS_ANY);

		List<DDMTemplate> templates =
			DDMTemplateServiceUtil.getTemplatesByClassPK(
				TestPropsValues.getCompanyId(), group.getGroupId(),
				ddmStructure.getStructureId(), _recordSetClassNameId,
				WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(templates.toString(), 3, templates.size());
	}

	@Test
	public void testGetTemplatesByIncludeAncestorTemplates() throws Exception {
		Group childGroup = GroupTestUtil.addGroup(group.getGroupId());

		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		addDisplayTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);
		addDisplayTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);

		List<DDMTemplate> templates = DDMTemplateServiceUtil.getTemplates(
			TestPropsValues.getCompanyId(), childGroup.getGroupId(),
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, true, WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(templates.toString(), 2, templates.size());

		templates = DDMTemplateServiceUtil.getTemplates(
			TestPropsValues.getCompanyId(), childGroup.getGroupId(),
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, false, WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(templates.toString(), 0, templates.size());

		GroupLocalServiceUtil.deleteGroup(childGroup);
	}

	@Test
	public void testGetTemplatesByStructureAnyStatus() throws Exception {
		DDMStructure structure1 = addStructure(
			_recordSetClassNameId, StringUtil.randomString());
		DDMStructure structure2 = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		addDisplayTemplate(
			structure1.getPrimaryKey(), StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			structure1.getPrimaryKey(), StringUtil.randomString(),
			WorkflowConstants.STATUS_DRAFT);
		addDisplayTemplate(
			structure2.getPrimaryKey(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);

		List<DDMTemplate> templates = DDMTemplateServiceUtil.getTemplates(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, structure1.getStructureId(),
			_recordSetClassNameId, WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(templates.toString(), 2, templates.size());

		templates = DDMTemplateServiceUtil.getTemplates(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, structure2.getStructureId(),
			_recordSetClassNameId, WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(templates.toString(), 1, templates.size());
	}

	@Test
	public void testGetTemplatesByStructureClassNameId() throws Exception {
		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		List<DDMTemplate> newTemplates = new ArrayList<>(3);

		DDMTemplate template = addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);

		newTemplates.add(template);

		template = addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);

		newTemplates.add(template);

		template = addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);

		newTemplates.add(template);

		List<DDMTemplate> templates =
			DDMTemplateServiceUtil.getTemplatesByStructureClassNameId(
				group.getGroupId(), _recordSetClassNameId,
				WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Assert.assertEquals(templates.toString(), 3, templates.size());

		for (DDMTemplate newTemplate : newTemplates) {
			Assert.assertTrue(
				templates.toString(), templates.contains(newTemplate));
		}
	}

	@Test
	public void testGetTemplatesByStructureClassNameIdCount() throws Exception {
		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);
		addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);
		addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);

		int count =
			DDMTemplateServiceUtil.getTemplatesByStructureClassNameIdCount(
				group.getGroupId(), _recordSetClassNameId,
				WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(3, count);
	}

	@Test
	public void testGetTemplatesByType() throws Exception {
		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		addDisplayTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);
		addDisplayTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);
		addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);

		List<DDMTemplate> templates = DDMTemplateServiceUtil.getTemplates(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(templates.toString(), 2, templates.size());

		templates = DDMTemplateServiceUtil.getTemplates(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, DDMTemplateConstants.TEMPLATE_TYPE_FORM,
			WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(templates.toString(), 1, templates.size());
	}

	@Test
	public void testGetTemplatesByWorkflowStatus() throws Exception {
		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		addDisplayTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_DRAFT);

		List<DDMTemplate> templates = DDMTemplateServiceUtil.getTemplates(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(templates.toString(), 2, templates.size());

		templates = DDMTemplateServiceUtil.getTemplates(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			WorkflowConstants.STATUS_DRAFT);

		Assert.assertEquals(templates.toString(), 1, templates.size());
	}

	@Test
	public void testSearch() throws Exception {
		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);
		addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);
		addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);

		List<DDMTemplate> templates = DDMTemplateServiceUtil.search(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, StringPool.BLANK,
			DDMTemplateConstants.TEMPLATE_TYPE_FORM, null,
			WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);

		Assert.assertEquals(templates.toString(), 3, templates.size());
	}

	@Test
	public void testSearch2() throws Exception {
		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);
		addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);
		addFormTemplate(
			structure.getStructureId(), StringUtil.randomString(),
			WorkflowConstants.STATUS_ANY);

		List<DDMTemplate> ddmTemplates = DDMTemplateServiceUtil.search(
			TestPropsValues.getCompanyId(),
			new long[] {group.getGroupId(), _group.getGroupId()},
			new long[] {_structureClassNameId},
			new long[] {structure.getStructureId()}, _recordSetClassNameId,
			StringPool.BLANK, DDMTemplateConstants.TEMPLATE_TYPE_FORM,
			DDMTemplateConstants.TEMPLATE_MODE_CREATE,
			WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);

		Assert.assertEquals(ddmTemplates.toString(), 3, ddmTemplates.size());
	}

	@Test
	public void testSearchByNameAndDescription1() throws Exception {
		String name = StringUtil.randomString();
		String description = StringUtil.randomString();

		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		String language = TemplateConstants.LANG_TYPE_FTL;

		String script = getTestTemplateScript(language);

		String type = null;
		String mode = null;

		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, name, description, type, mode,
			language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, name, StringUtil.randomString(), type,
			mode, language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, StringUtil.randomString(), description,
			type, mode, language, script, WorkflowConstants.STATUS_ANY);

		List<DDMTemplate> templates = DDMTemplateServiceUtil.search(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, name, description, type, mode, language,
			WorkflowConstants.STATUS_ANY, false, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(templates.toString(), 3, templates.size());
	}

	@Test
	public void testSearchByNameAndDescription2() throws Exception {
		String name = StringUtil.randomString();
		String description = StringUtil.randomString();

		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		String language = TemplateConstants.LANG_TYPE_FTL;

		String script = getTestTemplateScript(language);

		String type = null;
		String mode = null;

		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, name, description, type, mode,
			language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, name, StringUtil.randomString(), type,
			mode, language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, StringUtil.randomString(), description,
			type, mode, language, script, WorkflowConstants.STATUS_ANY);

		List<DDMTemplate> templates = DDMTemplateServiceUtil.search(
			TestPropsValues.getCompanyId(),
			new long[] {group.getGroupId(), _group.getGroupId()},
			new long[] {_structureClassNameId},
			new long[] {structure.getStructureId()}, _recordSetClassNameId,
			name, description, type, mode, language,
			WorkflowConstants.STATUS_ANY, false, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(templates.toString(), 3, templates.size());
	}

	@Test
	public void testSearchCountByDescription() throws Exception {
		String description = StringUtil.randomString();

		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		String language = TemplateConstants.LANG_TYPE_FTL;

		String script = getTestTemplateScript(language);

		String type = null;
		String mode = null;

		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, StringUtil.randomString(), description,
			type, mode, language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, StringUtil.randomString(),
			StringUtil.randomString(), type, mode, language, script,
			WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, StringUtil.randomString(), description,
			type, mode, language, script, WorkflowConstants.STATUS_ANY);

		int count = DDMTemplateServiceUtil.searchCount(
			TestPropsValues.getCompanyId(),
			new long[] {group.getGroupId(), _group.getGroupId()},
			new long[] {_structureClassNameId},
			new long[] {structure.getStructureId()}, _recordSetClassNameId,
			StringUtil.randomString(), description, type, mode, language,
			WorkflowConstants.STATUS_ANY, true);

		Assert.assertEquals(2, count);
	}

	@Test
	public void testSearchCountByName() throws Exception {
		String name = StringUtil.randomString();

		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		String language = TemplateConstants.LANG_TYPE_FTL;

		String script = getTestTemplateScript(language);

		String type = null;
		String mode = null;

		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, name, StringUtil.randomString(), type,
			mode, language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, name, StringUtil.randomString(), type,
			mode, language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, StringUtil.randomString(),
			StringUtil.randomString(), type, mode, language, script,
			WorkflowConstants.STATUS_ANY);

		int count = DDMTemplateServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, name, StringUtil.randomString(), type, mode,
			language, WorkflowConstants.STATUS_ANY, true);

		Assert.assertEquals(2, count);
	}

	@Test
	public void testSearchCountByNameAndDescription1() throws Exception {
		String name = StringUtil.randomString();
		String description = StringUtil.randomString();

		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		String language = TemplateConstants.LANG_TYPE_FTL;

		String script = getTestTemplateScript(language);

		String type = null;
		String mode = null;

		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, name, description, type, mode,
			language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, name, StringUtil.randomString(), type,
			mode, language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, StringUtil.randomString(), description,
			type, mode, language, script, WorkflowConstants.STATUS_ANY);

		int count = DDMTemplateServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), group.getGroupId(),
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, name, description, type, mode, language,
			WorkflowConstants.STATUS_ANY, false);

		Assert.assertEquals(3, count);
	}

	@Test
	public void testSearchCountByNameAndDescription2() throws Exception {
		String name = StringUtil.randomString();
		String description = StringUtil.randomString();

		DDMStructure structure = addStructure(
			_recordSetClassNameId, StringUtil.randomString());

		String language = TemplateConstants.LANG_TYPE_FTL;

		String script = getTestTemplateScript(language);

		String type = null;
		String mode = null;

		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, name, description, type, mode,
			language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, name, StringUtil.randomString(), type,
			mode, language, script, WorkflowConstants.STATUS_ANY);
		addTemplate(
			_structureClassNameId, structure.getStructureId(),
			_recordSetClassNameId, null, StringUtil.randomString(), description,
			type, mode, language, script, WorkflowConstants.STATUS_ANY);

		int count = DDMTemplateServiceUtil.searchCount(
			TestPropsValues.getCompanyId(),
			new long[] {group.getGroupId(), _group.getGroupId()},
			new long[] {_structureClassNameId},
			new long[] {structure.getStructureId()}, _recordSetClassNameId,
			name, description, type, mode, language,
			WorkflowConstants.STATUS_ANY, false);

		Assert.assertEquals(3, count);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void setUpPermissionThreadLocal() throws Exception {
		_originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_siteAdminUser));
	}

	protected void setUpPrincipalThreadLocal() throws Exception {
		_originalName = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(_siteAdminUser.getUserId());
	}

	private static long _recordSetClassNameId;
	private static long _structureClassNameId;

	@Inject
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Inject
	private DDMTemplateService _ddmTemplateService;

	@DeleteAfterTestRun
	private Group _group;

	private String _originalName;
	private PermissionChecker _originalPermissionChecker;

	@DeleteAfterTestRun
	private User _siteAdminUser;

}