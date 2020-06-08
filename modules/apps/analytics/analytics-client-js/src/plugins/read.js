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

import {getNumberOfWords} from '../utils/assets';
import {
	CHARS_PER_MIN,
	DEBOUNCE,
	LOGOGRAPHIC_LANGUAGES,
	MIMIMUN_SCROLL_DEPTH,
	READ_TIME_FACTOR,
	WORDS_PER_MIN,
} from '../utils/constants';
import {debounce} from '../utils/debounce';
import {onReady} from '../utils/events';
import {ReadTracker} from '../utils/read';
import {ScrollTracker} from '../utils/scroll';

const applicationId = 'Page';

const MIN_TO_MS = 60000;

function getAssetsElements() {
	return document.querySelectorAll(
		'[data-analytics-asset-type="web-content"], [data-analytics-asset-type="blog"]'
	);
}

function isTrackable(element) {
	return element && 'analyticsAssetId' in element.dataset;
}

function getLang() {
	return document.documentElement.lang;
}

function viewDurationByCharacters(content) {
	return (
		(content.replace(/\s+/gm, '').length / CHARS_PER_MIN) *
		MIN_TO_MS *
		READ_TIME_FACTOR
	);
}

function viewDurationByWords(content) {
	return (
		(getNumberOfWords({innerText: content}) / WORDS_PER_MIN) *
		MIN_TO_MS *
		READ_TIME_FACTOR
	);
}

function getExpectedViewDuration(content) {
	const language = getLang();

	if (LOGOGRAPHIC_LANGUAGES.has(language)) {
		return viewDurationByCharacters(content);
	}

	return viewDurationByWords(content);
}

function read(analytics) {
	const readTracker = new ReadTracker();
	let scrollTracker = new ScrollTracker();

	let content = '';

	const stopTrackingOnReady = onReady(() => {
		const assetsElements = getAssetsElements();

		Array.prototype.slice
			.call(assetsElements)
			.filter((element) => isTrackable(element))
			.forEach(({innerText}) => {
				content = content.concat(innerText);
			});

		readTracker.setExpectedViewDuration(
			() => analytics.send('pageRead', applicationId),
			getExpectedViewDuration(content)
		);
	});

	const onScroll = debounce(() => {
		scrollTracker.onDepthReached((depth) => {
			if (depth >= MIMIMUN_SCROLL_DEPTH) {
				readTracker.onDepthReached(() => {
					analytics.send('pageRead', applicationId);
				});
			}
		});
	}, DEBOUNCE);

	document.addEventListener('scroll', onScroll);

	return () => {
		stopTrackingOnReady();
		document.removeEventListener('scroll', onScroll);
		readTracker.dispose();
		scrollTracker = new ScrollTracker();
	};
}

export {read};
export default read;
