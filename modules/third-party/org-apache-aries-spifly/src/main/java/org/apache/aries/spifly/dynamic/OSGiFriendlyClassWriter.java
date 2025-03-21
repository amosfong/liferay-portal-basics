/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.aries.spifly.dynamic;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
/**
 * We need to override ASM's default behaviour in {@link #getCommonSuperClass(String, String)}
 * so that it doesn't load classes (which it was doing on the wrong {@link ClassLoader}
 * anyway...)
 *
 * Taken from the org.apache.aries.proxy.impl module.
 */
public final class OSGiFriendlyClassWriter extends ClassWriter {

  private static final String OBJECT_INTERNAL_NAME = "java/lang/Object";
  private final ClassLoader loader;


  public OSGiFriendlyClassWriter(ClassReader arg0, int arg1, ClassLoader loader) {
    super(arg0, arg1);

    this.loader = loader;
  }

  public OSGiFriendlyClassWriter(int arg0, ClassLoader loader) {
    super(arg0);

    this.loader = loader;
  }

  /**
   * We provide an implementation that doesn't cause class loads to occur. It may
   * not be sufficient because it expects to find the common parent using a single
   * classloader, though in fact the common parent may only be loadable by another
   * bundle from which an intermediate class is loaded
   *
   * precondition: arg0 and arg1 are not equal. (checked before this method is called)
   */
  @Override
  protected final String getCommonSuperClass(String arg0, String arg1) {
    //If either is Object, then Object must be the answer
    if(arg0.equals(OBJECT_INTERNAL_NAME) || arg1.equals(OBJECT_INTERNAL_NAME)) {
      return OBJECT_INTERNAL_NAME;
    }
    Set<String> names = new HashSet<String>();
    names.add(arg0);
    names.add(arg1);
    //Try loading the class (in ASM not for real)
    try {
      boolean bRunning = true;
      boolean aRunning = true;
      String arg00 = arg0;
      String arg11 = arg1;
      while(aRunning || bRunning ) {
        if(aRunning) {
			arg00 = _getSuperClassName(arg00);

			if (arg00 == null) {
				aRunning = false;
			}
			else if (!names.add(arg00)){
				return arg00;
			}
        }
        if(bRunning) {
			arg11 = _getSuperClassName(arg11);

			if (arg11 == null) {
				bRunning = false;
			}
			else if (!names.add(arg11)) {
				return arg11;
			}
        }
      }

      throw new RuntimeException("No Common Superclass:" + arg0 + " " + arg1);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String _getSuperClassName(String classInternalName) throws IOException {
	  if (classInternalName.startsWith("java/")) {
		  return OBJECT_INTERNAL_NAME;
	  }

	  InputStream is = loader.getResourceAsStream(classInternalName + ".class");

	  if (is == null) {
		  return null;
	  }

	  ClassReader cr = new ClassReader(is);

	  String superName = cr.getSuperName();

	  if (superName == null) {
		  return OBJECT_INTERNAL_NAME;
	  }

	  return superName;
  }
}
/* @generated */