create index IX_7E9C8FF8 on KBArticle (externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_8DC73951 on KBArticle (groupId, ctCollectionId, uuid_[$COLUMN_LENGTH:75$]);
create unique index IX_1096F938 on KBArticle (groupId, ctCollectionId, version, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_4A49CDD6 on KBArticle (groupId, kbFolderId, urlTitle[$COLUMN_LENGTH:75$]);
create index IX_7B1749F4 on KBArticle (groupId, latest, kbFolderId);
create index IX_E2460F71 on KBArticle (groupId, latest, parentResourcePrimKey);
create index IX_5C814FBF on KBArticle (groupId, main, parentResourcePrimKey);
create index IX_37000F91 on KBArticle (groupId, resourcePrimKey, latest);
create index IX_FF9D0743 on KBArticle (groupId, resourcePrimKey, main);
create index IX_F3FC873C on KBArticle (groupId, status, kbFolderId, urlTitle[$COLUMN_LENGTH:75$]);
create index IX_9CD524DA on KBArticle (groupId, status, latest, kbFolderId);
create index IX_68D688CB on KBArticle (groupId, status, latest, parentResourcePrimKey);
create index IX_E3B45799 on KBArticle (groupId, status, main, parentResourcePrimKey);
create index IX_FD8E8D66 on KBArticle (groupId, status, parentResourcePrimKey);
create index IX_2C55146B on KBArticle (groupId, status, resourcePrimKey, latest);
create index IX_8C417A9D on KBArticle (groupId, status, resourcePrimKey, main);
create index IX_827ACC48 on KBArticle (latest, companyId);
create index IX_B8038671 on KBArticle (latest, parentResourcePrimKey);
create index IX_FD5B5196 on KBArticle (main, companyId);
create index IX_A13086BF on KBArticle (main, parentResourcePrimKey);
create unique index IX_9A21A6D4 on KBArticle (resourcePrimKey, ctCollectionId, version);
create index IX_A9E2C691 on KBArticle (resourcePrimKey, latest);
create index IX_69C17E43 on KBArticle (resourcePrimKey, main);
create index IX_C5C0D1BD on KBArticle (status, companyId);
create index IX_A67A6415 on KBArticle (status, displayDate);
create index IX_69DBFCA2 on KBArticle (status, latest, companyId);
create index IX_12CFFCB on KBArticle (status, latest, parentResourcePrimKey);
create index IX_8020D070 on KBArticle (status, main, companyId);
create index IX_BD3C8E99 on KBArticle (status, main, parentResourcePrimKey);
create index IX_D34C0466 on KBArticle (status, parentResourcePrimKey);
create index IX_8890CB6B on KBArticle (status, resourcePrimKey, latest);
create index IX_61FEF19D on KBArticle (status, resourcePrimKey, main);
create index IX_C23FA26F on KBArticle (uuid_[$COLUMN_LENGTH:75$]);

create index IX_47D3AE89 on KBComment (classNameId, classPK, status);
create index IX_E952C7DD on KBComment (classNameId, classPK, userId);
create index IX_E8D43932 on KBComment (groupId, classNameId);
create index IX_828BA082 on KBComment (groupId, status);
create unique index IX_3854CAF6 on KBComment (groupId, uuid_[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_8E470726 on KBComment (uuid_[$COLUMN_LENGTH:75$]);

create index IX_F32A081D on KBFolder (companyId);
create unique index IX_538A8E60 on KBFolder (groupId, ctCollectionId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_3FA4415C on KBFolder (groupId, parentKBFolderId, name[$COLUMN_LENGTH:75$]);
create index IX_C8923D43 on KBFolder (groupId, parentKBFolderId, status);
create index IX_729A89FA on KBFolder (groupId, parentKBFolderId, urlTitle[$COLUMN_LENGTH:75$]);
create unique index IX_9697B53 on KBFolder (groupId, uuid_[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_30B67029 on KBFolder (uuid_[$COLUMN_LENGTH:75$]);

create index IX_83D9CC13 on KBTemplate (groupId);
create unique index IX_7C6D824B on KBTemplate (uuid_[$COLUMN_LENGTH:75$], groupId, ctCollectionId);