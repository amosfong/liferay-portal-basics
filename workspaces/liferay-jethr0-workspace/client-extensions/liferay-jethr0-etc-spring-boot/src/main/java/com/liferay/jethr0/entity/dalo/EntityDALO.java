/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.entity.dalo;

import com.liferay.jethr0.entity.Entity;
import com.liferay.jethr0.entity.factory.EntityFactory;

import java.util.Date;
import java.util.Set;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public interface EntityDALO<T extends Entity> extends DALO {

	public T create(JSONObject jsonObject);

	public void delete(T entity);

	public T get(long id);

	public Set<T> getAll();

	public Set<T> getAllAfterCreatedDate(Date createdDate);

	public Set<T> getAllAfterModifiedDate(Date modifiedDate);

	public EntityFactory<T> getEntityFactory();

	public T update(T entity);

}