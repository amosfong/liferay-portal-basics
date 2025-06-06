/*******************************************************************************
 * Copyright (c) 2005, 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.metatype.impl;

import java.util.Set;
import java.util.function.Supplier;
import javax.xml.parsers.SAXParser;
import org.eclipse.equinox.metatype.EquinoxMetaTypeInformation;
import org.osgi.framework.Bundle;

/**
 * Implementation of MetaTypeProvider
 * <p>
 * Extension of MetaTypeProvider
 * <p>
 * Provides methods to:
 * <p> - getPids() get the Pids for a given Locale
 * <p> - getFactoryPids() get the Factory Pids for a given Locale
 * <p>
 */
public class MetaTypeInformationImpl extends MetaTypeProviderImpl implements EquinoxMetaTypeInformation {

	/**
	 * Constructor of class MetaTypeInformationImpl.
	 */
	MetaTypeInformationImpl(Bundle bundle, Supplier<SAXParser> parserSupplier, LogTracker logger) {
		super(bundle, parserSupplier, logger);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.metatype.MetaTypeInformation#getPids()
	 */
	public String[] getPids() {

		if (_allPidOCDs.size() == 0) {
			return new String[0];
		}

		Set<String> keySet = _allPidOCDs.keySet();

		return keySet.toArray(new String[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.metatype.MetaTypeInformation#getFactoryPids()
	 */
	public String[] getFactoryPids() {
		if (_allFPidOCDs.size() == 0) {
			return new String[0];
		}
		Set<String> keySet = _allFPidOCDs.keySet();

		return keySet.toArray(new String[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.metatype.MetaTypeInformation#getBundle()
	 */
	public Bundle getBundle() {
		return this._bundle;
	}
}
/* @generated */