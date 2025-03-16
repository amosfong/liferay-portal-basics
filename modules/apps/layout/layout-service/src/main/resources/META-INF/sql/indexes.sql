create unique index IX_27A6E539 on LayoutClassedModelUsage (classNameId, classPK, cmExternalReferenceCode[$COLUMN_LENGTH:75$], containerType, plid, groupId, containerKey[$COLUMN_LENGTH:200$]);
create index IX_B041F1F5 on LayoutClassedModelUsage (classNameId, classPK, type_);
create index IX_B51E9567 on LayoutClassedModelUsage (classNameId, companyId, cmExternalReferenceCode[$COLUMN_LENGTH:75$], type_);
create index IX_6AAEDC6 on LayoutClassedModelUsage (classNameId, companyId, containerType);
create index IX_F747B9BD on LayoutClassedModelUsage (containerType, plid, containerKey[$COLUMN_LENGTH:200$]);
create index IX_19448DD6 on LayoutClassedModelUsage (plid);
create unique index IX_694CA341 on LayoutClassedModelUsage (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_914937DB on LayoutLocalization (plid, languageId[$COLUMN_LENGTH:75$]);
create unique index IX_870D757F on LayoutLocalization (uuid_[$COLUMN_LENGTH:75$], groupId);