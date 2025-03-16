create unique index IX_C961806A on LayoutPageTemplateCollection (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_5A1F4BFC on LayoutPageTemplateCollection (groupId, parentLPTCollectionId);
create unique index IX_6CC75087 on LayoutPageTemplateCollection (groupId, type_, lptCollectionKey[$COLUMN_LENGTH:75$]);
create index IX_D2A97D41 on LayoutPageTemplateCollection (groupId, type_, name[$COLUMN_LENGTH:75$]);
create unique index IX_286CB18C on LayoutPageTemplateCollection (groupId, type_, parentLPTCollectionId, name[$COLUMN_LENGTH:75$]);
create unique index IX_6C07B6E1 on LayoutPageTemplateCollection (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_A17F0EBD on LayoutPageTemplateCollection (uuid_[$COLUMN_LENGTH:75$]);

create index IX_A6459477 on LayoutPageTemplateEntry (groupId, classNameId, classTypeId, defaultTemplate);
create unique index IX_1904E8F8 on LayoutPageTemplateEntry (groupId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_E7CC5585 on LayoutPageTemplateEntry (groupId, layoutPageTemplateCollectionId);
create unique index IX_DB7DADB9 on LayoutPageTemplateEntry (groupId, layoutPageTemplateEntryKey[$COLUMN_LENGTH:75$]);
create index IX_FFE79984 on LayoutPageTemplateEntry (groupId, name[$COLUMN_LENGTH:75$], layoutPageTemplateCollectionId);
create index IX_416DDC6A on LayoutPageTemplateEntry (groupId, name[$COLUMN_LENGTH:75$], status, layoutPageTemplateCollectionId);
create index IX_F328D6D1 on LayoutPageTemplateEntry (groupId, status, classNameId, classTypeId, defaultTemplate);
create index IX_DB1B076B on LayoutPageTemplateEntry (groupId, status, layoutPageTemplateCollectionId);
create index IX_186B1B7F on LayoutPageTemplateEntry (groupId, type_, classNameId, classTypeId);
create index IX_F406284D on LayoutPageTemplateEntry (groupId, type_, classNameId, defaultTemplate);
create index IX_CD9D4A70 on LayoutPageTemplateEntry (groupId, type_, layoutPageTemplateCollectionId);
create index IX_DE43E7E on LayoutPageTemplateEntry (groupId, type_, name[$COLUMN_LENGTH:75$], classNameId, classTypeId);
create unique index IX_4989F22F on LayoutPageTemplateEntry (groupId, type_, name[$COLUMN_LENGTH:75$], layoutPageTemplateCollectionId);
create index IX_FB160AE4 on LayoutPageTemplateEntry (groupId, type_, name[$COLUMN_LENGTH:75$], status, classNameId, classTypeId);
create index IX_C7B456E5 on LayoutPageTemplateEntry (groupId, type_, status, classNameId, classTypeId);
create index IX_9C2D0C95 on LayoutPageTemplateEntry (groupId, type_, status, defaultTemplate);
create unique index IX_279FB6F on LayoutPageTemplateEntry (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_A185457E on LayoutPageTemplateEntry (layoutPrototypeId);
create unique index IX_84D30230 on LayoutPageTemplateEntry (plid);
create index IX_2D68D26F on LayoutPageTemplateEntry (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_2125A4CF on LayoutPageTemplateStructure (groupId, plid);
create unique index IX_4DB1775C on LayoutPageTemplateStructure (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_BB165B45 on LayoutPageTemplateStructureRel (layoutPageTemplateStructureId, segmentsExperienceId);
create index IX_12808938 on LayoutPageTemplateStructureRel (segmentsExperienceId);
create unique index IX_2467A355 on LayoutPageTemplateStructureRel (uuid_[$COLUMN_LENGTH:75$], groupId);