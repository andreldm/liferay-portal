/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.saml.opensaml.integration.field.expression.handler;

import com.liferay.saml.opensaml.integration.processor.context.SamlSpIdpConnectionProcessorContext;
import com.liferay.saml.persistence.model.SamlSpIdpConnection;

/**
 * @author Stian Sigvartsen
 */
public interface SamlSpIdpConnectionFieldExpressionHandler
	extends FieldExpressionHandler
		<SamlSpIdpConnection, SamlSpIdpConnectionProcessorContext> {

	@Override
	public void bindProcessorContext(
		SamlSpIdpConnectionProcessorContext
			samlSpIdpConnectionProcessorContext);

}