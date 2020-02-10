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

package com.liferay.batch.engine.internal.upgrade.v_4_0_1;

import com.liferay.batch.engine.internal.upgrade.v_4_0_1.util.BatchEngineExportTaskModelImpl;
import com.liferay.batch.engine.internal.upgrade.v_4_0_1.util.BatchEngineImportTaskModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Riccardo Ferrari
 */
public class UpgradeClassName extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumnType(
				getTableName(BatchEngineExportTaskModelImpl.class), "className",
				"VARCHAR(255) null")) {

			alter(
				BatchEngineExportTaskModelImpl.class,
				new AlterColumnType("className", "VARCHAR(255) null"));
		}

		if (!hasColumnType(
				getTableName(BatchEngineImportTaskModelImpl.class), "className",
				"VARCHAR(255) null")) {

			alter(
				BatchEngineImportTaskModelImpl.class,
				new AlterColumnType("className", "VARCHAR(255) null"));
		}
	}

}