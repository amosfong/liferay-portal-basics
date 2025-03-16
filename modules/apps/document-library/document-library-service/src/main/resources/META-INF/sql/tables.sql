create table DLFileVersionPreview (
	mvccVersion LONG default 0 not null,
	dlFileVersionPreviewId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	fileEntryId LONG,
	fileVersionId LONG,
	previewStatus INTEGER
);

create table DLStorageQuota (
	mvccVersion LONG default 0 not null,
	dlStorageQuotaId LONG not null primary key,
	companyId LONG,
	storageSize LONG
);