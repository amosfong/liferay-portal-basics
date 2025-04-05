create index IX_FEAFC68A on Address (companyId, classNameId, classPK, listTypeId);
create index IX_923BD178 on Address (companyId, classNameId, classPK, mailing);
create index IX_9226DBB4 on Address (companyId, classNameId, classPK, primary_);
create unique index IX_CBAD282F on Address (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_5A2093E7 on Address (countryId);
create index IX_C8E3E87D on Address (regionId);
create index IX_5BC8B0D4 on Address (userId);
create index IX_381E55DA on Address (uuid_[$COLUMN_LENGTH:75$]);

create index IX_37B0A8A2 on AnnouncementsDelivery (companyId);
create unique index IX_BA4413D5 on AnnouncementsDelivery (userId, type_[$COLUMN_LENGTH:75$]);

create index IX_14F06A6B on AnnouncementsEntry (classNameId, classPK, alert);
create index IX_94C04525 on AnnouncementsEntry (classNameId, classPK, companyId, alert);
create index IX_3F376E7C on AnnouncementsEntry (companyId);
create index IX_D49C2E66 on AnnouncementsEntry (userId);
create index IX_1AFBDE08 on AnnouncementsEntry (uuid_[$COLUMN_LENGTH:75$]);

create index IX_EF1F022A on AnnouncementsFlag (companyId);
create index IX_ED8CE4E8 on AnnouncementsFlag (entryId, userId, value);

create unique index IX_F3842169 on AssetCategory (groupId, ctCollectionId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_F67BECAD on AssetCategory (groupId, parentCategoryId);
create unique index IX_AF94405C on AssetCategory (groupId, uuid_[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_2710C64A on AssetCategory (groupId, vocabularyId, name[$COLUMN_LENGTH:255$]);
create index IX_68169942 on AssetCategory (groupId, vocabularyId, parentCategoryId);
create index IX_9DDD15EA on AssetCategory (parentCategoryId, name[$COLUMN_LENGTH:255$]);
create index IX_4D37BB00 on AssetCategory (uuid_[$COLUMN_LENGTH:75$]);
create index IX_3537E488 on AssetCategory (vocabularyId, name[$COLUMN_LENGTH:255$]);
create unique index IX_8C99329D on AssetCategory (vocabularyId, parentCategoryId, name[$COLUMN_LENGTH:255$], ctCollectionId);

create index IX_112337B8 on AssetEntries_AssetTags (companyId);
create index IX_B2A61B55 on AssetEntries_AssetTags (tagId);

create unique index IX_7BF8337B on AssetEntry (classNameId, classPK, ctCollectionId);
create index IX_23280E2 on AssetEntry (classNameId, companyId);
create index IX_7306C60 on AssetEntry (companyId);
create index IX_75D42FF9 on AssetEntry (expirationDate);
create index IX_6418BB52 on AssetEntry (groupId, classNameId, publishDate, expirationDate);
create index IX_82C4BEF6 on AssetEntry (groupId, classNameId, visible);
create index IX_1EBA6821 on AssetEntry (groupId, classUuid[$COLUMN_LENGTH:75$]);
create index IX_FEC4A201 on AssetEntry (layoutUuid[$COLUMN_LENGTH:75$]);
create index IX_2E4E3885 on AssetEntry (publishDate);
create index IX_9029E15A on AssetEntry (visible);

create unique index IX_FBB2C925 on AssetTag (groupId, ctCollectionId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_D63322F9 on AssetTag (groupId, name[$COLUMN_LENGTH:75$]);
create unique index IX_B421E018 on AssetTag (groupId, uuid_[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_C43137AF on AssetTag (name[$COLUMN_LENGTH:75$]);
create index IX_562A3FC4 on AssetTag (uuid_[$COLUMN_LENGTH:75$]);

create index IX_B22D908C on AssetVocabulary (companyId);
create unique index IX_E06DEF51 on AssetVocabulary (groupId, ctCollectionId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_32F2132B on AssetVocabulary (groupId, ctCollectionId, name[$COLUMN_LENGTH:75$]);
create index IX_C0AAD74D on AssetVocabulary (groupId, name[$COLUMN_LENGTH:75$]);
create unique index IX_3966DE44 on AssetVocabulary (groupId, uuid_[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_2F7F11EE on AssetVocabulary (groupId, visibilityType);
create index IX_55F58818 on AssetVocabulary (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_E7B95510 on BrowserTracker (userId);

create unique index IX_B27A301F on ClassName_ (value[$COLUMN_LENGTH:200$]);

create index IX_38EFE3FD on Company (logoId);
create index IX_12566EC2 on Company (mx[$COLUMN_LENGTH:200$]);
create unique index IX_EC00543C on Company (webId[$COLUMN_LENGTH:75$]);

create unique index IX_85C63FD7 on CompanyInfo (companyId);

create index IX_791914FA on Contact_ (classNameId, classPK);
create index IX_FD2E9BDD on Contact_ (companyId, userId);
create index IX_42F94F9F on Contact_ (userId);

create index IX_25D734CD on Country (active_);
create unique index IX_742FFB11 on Country (companyId, a2[$COLUMN_LENGTH:75$]);
create unique index IX_742FFED2 on Country (companyId, a3[$COLUMN_LENGTH:75$]);
create index IX_F9CD867E on Country (companyId, active_, billingAllowed);
create index IX_54E98CCD on Country (companyId, active_, shippingAllowed);
create unique index IX_410257AB on Country (companyId, name[$COLUMN_LENGTH:75$]);
create unique index IX_4B78E87A on Country (companyId, number_[$COLUMN_LENGTH:75$]);
create index IX_B59A9078 on Country (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_518948B3 on CountryLocalization (countryId, languageId[$COLUMN_LENGTH:75$]);

create index IX_4CB1B2B4 on DLFileEntry (companyId);
create index IX_B8526DBE on DLFileEntry (custom1ImageId);
create index IX_AC9BDEDD on DLFileEntry (custom2ImageId);
create index IX_772ECDE7 on DLFileEntry (fileEntryTypeId);
create index IX_8F6C75D0 on DLFileEntry (folderId, name[$COLUMN_LENGTH:255$]);
create index IX_57FFBBCA on DLFileEntry (folderId, repositoryId);
create unique index IX_EDDFFA47 on DLFileEntry (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_29D0AF28 on DLFileEntry (groupId, folderId, fileEntryTypeId);
create unique index IX_DF37D92E on DLFileEntry (groupId, folderId, fileName[$COLUMN_LENGTH:255$]);
create unique index IX_5391712 on DLFileEntry (groupId, folderId, name[$COLUMN_LENGTH:255$]);
create unique index IX_ED5CA615 on DLFileEntry (groupId, folderId, title[$COLUMN_LENGTH:255$]);
create index IX_1DC796CD on DLFileEntry (groupId, folderId, userId);
create index IX_43261870 on DLFileEntry (groupId, userId);
create unique index IX_89E78ABE on DLFileEntry (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_4DB7A143 on DLFileEntry (largeImageId);
create index IX_D9492CF6 on DLFileEntry (mimeType[$COLUMN_LENGTH:75$]);
create index IX_9EE96CAD on DLFileEntry (repositoryId);
create index IX_25F5CAB9 on DLFileEntry (smallImageId, largeImageId, custom1ImageId, custom2ImageId);
create index IX_64F0FE40 on DLFileEntry (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_152C288E on DLFileEntryMetadata (externalReferenceCode[$COLUMN_LENGTH:75$], companyId);
create index IX_4F40FE5E on DLFileEntryMetadata (fileEntryId);
create unique index IX_B2CB3519 on DLFileEntryMetadata (fileVersionId, DDMStructureId);
create index IX_D49AB5D1 on DLFileEntryMetadata (uuid_[$COLUMN_LENGTH:75$]);

create index IX_D2F8189A on DLFileEntryType (companyId);
create unique index IX_49948C28 on DLFileEntryType (groupId, dataDefinitionId);
create unique index IX_CE206021 on DLFileEntryType (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_5B6BEF5F on DLFileEntryType (groupId, fileEntryTypeKey[$COLUMN_LENGTH:75$]);
create unique index IX_E152E498 on DLFileEntryType (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_90724726 on DLFileEntryType (uuid_[$COLUMN_LENGTH:75$]);

create index IX_2E64D9F9 on DLFileEntryTypes_DLFolders (companyId);
create index IX_6E00A2EC on DLFileEntryTypes_DLFolders (folderId);

create index IX_8571953E on DLFileShortcut (companyId, status);
create unique index IX_93CCED23 on DLFileShortcut (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_17EE3098 on DLFileShortcut (groupId, folderId, active_, status);
create unique index IX_CB6DB59A on DLFileShortcut (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_4B7247F6 on DLFileShortcut (toFileEntryId);
create index IX_4831EBE4 on DLFileShortcut (uuid_[$COLUMN_LENGTH:75$]);

create index IX_CF394FE on DLFileVersion (companyId, storeUUID[$COLUMN_LENGTH:255$]);
create unique index IX_E2815081 on DLFileVersion (fileEntryId, version[$COLUMN_LENGTH:75$]);
create index IX_BC5541ED on DLFileVersion (groupId, folderId, version[$COLUMN_LENGTH:75$], title[$COLUMN_LENGTH:255$]);
create index IX_FFB3395C on DLFileVersion (mimeType[$COLUMN_LENGTH:75$]);
create index IX_5898E799 on DLFileVersion (status, companyId, expirationDate);
create index IX_92309600 on DLFileVersion (status, displayDate);
create index IX_D50EAA41 on DLFileVersion (status, fileEntryId);
create index IX_799D5D47 on DLFileVersion (status, groupId, folderId);
create unique index IX_C99B2650 on DLFileVersion (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A74DB14C on DLFolder (companyId);
create unique index IX_60B58AAF on DLFolder (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_CE360BF6 on DLFolder (groupId, parentFolderId, hidden_, status);
create index IX_2D8D2D2B on DLFolder (groupId, parentFolderId, mountPoint, hidden_, status);
create unique index IX_902FD874 on DLFolder (groupId, parentFolderId, name[$COLUMN_LENGTH:255$]);
create unique index IX_A7AEB26 on DLFolder (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_D6D77780 on DLFolder (mountPoint, repositoryId);
create index IX_51556082 on DLFolder (parentFolderId, name[$COLUMN_LENGTH:255$]);
create index IX_56F3D47C on DLFolder (parentFolderId, repositoryId);
create index IX_EE29C715 on DLFolder (repositoryId);
create index IX_B199E2A6 on DLFolder (status, companyId);
create index IX_CBC408D8 on DLFolder (uuid_[$COLUMN_LENGTH:75$]);

create index IX_2A2CB130 on EmailAddress (companyId, classNameId, classPK, primary_);
create unique index IX_5245F733 on EmailAddress (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_7B43CD8 on EmailAddress (userId);
create index IX_D24F3956 on EmailAddress (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_FEFC8DA7 on ExpandoColumn (tableId, name[$COLUMN_LENGTH:75$]);

create index IX_49EB3118 on ExpandoRow (classPK);
create unique index IX_81EFBFF5 on ExpandoRow (tableId, classPK);

create unique index IX_37562284 on ExpandoTable (companyId, classNameId, name[$COLUMN_LENGTH:75$]);

create index IX_CAD04B0D on ExpandoValue (classPK, classNameId);
create unique index IX_9DDD21E5 on ExpandoValue (columnId, rowId_);
create index IX_9112A7A0 on ExpandoValue (rowId_);
create index IX_1BD3F4C on ExpandoValue (tableId, classPK);
create unique index IX_D27B03E7 on ExpandoValue (tableId, columnId, classPK);
create index IX_B71E92D5 on ExpandoValue (tableId, rowId_);

create index IX_1827A2E5 on ExportImportConfiguration (companyId);
create index IX_38FA468D on ExportImportConfiguration (groupId, status);
create index IX_47CC6234 on ExportImportConfiguration (groupId, type_, status);

create index IX_75017452 on Group_ (active_, type_);
create index IX_8257E37B on Group_ (classNameId, classPK);
create index IX_DDC91A87 on Group_ (companyId, active_);
create unique index IX_D0D5E397 on Group_ (companyId, classNameId, classPK);
create index IX_ABE2D54 on Group_ (companyId, classNameId, parentGroupId);
create index IX_DF76A247 on Group_ (companyId, classNameId, site);
create unique index IX_4DFCA43B on Group_ (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_5BDDB872 on Group_ (companyId, friendlyURL[$COLUMN_LENGTH:255$]);
create unique index IX_ACD2B296 on Group_ (companyId, groupKey[$COLUMN_LENGTH:150$]);
create index IX_5D75499E on Group_ (companyId, parentGroupId);
create index IX_B91488EC on Group_ (companyId, site, active_);
create index IX_7B216735 on Group_ (companyId, site, parentGroupId, inheritContent);
create index IX_16218A38 on Group_ (liveGroupId);
create index IX_F981514E on Group_ (uuid_[$COLUMN_LENGTH:75$]);

create index IX_8BFD4548 on Groups_Orgs (companyId);
create index IX_6BBB7682 on Groups_Orgs (organizationId);

create index IX_557D8550 on Groups_Roles (companyId);
create index IX_3103EF3D on Groups_Roles (roleId);

create index IX_676FC818 on Groups_UserGroups (companyId);
create index IX_3B69160F on Groups_UserGroups (userGroupId);

create index IX_6A925A4D on Image (size_);

create index IX_B8E1E6E5 on Layout (classNameId, classPK);
create index IX_881EABCB on Layout (companyId, layoutPrototypeUuid[$COLUMN_LENGTH:75$]);
create unique index IX_91F9D9E3 on Layout (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_993CBA31 on Layout (groupId, masterLayoutPlid);
create unique index IX_BC2C4231 on Layout (groupId, privateLayout, friendlyURL[$COLUMN_LENGTH:255$]);
create unique index IX_7162C27C on Layout (groupId, privateLayout, layoutId);
create index IX_7DAA999F on Layout (groupId, privateLayout, parentLayoutId, hidden_);
create index IX_7399B71E on Layout (groupId, privateLayout, parentLayoutId, priority);
create index IX_8F78BAFA on Layout (groupId, privateLayout, parentLayoutId, system_);
create index IX_8CE8C0D9 on Layout (groupId, privateLayout, sourcePrototypeLayoutUuid[$COLUMN_LENGTH:75$]);
create index IX_A0364689 on Layout (groupId, privateLayout, status);
create index IX_1A1B61D2 on Layout (groupId, privateLayout, type_[$COLUMN_LENGTH:75$]);
create unique index IX_47CB21B1 on Layout (groupId, privateLayout, uuid_[$COLUMN_LENGTH:75$]);
create index IX_6EDC627B on Layout (groupId, type_[$COLUMN_LENGTH:75$]);
create index IX_23922F7D on Layout (iconImageId);
create index IX_B529BFD3 on Layout (layoutPrototypeUuid[$COLUMN_LENGTH:75$]);
create index IX_1D4DCAA5 on Layout (parentPlid);
create index IX_3BC009C0 on Layout (privateLayout, iconImageId);
create index IX_39A18ECC on Layout (sourcePrototypeLayoutUuid[$COLUMN_LENGTH:75$]);
create index IX_D0822724 on Layout (uuid_[$COLUMN_LENGTH:75$]);

create index IX_A705FF94 on LayoutBranch (layoutSetBranchId, plid, master);
create unique index IX_FD57097D on LayoutBranch (layoutSetBranchId, plid, name[$COLUMN_LENGTH:75$]);
create index IX_72FC531D on LayoutBranch (plid);

create index IX_EAB317C8 on LayoutFriendlyURL (companyId);
create index IX_C23A9814 on LayoutFriendlyURL (friendlyURL[$COLUMN_LENGTH:255$], companyId);
create index IX_D3B2D6DF on LayoutFriendlyURL (friendlyURL[$COLUMN_LENGTH:255$], plid);
create unique index IX_B20D5E9A on LayoutFriendlyURL (groupId, friendlyURL[$COLUMN_LENGTH:255$], privateLayout, languageId[$COLUMN_LENGTH:75$]);
create unique index IX_1E322A on LayoutFriendlyURL (groupId, uuid_[$COLUMN_LENGTH:75$]);
create unique index IX_C5762E72 on LayoutFriendlyURL (plid, languageId[$COLUMN_LENGTH:75$]);
create index IX_9F80D54 on LayoutFriendlyURL (uuid_[$COLUMN_LENGTH:75$]);

create index IX_557A639F on LayoutPrototype (companyId, active_);
create index IX_CEF72136 on LayoutPrototype (uuid_[$COLUMN_LENGTH:75$]);

create index IX_9EC9F954 on LayoutRevision (layoutSetBranchId, head, status);
create index IX_538BFC54 on LayoutRevision (layoutSetBranchId, plid, head, layoutBranchId);
create index IX_84668240 on LayoutRevision (layoutSetBranchId, plid, layoutBranchId);
create index IX_F93E5CC3 on LayoutRevision (layoutSetBranchId, plid, parentLayoutRevisionId);
create index IX_70DA9ECB on LayoutRevision (layoutSetBranchId, plid, status);
create index IX_7FFAE700 on LayoutRevision (layoutSetBranchId, status);
create index IX_27F4B32A on LayoutRevision (plid, head);
create index IX_8EC3D2BC on LayoutRevision (plid, status);
create index IX_421223B1 on LayoutRevision (status);

create unique index IX_48550691 on LayoutSet (groupId, privateLayout);
create index IX_C629311 on LayoutSet (layoutSetPrototypeUuid[$COLUMN_LENGTH:75$], companyId);
create index IX_1B698D9 on LayoutSet (privateLayout, logoId);

create index IX_CCF0DA29 on LayoutSetBranch (groupId, privateLayout, master);
create unique index IX_5FF18552 on LayoutSetBranch (groupId, privateLayout, name[$COLUMN_LENGTH:75$]);

create index IX_9178FC71 on LayoutSetPrototype (companyId, active_);
create index IX_C5D69B24 on LayoutSetPrototype (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_BF6DBF8A on ListType (companyId, type_[$COLUMN_LENGTH:75$], name[$COLUMN_LENGTH:75$]);
create index IX_56E29D16 on ListType (uuid_[$COLUMN_LENGTH:75$]);

create index IX_C28C72EC on MembershipRequest (groupId, statusId);
create index IX_35AA8FA6 on MembershipRequest (groupId, userId, statusId);
create index IX_66D70879 on MembershipRequest (userId);

create index IX_6AF0D434 on OrgLabor (organizationId);

create unique index IX_6B83F1C7 on Organization_ (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_E301BDF5 on Organization_ (companyId, name[$COLUMN_LENGTH:100$]);
create index IX_418E4522 on Organization_ (companyId, parentOrganizationId);
create index IX_396D6B42 on Organization_ (uuid_[$COLUMN_LENGTH:75$]);

create index IX_2C1142E on PasswordPolicy (companyId, defaultPolicy);
create unique index IX_3FBFA9F4 on PasswordPolicy (companyId, name[$COLUMN_LENGTH:75$]);
create index IX_51437A01 on PasswordPolicy (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_C3A17327 on PasswordPolicyRel (classNameId, classPK);
create index IX_CD25266E on PasswordPolicyRel (passwordPolicyId);

create index IX_326F75BD on PasswordTracker (userId);

create index IX_812CE07A on Phone (companyId, classNameId, classPK, primary_);
create unique index IX_2F178CA9 on Phone (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_F202B9CE on Phone (userId);
create index IX_EA6245A0 on Phone (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_7171B2E8 on PluginSetting (companyId, pluginId[$COLUMN_LENGTH:75$], pluginType[$COLUMN_LENGTH:75$]);

create unique index IX_D5E35599 on PortalPreferenceValue (portalPreferencesId, namespace[$COLUMN_LENGTH:255$], key_[$COLUMN_LENGTH:1024$], index_);
create index IX_737DBC36 on PortalPreferenceValue (portalPreferencesId, namespace[$COLUMN_LENGTH:255$], key_[$COLUMN_LENGTH:1024$], smallValue[$COLUMN_LENGTH:255$]);

create index IX_D1846D13 on PortalPreferences (ownerType, ownerId);

create unique index IX_12B5E51D on Portlet (companyId, portletId[$COLUMN_LENGTH:200$]);

create index IX_C6246ECD on PortletItem (groupId, classNameId, portletId[$COLUMN_LENGTH:200$], name[$COLUMN_LENGTH:75$]);

create index IX_EE8C5489 on PortletPreferenceValue (name[$COLUMN_LENGTH:255$], smallValue[$COLUMN_LENGTH:255$], companyId);
create unique index IX_AFB45FEF on PortletPreferenceValue (portletPreferencesId, name[$COLUMN_LENGTH:255$], index_);
create index IX_8E75AB8C on PortletPreferenceValue (portletPreferencesId, name[$COLUMN_LENGTH:255$], smallValue[$COLUMN_LENGTH:255$]);

create index IX_3EAB5A5A on PortletPreferences (ownerId);
create index IX_6DD4B410 on PortletPreferences (ownerType, ownerId, plid);
create index IX_F15C1C4F on PortletPreferences (plid);
create index IX_CEA05B46 on PortletPreferences (portletId[$COLUMN_LENGTH:200$], ownerType, ownerId, companyId);
create unique index IX_23485C6D on PortletPreferences (portletId[$COLUMN_LENGTH:200$], ownerType, ownerId, plid);
create index IX_EF5FCC07 on PortletPreferences (portletId[$COLUMN_LENGTH:200$], ownerType, plid);
create index IX_8DCFD52C on PortletPreferences (portletId[$COLUMN_LENGTH:200$], plid);

create index IX_A1A8CB8B on RatingsEntry (classNameId, classPK, score);
create unique index IX_A07A5E91 on RatingsEntry (classNameId, classPK, userId);
create index IX_C34DEAF2 on RatingsEntry (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_A6E99284 on RatingsStats (classNameId, classPK);

create index IX_B91F79BD on RecentLayoutBranch (groupId);
create index IX_351E86E8 on RecentLayoutBranch (layoutBranchId);
create unique index IX_C27D6369 on RecentLayoutBranch (userId, layoutSetBranchId, plid);

create index IX_8D8A2724 on RecentLayoutRevision (groupId);
create index IX_DA0788DA on RecentLayoutRevision (layoutRevisionId);
create unique index IX_4C600BD0 on RecentLayoutRevision (userId, layoutSetBranchId, plid);

create index IX_711995A5 on RecentLayoutSetBranch (groupId);
create index IX_23FF0700 on RecentLayoutSetBranch (layoutSetBranchId);
create unique index IX_4654D204 on RecentLayoutSetBranch (userId, layoutSetId);

create index IX_2D9A426F on Region (active_);
create index IX_11FB3E42 on Region (countryId, active_);
create unique index IX_A2635F5C on Region (countryId, regionCode[$COLUMN_LENGTH:75$]);
create index IX_48A89E9A on Region (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_A149763D on RegionLocalization (regionId, languageId[$COLUMN_LENGTH:75$]);

create unique index IX_8BD6BCA7 on Release_ (servletContextName[$COLUMN_LENGTH:75$]);

create index IX_D4C6FBCB on RememberMeToken (expirationDate);
create index IX_291F58D4 on RememberMeToken (userId);

create unique index IX_F6B6A203 on Repository (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_60C8634C on Repository (groupId, name[$COLUMN_LENGTH:200$], portletId[$COLUMN_LENGTH:200$]);
create unique index IX_DF1D2A7A on Repository (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_74C17B04 on Repository (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_9BDCF489 on RepositoryEntry (repositoryId, mappedId[$COLUMN_LENGTH:255$]);
create unique index IX_354AA664 on RepositoryEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_EDB9986E on ResourceAction (name[$COLUMN_LENGTH:255$], actionId[$COLUMN_LENGTH:75$]);

create index IX_26284944 on ResourcePermission (companyId, primKey[$COLUMN_LENGTH:255$]);
create index IX_A24F62CB on ResourcePermission (companyId, scope, name[$COLUMN_LENGTH:255$], primKey[$COLUMN_LENGTH:255$]);
create unique index IX_C46D2306 on ResourcePermission (companyId, scope, name[$COLUMN_LENGTH:255$], roleId, primKey[$COLUMN_LENGTH:255$]);
create index IX_D8997757 on ResourcePermission (companyId, scope, name[$COLUMN_LENGTH:255$], roleId, viewActionId, primKeyId);
create index IX_F6BAE86A on ResourcePermission (companyId, scope, primKey[$COLUMN_LENGTH:255$]);
create index IX_D5F1E2A2 on ResourcePermission (name[$COLUMN_LENGTH:255$]);
create index IX_A37A0588 on ResourcePermission (roleId);
create index IX_F4555981 on ResourcePermission (scope);

create unique index IX_A88E424E on Role_ (companyId, classNameId, classPK);
create unique index IX_6D501C64 on Role_ (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_EBC931B8 on Role_ (companyId, name[$COLUMN_LENGTH:75$]);
create index IX_F436EC8E on Role_ (name[$COLUMN_LENGTH:75$]);
create index IX_5EB4E2FB on Role_ (subtype[$COLUMN_LENGTH:75$]);
create index IX_CBE204 on Role_ (type_, subtype[$COLUMN_LENGTH:75$]);
create index IX_26DB26C5 on Role_ (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_4F0315B8 on ServiceComponent (buildNamespace[$COLUMN_LENGTH:75$], buildNumber);

create index IX_F542E9BC on SocialActivity (activitySetId);
create unique index IX_7E6A9AAD on SocialActivity (classNameId, classPK, groupId, userId, type_, receiverUserId, ctCollectionId, createDate);
create index IX_85370BF4 on SocialActivity (classNameId, classPK, mirrorActivityId);
create index IX_D0E9029E on SocialActivity (classNameId, classPK, type_);
create index IX_F885EA9C on SocialActivity (classNameId, companyId);
create index IX_64B1BC66 on SocialActivity (companyId);
create index IX_2A2468 on SocialActivity (groupId);
create index IX_1271F25F on SocialActivity (mirrorActivityId);
create index IX_121CA3CB on SocialActivity (receiverUserId);
create index IX_3504B8BC on SocialActivity (userId);

create index IX_83E16F2F on SocialActivityAchievement (groupId, firstInGroup);
create index IX_8F6408F0 on SocialActivityAchievement (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_AABC18E9 on SocialActivityAchievement (groupId, userId, firstInGroup);
create unique index IX_5ED94F08 on SocialActivityAchievement (groupId, userId, name[$COLUMN_LENGTH:75$], ctCollectionId);

create index IX_A4B9A23B on SocialActivityCounter (classNameId, classPK);
create unique index IX_56195A6B on SocialActivityCounter (groupId, classNameId, classPK, ownerType, name[$COLUMN_LENGTH:75$], ctCollectionId, endPeriod);
create unique index IX_379AA3B2 on SocialActivityCounter (groupId, classNameId, classPK, ownerType, name[$COLUMN_LENGTH:75$], ctCollectionId, startPeriod);

create index IX_B15863FA on SocialActivityLimit (classNameId, classPK);
create unique index IX_4A636E75 on SocialActivityLimit (groupId, userId, classNameId, classPK, activityType, activityCounterName[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_6F9EDE9F on SocialActivityLimit (userId);

create index IX_9E13F2DE on SocialActivitySet (groupId);
create index IX_5D1FA9E on SocialActivitySet (type_, classNameId, classPK);
create index IX_241D10A4 on SocialActivitySet (userId, type_, classNameId, classPK);
create index IX_6D0C8733 on SocialActivitySet (userId, type_, groupId, classNameId);

create index IX_384788CD on SocialActivitySetting (groupId, activityType);
create index IX_D984AABA on SocialActivitySetting (groupId, classNameId, activityType, name[$COLUMN_LENGTH:75$]);

create index IX_61171E99 on SocialRelation (companyId);
create index IX_5E1F07A2 on SocialRelation (type_, companyId);
create unique index IX_ECA579C5 on SocialRelation (type_, userId1, userId2, ctCollectionId);
create index IX_C91168D6 on SocialRelation (type_, userId2);
create index IX_B5C9C690 on SocialRelation (userId1, userId2);
create index IX_5A40D18D on SocialRelation (userId2);
create index IX_F0CA24A5 on SocialRelation (uuid_[$COLUMN_LENGTH:75$]);

create index IX_E8468A49 on SocialRequest (classNameId, classPK, receiverUserId, status, type_);
create index IX_A90FE5A0 on SocialRequest (companyId);
create index IX_D9380CB7 on SocialRequest (receiverUserId, status);
create unique index IX_2FE40453 on SocialRequest (userId, classNameId, classPK, receiverUserId, type_, ctCollectionId);
create index IX_7CFF5CB8 on SocialRequest (userId, classNameId, classPK, status, type_);
create index IX_AB5906A8 on SocialRequest (userId, status);
create unique index IX_87DD8A60 on SocialRequest (uuid_[$COLUMN_LENGTH:75$], ctCollectionId, groupId);

create index IX_FFCBB747 on SystemEvent (groupId, classNameId, classPK, type_);
create index IX_A19C89FF on SystemEvent (groupId, systemEventSetKey);

create index IX_93AB8545 on Team (companyId);
create unique index IX_143DC786 on Team (groupId, name[$COLUMN_LENGTH:75$]);
create unique index IX_39F69E79 on Team (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DAD135B4 on Ticket (classNameId, classPK, companyId, type_);
create index IX_1E8DFB2E on Ticket (classNameId, classPK, type_);
create index IX_B2468446 on Ticket (key_[$COLUMN_LENGTH:255$]);

create unique index IX_CB9015AF on UserGroup (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_23EAD0D on UserGroup (companyId, name[$COLUMN_LENGTH:255$]);
create index IX_69771487 on UserGroup (companyId, parentUserGroupId);
create index IX_5F1DD85A on UserGroup (uuid_[$COLUMN_LENGTH:75$]);

create index IX_CAB0CCC8 on UserGroupGroupRole (groupId, roleId);
create unique index IX_1CC8A01 on UserGroupGroupRole (groupId, userGroupId, roleId);
create index IX_1CDF88C on UserGroupGroupRole (roleId);
create index IX_DCDED558 on UserGroupGroupRole (userGroupId);

create index IX_871412DF on UserGroupRole (groupId, roleId);
create unique index IX_4FF47719 on UserGroupRole (groupId, userId, roleId);
create index IX_887A2C95 on UserGroupRole (roleId);
create index IX_887BE56A on UserGroupRole (userId);

create index IX_2AC5356C on UserGroups_Teams (companyId);
create index IX_7F187E63 on UserGroups_Teams (userGroupId);

create unique index IX_41A32E0D on UserIdMapper (type_[$COLUMN_LENGTH:75$], externalUserId[$COLUMN_LENGTH:75$]);
create unique index IX_D1C44A6E on UserIdMapper (userId, type_[$COLUMN_LENGTH:75$]);

create unique index IX_8B6E3ACE on UserNotificationDelivery (userId, portletId[$COLUMN_LENGTH:200$], classNameId, notificationType, deliveryType);

create index IX_BF29100B on UserNotificationEvent (type_[$COLUMN_LENGTH:200$]);
create index IX_6E095243 on UserNotificationEvent (userId, archived, actionRequired);
create index IX_E32CC19 on UserNotificationEvent (userId, delivered, actionRequired);
create index IX_AE54166F on UserNotificationEvent (userId, delivered, archived, actionRequired);
create index IX_7522B7DB on UserNotificationEvent (userId, delivered, deliveryType, actionRequired);
create index IX_3BE9B7B1 on UserNotificationEvent (userId, delivered, deliveryType, archived, actionRequired);
create index IX_2AB8294D on UserNotificationEvent (userId, delivered, deliveryType, archived, type_[$COLUMN_LENGTH:200$]);
create index IX_105871E3 on UserNotificationEvent (userId, delivered, deliveryType, type_[$COLUMN_LENGTH:200$]);
create index IX_EBF87241 on UserNotificationEvent (userId, delivered, type_[$COLUMN_LENGTH:200$], timestamp);
create index IX_D60FB085 on UserNotificationEvent (userId, deliveryType, archived, actionRequired);
create index IX_ECD8CFEA on UserNotificationEvent (uuid_[$COLUMN_LENGTH:75$]);

create index IX_29BA1CF5 on UserTracker (companyId);
create index IX_46B0AE8E on UserTracker (sessionId[$COLUMN_LENGTH:200$]);
create index IX_E4EFBA8D on UserTracker (userId);

create index IX_14D8BCC0 on UserTrackerPath (userTrackerId);

create index IX_BCFDA257 on User_ (companyId, createDate, modifiedDate);
create unique index IX_615E9F7A on User_ (companyId, emailAddress[$COLUMN_LENGTH:254$]);
create unique index IX_E1D3922F on User_ (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_1D731F03 on User_ (companyId, facebookId);
create index IX_B6E3AE1 on User_ (companyId, googleUserId[$COLUMN_LENGTH:75$]);
create index IX_EE8ABD19 on User_ (companyId, modifiedDate);
create index IX_89509087 on User_ (companyId, openId[$COLUMN_LENGTH:1024$]);
create unique index IX_C5806019 on User_ (companyId, screenName[$COLUMN_LENGTH:75$]);
create index IX_F6039434 on User_ (companyId, status);
create index IX_FD06BAAD on User_ (companyId, type_, status);
create unique index IX_5ADBE171 on User_ (contactId);
create index IX_762F63C6 on User_ (emailAddress[$COLUMN_LENGTH:254$]);
create index IX_A18034A4 on User_ (portraitId);
create index IX_E0422BDA on User_ (uuid_[$COLUMN_LENGTH:75$]);

create index IX_3499B657 on Users_Groups (companyId);
create index IX_F10B6C6B on Users_Groups (userId);

create index IX_5FBB883C on Users_Orgs (companyId);
create index IX_FB646CA6 on Users_Orgs (userId);

create index IX_F987A0DC on Users_Roles (companyId);
create index IX_C1A01806 on Users_Roles (userId);

create index IX_799F8283 on Users_Teams (companyId);
create index IX_A098EFBF on Users_Teams (userId);

create index IX_BB65040C on Users_UserGroups (companyId);
create index IX_66FF2503 on Users_UserGroups (userGroupId);

create index IX_EECA5FDA on VirtualHost (companyId, layoutSetId, defaultVirtualHost);
create unique index IX_431A3960 on VirtualHost (hostname[$COLUMN_LENGTH:200$]);
create index IX_774643D1 on VirtualHost (layoutSetId, hostname[$COLUMN_LENGTH:200$]);

create unique index IX_97DFA146 on WebDAVProps (classNameId, classPK);

create index IX_1AA07A6D on Website (companyId, classNameId, classPK, primary_);
create unique index IX_36B86556 on Website (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_F75690BB on Website (userId);
create index IX_76F15D13 on Website (uuid_[$COLUMN_LENGTH:75$]);

create index IX_EB4D2E32 on WorkflowDefinitionLink (companyId, groupId, classNameId, classPK, typePK);
create index IX_4AE45F6F on WorkflowDefinitionLink (companyId, groupId, classPK);
create index IX_A4DB1F0F on WorkflowDefinitionLink (companyId, workflowDefinitionName[$COLUMN_LENGTH:75$], workflowDefinitionVersion);

create index IX_415A7007 on WorkflowInstanceLink (groupId, companyId, classNameId, classPK);