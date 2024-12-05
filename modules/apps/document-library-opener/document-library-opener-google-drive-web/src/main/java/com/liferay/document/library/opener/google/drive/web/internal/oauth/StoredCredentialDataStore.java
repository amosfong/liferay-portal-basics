/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.opener.google.drive.web.internal.oauth;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;

import java.io.IOException;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author Marco Galluzzi
 */
public class StoredCredentialDataStore implements DataStore<StoredCredential> {

	public StoredCredentialDataStore(
		long companyId, DataStoreFactory dataStoreFactory, String id) {

		_companyId = companyId;
		_dataStoreFactory = dataStoreFactory;
		_id = id;
	}

	@Override
	public DataStore<StoredCredential> clear() throws IOException {
		StoredCredentialUtil.clear(_companyId);

		return this;
	}

	@Override
	public boolean containsKey(String key) throws IOException {
		if (key == null) {
			return false;
		}

		return StoredCredentialUtil.containsKey(_companyId, key);
	}

	@Override
	public boolean containsValue(StoredCredential storedCredential)
		throws IOException {

		if (storedCredential == null) {
			return false;
		}

		return StoredCredentialUtil.containsValue(_companyId, storedCredential);
	}

	@Override
	public DataStore<StoredCredential> delete(String key) throws IOException {
		if (key != null) {
			StoredCredentialUtil.delete(_companyId, key);
		}

		return this;
	}

	@Override
	public StoredCredential get(String key) throws IOException {
		return StoredCredentialUtil.get(_companyId, key);
	}

	@Override
	public DataStoreFactory getDataStoreFactory() {
		return _dataStoreFactory;
	}

	@Override
	public String getId() {
		return _id;
	}

	@Override
	public boolean isEmpty() throws IOException {
		return StoredCredentialUtil.isEmpty(_companyId);
	}

	@Override
	public Set<String> keySet() throws IOException {
		return Collections.unmodifiableSet(
			StoredCredentialUtil.keySet(_companyId));
	}

	@Override
	public DataStore<StoredCredential> set(
			String key, StoredCredential storedCredential)
		throws IOException {

		StoredCredentialUtil.add(_companyId, key, storedCredential);

		return this;
	}

	@Override
	public int size() throws IOException {
		return StoredCredentialUtil.size(_companyId);
	}

	@Override
	public Collection<StoredCredential> values() throws IOException {
		return Collections.unmodifiableCollection(
			StoredCredentialUtil.values(_companyId));
	}

	private final long _companyId;
	private final DataStoreFactory _dataStoreFactory;
	private final String _id;

}