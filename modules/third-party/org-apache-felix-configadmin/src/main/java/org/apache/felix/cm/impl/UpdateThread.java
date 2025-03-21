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
package org.apache.felix.cm.impl;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.osgi.service.log.LogService;


/**
 * The <code>UpdateThread</code> is the thread used to update managed services
 * and managed service factories as well as to send configuration events.
 */
public class UpdateThread implements Runnable
{

    // the thread group into which the worker thread will be placed
    private final ThreadGroup workerThreadGroup;

    // the thread's base name
    private final String workerBaseName;

    // the queue of Runnable instances  to be run
    private final BlockingDeque<Runnable> updateTasks = new LinkedBlockingDeque<>();

    // the actual thread
    private volatile Thread worker;

    // the access control context
    private final AccessControlContext acc;

    public UpdateThread( final ThreadGroup tg, final String name )
    {
        this.workerThreadGroup = tg;
        this.workerBaseName = name;
        this.acc = AccessController.getContext();
    }


    // waits on Runnable instances coming into the queue. As instances come
    // in, this method calls the Runnable.run method, logs any exception
    // happening and keeps on waiting for the next Runnable. If the Runnable
    // taken from the queue is this thread instance itself, the thread
    // terminates.
    @Override
    public void run()
    {
        try
        {
            Runnable task;
            // return if the task is this thread itself
            while ((task = updateTasks.take()) != this)
            {
                // otherwise execute the task, log any issues
                try
                {
                    // set the thread name indicating the current task
                    Thread.currentThread().setName( workerBaseName + " (" + task + ")" );

                    Log.logger.log( LogService.LOG_DEBUG, "Running task {0}", new Object[]
                        { task } );

                    run0(task);
                }
                catch ( Throwable t )
                {
                    Log.logger.log( LogService.LOG_ERROR, "Unexpected problem executing task", t );
                }
                finally
                {
                    // reset the thread name to "idle"
                    Thread.currentThread().setName( workerBaseName );
                }
            }
        }
        catch (InterruptedException e)
        {
            // don't care
        }
    }

    void run0(final Runnable task) throws Throwable {
        if (System.getSecurityManager() != null) {
            try {
                AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Void>() {
                        @Override
                        public Void run() throws Exception {
                            task.run();
                            return null;
                        }
                    },
                    acc
                );
            }
            catch (PrivilegedActionException pae) {
                throw pae.getException();
            }
        }
        else {
            task.run();
        }
    }

    /**
     * Starts processing the queued tasks. This method does nothing if the
     * worker has already been started.
     */
    synchronized void start()
    {
        if ( this.worker == null )
        {
            Thread workerThread = new Thread( workerThreadGroup, this, workerBaseName );
            workerThread.setDaemon( true );
            workerThread.start();
            this.worker = workerThread;
        }
    }


    /**
     * Terminates the worker thread and waits for the thread to have processed
     * all outstanding events up to and including the termination job. All
     * jobs {@link #schedule(Runnable) scheduled} after termination has been
     * initiated will not be processed any more. This method does nothing if
     * the worker thread is not currently active.
     * <p>
     * If the worker thread does not terminate within 5 seconds it is killed
     * by calling the <code>Thread.interrupt()</code> method.
     */
    synchronized void terminate()
    {
        if ( this.worker != null )
        {
            Thread workerThread = this.worker;
            this.worker = null;

            updateTasks.offerFirst( this );

            // wait for all updates to terminate (<= 10 seconds !)
            try
            {
                workerThread.join( 5000 );

                if ( workerThread.isAlive() )
                {
                    Log.logger.log( LogService.LOG_ERROR,
                        "Worker thread {0} did not terminate within 5 seconds; trying to kill", new Object[]
                            { workerBaseName } );
                    workerThread.interrupt();
                }
            }
            catch ( InterruptedException ie )
            {
                workerThread.interrupt();
            }
        }
    }


    // queue the given runnable to be run as soon as possible
    void schedule( Runnable update )
    {
        Log.logger.log( LogService.LOG_DEBUG, "Scheduling task {0}", new Object[]
            { update } );

        // append to the task queue
        updateTasks.offer( update );
    }
}
/* @generated */
