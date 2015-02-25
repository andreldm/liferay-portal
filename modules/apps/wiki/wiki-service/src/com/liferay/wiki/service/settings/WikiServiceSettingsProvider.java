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

package com.liferay.wiki.service.settings;

import com.liferay.portal.kernel.settings.GroupServiceSettingsProvider;
import com.liferay.wiki.configuration.WikiGroupServiceConfiguration;
import com.liferay.wiki.settings.WikiGroupServiceSettings;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera
 */
@Component(
	immediate = true
)
public class WikiServiceSettingsProvider {

	public static WikiServiceSettingsProvider getWikiServiceSettingsProvider() {
		return _wikiServiceSettingsProvider;
	}

	public WikiGroupServiceConfiguration getWikiGroupServiceConfiguration() {
		return _wikiGroupServiceConfiguration;
	}

	public GroupServiceSettingsProvider<WikiGroupServiceSettings>
		getWikiGroupServiceSettingsProvider() {

		return _wikiGroupServiceSettingsProvider;
	}

	@Activate
	protected void activate() {
		_wikiServiceSettingsProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_wikiServiceSettingsProvider = null;
	}

	@Reference(
		target = "(class.name=com.liferay.wiki.settings.WikiGroupServiceSettings)"
	)
	protected void setWikiGroupServiceSettingsProvider(
		GroupServiceSettingsProvider<WikiGroupServiceSettings>
			wikiGroupServiceSettingsProvider) {

		_wikiGroupServiceSettingsProvider = wikiGroupServiceSettingsProvider;
	}

	@Reference
	protected void setWikiGroupServiceConfiguration(
		WikiGroupServiceConfiguration wikiGroupServiceConfiguration) {

		_wikiGroupServiceConfiguration = wikiGroupServiceConfiguration;
	}

	protected void unsetWikiGroupServiceSettingsProvider(
		GroupServiceSettingsProvider<WikiGroupServiceSettings>
			wikiGroupServiceSettingsProvider) {

		_wikiGroupServiceSettingsProvider = null;
	}

	protected void unsetWikiGroupServiceConfiguration
		(WikiGroupServiceConfiguration wikiGroupServiceConfiguration) {

		_wikiGroupServiceConfiguration = null;
	}

	private static WikiServiceSettingsProvider _wikiServiceSettingsProvider;

	private WikiGroupServiceConfiguration _wikiGroupServiceConfiguration;
	private GroupServiceSettingsProvider<WikiGroupServiceSettings>
		_wikiGroupServiceSettingsProvider;

}