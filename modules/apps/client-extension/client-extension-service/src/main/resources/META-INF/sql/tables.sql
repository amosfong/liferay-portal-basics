create table ClientExtensionEntry (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	clientExtensionEntryId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	description TEXT null,
	name STRING null,
	properties TEXT null,
	sourceCodeURL STRING null,
	type_ VARCHAR(75) null,
	typeSettings TEXT null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	primary key (clientExtensionEntryId, ctCollectionId)
);

create table ClientExtensionEntryRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	clientExtensionEntryRelId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	cetExternalReferenceCode VARCHAR(75) null,
	type_ VARCHAR(75) null,
	typeSettings TEXT null,
	lastPublishDate DATE null,
	primary key (clientExtensionEntryRelId, ctCollectionId)
);