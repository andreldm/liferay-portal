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
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André Miranda
 */
@Component(
	property = {
		"javax.portlet.name=" + ConfigurationAdminPortletKeys.INSTANCE_SETTINGS,
		"mvc.command.name=/analytics_settings/add_new_property"
	},
	service = MVCRenderCommand.class
)
public class AddNewPropertyMVCRenderCommand
	extends BaseAnalyticsMVCRenderCommand {

	@Override
	protected String getJspPath() {
		return "/add_new_property.jsp";
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.analytics.settings.web)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		super.servletContext = servletContext;
	}

}