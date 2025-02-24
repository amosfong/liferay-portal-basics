/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.text.localizer.taglib.internal.address;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.AddressWrapper;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.CountryWrapper;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.RegionWrapper;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.text.localizer.address.AddressTextLocalizer;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Yasuyuki Takeo
 */
public class JPAddressTextLocalizerTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_address = _createAddress();
	}

	@Test
	public void testAllFields() {
		_setCity(_CITY);
		_setCountry(_COUNTRY_NAME);
		_setRegion(_REGION_NAME);
		_setStreets(_STREET_1, _STREET_2, _STREET_3);
		_setZip(_ZIP);

		Assert.assertEquals(
			StringBundler.concat(
				_COUNTRY_NAME, StringPool.NEW_LINE, _ZIP, StringPool.SPACE,
				_REGION_NAME, StringPool.SPACE, _CITY, StringPool.SPACE,
				_STREET_1, StringPool.NEW_LINE, _STREET_2, StringPool.SPACE,
				_STREET_3),
			_addressTextLocalizer.format(_address));
	}

	@Test
	public void testCountryLine() {
		_setCountry(_COUNTRY_NAME);

		Assert.assertEquals(
			_COUNTRY_NAME, _addressTextLocalizer.format(_address));
	}

	@Test
	public void testRegionLineWithCity() {
		_setCity(_CITY);

		Assert.assertEquals(_CITY, _addressTextLocalizer.format(_address));
	}

	@Test
	public void testRegionLineWithCityAndRegionName() {
		_setCity(_CITY);
		_setRegion(_REGION_NAME);

		Assert.assertEquals(
			StringBundler.concat(_REGION_NAME, StringPool.SPACE, _CITY),
			_addressTextLocalizer.format(_address));
	}

	@Test
	public void testRegionLineWithCityAndRegionNameAndZip() {
		_setCity(_CITY);
		_setRegion(_REGION_NAME);
		_setZip(_ZIP);

		Assert.assertEquals(
			StringBundler.concat(
				_ZIP, StringPool.SPACE, _REGION_NAME, StringPool.SPACE, _CITY),
			_addressTextLocalizer.format(_address));
	}

	@Test
	public void testRegionLineWithCityAndZip() {
		_setCity(_CITY);
		_setZip(_ZIP);

		Assert.assertEquals(
			StringBundler.concat(_ZIP, StringPool.SPACE, _CITY),
			_addressTextLocalizer.format(_address));
	}

	@Test
	public void testRegionLineWithRegionName() {
		_setRegion(_REGION_NAME);

		Assert.assertEquals(
			_REGION_NAME, _addressTextLocalizer.format(_address));
	}

	@Test
	public void testRegionLineWithRegionNameAndZip() {
		_setRegion(_REGION_NAME);
		_setZip(_ZIP);

		Assert.assertEquals(
			StringBundler.concat(_ZIP, StringPool.SPACE, _REGION_NAME),
			_addressTextLocalizer.format(_address));
	}

	@Test
	public void testRegionLineWithZip() {
		_setZip(_ZIP);

		Assert.assertEquals(_ZIP, _addressTextLocalizer.format(_address));
	}

	@Test
	public void testStreetAndCountryLines() {
		_setCountry(_COUNTRY_NAME);
		_setStreets(_STREET_1, _STREET_2, _STREET_3);

		Assert.assertEquals(
			StringBundler.concat(
				_COUNTRY_NAME, StringPool.NEW_LINE, _STREET_1,
				StringPool.NEW_LINE, _STREET_2, StringPool.SPACE, _STREET_3),
			_addressTextLocalizer.format(_address));
	}

	@Test
	public void testStreetLines() {
		_setStreets(_STREET_1, _STREET_2, _STREET_3);

		Assert.assertEquals(
			StringBundler.concat(
				_STREET_1, StringPool.NEW_LINE, _STREET_2, StringPool.SPACE,
				_STREET_3),
			_addressTextLocalizer.format(_address));
	}

	@Test
	public void testWillEscapeFields() {
		String unescapedValue =
			"<script type=\"text/javascript\">alert(\"Hello World\");</script>";

		_setCity(unescapedValue);
		_setStreets(unescapedValue, unescapedValue, unescapedValue);
		_setZip(unescapedValue);

		_setCountry(unescapedValue);
		_setRegion(unescapedValue);

		String escapedValue = HtmlUtil.escape(unescapedValue);

		Assert.assertEquals(
			StringBundler.concat(
				escapedValue, StringPool.NEW_LINE, escapedValue,
				StringPool.SPACE, escapedValue, StringPool.SPACE, escapedValue,
				StringPool.SPACE, escapedValue, StringPool.NEW_LINE,
				escapedValue, StringPool.SPACE, escapedValue),
			_addressTextLocalizer.format(_address));
	}

	private Address _createAddress() {
		return new AddressWrapper(null) {

			@Override
			public Country getCountry() {
				return _country;
			}

			@Override
			public Region getRegion() {
				return _region;
			}

			@Override
			public Address toEscapedModel() {
				return new AddressWrapper(null) {

					@Override
					public String getCity() {
						return HtmlUtil.escape(_city);
					}

					@Override
					public String getStreet1() {
						return HtmlUtil.escape(_street1);
					}

					@Override
					public String getStreet2() {
						return HtmlUtil.escape(_street2);
					}

					@Override
					public String getStreet3() {
						return HtmlUtil.escape(_street3);
					}

					@Override
					public String getZip() {
						return HtmlUtil.escape(_zip);
					}

				};
			}

		};
	}

	private AddressTextLocalizer _createAddressTextLocalizer() {
		return new JPAddressTextLocalizer();
	}

	private void _setCity(String city) {
		_city = city;
	}

	private void _setCountry(String countryName) {
		_country = new CountryWrapper(null) {

			@Override
			public String getTitle(Locale locale) {
				return countryName;
			}

			@Override
			public boolean isNew() {
				return false;
			}

		};
	}

	private void _setRegion(String regionName) {
		_region = new RegionWrapper(null) {

			@Override
			public String getName() {
				return regionName;
			}

			@Override
			public boolean isNew() {
				return false;
			}

		};
	}

	private void _setStreets(String street1, String street2, String street3) {
		_street1 = street1;
		_street2 = street2;
		_street3 = street3;
	}

	private void _setZip(String zip) {
		_zip = zip;
	}

	private static final String _CITY = "渋谷区";

	private static final String _COUNTRY_NAME = "日本";

	private static final String _REGION_NAME = "東京都";

	private static final String _STREET_1 = "恵比寿西1丁目26番7号";

	private static final String _STREET_2 = "ファブールエビス";

	private static final String _STREET_3 = "1階 101";

	private static final String _ZIP = "〒 150-0021";

	private Address _address;
	private final AddressTextLocalizer _addressTextLocalizer =
		_createAddressTextLocalizer();
	private String _city;
	private Country _country;
	private Region _region;
	private String _street1;
	private String _street2;
	private String _street3;
	private String _zip;

}