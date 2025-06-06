/*******************************************************************************
 * Copyright (c) 2004, 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.osgi.internal.serviceregistry;

import java.security.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.eclipse.osgi.container.Module;
import org.eclipse.osgi.container.ModuleRevision;
import org.eclipse.osgi.framework.eventmgr.*;
import org.eclipse.osgi.internal.debug.Debug;
import org.eclipse.osgi.internal.framework.BundleContextImpl;
import org.eclipse.osgi.internal.framework.EquinoxConfiguration;
import org.eclipse.osgi.internal.framework.EquinoxContainer;
import org.eclipse.osgi.internal.framework.FilterImpl;
import org.eclipse.osgi.internal.framework.EquinoxEventPublisher;
import org.eclipse.osgi.internal.messages.Msg;
import org.eclipse.osgi.storage.BundleInfo.Generation;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.*;
import org.osgi.framework.hooks.service.*;
import org.osgi.framework.hooks.service.ListenerHook.ListenerInfo;

/**
 * The Service Registry. This class is the main control point for service 
 * layer operations in the framework.
 * 
 * @ThreadSafe
 */
public class ServiceRegistry {
	public static final int SERVICEEVENT = 3;

	static final String findHookName = FindHook.class.getName();
	@SuppressWarnings("deprecation")
	static final String eventHookName = EventHook.class.getName();
	static final String eventListenerHookName = EventListenerHook.class.getName();
	static final String listenerHookName = ListenerHook.class.getName();

	/** Published services by class name. 
	 * The {@literal List<ServiceRegistrationImpl<?>>}s are both sorted 
	 * in the natural order of ServiceRegistrationImpl and also are sets in that
	 * there must be no two entries in a List which are equal.
	 */
	private final Map<String, List<ServiceRegistrationImpl<?>>> publishedServicesByClass = new ConcurrentHashMap<>();

	/** All published services. 
	 * The List is both sorted in the natural order of ServiceRegistrationImpl and also is a
	 * set in that there must be no two entries in the List which are equal.
	 */
	private final Set<ServiceRegistrationImpl<?>> allPublishedServices = Collections.newSetFromMap(new ConcurrentSkipListMap<>());

	/** Published services by BundleContextImpl.  
	 * The {@literal List<ServiceRegistrationImpl<?>>}s are NOT sorted 
	 * and also are sets in that
	 * there must be no two entries in a List which are equal.
	 */
	private final Map<BundleContextImpl, Queue<ServiceRegistrationImpl<?>>> publishedServicesByContext = new ConcurrentHashMap<>();

	/** next free service id. */
	private final AtomicLong serviceid = new AtomicLong(1);

	private final Map<String, List<FilteredServiceListener>> _objectClassFilteredServiceListeners;

	private final Map<ServiceListener, FilteredServiceListener> _serviceListenerFilteredServiceListeners;

	private final Map<ServiceListener, FilteredServiceListener> _globalFilteredServiceListeners;

	/** initial capacity of the main data structure */
	private static final int initialCapacity = 50;
	/** initial capacity of the nested data structure */
	private static final int initialSubCapacity = 10;
	/** container which created this service registry */
	private final EquinoxContainer container;
	private final BundleContextImpl systemBundleContext;
	final Debug debug;

	/**
	 * Initializes the internal data structures of this ServiceRegistry.
	 *
	 */
	public ServiceRegistry(EquinoxContainer container) {
		this.container = container;
		this.debug = container.getConfiguration().getDebug();
		_objectClassFilteredServiceListeners = new ConcurrentHashMap<>(initialCapacity);
		_serviceListenerFilteredServiceListeners = new ConcurrentHashMap<>(initialCapacity);
		_globalFilteredServiceListeners = new ConcurrentHashMap<>();
		Module systemModule = container.getStorage().getModuleContainer().getModule(0);
		systemBundleContext = (BundleContextImpl) systemModule.getBundle().getBundleContext();
		systemBundleContext.provisionServicesInUseMap();
	}

	/**
	 * Registers the specified service object with the specified properties
	 * under the specified class names into the Framework. A
	 * <code>ServiceRegistrationImpl</code> object is returned. The
	 * <code>ServiceRegistrationImpl</code> object is for the private use of the
	 * bundle registering the service and should not be shared with other
	 * bundles. The registering bundle is defined to be the context bundle.
	 * Other bundles can locate the service by using either the
	 * {@link #getServiceReferences} or {@link #getServiceReference} method.
	 * 
	 * <p>
	 * A bundle can register a service object that implements the
	 * {@link ServiceFactory} interface to have more flexibility in providing
	 * service objects to other bundles.
	 * 
	 * <p>
	 * The following steps are required to register a service:
	 * <ol>
	 * <li>If <code>service</code> is not a <code>ServiceFactory</code>,
	 * an <code>IllegalArgumentException</code> is thrown if
	 * <code>service</code> is not an <code>instanceof</code> all the
	 * classes named.
	 * <li>The Framework adds these service properties to the specified
	 * <code>Dictionary</code> (which may be <code>null</code>): a property
	 * named {@link Constants#SERVICE_ID} identifying the registration number of
	 * the service and a property named {@link Constants#OBJECTCLASS} containing
	 * all the specified classes. If any of these properties have already been
	 * specified by the registering bundle, their values will be overwritten by
	 * the Framework.
	 * <li>The service is added to the Framework service registry and may now
	 * be used by other bundles.
	 * <li>A service event of type {@link ServiceEvent#REGISTERED} is fired.
	 * <li>A <code>ServiceRegistration</code> object for this registration is
	 * returned.
	 * </ol>
	 * 
	 * @param context The BundleContext of the registering bundle.
	 * @param clazzes The class names under which the service can be located.
	 *        The class names in this array will be stored in the service's
	 *        properties under the key {@link Constants#OBJECTCLASS}.
	 * @param service The service object or a <code>ServiceFactory</code>
	 *        object.
	 * @param properties The properties for this service. The keys in the
	 *        properties object must all be <code>String</code> objects. See
	 *        {@link Constants} for a list of standard service property keys.
	 *        Changes should not be made to this object after calling this
	 *        method. To update the service's properties the
	 *        {@link ServiceRegistration#setProperties} method must be called.
	 *        The set of properties may be <code>null</code> if the service
	 *        has no properties.
	 * 
	 * @return A <code>ServiceRegistrationImpl</code> object for use by the bundle
	 *         registering the service to update the service's properties or to
	 *         unregister the service.
	 * 
	 * @throws java.lang.IllegalArgumentException If one of the following is
	 *         true:
	 *         <ul>
	 *         <li><code>service</code> is <code>null</code>.
	 *         <li><code>service</code> is not a <code>ServiceFactory</code>
	 *         object and is not an instance of all the named classes in
	 *         <code>clazzes</code>.
	 *         <li><code>properties</code> contains case variants of the same
	 *         key name.
	 *         </ul>
	 * 
	 * @throws java.lang.SecurityException If the caller does not have the
	 *         <code>ServicePermission</code> to register the service for all
	 *         the named classes and the Java Runtime Environment supports
	 *         permissions.
	 * 
	 * @throws java.lang.IllegalStateException If this BundleContext is no
	 *         longer valid.
	 * 
	 * @see ServiceRegistration
	 * @see ServiceFactory
	 */
	public ServiceRegistrationImpl<?> registerService(BundleContextImpl context, String[] clazzes, Object service, Dictionary<String, ?> properties) {
		if (service == null) {
			if (debug.DEBUG_SERVICES) {
				Debug.println("Service object is null"); //$NON-NLS-1$
			}

			throw new IllegalArgumentException(Msg.SERVICE_ARGUMENT_NULL_EXCEPTION);
		}

		int size = clazzes.length;

		if (size == 0) {
			if (debug.DEBUG_SERVICES) {
				Debug.println("Classes array is empty"); //$NON-NLS-1$
			}

			throw new IllegalArgumentException(Msg.SERVICE_EMPTY_CLASS_LIST_EXCEPTION);
		}

		/* copy the array so that changes to the original will not affect us. */
		List<String> copy = new ArrayList<>(size);
		// intern the strings and remove duplicates
		for (int i = 0; i < size; i++) {
			String clazz = clazzes[i].intern();
			if (!copy.contains(clazz)) {
				copy.add(clazz);
			}
		}
		size = copy.size();
		clazzes = copy.toArray(new String[size]);

		/* check for ServicePermissions. */
		checkRegisterServicePermission(clazzes);

		if (!(service instanceof ServiceFactory<?>)) {
			String invalidService = checkServiceClass(clazzes, service);
			if (invalidService != null) {
				if (debug.DEBUG_SERVICES) {
					Debug.println("Service object is not an instanceof " + invalidService); //$NON-NLS-1$
				}
				throw new IllegalArgumentException(NLS.bind(Msg.SERVICE_NOT_INSTANCEOF_CLASS_EXCEPTION, invalidService));
			}
		}

		ServiceRegistrationImpl<?> registration = new ServiceRegistrationImpl<>(this, context, clazzes, service);
		registration.register(properties);
		if (copy.contains(listenerHookName)) {
			notifyNewListenerHook(registration);
		}
		return registration;
	}

