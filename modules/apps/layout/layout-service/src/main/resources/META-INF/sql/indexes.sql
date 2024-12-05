create unique index IX_9A7A2997 on LayoutClassedModelUsage (classNameId, classPK, cmExternalReferenceCode[$COLUMN_LENGTH:75$], containerType, plid, groupId, containerKey[$COLUMN_LENGTH:200$], ctCollectionId);
create index IX_B041F1F5 on LayoutClassedModelUsage (classNameId, classPK, type_);
create index IX_B51E9567 on LayoutClassedModelUsage (classNameId, companyId, cmExternalReferenceCode[$COLUMN_LENGTH:75$], type_);
create index IX_6AAEDC6 on LayoutClassedModelUsage (classNameId, companyId, containerType);
create index IX_F747B9BD on LayoutClassedModelUsage (containerType, plid, containerKey[$COLUMN_LENGTH:200$]);
create index IX_19448DD6 on LayoutClassedModelUsage (plid);
create unique index IX_8A32D79F on LayoutClassedModelUsage (uuid_[$COLUMN_LENGTH:75$], groupId, ctCollectionId);

create unique index IX_7925C939 on LayoutLocalization (plid, ctCollectionId, languageId[$COLUMN_LENGTH:75$]);
create unique index IX_8DC09EE1 on LayoutLocalization (uuid_[$COLUMN_LENGTH:75$], ctCollectionId, groupId);