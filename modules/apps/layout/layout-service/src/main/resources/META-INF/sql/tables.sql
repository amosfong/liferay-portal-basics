create table LayoutClassedModelUsage (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	layoutClassedModelUsageId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	cmExternalReferenceCode VARCHAR(75) null,
	containerKey VARCHAR(200) null,
	containerType LONG,
	plid LONG,
	type_ INTEGER,
	lastPublishDate DATE null
);

create table LayoutLocalization (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	layoutLocalizationId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	content TEXT null,
	languageId VARCHAR(75) null,
	plid LONG,
	lastPublishDate DATE null
);