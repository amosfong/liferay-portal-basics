create index IX_F3DC928B on FriendlyURLEntry (groupId, classNameId, classPK);
create unique index IX_63FD57EA on FriendlyURLEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_BFA6E36A on FriendlyURLEntryLocalization (friendlyURLEntryId);
create index IX_543EE90B on FriendlyURLEntryLocalization (groupId, classNameId, languageId[$COLUMN_LENGTH:75$], classPK);
create unique index IX_DA293EB5 on FriendlyURLEntryLocalization (groupId, classNameId, languageId[$COLUMN_LENGTH:75$], urlTitle[$COLUMN_LENGTH:255$]);
create index IX_8AB5CAE on FriendlyURLEntryLocalization (groupId, classNameId, urlTitle[$COLUMN_LENGTH:255$]);
create unique index IX_68A4BEB1 on FriendlyURLEntryLocalization (languageId[$COLUMN_LENGTH:75$], friendlyURLEntryId);

create unique index IX_3B5E645B on FriendlyURLEntryMapping (classNameId, classPK);