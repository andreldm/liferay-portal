/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {
	MARK_LOAD_EVENT_START,
	MARK_NAVIGATION_START,
	MARK_PAGE_LOAD_TIME,
	MARK_VIEW_DURATION,
} from '../utils/constants';

const pageApplicationId = 'Page';

function getDuration(measureName, startMark, endMark = undefined) {
	window.performance.measure(measureName, startMark, endMark);

	const {duration} = window.performance.getEntriesByName(measureName).pop();

	return ~~duration;
}

/**
 * Plugin function that registers listeners related to DXP
 * @param {Object} analytics The Analytics client
 */

function dxp(analytics) {

	/**
	 * Sends view duration information on the window unload event
	 */
	function sendUnloadEvent() {
		const duration = getDuration(MARK_VIEW_DURATION, MARK_NAVIGATION_START);

		const props = {
			viewDuration: duration,
		};

		analytics.send('pageUnloaded', pageApplicationId, props);

		window.performance.mark(MARK_LOAD_EVENT_START);
		window.Liferay.detach('beforeNavigate', sendUnloadEvent);
	}

	/**
	 * Sends page load information on the endNavigate event when SPA is enabled on DXP
	 */
	function sendLoadEvent() {
		window.performance.mark(MARK_NAVIGATION_START);

		const loadingStartMark = window.performance.getEntriesByName(
			MARK_LOAD_EVENT_START
		);

		if (!loadingStartMark.length) {
			window.performance.mark(MARK_LOAD_EVENT_START);
		}

		const duration = getDuration(
			MARK_PAGE_LOAD_TIME,
			MARK_LOAD_EVENT_START,
			MARK_NAVIGATION_START
		);

		const props = {
			pageLoadTime: duration,
		};

		analytics.send('pageLoaded', pageApplicationId, props);
	}

	if (window.Liferay && window.Liferay.SPA) {
		window.performance.mark(MARK_NAVIGATION_START);

		window.Liferay.on('beforeNavigate', sendUnloadEvent);

		if (document.readyState === 'complete') {
			sendLoadEvent();
		}
	}
}

export {dxp};
export default dxp;
