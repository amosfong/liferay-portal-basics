/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	getNumberOfWords,
	isTrackable,
	transformAssetTypeToSelector,
} from '../utils/assets';
import {BLOG, DEBOUNCE} from '../utils/constants';
import {debounce} from '../utils/debounce';
import {clickEvent, onReady} from '../utils/events';
import {ScrollTracker} from '../utils/scroll';

const applicationId = BLOG;

export const blogTypes = ['blog', 'com.liferay.blogs.model.BlogsEntry'];

/**
 * Returns analytics payload with Blog information.
 * @param {Object} blog The blog DOM element
 * @returns {Object} The payload with blog information
 */
function getBlogPayload({dataset}) {
	const payload = {
		entryId: dataset.analyticsAssetId.trim(),
	};

	if (dataset.analyticsAssetSubtype) {
		Object.assign(payload, {subtype: dataset.analyticsAssetSubtype.trim()});
	}

	if (dataset.analyticsAssetTitle) {
		Object.assign(payload, {title: dataset.analyticsAssetTitle.trim()});
	}

	if (dataset.analyticsAssetType) {
		Object.assign(payload, {type: dataset.analyticsAssetType.trim()});
	}

	return payload;
}

/**
 * Sends information about Blogs scroll actions.
 * @param {Object} The Analytics client instance
 */
function trackBlogsScroll(analytics, blogElements) {
	const scrollSessionId = new Date().toISOString();
	const scrollTracker = new ScrollTracker();

	const onScroll = debounce(() => {
		blogElements.forEach((element) => {
			scrollTracker.onDepthReached((depth) => {
				const payload = getBlogPayload(element);
				Object.assign(payload, {depth, sessionId: scrollSessionId});

				analytics.send('blogDepthReached', applicationId, payload);
			}, element);
		});
	}, DEBOUNCE);

	document.addEventListener('scroll', onScroll);

	return () => {
		document.removeEventListener('scroll', onScroll);
	};
}

/**
 * Sends information when user scrolls on a Blog.
 * @param {Object} The Analytics client instance
 */
function trackBlog(analytics, {eventId, isTrackable}) {
	const blogElements = [];

	const selector = transformAssetTypeToSelector(blogTypes);

	const stopTrackingOnReady = onReady(() => {
		Array.prototype.slice
			.call(document.querySelectorAll(selector))
			.filter(isTrackable)
			.forEach((element) => {
				const payload = getBlogPayload(element);
				Object.assign(payload, {
					numberOfWords: getNumberOfWords(element),
				});

				blogElements.push(element);

				analytics.send(eventId, applicationId, payload);
			});
	});

	const stopTrackingBlogsScroll = trackBlogsScroll(analytics, blogElements);

	return () => {
		stopTrackingBlogsScroll();
		stopTrackingOnReady();
	};
}

/**
 * Sends information when user clicks on a Blog.
 * @param {Object} The Analytics client instance
 */
function trackBlogClicked(analytics) {
	return clickEvent({
		analytics,
		applicationId,
		eventType: 'blogClicked',
		getPayload: getBlogPayload,
		isTrackable,
		type: 'blog',
	});
}

/**
 * Plugin function that registers listeners for Blog events
 * @param {Object} analytics The Analytics client
 */
function blogs(analytics) {
	const stopTrackingBlogClicked = trackBlogClicked(analytics);
	const stopTrackingBlogImpressionMade = trackBlog(analytics, {
		eventId: 'blogImpressionMade',
		isTrackable: (element) =>
			isTrackable(element) &&
			element.dataset?.analyticsAssetAction === 'impression',
	});
	const stopTrackingBlogViewed = trackBlog(analytics, {
		eventId: 'blogViewed',
		isTrackable: (element) =>
			isTrackable(element) &&
			(!element.dataset?.analyticsAssetAction ||
				element.dataset?.analyticsAssetAction === 'view'),
	});

	return () => {
		stopTrackingBlogClicked();
		stopTrackingBlogImpressionMade();
		stopTrackingBlogViewed();
	};
}

export {blogs};
export default blogs;
