/*
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//httpclient/src/java/org/apache/commons/httpclient/params/DefaultHttpParamsFactory.java,v 1.16 2004/11/20 21:48:47 mbecke Exp $
 * $Revision: 566065 $
 * $Date: 2007-08-15 10:34:30 +0200 (Wed, 15 Aug 2007) $
 *
 * ====================================================================
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.commons.httpclient.params;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.util.DateUtil;

/**
 * @since 3.0
 */
public class DefaultHttpParamsFactory implements HttpParamsFactory {

    private HttpParams httpParams;

    /**
     *
     */
    public DefaultHttpParamsFactory() {
        super();
    }

    /* (non-Javadoc)
     * @see org.apache.commons.httpclient.params.HttpParamsFactory#getDefaultParams()
     */
    public synchronized HttpParams getDefaultParams() {
        if (httpParams == null) {
            httpParams = createParams();
        }

        return httpParams;
    }

    protected HttpParams createParams() {
        HttpClientParams params = new HttpClientParams(null);


    }
}
