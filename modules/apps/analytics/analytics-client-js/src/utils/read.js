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

function hasVerticalScrollBar() {
	return (
		document.documentElement.scrollHeight >
		document.documentElement.clientHeight
	);
}

class ReadTracker {
	constructor() {
		this.timeReached = false;
		this.depthReached = !hasVerticalScrollBar();
	}

	dispose() {
		this.timeReached = false;
		this.depthReached = !hasVerticalScrollBar();

		if (this.timeoutId) {
			clearTimeout(this.timeoutId);
		}
	}

	setExpectedViewDuration(fn, expectedViewDuration) {
		if (expectedViewDuration && !this.timeoutId) {
			this.timeoutId = setTimeout(
				() => this.onTimeReached(fn),
				Math.trunc(expectedViewDuration)
			);
		}
	}

	onDepthReached(fn) {
		this.depthReached = true;
		this.checkIsRead(fn);
	}

	onTimeReached(fn) {
		this.timeReached = true;
		this.checkIsRead(fn);
	}

	checkIsRead(fn) {
		if (this.timeReached && this.depthReached) {
			fn();
		}
	}
}

export {ReadTracker};
export default ReadTracker;
