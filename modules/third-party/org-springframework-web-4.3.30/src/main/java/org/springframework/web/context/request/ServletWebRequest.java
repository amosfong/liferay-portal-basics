/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.context.request;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ETag;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

/**
 * {@link WebRequest} adapter for an {@link javax.servlet.http.HttpServletRequest}.
 *
 * @author Juergen Hoeller
 * @author Brian Clozel
 * @author Markus Malkusch
 * @since 2.0
 */
public class ServletWebRequest extends ServletRequestAttributes implements NativeWebRequest {

	private static final String ETAG = "ETag";

	private static final String IF_MODIFIED_SINCE = "If-Modified-Since";

	private static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";

	private static final String IF_NONE_MATCH = "If-None-Match";

	private static final String LAST_MODIFIED = "Last-Modified";

	private static final List<String> SAFE_METHODS = Arrays.asList("GET", "HEAD");

	/**
	 * Date formats as specified in the HTTP RFC.
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-7.1.1.1">Section 7.1.1.1 of RFC 7231</a>
	 */
	private static final String[] DATE_FORMATS = new String[] {
			"EEE, dd MMM yyyy HH:mm:ss zzz",
			"EEE, dd-MMM-yy HH:mm:ss zzz",
			"EEE MMM dd HH:mm:ss yyyy"
	};

	private static final TimeZone GMT = TimeZone.getTimeZone("GMT");

	/** Checking for Servlet 3.0+ HttpServletResponse.getHeader(String) */
	private static final boolean servlet3Present =
			ClassUtils.hasMethod(HttpServletResponse.class, "getHeader", String.class);

	private boolean notModified = false;


	/**
	 * Create a new ServletWebRequest instance for the given request.
	 * @param request current HTTP request
	 */
	public ServletWebRequest(HttpServletRequest request) {
		super(request);
	}

	/**
	 * Create a new ServletWebRequest instance for the given request/response pair.
	 * @param request current HTTP request
	 * @param response current HTTP response (for automatic last-modified handling)
	 */
	public ServletWebRequest(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}


	@Override
	public Object getNativeRequest() {
		return getRequest();
	}

	@Override
	public Object getNativeResponse() {
		return getResponse();
	}

	@Override
	public <T> T getNativeRequest(Class<T> requiredType) {
		return WebUtils.getNativeRequest(getRequest(), requiredType);
	}

	@Override
	public <T> T getNativeResponse(Class<T> requiredType) {
		return WebUtils.getNativeResponse(getResponse(), requiredType);
	}

	/**
	 * Return the HTTP method of the request.
	 * @since 4.0.2
	 */
	public HttpMethod getHttpMethod() {
		return HttpMethod.resolve(getRequest().getMethod());
	}

	@Override
	public String getHeader(String headerName) {
		return getRequest().getHeader(headerName);
	}

	@Override
	public String[] getHeaderValues(String headerName) {
		String[] headerValues = StringUtils.toStringArray(getRequest().getHeaders(headerName));
		return (!ObjectUtils.isEmpty(headerValues) ? headerValues : null);
	}

	@Override
	public Iterator<String> getHeaderNames() {
		return CollectionUtils.toIterator(getRequest().getHeaderNames());
	}

	@Override
	public String getParameter(String paramName) {
		return getRequest().getParameter(paramName);
	}

	@Override
	public String[] getParameterValues(String paramName) {
		return getRequest().getParameterValues(paramName);
	}

	@Override
	public Iterator<String> getParameterNames() {
		return CollectionUtils.toIterator(getRequest().getParameterNames());
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return getRequest().getParameterMap();
	}

	@Override
	public Locale getLocale() {
		return getRequest().getLocale();
	}

	@Override
	public String getContextPath() {
		return getRequest().getContextPath();
	}

	@Override
	public String getRemoteUser() {
		return getRequest().getRemoteUser();
	}

	@Override
	public Principal getUserPrincipal() {
		return getRequest().getUserPrincipal();
	}

	@Override
	public boolean isUserInRole(String role) {
		return getRequest().isUserInRole(role);
	}

	@Override
	public boolean isSecure() {
		return getRequest().isSecure();
	}


	@Override
	public boolean checkNotModified(long lastModifiedTimestamp) {
		return checkNotModified(null, lastModifiedTimestamp);
	}

	@Override
	public boolean checkNotModified(String etag) {
		return checkNotModified(etag, -1);
	}

	@Override
	public boolean checkNotModified(String etag, long lastModifiedTimestamp) {
		HttpServletResponse response = getResponse();
		if (this.notModified || !isStatusOK(response)) {
			return this.notModified;
		}

		// Evaluate conditions in order of precedence.
		// See https://tools.ietf.org/html/rfc7232#section-6

		if (validateIfUnmodifiedSince(lastModifiedTimestamp)) {
			if (this.notModified) {
				response.setStatus(HttpStatus.PRECONDITION_FAILED.value());
			}
			return this.notModified;
		}

		boolean validated = validateIfNoneMatch(etag);
		if (!validated) {
			validateIfModifiedSince(lastModifiedTimestamp);
		}

		// Update response
		boolean isHttpGetOrHead = SAFE_METHODS.contains(getRequest().getMethod());
		if (this.notModified) {
			response.setStatus(isHttpGetOrHead ?
					HttpStatus.NOT_MODIFIED.value() : HttpStatus.PRECONDITION_FAILED.value());
		}
		if (isHttpGetOrHead) {
			if(lastModifiedTimestamp > 0 && isHeaderAbsent(response, LAST_MODIFIED)) {
				response.setDateHeader(LAST_MODIFIED, lastModifiedTimestamp);
			}
			if (StringUtils.hasLength(etag) && isHeaderAbsent(response, ETAG)) {
				response.setHeader(ETAG, padEtagIfNecessary(etag));
			}
		}

		return this.notModified;
	}

