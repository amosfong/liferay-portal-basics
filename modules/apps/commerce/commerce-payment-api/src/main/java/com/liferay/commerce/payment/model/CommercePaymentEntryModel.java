/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ExternalReferenceCodeModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommercePaymentEntry service. Represents a row in the &quot;CommercePaymentEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.payment.model.impl.CommercePaymentEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.payment.model.impl.CommercePaymentEntryImpl</code>.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommercePaymentEntry
 * @generated
 */
@ProviderType
public interface CommercePaymentEntryModel
	extends AttachedModel, AuditedModel, BaseModel<CommercePaymentEntry>,
			ExternalReferenceCodeModel, LocalizedModel, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce payment entry model instance should use the {@link CommercePaymentEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce payment entry.
	 *
	 * @return the primary key of this commerce payment entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce payment entry.
	 *
	 * @param primaryKey the primary key of this commerce payment entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce payment entry.
	 *
	 * @return the mvcc version of this commerce payment entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce payment entry.
	 *
	 * @param mvccVersion the mvcc version of this commerce payment entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the external reference code of this commerce payment entry.
	 *
	 * @return the external reference code of this commerce payment entry
	 */
	@AutoEscape
	@Override
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce payment entry.
	 *
	 * @param externalReferenceCode the external reference code of this commerce payment entry
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the commerce payment entry ID of this commerce payment entry.
	 *
	 * @return the commerce payment entry ID of this commerce payment entry
	 */
	public long getCommercePaymentEntryId();

	/**
	 * Sets the commerce payment entry ID of this commerce payment entry.
	 *
	 * @param commercePaymentEntryId the commerce payment entry ID of this commerce payment entry
	 */
	public void setCommercePaymentEntryId(long commercePaymentEntryId);

	/**
	 * Returns the company ID of this commerce payment entry.
	 *
	 * @return the company ID of this commerce payment entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce payment entry.
	 *
	 * @param companyId the company ID of this commerce payment entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce payment entry.
	 *
	 * @return the user ID of this commerce payment entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce payment entry.
	 *
	 * @param userId the user ID of this commerce payment entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce payment entry.
	 *
	 * @return the user uuid of this commerce payment entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce payment entry.
	 *
	 * @param userUuid the user uuid of this commerce payment entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce payment entry.
	 *
	 * @return the user name of this commerce payment entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce payment entry.
	 *
	 * @param userName the user name of this commerce payment entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce payment entry.
	 *
	 * @return the create date of this commerce payment entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce payment entry.
	 *
	 * @param createDate the create date of this commerce payment entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce payment entry.
	 *
	 * @return the modified date of this commerce payment entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce payment entry.
	 *
	 * @param modifiedDate the modified date of this commerce payment entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this commerce payment entry.
	 *
	 * @return the fully qualified class name of this commerce payment entry
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this commerce payment entry.
	 *
	 * @return the class name ID of this commerce payment entry
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this commerce payment entry.
	 *
	 * @param classNameId the class name ID of this commerce payment entry
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this commerce payment entry.
	 *
	 * @return the class pk of this commerce payment entry
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this commerce payment entry.
	 *
	 * @param classPK the class pk of this commerce payment entry
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the commerce channel ID of this commerce payment entry.
	 *
	 * @return the commerce channel ID of this commerce payment entry
	 */
	public long getCommerceChannelId();

	/**
	 * Sets the commerce channel ID of this commerce payment entry.
	 *
	 * @param commerceChannelId the commerce channel ID of this commerce payment entry
	 */
	public void setCommerceChannelId(long commerceChannelId);

	/**
	 * Returns the amount of this commerce payment entry.
	 *
	 * @return the amount of this commerce payment entry
	 */
	public BigDecimal getAmount();

	/**
	 * Sets the amount of this commerce payment entry.
	 *
	 * @param amount the amount of this commerce payment entry
	 */
	public void setAmount(BigDecimal amount);

	/**
	 * Returns the callback url of this commerce payment entry.
	 *
	 * @return the callback url of this commerce payment entry
	 */
	@AutoEscape
	public String getCallbackURL();

	/**
	 * Sets the callback url of this commerce payment entry.
	 *
	 * @param callbackURL the callback url of this commerce payment entry
	 */
	public void setCallbackURL(String callbackURL);

	/**
	 * Returns the cancel url of this commerce payment entry.
	 *
	 * @return the cancel url of this commerce payment entry
	 */
	@AutoEscape
	public String getCancelURL();

	/**
	 * Sets the cancel url of this commerce payment entry.
	 *
	 * @param cancelURL the cancel url of this commerce payment entry
	 */
	public void setCancelURL(String cancelURL);

	/**
	 * Returns the currency code of this commerce payment entry.
	 *
	 * @return the currency code of this commerce payment entry
	 */
	@AutoEscape
	public String getCurrencyCode();

	/**
	 * Sets the currency code of this commerce payment entry.
	 *
	 * @param currencyCode the currency code of this commerce payment entry
	 */
	public void setCurrencyCode(String currencyCode);

	/**
	 * Returns the error messages of this commerce payment entry.
	 *
	 * @return the error messages of this commerce payment entry
	 */
	@AutoEscape
	public String getErrorMessages();

	/**
	 * Sets the error messages of this commerce payment entry.
	 *
	 * @param errorMessages the error messages of this commerce payment entry
	 */
	public void setErrorMessages(String errorMessages);

	/**
	 * Returns the language ID of this commerce payment entry.
	 *
	 * @return the language ID of this commerce payment entry
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this commerce payment entry.
	 *
	 * @param languageId the language ID of this commerce payment entry
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the note of this commerce payment entry.
	 *
	 * @return the note of this commerce payment entry
	 */
	@AutoEscape
	public String getNote();

	/**
	 * Sets the note of this commerce payment entry.
	 *
	 * @param note the note of this commerce payment entry
	 */
	public void setNote(String note);

	/**
	 * Returns the payload of this commerce payment entry.
	 *
	 * @return the payload of this commerce payment entry
	 */
	@AutoEscape
	public String getPayload();

	/**
	 * Sets the payload of this commerce payment entry.
	 *
	 * @param payload the payload of this commerce payment entry
	 */
	public void setPayload(String payload);

	/**
	 * Returns the payment integration key of this commerce payment entry.
	 *
	 * @return the payment integration key of this commerce payment entry
	 */
	@AutoEscape
	public String getPaymentIntegrationKey();

	/**
	 * Sets the payment integration key of this commerce payment entry.
	 *
	 * @param paymentIntegrationKey the payment integration key of this commerce payment entry
	 */
	public void setPaymentIntegrationKey(String paymentIntegrationKey);

	/**
	 * Returns the payment integration type of this commerce payment entry.
	 *
	 * @return the payment integration type of this commerce payment entry
	 */
	public int getPaymentIntegrationType();

	/**
	 * Sets the payment integration type of this commerce payment entry.
	 *
	 * @param paymentIntegrationType the payment integration type of this commerce payment entry
	 */
	public void setPaymentIntegrationType(int paymentIntegrationType);

	/**
	 * Returns the payment status of this commerce payment entry.
	 *
	 * @return the payment status of this commerce payment entry
	 */
	public int getPaymentStatus();

	/**
	 * Sets the payment status of this commerce payment entry.
	 *
	 * @param paymentStatus the payment status of this commerce payment entry
	 */
	public void setPaymentStatus(int paymentStatus);

	/**
	 * Returns the reason key of this commerce payment entry.
	 *
	 * @return the reason key of this commerce payment entry
	 */
	@AutoEscape
	public String getReasonKey();

	/**
	 * Sets the reason key of this commerce payment entry.
	 *
	 * @param reasonKey the reason key of this commerce payment entry
	 */
	public void setReasonKey(String reasonKey);

	/**
	 * Returns the reason name of this commerce payment entry.
	 *
	 * @return the reason name of this commerce payment entry
	 */
	public String getReasonName();

	/**
	 * Returns the localized reason name of this commerce payment entry in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized reason name of this commerce payment entry
	 */
	@AutoEscape
	public String getReasonName(Locale locale);

	/**
	 * Returns the localized reason name of this commerce payment entry in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized reason name of this commerce payment entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getReasonName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized reason name of this commerce payment entry in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized reason name of this commerce payment entry
	 */
	@AutoEscape
	public String getReasonName(String languageId);

	/**
	 * Returns the localized reason name of this commerce payment entry in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized reason name of this commerce payment entry
	 */
	@AutoEscape
	public String getReasonName(String languageId, boolean useDefault);

	@AutoEscape
	public String getReasonNameCurrentLanguageId();

	@AutoEscape
	public String getReasonNameCurrentValue();

	/**
	 * Returns a map of the locales and localized reason names of this commerce payment entry.
	 *
	 * @return the locales and localized reason names of this commerce payment entry
	 */
	public Map<Locale, String> getReasonNameMap();

	/**
	 * Sets the reason name of this commerce payment entry.
	 *
	 * @param reasonName the reason name of this commerce payment entry
	 */
	public void setReasonName(String reasonName);

	/**
	 * Sets the localized reason name of this commerce payment entry in the language.
	 *
	 * @param reasonName the localized reason name of this commerce payment entry
	 * @param locale the locale of the language
	 */
	public void setReasonName(String reasonName, Locale locale);

	/**
	 * Sets the localized reason name of this commerce payment entry in the language, and sets the default locale.
	 *
	 * @param reasonName the localized reason name of this commerce payment entry
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setReasonName(
		String reasonName, Locale locale, Locale defaultLocale);

	public void setReasonNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized reason names of this commerce payment entry from the map of locales and localized reason names.
	 *
	 * @param reasonNameMap the locales and localized reason names of this commerce payment entry
	 */
	public void setReasonNameMap(Map<Locale, String> reasonNameMap);

	/**
	 * Sets the localized reason names of this commerce payment entry from the map of locales and localized reason names, and sets the default locale.
	 *
	 * @param reasonNameMap the locales and localized reason names of this commerce payment entry
	 * @param defaultLocale the default locale
	 */
	public void setReasonNameMap(
		Map<Locale, String> reasonNameMap, Locale defaultLocale);

	/**
	 * Returns the redirect url of this commerce payment entry.
	 *
	 * @return the redirect url of this commerce payment entry
	 */
	@AutoEscape
	public String getRedirectURL();

	/**
	 * Sets the redirect url of this commerce payment entry.
	 *
	 * @param redirectURL the redirect url of this commerce payment entry
	 */
	public void setRedirectURL(String redirectURL);

	/**
	 * Returns the transaction code of this commerce payment entry.
	 *
	 * @return the transaction code of this commerce payment entry
	 */
	@AutoEscape
	public String getTransactionCode();

	/**
	 * Sets the transaction code of this commerce payment entry.
	 *
	 * @param transactionCode the transaction code of this commerce payment entry
	 */
	public void setTransactionCode(String transactionCode);

	/**
	 * Returns the type of this commerce payment entry.
	 *
	 * @return the type of this commerce payment entry
	 */
	public int getType();

	/**
	 * Sets the type of this commerce payment entry.
	 *
	 * @param type the type of this commerce payment entry
	 */
	public void setType(int type);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public CommercePaymentEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}