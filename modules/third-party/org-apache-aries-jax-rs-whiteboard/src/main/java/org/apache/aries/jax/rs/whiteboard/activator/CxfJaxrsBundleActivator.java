/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.aries.jax.rs.whiteboard.activator;

import java.util.Arrays;
import java.util.List;
import org.osgi.annotation.bundle.Header;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

@Header(name = Constants.BUNDLE_ACTIVATOR, value = "${@class}")
public class CxfJaxrsBundleActivator implements BundleActivator {

    public static final List<Class<?>> INTERNALLY_REQUIRED_CLASSES = Arrays.asList(
        com.ctc.wstx.stax.WstxInputFactory.class,
        com.sun.xml.bind.annotation.XmlLocation.class
    );

    @Override
    public void start(BundleContext bundleContext) throws Exception {
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }

}
/* @generated */