	private boolean isStatusOK(HttpServletResponse response) {
		if (response == null || !servlet3Present) {
			// Can't check response.getStatus() - let's assume we're good
			return true;
		}
		return response.getStatus() == 200;
	}

	private boolean isHeaderAbsent(HttpServletResponse response, String header) {
		if (response == null || !servlet3Present) {
			// Can't check response.getHeader(header) - let's assume it's not set
			return true;
		}
		return (response.getHeader(header) == null);
	}

	private boolean validateIfUnmodifiedSince(long lastModifiedTimestamp) {
		if (lastModifiedTimestamp < 0) {
			return false;
		}
		long ifUnmodifiedSince = parseDateHeader(IF_UNMODIFIED_SINCE);
		if (ifUnmodifiedSince == -1) {
			return false;
		}
		// We will perform this validation...
		this.notModified = (ifUnmodifiedSince < (lastModifiedTimestamp / 1000 * 1000));
		return true;
	}

	private boolean validateIfNoneMatch(String etag) {
		if (!StringUtils.hasLength(etag)) {
			return false;
		}

		Enumeration<String> ifNoneMatch;
		try {
			ifNoneMatch = getRequest().getHeaders(IF_NONE_MATCH);
		}
		catch (IllegalArgumentException ex) {
			return false;
		}
		if (!ifNoneMatch.hasMoreElements()) {
			return false;
		}

		// We will perform this validation...
		etag = padEtagIfNecessary(etag);
		while (ifNoneMatch.hasMoreElements()) {
			// Compare weak/strong ETags as per https://tools.ietf.org/html/rfc7232#section-2.3
			for (ETag requestedETag : ETag.parse(ifNoneMatch.nextElement())) {
				String tag = requestedETag.tag();
				if (StringUtils.hasLength(tag) && etag.equals(padEtagIfNecessary(tag))) {
					this.notModified = true;
					break;
				}
			}
		}

		return true;
	}

	private String padEtagIfNecessary(String etag) {
		if (!StringUtils.hasLength(etag)) {
			return etag;
		}
		if ((etag.startsWith("\"") || etag.startsWith("W/\"")) && etag.endsWith("\"")) {
			return etag;
		}
		return "\"" + etag + "\"";
	}

	private boolean validateIfModifiedSince(long lastModifiedTimestamp) {
		if (lastModifiedTimestamp < 0) {
			return false;
		}
		long ifModifiedSince = parseDateHeader(IF_MODIFIED_SINCE);
		if (ifModifiedSince == -1) {
			return false;
		}
		// We will perform this validation...
		this.notModified = ifModifiedSince >= (lastModifiedTimestamp / 1000 * 1000);
		return true;
	}

	public boolean isNotModified() {
		return this.notModified;
	}

	private long parseDateHeader(String headerName) {
		long dateValue = -1;
		try {
			dateValue = getRequest().getDateHeader(headerName);
		}
		catch (IllegalArgumentException ex) {
			String headerValue = getHeader(headerName);
			// Possibly an IE 10 style value: "Wed, 09 Apr 2014 09:57:42 GMT; length=13774"
			int separatorIndex = headerValue.indexOf(';');
			if (separatorIndex != -1) {
				String datePart = headerValue.substring(0, separatorIndex);
				dateValue = parseDateValue(datePart);
			}
		}
		return dateValue;
	}

	private long parseDateValue(String headerValue) {
		if (headerValue == null) {
			// No header value sent at all
			return -1;
		}
		if (headerValue.length() >= 3) {
			// Short "0" or "-1" like values are never valid HTTP date headers...
			// Let's only bother with SimpleDateFormat parsing for long enough values.
			for (String dateFormat : DATE_FORMATS) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
				simpleDateFormat.setTimeZone(GMT);
				try {
					return simpleDateFormat.parse(headerValue).getTime();
				}
				catch (ParseException ex) {
					// ignore
				}
			}
		}
		return -1;
	}

	@Override
	public String getDescription(boolean includeClientInfo) {
		HttpServletRequest request = getRequest();
		StringBuilder sb = new StringBuilder();
		sb.append("uri=").append(request.getRequestURI());
		if (includeClientInfo) {
			String client = request.getRemoteAddr();
			if (StringUtils.hasLength(client)) {
				sb.append(";client=").append(client);
			}
			HttpSession session = request.getSession(false);
			if (session != null) {
				sb.append(";session=").append(session.getId());
			}
			String user = request.getRemoteUser();
			if (StringUtils.hasLength(user)) {
				sb.append(";user=").append(user);
			}
		}
		return sb.toString();
	}


	@Override
	public String toString() {
		return "ServletWebRequest: " + getDescription(true);
	}

}
/* @generated */