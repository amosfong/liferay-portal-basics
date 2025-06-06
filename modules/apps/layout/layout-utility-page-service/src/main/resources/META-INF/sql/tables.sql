create table LayoutUtilityPageEntry (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	LayoutUtilityPageEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	plid LONG,
	previewFileEntryId LONG,
	defaultLayoutUtilityPageEntry BOOLEAN,
	name VARCHAR(75) null,
	type_ VARCHAR(75) null,
	lastPublishDate DATE null
);