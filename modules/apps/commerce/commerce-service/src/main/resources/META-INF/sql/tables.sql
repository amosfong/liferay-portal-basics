create table CPDAvailabilityEstimate (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPDAvailabilityEstimateId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceAvailabilityEstimateId LONG,
	CProductId LONG,
	lastPublishDate DATE null
);

create table CPDefinitionInventory (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPDefinitionInventoryId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionId LONG,
	CPDefinitionInventoryEngine VARCHAR(75) null,
	lowStockActivity VARCHAR(75) null,
	displayAvailability BOOLEAN,
	displayStockQuantity BOOLEAN,
	minStockQuantity BIGDECIMAL null,
	backOrders BOOLEAN,
	minOrderQuantity BIGDECIMAL null,
	maxOrderQuantity BIGDECIMAL null,
	allowedOrderQuantities VARCHAR(75) null,
	multipleOrderQuantity BIGDECIMAL null,
	primary key (CPDefinitionInventoryId, ctCollectionId)
);

create table CSOptionAccountEntryRel (
	mvccVersion LONG default 0 not null,
	CSOptionAccountEntryRelId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountEntryId LONG,
	commerceChannelId LONG,
	commerceShippingMethodKey VARCHAR(75) null,
	commerceShippingOptionKey VARCHAR(75) null
);

create table CommerceAddressRestriction (
	mvccVersion LONG default 0 not null,
	commerceAddressRestrictionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	countryId LONG
);

create table CommerceAvailabilityEstimate (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	commerceAvailabilityEstimateId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title STRING null,
	priority DOUBLE,
	lastPublishDate DATE null
);

