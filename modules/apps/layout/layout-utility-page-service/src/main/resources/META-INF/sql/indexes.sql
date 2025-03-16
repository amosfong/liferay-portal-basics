create unique index IX_AEC11B5A on LayoutUtilityPageEntry (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_DCFECA00 on LayoutUtilityPageEntry (groupId, type_[$COLUMN_LENGTH:75$], defaultLayoutUtilityPageEntry);
create unique index IX_B0D10431 on LayoutUtilityPageEntry (groupId, type_[$COLUMN_LENGTH:75$], name[$COLUMN_LENGTH:75$]);
create unique index IX_C45B31D1 on LayoutUtilityPageEntry (groupId, uuid_[$COLUMN_LENGTH:75$]);
create unique index IX_C706B12 on LayoutUtilityPageEntry (plid);
create index IX_997885CD on LayoutUtilityPageEntry (uuid_[$COLUMN_LENGTH:75$]);