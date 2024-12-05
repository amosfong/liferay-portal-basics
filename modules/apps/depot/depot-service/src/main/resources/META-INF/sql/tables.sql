create table DepotAppCustomization (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	depotAppCustomizationId LONG not null,
	companyId LONG,
	depotEntryId LONG,
	enabled BOOLEAN,
	portletId VARCHAR(75) null,
	primary key (depotAppCustomizationId, ctCollectionId)
);

create table DepotEntry (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	depotEntryId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (depotEntryId, ctCollectionId)
);

create table DepotEntryGroupRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	depotEntryGroupRelId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	ddmStructuresAvailable BOOLEAN,
	depotEntryId LONG,
	searchable BOOLEAN,
	toGroupId LONG,
	lastPublishDate DATE null,
	primary key (depotEntryGroupRelId, ctCollectionId)
);