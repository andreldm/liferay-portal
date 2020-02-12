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

package com.liferay.analytics.settings.web.internal.display.context;

import com.liferay.analytics.settings.configuration.AnalyticsConfiguration;
import com.liferay.analytics.settings.web.internal.constants.AnalyticsSettingsWebKeys;
import com.liferay.analytics.settings.web.internal.model.Channel;
import com.liferay.analytics.settings.web.internal.search.ChannelSearch;
import java.util.Arrays;
import java.util.List;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Marcellus Tavares
 */
public class ChannelDisplayContext {

	public ChannelDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_analyticsConfiguration =
			(AnalyticsConfiguration)renderRequest.getAttribute(
				AnalyticsSettingsWebKeys.ANALYTICS_CONFIGURATION);
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
	}

	public ChannelSearch getChannelSearch() {
		ChannelSearch channelSearch = new ChannelSearch(
			_renderRequest, getPortletURL());

		List<Channel> channels = Arrays.asList(
			new Channel(123L, "b"), new Channel(123L, "b"),
			new Channel(123L, "b"), new Channel(123L, "b"),
			new Channel(123L, "b"), new Channel(123L, "b"),
			new Channel(123L, "b"), new Channel(123L, "b"),
			new Channel(123L, "b"), new Channel(123L, "b"));

		channelSearch.setResults(channels);

		channelSearch.setTotal(100);

		// should return null when there is no properties. It renders an empty state

		return null;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/view_configuration_screen");
		portletURL.setParameter("configurationScreenKey", "synced-sites");

		return portletURL;
	}

	private final AnalyticsConfiguration _analyticsConfiguration;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}