/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.store;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;

import java.io.File;
import java.io.InputStream;

/**
 * Provides methods for storing files in the portal. The file storage
 * implementation can be selected in <code>portal.properties</code> under the
 * property <code>dl.store.impl</code>. Virus checking can also be enabled under
 * the property <code>dl.store.antivirus.impl</code>.
 *
 * <p>
 * The main client for this class is the Document Library portlet. It is also
 * used by other portlets like Wiki and Message Boards to store file
 * attachments. For the Document Library portlet, the <code>repositoryId</code>
 * can be obtained by calling {@link
 * com.liferay.portlet.documentlibrary.model.DLFolderConstants#getDataRepositoryId(
 * long,long)}. For all other portlets, the <code>repositoryId</code> should be
 * set to {@link com.liferay.portal.kernel.model.CompanyConstants#SYSTEM}. These
 * methods can be used in plugins and other portlets, as shown below.
 * </p>
 *
 * <p>
 * <pre>
 * <code>
 * long repositoryId = CompanyConstants.SYSTEM;
 * String dirName = "portlet_name/1234";
 *
 * try {
 *     DLStoreUtil.addDirectory(companyId, repositoryId, dirName);
 * }
 * catch (PortalException pe) {
 * }
 *
 * DLStoreUtil.addFile(
 *     companyId, repositoryId, dirName + "/" + fileName, file);
 * </code>
 * </pre></p>
 *
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Edward Han
 * @author Raymond Augé
 * @see    DLStoreImpl
 */
public class DLStoreUtil {

