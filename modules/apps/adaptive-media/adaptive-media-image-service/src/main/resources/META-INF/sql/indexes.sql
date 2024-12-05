create index IX_6DDD0C5F on AMImageEntry (companyId);
create index IX_51249CB0 on AMImageEntry (configurationUuid[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EBBEA9CD on AMImageEntry (configurationUuid[$COLUMN_LENGTH:75$], fileVersionId, ctCollectionId);
create index IX_E879919E on AMImageEntry (fileVersionId);
create index IX_65AB1EA1 on AMImageEntry (groupId);
create unique index IX_681D2FFD on AMImageEntry (uuid_[$COLUMN_LENGTH:75$], groupId, ctCollectionId);