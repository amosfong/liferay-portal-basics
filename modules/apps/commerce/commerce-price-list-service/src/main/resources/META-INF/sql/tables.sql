create table CPLCommerceGroupAccountRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPLCommerceAccountGroupRelId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commercePriceListId LONG,
	commerceAccountGroupId LONG,
	order_ INTEGER,
	lastPublishDate DATE null,
	primary key (CPLCommerceAccountGroupRelId, ctCollectionId)
);

create table CommercePriceEntry (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commercePriceEntryId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commercePriceListId LONG,
	CPInstanceUuid VARCHAR(75) null,
	CProductId LONG,
	bulkPricing BOOLEAN,
	discountDiscovery BOOLEAN,
	discountLevel1 BIGDECIMAL null,
	discountLevel2 BIGDECIMAL null,
	discountLevel3 BIGDECIMAL null,
	discountLevel4 BIGDECIMAL null,
	displayDate DATE null,
	expirationDate DATE null,
	hasTierPrice BOOLEAN,
	price BIGDECIMAL null,
	priceOnApplication BOOLEAN,
	pricingQuantity BIGDECIMAL null,
	promoPrice BIGDECIMAL null,
	quantity BIGDECIMAL null,
	unitOfMeasureKey VARCHAR(75) null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	primary key (commercePriceEntryId, ctCollectionId)
);

create table CommercePriceList (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commercePriceListId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceCurrencyCode VARCHAR(75) null,
	parentCommercePriceListId LONG,
	catalogBasePriceList BOOLEAN,
	netPrice BOOLEAN,
	type_ VARCHAR(75) null,
	name VARCHAR(75) null,
	priority DOUBLE,
	displayDate DATE null,
	expirationDate DATE null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	primary key (commercePriceListId, ctCollectionId)
);

create table CommercePriceListAccountRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	commercePriceListAccountRelId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceAccountId LONG,
	commercePriceListId LONG,
	order_ INTEGER,
	lastPublishDate DATE null,
	primary key (commercePriceListAccountRelId, ctCollectionId)
);

create table CommercePriceListChannelRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CommercePriceListChannelRelId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceChannelId LONG,
	commercePriceListId LONG,
	order_ INTEGER,
	lastPublishDate DATE null,
	primary key (CommercePriceListChannelRelId, ctCollectionId)
);

create table CommercePriceListDiscountRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	commercePriceListDiscountRelId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceDiscountId LONG,
	commercePriceListId LONG,
	order_ INTEGER,
	lastPublishDate DATE null,
	primary key (commercePriceListDiscountRelId, ctCollectionId)
);

create table CommercePriceListOrderTypeRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPriceListOrderTypeRelId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commercePriceListId LONG,
	commerceOrderTypeId LONG,
	priority INTEGER,
	lastPublishDate DATE null,
	primary key (CPriceListOrderTypeRelId, ctCollectionId)
);

create table CommerceTierPriceEntry (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commerceTierPriceEntryId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commercePriceEntryId LONG,
	price BIGDECIMAL null,
	promoPrice BIGDECIMAL null,
	discountDiscovery BOOLEAN,
	discountLevel1 BIGDECIMAL null,
	discountLevel2 BIGDECIMAL null,
	discountLevel3 BIGDECIMAL null,
	discountLevel4 BIGDECIMAL null,
	minQuantity BIGDECIMAL null,
	displayDate DATE null,
	expirationDate DATE null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	primary key (commerceTierPriceEntryId, ctCollectionId)
);