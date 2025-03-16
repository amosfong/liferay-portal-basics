create index IX_68E2B208 on SiteNavigationMenu (companyId);
create index IX_1D786176 on SiteNavigationMenu (groupId, auto_);
create unique index IX_A8759773 on SiteNavigationMenu (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_ECBADAC9 on SiteNavigationMenu (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_1125400B on SiteNavigationMenu (groupId, type_);
create unique index IX_3ED4FFEA on SiteNavigationMenu (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_828EC794 on SiteNavigationMenu (uuid_[$COLUMN_LENGTH:75$]);

create index IX_B88C2AB5 on SiteNavigationMenuItem (companyId);
create unique index IX_84463CE6 on SiteNavigationMenuItem (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_75495C39 on SiteNavigationMenuItem (parentSiteNavigationMenuItemId);
create index IX_9FA7003B on SiteNavigationMenuItem (siteNavigationMenuId, name[$COLUMN_LENGTH:255$]);
create index IX_2294C622 on SiteNavigationMenuItem (siteNavigationMenuId, parentSiteNavigationMenuItemId);
create unique index IX_6FD3DF09 on SiteNavigationMenuItem (uuid_[$COLUMN_LENGTH:75$], groupId);