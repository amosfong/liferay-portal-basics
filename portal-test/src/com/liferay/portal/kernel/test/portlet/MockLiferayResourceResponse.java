/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.test.portlet;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.servlet.URLEncoder;
import com.liferay.portlet.test.MockActionURL;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionURL;
import javax.portlet.CacheControl;
import javax.portlet.PortletURL;
import javax.portlet.RenderURL;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletResponse;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/**
 * @author Cristina González
 */
public class MockLiferayResourceResponse
	implements LiferayPortletResponse, ResourceResponse {

	@Override
	public void addDateHeader(String name, long date) {
	}

	@Override
	public void addHeader(String name, String value) {
	}

	@Override
	public void addIntHeader(String name, int value) {
	}

	@Override
	public void addProperty(Cookie cookie) {
	}

	@Override
	public void addProperty(String name, Element element) {
	}

	@Override
	public void addProperty(String name, String value) {
	}

	@Override
	public <T extends PortletURL & ActionURL> T createActionURL() {
		return (T)new MockActionURL();
	}

	@Override
	public ActionURL createActionURL(Copy copy) {
		return new MockActionURL();
	}

	@Override
	public LiferayPortletURL createActionURL(String portletName) {
		return new MockLiferayPortletURL();
	}

	@Override
	public LiferayPortletURL createActionURL(String portletName, Copy copy) {
		return new MockLiferayPortletURL();
	}

	@Override
	public Element createElement(String tagName) throws DOMException {
		return null;
	}

	@Override
	public LiferayPortletURL createLiferayPortletURL(
		long plid, String portletName, String lifecycle) {

		return new MockLiferayPortletURL();
	}

	@Override
	public LiferayPortletURL createLiferayPortletURL(
		long plid, String portletName, String lifecycle,
		boolean includeLinkToLayoutUuid) {

		return new MockLiferayPortletURL();
	}

	@Override
	public LiferayPortletURL createLiferayPortletURL(
		long plid, String portletName, String lifecycle, Copy copy) {

		return new MockLiferayPortletURL();
	}

	@Override
	public LiferayPortletURL createLiferayPortletURL(
		long plid, String portletName, String lifecycle, Copy copy,
		boolean includeLinkToLayoutUuid) {

		return new MockLiferayPortletURL();
	}

	@Override
	public LiferayPortletURL createLiferayPortletURL(String lifecycle) {
		return new MockLiferayPortletURL();
	}

	@Override
	public LiferayPortletURL createLiferayPortletURL(
		String portletName, String lifecycle) {

		return new MockLiferayPortletURL();
	}

	@Override
	public LiferayPortletURL createLiferayPortletURL(
		String portletName, String lifecycle, Copy copy) {

		return new MockLiferayPortletURL();
	}

	@Override
	public <T extends PortletURL & RenderURL> T createRenderURL() {
		MockLiferayPortletURL liferayPortletURL = new MockLiferayPortletURL();

		return (T)liferayPortletURL;
	}

	@Override
	public RenderURL createRenderURL(Copy copy) {
		return null;
	}

	@Override
	public LiferayPortletURL createRenderURL(String portletName) {
		return new MockLiferayPortletURL();
	}

	@Override
	public LiferayPortletURL createRenderURL(String portletName, Copy copy) {
		return new MockLiferayPortletURL();
	}

	@Override
	public ResourceURL createResourceURL() {
		return new MockLiferayPortletURL();
	}

	@Override
	public LiferayPortletURL createResourceURL(String portletName) {
		return new MockLiferayPortletURL();
	}

	@Override
	public String encodeURL(String url) {
		return null;
	}

	@Override
	public void flushBuffer() throws IOException {
	}

	@Override
	public int getBufferSize() {
		return 0;
	}

	@Override
	public CacheControl getCacheControl() {
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public HttpServletResponse getHttpServletResponse() {
		return _mockHttpServletResponse;
	}

	@Override
	public String getLifecycle() {
		return null;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public String getNamespace() {
		return null;
	}

	@Override
	public Portlet getPortlet() {
		return null;
	}

	@Override
	public OutputStream getPortletOutputStream() throws IOException {
		return _byteArrayOutputStream;
	}

	@Override
	public Map<String, String[]> getProperties() {
		return null;
	}

	@Override
	public String getProperty(String name) {
		return null;
	}

	@Override
	public Collection<String> getPropertyNames() {
		return null;
	}

	@Override
	public Collection<String> getPropertyValues(String key) {
		return null;
	}

	@Override
	public int getStatus() {
		return 0;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return null;
	}

	@Override
	public boolean isCommitted() {
		return false;
	}

	@Override
	public void reset() {
	}

	@Override
	public void resetBuffer() {
	}

	@Override
	public void setBufferSize(int bufferSize) {
	}

	@Override
	public void setCharacterEncoding(String characterEncoding) {
	}

	@Override
	public void setContentLength(int contentLength) {
	}

	@Override
	public void setContentLengthLong(long contentLength) {
	}

	@Override
	public void setContentType(String contentType) {
	}

	@Override
	public void setDateHeader(String name, long date) {
	}

	@Override
	public void setHeader(String name, String value) {
	}

	@Override
	public void setIntHeader(String name, int value) {
	}

	@Override
	public void setLocale(Locale locale) {
	}

	@Override
	public void setProperty(String property, String value) {
	}

	@Override
	public void setStatus(int status) {
	}

	@Override
	public void setURLEncoder(URLEncoder urlEncoder) {
	}

	@Override
	public void transferHeaders(HttpServletResponse httpServletResponse) {
	}

	@Override
	public void transferMarkupHeadElements() {
	}

	private final ByteArrayOutputStream _byteArrayOutputStream =
		new ByteArrayOutputStream();
	private final MockHttpServletResponse _mockHttpServletResponse =
		new MockHttpServletResponse();

}