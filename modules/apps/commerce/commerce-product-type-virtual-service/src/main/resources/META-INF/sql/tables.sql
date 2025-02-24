create table CPDVirtualSettingFileEntry (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPDVirtualSettingFileEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionVirtualSettingId LONG,
	fileEntryId LONG,
	url VARCHAR(255) null,
	version VARCHAR(75) null
);

create table CPDefinitionVirtualSetting (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPDefinitionVirtualSettingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	activationStatus INTEGER,
	duration LONG,
	maxUsages INTEGER,
	useSample BOOLEAN,
	sampleFileEntryId LONG,
	sampleURL VARCHAR(255) null,
	termsOfUseRequired BOOLEAN,
	termsOfUseContent STRING null,
	termsOfUseArticleResourcePK LONG,
	override BOOLEAN,
	lastPublishDate DATE null
);