create table CommerceOrder (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commerceOrderId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	billingAddressId LONG,
	commerceAccountId LONG,
	commerceCurrencyCode VARCHAR(75) null,
	commerceOrderTypeId LONG,
	commerceShippingMethodId LONG,
	deliveryCommerceTermEntryId LONG,
	paymentCommerceTermEntryId LONG,
	shippingAddressId LONG,
	advanceStatus VARCHAR(75) null,
	commercePaymentMethodKey VARCHAR(75) null,
	couponCode VARCHAR(75) null,
	deliveryCTermEntryDescription TEXT null,
	deliveryCommerceTermEntryName VARCHAR(75) null,
	lastPriceUpdateDate DATE null,
	manuallyAdjusted BOOLEAN,
	name VARCHAR(75) null,
	orderDate DATE null,
	orderStatus INTEGER,
	paymentCTermEntryDescription TEXT null,
	paymentCommerceTermEntryName VARCHAR(75) null,
	paymentStatus INTEGER,
	printedNote STRING null,
	purchaseOrderNumber VARCHAR(75) null,
	requestedDeliveryDate DATE null,
	shippable BOOLEAN,
	shippingAmount BIGDECIMAL null,
	shippingDiscountAmount BIGDECIMAL null,
	shippingDiscountPercentLevel1 BIGDECIMAL null,
	shippingDiscountPercentLevel2 BIGDECIMAL null,
	shippingDiscountPercentLevel3 BIGDECIMAL null,
	shippingDiscountPercentLevel4 BIGDECIMAL null,
	shippingDiscountPctLev1WithTax BIGDECIMAL null,
	shippingDiscountPctLev2WithTax BIGDECIMAL null,
	shippingDiscountPctLev3WithTax BIGDECIMAL null,
	shippingDiscountPctLev4WithTax BIGDECIMAL null,
	shippingDiscountWithTaxAmount BIGDECIMAL null,
	shippingOptionName VARCHAR(255) null,
	shippingWithTaxAmount BIGDECIMAL null,
	subtotal BIGDECIMAL null,
	subtotalDiscountAmount BIGDECIMAL null,
	subtotalDiscountPercentLevel1 BIGDECIMAL null,
	subtotalDiscountPercentLevel2 BIGDECIMAL null,
	subtotalDiscountPercentLevel3 BIGDECIMAL null,
	subtotalDiscountPercentLevel4 BIGDECIMAL null,
	subtotalDiscountPctLev1WithTax BIGDECIMAL null,
	subtotalDiscountPctLev2WithTax BIGDECIMAL null,
	subtotalDiscountPctLev3WithTax BIGDECIMAL null,
	subtotalDiscountPctLev4WithTax BIGDECIMAL null,
	subtotalDiscountWithTaxAmount BIGDECIMAL null,
	subtotalWithTaxAmount BIGDECIMAL null,
	taxAmount BIGDECIMAL null,
	total BIGDECIMAL null,
	totalDiscountAmount BIGDECIMAL null,
	totalDiscountPercentageLevel1 BIGDECIMAL null,
	totalDiscountPercentageLevel2 BIGDECIMAL null,
	totalDiscountPercentageLevel3 BIGDECIMAL null,
	totalDiscountPercentageLevel4 BIGDECIMAL null,
	totalDiscountPctLev1WithTax BIGDECIMAL null,
	totalDiscountPctLev2WithTax BIGDECIMAL null,
	totalDiscountPctLev3WithTax BIGDECIMAL null,
	totalDiscountPctLev4WithTax BIGDECIMAL null,
	totalDiscountWithTaxAmount BIGDECIMAL null,
	totalWithTaxAmount BIGDECIMAL null,
	transactionId TEXT null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table CommerceOrderItem (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commerceOrderItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CIBookedQuantityId LONG,
	commerceOrderId LONG,
	commercePriceListId LONG,
	CPInstanceId LONG,
	CPMeasurementUnitId LONG,
	CProductId LONG,
	customerCommerceOrderItemId LONG,
	parentCommerceOrderItemId LONG,
	shippingAddressId LONG,
	deliveryGroupName VARCHAR(75) null,
	deliveryMaxSubscriptionCycles LONG,
	deliverySubscriptionLength INTEGER,
	deliverySubscriptionType VARCHAR(75) null,
	deliverySubTypeSettings VARCHAR(75) null,
	depth DOUBLE,
	discountAmount BIGDECIMAL null,
	discountManuallyAdjusted BOOLEAN,
	discountPercentageLevel1 BIGDECIMAL null,
	discountPercentageLevel2 BIGDECIMAL null,
	discountPercentageLevel3 BIGDECIMAL null,
	discountPercentageLevel4 BIGDECIMAL null,
	discountPctLevel1WithTaxAmount BIGDECIMAL null,
	discountPctLevel2WithTaxAmount BIGDECIMAL null,
	discountPctLevel3WithTaxAmount BIGDECIMAL null,
	discountPctLevel4WithTaxAmount BIGDECIMAL null,
	discountWithTaxAmount BIGDECIMAL null,
	finalPrice BIGDECIMAL null,
	finalPriceWithTaxAmount BIGDECIMAL null,
	freeShipping BOOLEAN,
	height DOUBLE,
	json TEXT null,
	manuallyAdjusted BOOLEAN,
	maxSubscriptionCycles LONG,
	name STRING null,
	priceManuallyAdjusted BOOLEAN,
	priceOnApplication BOOLEAN,
	printedNote STRING null,
	promoPrice BIGDECIMAL null,
	promoPriceWithTaxAmount BIGDECIMAL null,
	quantity BIGDECIMAL null,
	replacedCPInstanceId LONG,
	replacedSku VARCHAR(75) null,
	requestedDeliveryDate DATE null,
	shipSeparately BOOLEAN,
	shippable BOOLEAN,
	shippedQuantity BIGDECIMAL null,
	shippingExtraPrice DOUBLE,
	sku VARCHAR(75) null,
	subscription BOOLEAN,
	subscriptionLength INTEGER,
	subscriptionType VARCHAR(75) null,
	subscriptionTypeSettings VARCHAR(75) null,
	UOMIncrementalOrderQuantity BIGDECIMAL null,
	unitOfMeasureKey VARCHAR(75) null,
	unitPrice BIGDECIMAL null,
	unitPriceWithTaxAmount BIGDECIMAL null,
	weight DOUBLE,
	width DOUBLE
);

create table CommerceOrderNote (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commerceOrderNoteId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceOrderId LONG,
	content STRING null,
	restricted BOOLEAN
);

create table CommerceOrderPayment (
	mvccVersion LONG default 0 not null,
	commerceOrderPaymentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceOrderId LONG,
	commercePaymentMethodKey VARCHAR(75) null,
	content TEXT null,
	status INTEGER
);

create table CommerceOrderType (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commerceOrderTypeId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	active_ BOOLEAN,
	displayDate DATE null,
	displayOrder INTEGER,
	expirationDate DATE null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table CommerceOrderTypeRel (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commerceOrderTypeRelId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	commerceOrderTypeId LONG
);

create table CommerceShipment (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commerceShipmentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceAccountId LONG,
	commerceAddressId LONG,
	commerceShippingMethodId LONG,
	carrier VARCHAR(75) null,
	expectedDate DATE null,
	shippingDate DATE null,
	shippingOptionName TEXT null,
	trackingNumber VARCHAR(75) null,
	trackingURL STRING null,
	status INTEGER
);

create table CommerceShipmentItem (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commerceShipmentItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceShipmentId LONG,
	commerceOrderItemId LONG,
	commerceInventoryWarehouseId LONG,
	quantity BIGDECIMAL null,
	unitOfMeasureKey VARCHAR(75) null
);

create table CommerceShippingMethod (
	mvccVersion LONG default 0 not null,
	commerceShippingMethodId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	active_ BOOLEAN,
	engineKey VARCHAR(75) null,
	imageId LONG,
	priority DOUBLE,
	trackingURL STRING null,
	typeSettings TEXT null
);

create table CommerceSubscriptionEntry (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	commerceSubscriptionEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPInstanceUuid VARCHAR(75) null,
	CProductId LONG,
	commerceOrderItemId LONG,
	subscriptionLength INTEGER,
	subscriptionType VARCHAR(75) null,
	subscriptionTypeSettings TEXT null,
	currentCycle LONG,
	maxSubscriptionCycles LONG,
	subscriptionStatus INTEGER,
	lastIterationDate DATE null,
	nextIterationDate DATE null,
	startDate DATE null,
	deliverySubscriptionLength INTEGER,
	deliverySubscriptionType VARCHAR(75) null,
	deliverySubTypeSettings VARCHAR(75) null,
	deliveryCurrentCycle LONG,
	deliveryMaxSubscriptionCycles LONG,
	deliverySubscriptionStatus INTEGER,
	deliveryLastIterationDate DATE null,
	deliveryNextIterationDate DATE null,
	deliveryStartDate DATE null
);