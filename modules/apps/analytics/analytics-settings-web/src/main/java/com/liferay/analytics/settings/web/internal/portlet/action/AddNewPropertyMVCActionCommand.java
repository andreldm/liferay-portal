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

package com.liferay.analytics.settings.web.internal.portlet.action;

import com.liferay.configuration.admin.constants.ConfigurationAdminPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Dictionary;

import javax.portlet.ActionRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author André Miranda
 */
@Component(
	property = {
		"javax.portlet.name=" + ConfigurationAdminPortletKeys.INSTANCE_SETTINGS,
		"mvc.command.name=/analytics/add_new_property"
	},
	service = MVCActionCommand.class
)
public class AddNewPropertyMVCActionCommand
	extends BaseAnalyticsMVCActionCommand {

	@Override
	protected void updateConfigurationProperties(
			ActionRequest actionRequest,
			Dictionary<String, Object> configurationProperties)
		throws Exception {

		boolean syncAllContacts = ParamUtil.getBoolean(
			actionRequest, "syncAllContacts");
		String[] syncedOrganizationIds = ParamUtil.getStringValues(
			actionRequest, "syncedOrganizationIds");
		String[] syncedUserGroupIds = ParamUtil.getStringValues(
			actionRequest, "syncedUserGroupIds");

		System.out.println("chegou");
	}

}