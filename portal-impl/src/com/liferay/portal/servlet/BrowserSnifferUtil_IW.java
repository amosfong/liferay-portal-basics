/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet;

/**
 * @author Brian Wing Shun Chan
 */
public class BrowserSnifferUtil_IW {
	public static BrowserSnifferUtil_IW getInstance() {
		return _instance;
	}

	public boolean acceptsGzip(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.acceptsGzip(httpServletRequest);
	}

	public java.lang.String getAccept(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.getAccept(httpServletRequest);
	}

	public java.lang.String getBrowserId(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.getBrowserId(httpServletRequest);
	}

	public com.liferay.portal.kernel.servlet.BrowserMetadata getBrowserMetadata(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.getBrowserMetadata(httpServletRequest);
	}

	public float getMajorVersion(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.getMajorVersion(httpServletRequest);
	}

	public java.lang.String getRevision(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.getRevision(httpServletRequest);
	}

	public java.lang.String getVersion(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.getVersion(httpServletRequest);
	}

	public boolean isAir(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isAir(httpServletRequest);
	}

	public boolean isAndroid(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isAndroid(httpServletRequest);
	}

	public boolean isChrome(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isChrome(httpServletRequest);
	}

	public boolean isEdge(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isEdge(httpServletRequest);
	}

	public boolean isFirefox(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isFirefox(httpServletRequest);
	}

	public boolean isGecko(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isGecko(httpServletRequest);
	}

	public boolean isIe(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isIe(httpServletRequest);
	}

	public boolean isIeOnWin32(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isIeOnWin32(httpServletRequest);
	}

	public boolean isIeOnWin64(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isIeOnWin64(httpServletRequest);
	}

	public boolean isIphone(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isIphone(httpServletRequest);
	}

	public boolean isLinux(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isLinux(httpServletRequest);
	}

	public boolean isMac(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isMac(httpServletRequest);
	}

	public boolean isMobile(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isMobile(httpServletRequest);
	}

	public boolean isMozilla(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isMozilla(httpServletRequest);
	}

	public boolean isOpera(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isOpera(httpServletRequest);
	}

	public boolean isRtf(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isRtf(httpServletRequest);
	}

	public boolean isSafari(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isSafari(httpServletRequest);
	}

	public boolean isSun(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isSun(httpServletRequest);
	}

	public boolean isWebKit(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isWebKit(httpServletRequest);
	}

	public boolean isWindows(
		javax.servlet.http.HttpServletRequest httpServletRequest) {
		return BrowserSnifferUtil.isWindows(httpServletRequest);
	}

	public java.lang.String parseVersion(java.lang.String userAgent,
		java.lang.String[] leadings, char[] separators) {
		return BrowserSnifferUtil.parseVersion(userAgent, leadings, separators);
	}

	private BrowserSnifferUtil_IW() {
	}

	private static BrowserSnifferUtil_IW _instance = new BrowserSnifferUtil_IW();
}