	/**
	 * Returns an array of <code>ServiceReferenceImpl</code> objects. The returned
	 * array of <code>ServiceReferenceImpl</code> objects contains services that
	 * were registered under the specified class, match the specified filter
	 * criteria, and the packages for the class names under which the services
	 * were registered match the context bundle's packages as defined in
	 * {@link ServiceReference#isAssignableTo(Bundle, String)}.
	 * 
	 * <p>
	 * The list is valid at the time of the call to this method, however since
	 * the Framework is a very dynamic environment, services can be modified or
	 * unregistered at anytime.
	 * 
	 * <p>
	 * <code>filter</code> is used to select the registered service whose
	 * properties objects contain keys and values which satisfy the filter. See
	 * {@link Filter} for a description of the filter string syntax.
	 * 
	 * <p>
	 * If <code>filter</code> is <code>null</code>, all registered services
	 * are considered to match the filter. If <code>filter</code> cannot be
	 * parsed, an {@link InvalidSyntaxException} will be thrown with a human
	 * readable message where the filter became unparsable.
	 * 
	 * <p>
	 * The following steps are required to select a set of
	 * <code>ServiceReferenceImpl</code> objects:
	 * <ol>
	 * <li>If the filter string is not <code>null</code>, the filter string
	 * is parsed and the set <code>ServiceReferenceImpl</code> objects of
	 * registered services that satisfy the filter is produced. If the filter
	 * string is <code>null</code>, then all registered services are
	 * considered to satisfy the filter.
	 * <li>If the Java Runtime Environment supports permissions, the set of
	 * <code>ServiceReferenceImpl</code> objects produced by the previous step is
	 * reduced by checking that the caller has the
	 * <code>ServicePermission</code> to get at least one of the class names
	 * under which the service was registered. If the caller does not have the
	 * correct permission for a particular <code>ServiceReferenceImpl</code>
	 * object, then it is removed from the set.
	 * <li>If <code>clazz</code> is not <code>null</code>, the set is
	 * further reduced to those services that are an <code>instanceof</code>
	 * and were registered under the specified class. The complete list of
	 * classes of which a service is an instance and which were specified when
	 * the service was registered is available from the service's
	 * {@link Constants#OBJECTCLASS} property.
	 * <li>The set is reduced one final time by cycling through each
	 * <code>ServiceReference</code> object and calling
	 * {@link ServiceReference#isAssignableTo(Bundle, String)} with the context
	 * bundle and each class name under which the <code>ServiceReference</code>
	 * object was registered. For any given <code>ServiceReferenceImpl</code>
	 * object, if any call to
	 * {@link ServiceReference#isAssignableTo(Bundle, String)} returns
	 * <code>false</code>, then it is removed from the set of
	 * <code>ServiceReferenceImpl</code> objects.
	 * <li>An array of the remaining <code>ServiceReferenceImpl</code> objects is
	 * returned.
	 * </ol>
	 * 
	 * @param context The BundleContext of the requesting bundle.
	 * @param clazz The class name with which the service was registered or
	 *        <code>null</code> for all services.
	 * @param filterstring The filter criteria.
	 * @param allservices True if the bundle called getAllServiceReferences.
	 * @return An array of <code>ServiceReferenceImpl</code> objects or
	 *         <code>null</code> if no services are registered which satisfy
	 *         the search.
	 * @throws InvalidSyntaxException If <code>filter</code> contains an
	 *         invalid filter string that cannot be parsed.
	 * @throws java.lang.IllegalStateException If this BundleContext is no
	 *         longer valid.
	 */
	public ServiceReferenceImpl<?>[] getServiceReferences(final BundleContextImpl context, final String clazz, final String filterstring, final boolean allservices) throws InvalidSyntaxException {
		if (debug.DEBUG_SERVICES) {
			Debug.println((allservices ? "getAllServiceReferences(" : "getServiceReferences(") + clazz + ", \"" + filterstring + "\")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		Filter filter = (filterstring == null) ? null : context.createFilter(filterstring);
		List<ServiceRegistrationImpl<?>> registrations = lookupServiceRegistrations(clazz, filter);
		List<ServiceReferenceImpl<?>> references = new ArrayList<>(registrations.size());
		for (ServiceRegistrationImpl<?> registration : registrations) {
			ServiceReferenceImpl<?> reference;
			try {
				reference = registration.getReferenceImpl();
			} catch (IllegalStateException e) {
				continue; // got unregistered, don't return reference
			}
			if (allservices || isAssignableTo(context, reference)) {
				try { /* test for permission to get the service */
					checkGetServicePermission(reference);
				} catch (SecurityException se) {
					continue; // don't return reference
				}
			} else {
				continue; // don't return reference
			}
			references.add(reference);
		}

		Collection<ServiceReferenceImpl<?>> copyReferences = references;
		if (context.getBundleImpl().getBundleId() == 0) {
			// Make a copy for the purposes of calling the hooks;
			// The the removals from the hooks are ignored for the system bundle
			copyReferences = new ArrayList<>(references);
		}
		Collection<ServiceReference<?>> shrinkable = new ShrinkableCollection<ServiceReference<?>>(copyReferences);
		notifyFindHooks(context, clazz, filterstring, allservices, shrinkable);

		int size = references.size();
		if (size == 0) {
			return null;
		}
		return references.toArray(new ServiceReferenceImpl[size]);
	}

	/**
	 * Returns a <code>ServiceReference</code> object for a service that
	 * implements and was registered under the specified class.
	 * 
	 * <p>
	 * This <code>ServiceReference</code> object is valid at the time of the
	 * call to this method, however as the Framework is a very dynamic
	 * environment, services can be modified or unregistered at anytime.
	 * 
	 * <p>
	 * This method is the same as calling
	 * {@link BundleContext#getServiceReferences(String, String)} with a
	 * <code>null</code> filter string. It is provided as a convenience for
	 * when the caller is interested in any service that implements the
	 * specified class.
	 * <p>
	 * If multiple such services exist, the service with the highest ranking (as
	 * specified in its {@link Constants#SERVICE_RANKING} property) is returned.
	 * <p>
	 * If there is a tie in ranking, the service with the lowest service ID (as
	 * specified in its {@link Constants#SERVICE_ID} property); that is, the
	 * service that was registered first is returned.
	 * 
	 * @param context The BundleContext of the requesting bundle.
	 * @param clazz The class name with which the service was registered.
	 * @return A <code>ServiceReference</code> object, or <code>null</code>
	 *         if no services are registered which implement the named class.
	 * @throws java.lang.IllegalStateException If this BundleContext is no
	 *         longer valid.
	 */
	public ServiceReferenceImpl<?> getServiceReference(BundleContextImpl context, String clazz) {
		if (debug.DEBUG_SERVICES) {
			Debug.println("getServiceReference(" + clazz + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		try {
			ServiceReferenceImpl<?>[] references = getServiceReferences(context, clazz, null, false);

			if (references != null) {
				// Since we maintain the registrations in a sorted List, the first element is always the
				// correct one to return.
				return references[0];
			}
		} catch (InvalidSyntaxException e) {
			if (debug.DEBUG_GENERAL) {
				Debug.println("InvalidSyntaxException w/ null filter" + e.getMessage()); //$NON-NLS-1$
				Debug.printStackTrace(e);
			}
		}

		return null;
	}

	/**
	 * Returns the specified service object for a service.
	 * <p>
	 * A bundle's use of a service is tracked by the bundle's use count of that
	 * service. Each time a service's service object is returned by
	 * {@link #getService(BundleContextImpl, ServiceReferenceImpl)} the context bundle's use count for
	 * that service is incremented by one. Each time the service is released by
	 * {@link #ungetService(BundleContextImpl, ServiceReferenceImpl)} the context bundle's use count
	 * for that service is decremented by one.
	 * <p>
	 * When a bundle's use count for a service drops to zero, the bundle should
	 * no longer use that service.
	 * 
	 * <p>
	 * This method will always return <code>null</code> when the service
	 * associated with this <code>reference</code> has been unregistered.
	 * 
	 * <p>
	 * The following steps are required to get the service object:
	 * <ol>
	 * <li>If the service has been unregistered, <code>null</code> is
	 * returned.
	 * <li>The context bundle's use count for this service is incremented by
	 * one.
	 * <li>If the context bundle's use count for the service is currently one
	 * and the service was registered with an object implementing the
	 * <code>ServiceFactory</code> interface, the
	 * {@link ServiceFactory#getService(Bundle, ServiceRegistration)} method is
	 * called to create a service object for the context bundle. This service
	 * object is cached by the Framework. While the context bundle's use count
	 * for the service is greater than zero, subsequent calls to get the
	 * services's service object for the context bundle will return the cached
	 * service object. <br>
	 * If the service object returned by the <code>ServiceFactory</code>
	 * object is not an <code>instanceof</code> all the classes named when the
	 * service was registered or the <code>ServiceFactory</code> object throws
	 * an exception, <code>null</code> is returned and a Framework event of
	 * type {@link FrameworkEvent#ERROR} containing a {@link ServiceException}
	 * describing the error is fired.
	 * <li>The service object for the service is returned.
	 * </ol>
	 * 
	 * @param context The BundleContext of the requesting bundle.
	 * @param reference A reference to the service.
	 * @return A service object for the service associated with
	 *         <code>reference</code> or <code>null</code> if the service is
	 *         not registered, the service object returned by a
	 *         <code>ServiceFactory</code> does not implement the classes
	 *         under which it was registered or the <code>ServiceFactory</code>
	 *         threw an exception.
	 * @throws java.lang.SecurityException If the caller does not have the
	 *         <code>ServicePermission</code> to get the service using at
	 *         least one of the named classes the service was registered under
	 *         and the Java Runtime Environment supports permissions.
	 * @throws java.lang.IllegalStateException If this BundleContext is no
	 *         longer valid.
	 * @see #ungetService(BundleContextImpl, ServiceReferenceImpl)
	 * @see ServiceFactory
	 */
	public <S> S getService(BundleContextImpl context, ServiceReferenceImpl<S> reference) {
		/* test for permission to get the service */
		checkGetServicePermission(reference);
		return reference.getRegistration().getService(context, ServiceConsumer.singletonConsumer);
	}

	/**
	 * Returns the {@link ServiceObjects} object for the service referenced by
	 * the specified {@code ServiceReference} object.
	 * 
	 * <p>
	 * The {@link ServiceObjects} object can be used to obtain multiple
	 * service objects for services with {@link Constants#SCOPE_PROTOTYPE
	 * prototype} scope. For services with {@link Constants#SCOPE_SINGLETON
	 * singleton} or {@link Constants#SCOPE_BUNDLE bundle} scope, the
	 * {@link ServiceObjects#getService()} method behaves the same as the
	 * {@link BundleContext#getService(ServiceReference)} method and the
	 * {@link ServiceObjects#ungetService(Object)} method behaves the same as
	 * the {@link BundleContext#ungetService(ServiceReference)} method. That is, only one,
	 * use-counted service object is available from the {@link ServiceObjects}
	 * object.
	 * 
	 * <p>
	 * This method will always return {@code null} when the service associated
	 * with the specified {@code reference} has been unregistered.
	 * 
	 * @param <S> Type of Service.
	 * @param context The BundleContext of the requesting bundle.
	 * @param reference A reference to the service.
	 * @return A {@link ServiceObjects} object for the service associated with
	 *         the specified {@code reference} or {@code null} if the service is
	 *         not registered.
	 * @throws SecurityException If the caller does not have the
	 *         {@code ServicePermission} to get the service using at least one
	 *         of the named classes the service was registered under and the
	 *         Java Runtime Environment supports permissions.
	 */
	public <S> ServiceObjectsImpl<S> getServiceObjects(BundleContextImpl context, ServiceReferenceImpl<S> reference) {
		checkGetServicePermission(reference);
		return reference.getRegistration().getServiceObjects(context);
	}

	/**
	 * Releases the service object referenced by the specified
	 * <code>ServiceReference</code> object. If the context bundle's use count
	 * for the service is zero, this method returns <code>false</code>.
	 * Otherwise, the context bundle's use count for the service is decremented
	 * by one.
	 * 
	 * <p>
	 * The service's service object should no longer be used and all references
	 * to it should be destroyed when a bundle's use count for the service drops
	 * to zero.
	 * 
	 * <p>
	 * The following steps are required to unget the service object:
	 * <ol>
	 * <li>If the context bundle's use count for the service is zero or the
	 * service has been unregistered, <code>false</code> is returned.
	 * <li>The context bundle's use count for this service is decremented by
	 * one.
	 * <li>If the context bundle's use count for the service is currently zero
	 * and the service was registered with a <code>ServiceFactory</code>
	 * object, the
	 * {@link ServiceFactory#ungetService(Bundle, ServiceRegistration, Object)}
	 * method is called to release the service object for the context bundle.
	 * <li><code>true</code> is returned.
	 * </ol>
	 * 
	 * @param context The BundleContext of the requesting bundle.
	 * @param reference A reference to the service to be released.
	 * @return <code>false</code> if the context bundle's use count for the
	 *         service is zero or if the service has been unregistered;
	 *         <code>true</code> otherwise.
	 * @throws java.lang.IllegalStateException If this BundleContext is no
	 *         longer valid.
	 * @see #getService
	 * @see ServiceFactory
	 */
	public boolean ungetService(BundleContextImpl context, ServiceReferenceImpl<?> reference) {
		ServiceRegistrationImpl<?> registration = reference.getRegistration();

		return registration.ungetService(context, ServiceConsumer.singletonConsumer, null);
	}

	/**
	 * Returns this bundle's <code>ServiceReference</code> list for all
	 * services it has registered or <code>null</code> if this bundle has no
	 * registered services.
	 * 
	 * <p>
	 * If the Java runtime supports permissions, a <code>ServiceReference</code>
	 * object to a service is included in the returned list only if the caller
	 * has the <code>ServicePermission</code> to get the service using at
	 * least one of the named classes the service was registered under.
	 * 
	 * <p>
	 * The list is valid at the time of the call to this method, however, as the
	 * Framework is a very dynamic environment, services can be modified or
	 * unregistered at anytime.
	 * 
	 * @param context The BundleContext of the requesting bundle.
	 * @return An array of <code>ServiceReference</code> objects or
	 *         <code>null</code>.
	 * @throws java.lang.IllegalStateException If this bundle has been
	 *         uninstalled.
	 * @see ServiceRegistration
	 * @see ServiceReference
	 * @see ServicePermission
	 */
	public ServiceReferenceImpl<?>[] getRegisteredServices(BundleContextImpl context) {
		List<ServiceRegistrationImpl<?>> registrations = lookupServiceRegistrations(context);
		List<ServiceReferenceImpl<?>> references = new ArrayList<>(registrations.size());
		for (ServiceRegistrationImpl<?> registration : registrations) {
			ServiceReferenceImpl<?> reference;
			try {
				reference = registration.getReferenceImpl();
			} catch (IllegalStateException e) {
				continue; // got unregistered, don't return reference
			}
			try {
				/* test for permission to get the service */
				checkGetServicePermission(reference);
			} catch (SecurityException se) {
				continue; // don't return reference
			}
			references.add(reference);
		}

		int size = references.size();
		if (size == 0) {
			return null;
		}
		return references.toArray(new ServiceReferenceImpl[size]);
	}

	/**
	 * Returns this bundle's <code>ServiceReference</code> list for all
	 * services it is using or returns <code>null</code> if this bundle is not
	 * using any services. A bundle is considered to be using a service if its
	 * use count for that service is greater than zero.
	 * 
	 * <p>
	 * If the Java Runtime Environment supports permissions, a
	 * <code>ServiceReference</code> object to a service is included in the
	 * returned list only if the caller has the <code>ServicePermission</code>
	 * to get the service using at least one of the named classes the service
	 * was registered under.
	 * <p>
	 * The list is valid at the time of the call to this method, however, as the
	 * Framework is a very dynamic environment, services can be modified or
	 * unregistered at anytime.
	 * 
	 * @param context The BundleContext of the requesting bundle.
	 * @return An array of <code>ServiceReference</code> objects or
	 *         <code>null</code>.
	 * @throws java.lang.IllegalStateException If this bundle has been
	 *         uninstalled.
	 * @see ServiceReference
	 * @see ServicePermission
	 */
	public ServiceReferenceImpl<?>[] getServicesInUse(BundleContextImpl context) {
		Map<ServiceRegistrationImpl<?>, ServiceUse<?>> servicesInUse = context.getServicesInUseMap();
		if (servicesInUse == null) {
			return null;
		}

		List<ServiceRegistrationImpl<?>> registrations;
		synchronized (servicesInUse) {
			if (servicesInUse.isEmpty()) {
				return null;
			}
			registrations = new ArrayList<>(servicesInUse.keySet());
		}
		List<ServiceReferenceImpl<?>> references = new ArrayList<>(registrations.size());
		for (ServiceRegistrationImpl<?> registration : registrations) {
			ServiceReferenceImpl<?> reference;
			try {
				reference = registration.getReferenceImpl();
			} catch (IllegalStateException e) {
				continue; // got unregistered, don't return reference
			}
			try {
				/* test for permission to get the service */
				checkGetServicePermission(reference);
			} catch (SecurityException se) {
				continue; // don't return reference
			}
			references.add(reference);
		}

		int size = references.size();
		if (size == 0) {
			return null;
		}
		return references.toArray(new ServiceReferenceImpl[size]);
	}

	/**
	 * Called when the BundleContext is closing to unregister all services
	 * currently registered by the bundle.
	 * 
	 * @param context The BundleContext of the closing bundle.
	 */
	public void unregisterServices(BundleContextImpl context) {
		for (ServiceRegistrationImpl<?> registration : lookupServiceRegistrations(context)) {
			try {
				registration.unregister();
			} catch (IllegalStateException e) {
				/* already unregistered */
			}
		}
		removeServiceRegistrations(context); // remove empty list
	}

	/**
	 * Called when the BundleContext is closing to unget all services
	 * currently used by the bundle.
	 * 
	 * @param context The BundleContext of the closing bundle.
	 */
	public void releaseServicesInUse(BundleContextImpl context) {
		Map<ServiceRegistrationImpl<?>, ServiceUse<?>> servicesInUse = context.getServicesInUseMap();
		if (servicesInUse == null) {
			return;
		}
		List<ServiceRegistrationImpl<?>> registrations;
		synchronized (servicesInUse) {
			if (servicesInUse.isEmpty()) {
				return;
			}
			registrations = new ArrayList<>(servicesInUse.keySet());
		}
		if (debug.DEBUG_SERVICES) {
			Debug.println("Releasing services"); //$NON-NLS-1$
		}
		for (ServiceRegistrationImpl<?> registration : registrations) {
			registration.releaseService(context);
		}
	}

	/**
	 * Add a new Service Listener for a bundle.
	 * 
	 * @param context Context of bundle adding listener.
	 * @param listener Service Listener to be added.
	 * @param filter Filter string for listener or null.
	 * @throws InvalidSyntaxException If the filter string is invalid.
	 */
	public void addServiceListener(BundleContextImpl context, ServiceListener listener, String filter) throws InvalidSyntaxException {
		if (debug.DEBUG_EVENTS) {
			String listenerName = listener.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(listener)); //$NON-NLS-1$
			Debug.println("addServiceListener[" + context.getBundleImpl() + "](" + listenerName + ", \"" + filter + "\")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}

		FilteredServiceListener filteredListener = new FilteredServiceListener(context, listener, filter);

		String objectClass = filteredListener.getObjectClass();

		if (objectClass == null) {
			_globalFilteredServiceListeners.put(listener, filteredListener);
		}
		else {
			_objectClassFilteredServiceListeners.compute(
				objectClass,
				(key, filteredListeners) -> {
					if (filteredListeners == null) {
						filteredListeners = new CopyOnWriteArrayList<>();
					}

					filteredListeners.add(filteredListener);

					return filteredListeners;
				}
			);
		}

		FilteredServiceListener oldFilteredListener = _serviceListenerFilteredServiceListeners.put(listener, filteredListener);

		if (oldFilteredListener != null) {
			oldFilteredListener.markRemoved();
			Collection<ListenerInfo> removedListeners = Collections.<ListenerInfo> singletonList(oldFilteredListener);
			notifyListenerHooks(removedListeners, false);
		}

		Collection<ListenerInfo> addedListeners = Collections.<ListenerInfo> singletonList(filteredListener);
		notifyListenerHooks(addedListeners, true);
	}

	/**
	 * Remove a Service Listener for a bundle.
	 * 
	 * @param context Context of bundle removing listener.
	 * @param listener Service Listener to be removed.
	 */
	public void removeServiceListener(BundleContextImpl context, ServiceListener listener) {
		if (debug.DEBUG_EVENTS) {
			String listenerName = listener.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(listener)); //$NON-NLS-1$
			Debug.println("removeServiceListener[" + context.getBundleImpl() + "](" + listenerName + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

		FilteredServiceListener oldFilteredListener = _serviceListenerFilteredServiceListeners.remove(listener);

		if (oldFilteredListener == null) {
			return;
		}

		String objectClass = oldFilteredListener.getObjectClass();

		if (objectClass == null) {
			_globalFilteredServiceListeners.remove(listener);
		}
		else {
			_objectClassFilteredServiceListeners.compute(
				objectClass,
				(key, filteredListeners) -> {
					filteredListeners.remove(oldFilteredListener);

					if (filteredListeners.isEmpty()) {
						return null;
					}

					return filteredListeners;
				}
			);
		}

		oldFilteredListener.markRemoved();
		Collection<ListenerInfo> removedListeners = Collections.<ListenerInfo> singletonList(oldFilteredListener);
		notifyListenerHooks(removedListeners, false);
	}

	/**
	 * Remove all Service Listener for a bundle.
	 * 
	 * @param context Context of bundle removing all listeners.
	 */
	public void removeAllServiceListeners(BundleContextImpl context) {
		List<FilteredServiceListener> removedListeners = new ArrayList<>();

		Iterator<FilteredServiceListener> iterator =
			_serviceListenerFilteredServiceListeners.values().iterator();

		while (iterator.hasNext()) {
			FilteredServiceListener filteredServiceListener = iterator.next();

			if (context == filteredServiceListener.getBundleContext()) {
				filteredServiceListener.markRemoved();

				removedListeners.add(filteredServiceListener);

				String objectClass = filteredServiceListener.getObjectClass();

				if (objectClass == null) {
					_globalFilteredServiceListeners.remove(
						filteredServiceListener.getListener());
				}
				else {
					_objectClassFilteredServiceListeners.compute(
						objectClass, (key, filteredListeners) -> {
							filteredListeners.remove(filteredServiceListener);

							if (filteredListeners.isEmpty()) {
								return null;
							}

							return filteredListeners;
						});
				}

				iterator.remove();
			}
		}
		notifyListenerHooks(asListenerInfos(removedListeners), false);
	}

	/**
	 * Coerce the generic type of a collection from Collection<FilteredServiceListener>
	 * to Collection<ListenerInfo>
	 * @param c Collection to be coerced.
	 * @return c coerced to Collection<ListenerInfo>
	 */
	@SuppressWarnings("unchecked")
	private static Collection<ListenerInfo> asListenerInfos(Collection<? extends ListenerInfo> c) {
		return (Collection<ListenerInfo>) c;
	}

	/**
	 * Deliver a ServiceEvent.
	 * 
	 * @param event The ServiceEvent to deliver.
	 */
	public void publishServiceEvent(final ServiceEvent event) {
		publishServiceEventPrivileged(event);
	}

	void publishServiceEventPrivileged(final ServiceEvent event) {
		/* Build the listener snapshot */

		Map<String, List<FilteredServiceListener>> listenerSnapshot;

		List<ServiceRegistrationImpl<?>> eventHooks = lookupServiceRegistrations(eventHookName, null);
		List<ServiceRegistrationImpl<?>> eventListenerHooks = lookupServiceRegistrations(eventListenerHookName, null);

		if (eventHooks.isEmpty() && eventListenerHooks.isEmpty()) {
			listenerSnapshot = _objectClassFilteredServiceListeners;
		}
		else {
			CopyOnWriteIdentityMap<ServiceListener, FilteredServiceListener> systemServiceListenersOrig = null;
			BundleContextImpl systemContext = null;

			Map<BundleContextImpl, CopyOnWriteIdentityMap<ServiceListener, FilteredServiceListener>> bundleContextFilteredServiceListeners = new HashMap<>();

			for (Map.Entry<ServiceListener, FilteredServiceListener> entry : _serviceListenerFilteredServiceListeners.entrySet()) {
				FilteredServiceListener filteredServiceListener = entry.getValue();

				BundleContextImpl bundleContextImpl = (BundleContextImpl)filteredServiceListener.getBundleContext();

				CopyOnWriteIdentityMap<ServiceListener, FilteredServiceListener> copyOnWriteIdentityMap = bundleContextFilteredServiceListeners.computeIfAbsent(bundleContextImpl, key -> new CopyOnWriteIdentityMap<>());

				copyOnWriteIdentityMap.put(entry.getKey(), filteredServiceListener);

				if (bundleContextImpl.getBundleImpl().getBundleId() == 0) {
					systemContext = bundleContextImpl;
					systemServiceListenersOrig = copyOnWriteIdentityMap;
				}
			}

			/* shrink the snapshot.
			 * keySet returns a Collection which cannot be added to and
			 * removals from that collection will result in removals of the
			 * entry from the snapshot.
			 */
			Collection<BundleContext> contexts = asBundleContexts(bundleContextFilteredServiceListeners.keySet());
			notifyEventHooksPrivileged(event, contexts);
			if (!bundleContextFilteredServiceListeners.isEmpty()) {
				Map<BundleContextImpl, Set<Map.Entry<ServiceListener, FilteredServiceListener>>> adaptedListenerSnapshot = new HashMap<>();

				bundleContextFilteredServiceListeners.forEach(
					(key, value) -> {
						adaptedListenerSnapshot.put(key, value.entrySet());
					}
				);

				Map<BundleContext, Collection<ListenerInfo>> listeners = new ShrinkableValueCollectionMap<BundleContext, ListenerInfo>(adaptedListenerSnapshot);

				notifyEventListenerHooksPrivileged(event, listeners);

				bundleContextFilteredServiceListeners.keySet().retainAll(adaptedListenerSnapshot.keySet());
			}
			// always add back the system service listeners if they were removed
			if (systemServiceListenersOrig != null) {
				// No contains key check is done because hooks may have removed
				// a single listener from the value instead of the whole context key.
				// It is more simple to just replace with the original snapshot.
				bundleContextFilteredServiceListeners.put(systemContext, systemServiceListenersOrig);
			}

			Map<String, List<FilteredServiceListener>> copyListenerSnapshot = new HashMap<>();

			for (CopyOnWriteIdentityMap<ServiceListener, FilteredServiceListener> copyOnWriteIdentityMap : bundleContextFilteredServiceListeners.values()) {
				for (FilteredServiceListener filteredServiceListener : copyOnWriteIdentityMap.values()) {
					String objectClass = filteredServiceListener.getObjectClass();

					if (objectClass != null) {
						List<FilteredServiceListener> filteredServiceListeners = copyListenerSnapshot.computeIfAbsent(objectClass, key -> new ArrayList<>());

						filteredServiceListeners.add(filteredServiceListener);
					}
				}
			}

			listenerSnapshot = copyListenerSnapshot;
		}
		if (listenerSnapshot.isEmpty()) {
			return;
		}

		/* deliver the event to the snapshot */

		EquinoxConfiguration equinoxConfiguration =
			container.getConfiguration();

		Thread currentThread = null;
		ClassLoader previousTCCL = null;

		if (equinoxConfiguration.BUNDLE_SET_TCCL) {
			currentThread = Thread.currentThread();

			previousTCCL = currentThread.getContextClassLoader();

			ClassLoader contextFinder = container.getContextFinder();

			if (previousTCCL == contextFinder) {
				previousTCCL = null;
			}
			else {
				currentThread.setContextClassLoader(contextFinder);
			}
		}

		try {
			ServiceReferenceImpl<?> reference = (ServiceReferenceImpl<?>) event.getServiceReference();

			for (String clazz : reference.getClasses()) {
				List<FilteredServiceListener> filteredServiceListeners = _objectClassFilteredServiceListeners.get(clazz);

				if (filteredServiceListeners != null) {
					for (FilteredServiceListener filteredServiceListener : filteredServiceListeners) {
						_serviceChanged(filteredServiceListener, event);
					}
				}
			}

			for (FilteredServiceListener filteredServiceListener : _globalFilteredServiceListeners.values()) {
				_serviceChanged(filteredServiceListener, event);
			}
		}
		finally {
			if (previousTCCL != null) {
				currentThread.setContextClassLoader(previousTCCL);
			}
		}
	}

	private void _serviceChanged(FilteredServiceListener filteredServiceListener, ServiceEvent serviceEvent) {
		try {
			filteredServiceListener.doServiceChanged(serviceEvent);
		}
		catch (Throwable t) {
			if (debug.DEBUG_GENERAL) {
				Debug.println(
					"Exception in bottom level event dispatcher: " +
						t.getMessage());

				Debug.printStackTrace(t);
			}

			container.handleRuntimeError(t);

			EquinoxEventPublisher equinoxEventPublisher =
				container.getEventPublisher();

			BundleContextImpl bundleContextImpl = (BundleContextImpl)filteredServiceListener.getBundleContext();

			equinoxEventPublisher.publishFrameworkEvent(
				FrameworkEvent.ERROR, bundleContextImpl.getBundleImpl(), t);
		}
	}

	/**
	 * Coerce the generic type of a collection from Collection<BundleContextImpl>
	 * to Collection<BundleContext>
	 * @param c Collection to be coerced.
	 * @return c coerced to Collection<BundleContext>
	 */
	@SuppressWarnings("unchecked")
	private static Collection<BundleContext> asBundleContexts(Collection<? extends BundleContext> c) {
		return (Collection<BundleContext>) c;
	}

	/**
	 * Return the next available service id.
	 * 
	 * @return next service id.
	 */
	long getNextServiceId() {
		return serviceid.getAndIncrement();
	}

	/**
	 * Add the ServiceRegistrationImpl to the data structure.
	 * 
	 * @param context The BundleContext of the bundle registering the service.
	 * @param registration The new ServiceRegistration.
	 */
	void addServiceRegistration(BundleContextImpl context, ServiceRegistrationImpl<?> registration) {
		// Add the ServiceRegistrationImpl to the list of Services published by BundleContextImpl.
		Queue<ServiceRegistrationImpl<?>> contextServices = publishedServicesByContext.get(context);
		if (contextServices == null) {
			contextServices = new ConcurrentLinkedQueue<>();

			Queue<ServiceRegistrationImpl<?>> previousContextServices =
				publishedServicesByContext.putIfAbsent(context, contextServices);

			if (previousContextServices != null) {
				contextServices = previousContextServices;
			}
		}
		// The list is NOT sorted, so we just add
		contextServices.add(registration);

		// Add the ServiceRegistrationImpl to the list of Services published by Class Name.
		for (String clazz : registration.getClasses()) {
			publishedServicesByClass.compute(
				clazz,
				(className, services) -> {
					if (services == null) {
						return Collections.singletonList(registration);
					}

					services = new ArrayList<>(services);

					int insertIndex = -Collections.binarySearch(services, registration) - 1;

					services.add(insertIndex, registration);

					return services;
				});
		}

		allPublishedServices.add(registration);
	}

	/**
	 * Modify the ServiceRegistrationImpl in the data structure.
	 * 
	 * @param context The BundleContext of the bundle registering the service.
	 * @param registration The modified ServiceRegistration.
	 */
	void modifyServiceRegistration(BundleContextImpl context, ServiceRegistrationImpl<?> registration) {
		// The list of Services published by BundleContextImpl is not sorted, so
		// we do not need to modify it.

		// Remove the ServiceRegistrationImpl from the list of Services published by Class Name
		// and then add at the correct index.
		for (String clazz : registration.getClasses()) {
			publishedServicesByClass.compute(
				clazz,
				(className, services) -> {
					services = new ArrayList<>(services);

					services.remove(registration);

					// The list is sorted, so we must find the proper location to insert
					int insertIndex = -Collections.binarySearch(services, registration) - 1;
					services.add(insertIndex, registration);

					return services;
				});
		}

		// Remove the ServiceRegistrationImpl from the list of all published Services
		// and then add at the correct index.
		allPublishedServices.remove(registration);
		allPublishedServices.add(registration);
	}

	/**
	 * Remove the ServiceRegistrationImpl from the data structure.
	 * 
	 * @param context The BundleContext of the bundle registering the service.
	 * @param registration The ServiceRegistration to remove.
	 */
	void removeServiceRegistration(BundleContextImpl context, ServiceRegistrationImpl<?> registration) {
		// Remove the ServiceRegistrationImpl from the list of Services published by BundleContextImpl.
		Queue<ServiceRegistrationImpl<?>> contextServices = publishedServicesByContext.get(context);
		if (contextServices != null) {
			contextServices.remove(registration);
		}

		// Remove the ServiceRegistrationImpl from the list of Services published by Class Name.
		for (String clazz : registration.getClasses()) {
			publishedServicesByClass.compute(
				clazz,
				(className, services) -> {
					services = new ArrayList<>(services);

					services.remove(registration);

					if (services.isEmpty()) {
						return null;
					}

					return services;
				});
		}

		// Remove the ServiceRegistrationImpl from the list of all published Services.
		allPublishedServices.remove(registration);
	}

	/**
	 * Lookup Service Registrations in the data structure by class name and filter.
	 * 
	 * @param clazz The class name with which the service was registered or
	 *        <code>null</code> for all services.
	 * @param filter The filter criteria.
	 * @return List<ServiceRegistrationImpl>
	 */
	private List<ServiceRegistrationImpl<?>> lookupServiceRegistrations(String clazz, Filter filter) {
		if ((clazz == null) && (filter instanceof FilterImpl)) {
			FilterImpl filterImpl = (FilterImpl)filter;

			clazz = filterImpl.getRequiredObjectClass();
		}

		Collection<ServiceRegistrationImpl<?>> result;

		if (clazz == null) { /* all services */
			result = allPublishedServices;
		} else {
			/* services registered under the class name */
			result = publishedServicesByClass.get(clazz);
		}

		if ((result == null) || result.isEmpty()) {
			List<ServiceRegistrationImpl<?>> empty = Collections.<ServiceRegistrationImpl<?>> emptyList();
			return empty;
		}

		if (filter == null) {
			return new ArrayList<>(result);
		}

		List<ServiceRegistrationImpl<?>> listResult = new LinkedList<>(result); /* make a new list since we don't want to change the real list */

		for (Iterator<ServiceRegistrationImpl<?>> iter = listResult.iterator(); iter.hasNext();) {
			ServiceRegistrationImpl<?> registration = iter.next();
			ServiceReferenceImpl<?> reference;
			try {
				reference = registration.getReferenceImpl();
			} catch (IllegalStateException e) {
				iter.remove(); /* service was unregistered after we left the synchronized block above */
				continue;
			}
			if (!filter.match(reference)) {
				iter.remove();
			}
		}
		return listResult;
	}

	/**
	 * Lookup Service Registrations in the data structure by BundleContext.
	 * 
	 * @param context The BundleContext for which to return Service Registrations.
	 * @return List<ServiceRegistrationImpl>
	 */
	private List<ServiceRegistrationImpl<?>> lookupServiceRegistrations(BundleContextImpl context) {
		Queue<ServiceRegistrationImpl<?>> result = publishedServicesByContext.get(context);

		if ((result == null) || result.isEmpty()) {
			List<ServiceRegistrationImpl<?>> empty = Collections.<ServiceRegistrationImpl<?>> emptyList();
			return empty;
		}

		return new ArrayList<>(result); /* make a new list since we don't want to change the real list */
	}

	/**
	 * Remove Service Registrations in the data structure by BundleContext.
	 * 
	 * @param context The BundleContext for which to remove Service Registrations.
	 */
	private void removeServiceRegistrations(BundleContextImpl context) {
		publishedServicesByContext.remove(context);
	}

	/**
	 * Check for permission to register a service.
	 * 
	 * The caller must have permission for ALL names.
	 */
	private static void checkRegisterServicePermission(String[] names) {
		SecurityManager sm = System.getSecurityManager();
		if (sm == null) {
			return;
		}
		for (int i = 0, len = names.length; i < len; i++) {
			sm.checkPermission(new ServicePermission(names[i], ServicePermission.REGISTER));
		}
	}

	/**
	 * Check for permission to get a service.
	 */
	private static void checkGetServicePermission(ServiceReference<?> reference) {
		SecurityManager sm = System.getSecurityManager();
		if (sm == null) {
			return;
		}
		sm.checkPermission(new ServicePermission(reference, ServicePermission.GET));
	}

	/**
	 * Check for permission to listen to a service.
	 */
	static boolean hasListenServicePermission(ServiceEvent event, BundleContextImpl context) {
		ModuleRevision revision = context.getBundleImpl().getModule().getCurrentRevision();
		if (revision == null) {
			return false;
		}
		ProtectionDomain domain = ((Generation) revision.getRevisionInfo()).getDomain();
		if (domain == null) {
			return true;
		}

		return domain.implies(new ServicePermission(event.getServiceReference(), ServicePermission.GET));
	}

	/** 
	 * Return the name of the class that is not satisfied by the service object. 
	 * @param clazzes Array of class names.
	 * @param serviceObject Service object.
	 * @return The name of the class that is not satisfied by the service object.
	 */
	static String checkServiceClass(final String[] clazzes, final Object serviceObject) {
		ClassLoader cl = serviceObject.getClass().getClassLoader();

		for (int i = 0, len = clazzes.length; i < len; i++) {
			try {
				Class<?> serviceClazz = cl == null ? Class.forName(clazzes[i]) : cl.loadClass(clazzes[i]);
				if (!serviceClazz.isInstance(serviceObject))
					return clazzes[i];
			} catch (ClassNotFoundException e) {
				//This check is rarely done
				if (extensiveCheckServiceClass(clazzes[i], serviceObject.getClass()))
					return clazzes[i];
			}
		}
		return null;
	}

	private static boolean extensiveCheckServiceClass(String clazz, Class<?> serviceClazz) {
		if (clazz.equals(serviceClazz.getName()))
			return false;
		Class<?>[] interfaces = serviceClazz.getInterfaces();
		for (int i = 0, len = interfaces.length; i < len; i++)
			if (!extensiveCheckServiceClass(clazz, interfaces[i]))
				return false;
		Class<?> superClazz = serviceClazz.getSuperclass();
		if (superClazz != null)
			if (!extensiveCheckServiceClass(clazz, superClazz))
				return false;
		return true;
	}

	static boolean isAssignableTo(BundleContextImpl context, ServiceReferenceImpl<?> reference) {
		Bundle bundle = context.getBundleImpl();
		String[] clazzes = reference.getClasses();
		for (int i = 0, len = clazzes.length; i < len; i++)
			if (!reference.isAssignableTo(bundle, clazzes[i]))
				return false;
		return true;
	}

	/**
	 * Call the registered FindHook services to allow them to inspect and possibly shrink the result.
	 * The FindHook must be called in order: descending by service.ranking, then ascending by service.id.
	 * This is the natural order for ServiceReference.
	 * 
	 * @param context The context of the bundle getting the service references.
	 * @param clazz The class name used to search for the service references.
	 * @param filterstring The filter used to search for the service references.
	 * @param allservices True if getAllServiceReferences called.
	 * @param result The result to return to the caller which may have been shrunk by the FindHooks.
	 */
	private void notifyFindHooks(final BundleContextImpl context, final String clazz, final String filterstring, final boolean allservices, final Collection<ServiceReference<?>> result) {
		notifyFindHooksPrivileged(context, clazz, filterstring, allservices, result);
	}

	void notifyFindHooksPrivileged(final BundleContextImpl context, final String clazz, final String filterstring, final boolean allservices, final Collection<ServiceReference<?>> result) {
		if (debug.DEBUG_HOOKS) {
			Debug.println("notifyServiceFindHooks(" + context.getBundleImpl() + "," + clazz + "," + filterstring + "," + allservices + "," + result + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		}
		notifyHooksPrivileged(new HookContext() {
			public void call(Object hook, ServiceRegistration<?> hookRegistration) throws Exception {
				if (hook instanceof FindHook) {
					((FindHook) hook).find(context, clazz, filterstring, allservices, result);
				}
			}

			public String getHookClassName() {
				return findHookName;
			}

			public String getHookMethodName() {
				return "find"; //$NON-NLS-1$
			}

			@Override
			public boolean skipRegistration(ServiceRegistration<?> hookRegistration) {
				return false;
			}
		});
	}

	/**
	 * Call the registered EventHook services to allow them to inspect and possibly shrink the result.
	 * The EventHooks must be called in order: descending by service.ranking, then ascending by service.id.
	 * This is the natural order for ServiceReference.
	 * 
	 * @param event The service event to be delivered.
	 * @param result The result to return to the caller which may have been shrunk by the EventHooks.
	 */
	private void notifyEventHooksPrivileged(final ServiceEvent event, final Collection<BundleContext> result) {
		if (debug.DEBUG_HOOKS) {
			Debug.println("notifyServiceEventHooks(" + event.getType() + ":" + event.getServiceReference() + "," + result + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
		}
		notifyHooksPrivileged(new HookContext() {
			@SuppressWarnings("deprecation")
			public void call(Object hook, ServiceRegistration<?> hookRegistration) throws Exception {
				if (hook instanceof EventHook) {
					((EventHook) hook).event(event, result);
				}
			}

			public String getHookClassName() {
				return eventHookName;
			}

			public String getHookMethodName() {
				return "event"; //$NON-NLS-1$
			}

			@Override
			public boolean skipRegistration(ServiceRegistration<?> hookRegistration) {
				return false;
			}
		});
	}

	/**
	 * Call the registered EventListenerHook services to allow them to inspect and possibly shrink the result.
	 * The EventListenerHooks must be called in order: descending by service.ranking, then ascending by service.id.
	 * This is the natural order for ServiceReference.
	 * 
	 * @param event The service event to be delivered.
	 * @param result The result to return to the caller which may have been shrunk by the EventListenerHooks.
	 */
	private void notifyEventListenerHooksPrivileged(final ServiceEvent event, final Map<BundleContext, Collection<ListenerInfo>> result) {
		if (debug.DEBUG_HOOKS) {
			Debug.println("notifyServiceEventListenerHooks(" + event.getType() + ":" + event.getServiceReference() + "," + result + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
		}
		notifyHooksPrivileged(new HookContext() {
			public void call(Object hook, ServiceRegistration<?> hookRegistration) throws Exception {
				if (hook instanceof EventListenerHook) {
					((EventListenerHook) hook).event(event, result);
				}
			}

			public String getHookClassName() {
				return eventListenerHookName;
			}

			public String getHookMethodName() {
				return "event"; //$NON-NLS-1$
			}

			@Override
			public boolean skipRegistration(ServiceRegistration<?> hookRegistration) {
				return false;
			}
		});
	}

	/**
	 * Calls all hook services of the type specified by the hook context.
	 * 
	 * @param hookContext Context to use when calling the hook services.
	 */
	public void notifyHooksPrivileged(HookContext hookContext) {
		List<ServiceRegistrationImpl<?>> hooks = lookupServiceRegistrations(hookContext.getHookClassName(), null);
		// Since the list is already sorted, we don't need to sort the list to call the hooks
		// in the proper order.

		for (ServiceRegistrationImpl<?> registration : hooks) {
			notifyHookPrivileged(systemBundleContext, registration, hookContext);
		}
	}

	/**
	 * Call a hook service via a hook context.
	 * 
	 * @param context Context of the bundle to get the hook service.
	 * @param registration Hook service to call.
	 * @param hookContext Context to use when calling the hook service.
	 */
	private void notifyHookPrivileged(BundleContextImpl context, ServiceRegistrationImpl<?> registration, HookContext hookContext) {
		if (hookContext.skipRegistration(registration)) {
			return;
		}
		Object hook = registration.getSafeService(context, ServiceConsumer.singletonConsumer);
		if (hook == null) { // if the hook is null
			return;
		}
		try {
			hookContext.call(hook, registration);
		} catch (Throwable t) {
			if (debug.DEBUG_HOOKS) {
				Debug.println(hook.getClass().getName() + "." + hookContext.getHookMethodName() + "() exception: " + t.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
				Debug.printStackTrace(t);
			}
			// allow the adaptor to handle this unexpected error
			container.handleRuntimeError(t);
			ServiceException se = new ServiceException(NLS.bind(Msg.SERVICE_FACTORY_EXCEPTION, hook.getClass().getName(), hookContext.getHookMethodName()), t);
			container.getEventPublisher().publishFrameworkEvent(FrameworkEvent.ERROR, registration.getBundle(), se);
		} finally {
			registration.ungetService(context, ServiceConsumer.singletonConsumer, null);
		}
	}

	/**
	 * Call a newly registered ListenerHook service to provide the current collection of
	 * service listeners.
	 * 
	 * @param registration The newly registered ListenerHook service.
	 */
	private void notifyNewListenerHook(final ServiceRegistrationImpl<?> registration) {
		notifyNewListenerHookPrivileged(registration);
	}

	void notifyNewListenerHookPrivileged(ServiceRegistrationImpl<?> registration) {
		if (debug.DEBUG_HOOKS) {
			Debug.println("notifyServiceNewListenerHook(" + registration + ")"); //$NON-NLS-1$ //$NON-NLS-2$ 
		}

		Collection<ListenerInfo> addedListeners = new ArrayList<>(_serviceListenerFilteredServiceListeners.values());

		final Collection<ListenerInfo> listeners = Collections.unmodifiableCollection(addedListeners);
		notifyHookPrivileged(systemBundleContext, registration, new HookContext() {
			public void call(Object hook, ServiceRegistration<?> hookRegistration) throws Exception {
				if (hook instanceof ListenerHook) {
					((ListenerHook) hook).added(listeners);
				}
			}

			public String getHookClassName() {
				return listenerHookName;
			}

			public String getHookMethodName() {
				return "added"; //$NON-NLS-1$
			}

			@Override
			public boolean skipRegistration(ServiceRegistration<?> hookRegistration) {
				return false;
			}
		});
	}

	/**
	 * Call the registered ListenerHook services to notify them of newly added or removed service listeners.
	 * The ListenerHook must be called in order: descending by service.ranking, then ascending by service.id.
	 * This is the natural order for ServiceReference.
	 * 
	 * @param listeners A non-empty, unmodifiable collection of ListenerInfo objects. 
	 * All elements in the list must be for the same bundle. 
	 * @param added <code>true</code> if the specified listeners are being added. <code>false</code>
	 * if they are being removed.
	 */
	private void notifyListenerHooks(final Collection<ListenerInfo> listeners, final boolean added) {
		notifyListenerHooksPrivileged(listeners, added);
	}

	void notifyListenerHooksPrivileged(final Collection<ListenerInfo> listeners, final boolean added) {
		assert !listeners.isEmpty();
		if (debug.DEBUG_HOOKS) {
			Debug.println("notifyServiceListenerHooks(" + listeners + "," + (added ? "added" : "removed") + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}

		notifyHooksPrivileged(new HookContext() {
			public void call(Object hook, ServiceRegistration<?> hookRegistration) throws Exception {
				if (hook instanceof ListenerHook) {
					if (added) {
						((ListenerHook) hook).added(listeners);
					} else {
						((ListenerHook) hook).removed(listeners);
					}
				}
			}

			public String getHookClassName() {
				return listenerHookName;
			}

			public String getHookMethodName() {
				return added ? "added" : "removed"; //$NON-NLS-1$ //$NON-NLS-2$
			}

			@Override
			public boolean skipRegistration(ServiceRegistration<?> hookRegistration) {
				return false;
			}
		});
	}

	final EquinoxContainer getContainer() {
		return container;
	}
}
/* @generated */