	public static void addFile(DLStoreRequest dlStoreRequest, byte[] bytes)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.addFile(dlStoreRequest, bytes);
	}

	public static void addFile(DLStoreRequest dlStoreRequest, File file)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.addFile(dlStoreRequest, file);
	}

	public static void addFile(
			DLStoreRequest dlStoreRequest, InputStream inputStream)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.addFile(dlStoreRequest, inputStream);
	}

	/**
	 * Creates a new copy of the file version.
	 *
	 * @param companyId the primary key of the company
	 * @param repositoryId the primary key of the data repository (optionally
	 *        {@link com.liferay.portal.kernel.model.CompanyConstants#SYSTEM})
	 * @param fileName the original's file name
	 * @param fromVersionLabel the original file's version label
	 * @param toVersionLabel the new version label
	 */
	public static void copyFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.copyFileVersion(
			companyId, repositoryId, fileName, fromVersionLabel,
			toVersionLabel);
	}

	/**
	 * Deletes a directory.
	 *
	 * @param companyId the primary key of the company
	 * @param repositoryId the primary key of the data repository (optionally
	 *        {@link com.liferay.portal.kernel.model.CompanyConstants#SYSTEM})
	 * @param dirName the directory's name
	 */
	public static void deleteDirectory(
			long companyId, long repositoryId, String dirName)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.deleteDirectory(companyId, repositoryId, dirName);
	}

	/**
	 * Deletes a file. If a file has multiple versions, all versions will be
	 * deleted.
	 *
	 * @param companyId the primary key of the company
	 * @param repositoryId the primary key of the data repository (optionally
	 *        {@link com.liferay.portal.kernel.model.CompanyConstants#SYSTEM})
	 * @param fileName the file's name
	 */
	public static void deleteFile(
			long companyId, long repositoryId, String fileName)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.deleteFile(companyId, repositoryId, fileName);
	}

	/**
	 * Deletes a file at a particular version.
	 *
	 * @param companyId the primary key of the company
	 * @param repositoryId the primary key of the data repository (optionally
	 *        {@link com.liferay.portal.kernel.model.CompanyConstants#SYSTEM})
	 * @param fileName the file's name
	 * @param versionLabel the file's version label
	 */
	public static void deleteFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.deleteFile(companyId, repositoryId, fileName, versionLabel);
	}

	/**
	 * Returns the file as an {@link InputStream} object.
	 *
	 * <p>
	 * If using an S3 store, it is preferable for performance reasons to use
	 * this method to get the file as an {@link InputStream} instead of using
	 * other methods to get the file as a {@link File}.
	 * </p>
	 *
	 * @param  companyId the primary key of the company
	 * @param  repositoryId the primary key of the data repository (optionally
	 *         {@link com.liferay.portal.kernel.model.CompanyConstants#SYSTEM})
	 * @param  fileName the file's name
	 * @param  versionLabel the file's version label
	 * @return Returns the {@link InputStream} object with the file's name
	 */
	public static InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		return dlStore.getFileAsStream(
			companyId, repositoryId, fileName, versionLabel);
	}

	/**
	 * Returns all files of the directory.
	 *
	 * @param  companyId the primary key of the company
	 * @param  repositoryId the primary key of the data repository (optionally
	 *         {@link com.liferay.portal.kernel.model.CompanyConstants#SYSTEM})
	 * @param  dirName the directory's name
	 * @return Returns all files of the directory
	 */
	public static String[] getFileNames(
			long companyId, long repositoryId, String dirName)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		return dlStore.getFileNames(companyId, repositoryId, dirName);
	}

	/**
	 * Returns the size of the file.
	 *
	 * @param  companyId the primary key of the company
	 * @param  repositoryId the primary key of the data repository (optionally
	 *         {@link com.liferay.portal.kernel.model.CompanyConstants#SYSTEM})
	 * @param  fileName the file's name
	 * @return Returns the size of the file
	 */
	public static long getFileSize(
			long companyId, long repositoryId, String fileName)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		return dlStore.getFileSize(companyId, repositoryId, fileName);
	}

	/**
	 * Returns the {@link DLStore} object. Used primarily by Spring and should
	 * not be used by the client.
	 *
	 * @return Returns the {@link DLStore} object
	 */
	public static DLStore getStore() {
		return _getDLStore();
	}

	/**
	 * Returns <code>true</code> if the file exists.
	 *
	 * @param  companyId the primary key of the company
	 * @param  repositoryId the primary key of the data repository (optionally
	 *         {@link com.liferay.portal.kernel.model.CompanyConstants#SYSTEM})
	 * @param  fileName the file's name
	 * @param  versionLabel the file's version label
	 * @return <code>true</code> if the file exists; <code>false</code>
	 *         otherwise
	 */
	public static boolean hasFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		return dlStore.hasFile(companyId, repositoryId, fileName, versionLabel);
	}

	public static void updateFile(DLStoreRequest dlStoreRequest, File file)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.updateFile(dlStoreRequest, file);
	}

	public static void updateFile(
			DLStoreRequest dlStoreRequest, InputStream inputStream)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.updateFile(dlStoreRequest, inputStream);
	}

	/**
	 * Moves a file to a new data repository.
	 *
	 * @param companyId the primary key of the company
	 * @param repositoryId the primary key of the data repository
	 * @param newRepositoryId the primary key of the new data repository
	 * @param fileName the file's name
	 */
	public static void updateFile(
			long companyId, long repositoryId, long newRepositoryId,
			String fileName)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.updateFile(companyId, repositoryId, newRepositoryId, fileName);
	}

	/**
	 * Update's a file version label. Similar to {@link #copyFileVersion(long,
	 * long, String, String, String)} except that the old file version is
	 * deleted.
	 *
	 * @param companyId the primary key of the company
	 * @param repositoryId the primary key of the data repository (optionally
	 *        {@link com.liferay.portal.kernel.model.CompanyConstants#SYSTEM})
	 * @param fileName the file's name
	 * @param fromVersionLabel the file's version label
	 * @param toVersionLabel the file's new version label
	 */
	public static void updateFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws PortalException {

		DLStore dlStore = _getDLStore();

		dlStore.updateFileVersion(
			companyId, repositoryId, fileName, fromVersionLabel,
			toVersionLabel);
	}

	public void setDLStore(DLStore dlStore) {
		_dlStore = dlStore;
	}

	private static DLStore _getDLStore() {
		DLStore dlStore = _storeSnapshot.get();

		if (dlStore != null) {
			return dlStore;
		}

		return _dlStore;
	}

	private static DLStore _dlStore;
	private static volatile Snapshot<DLStore> _storeSnapshot = new Snapshot<>(
		DLStoreUtil.class, DLStore.class, null, true);

}