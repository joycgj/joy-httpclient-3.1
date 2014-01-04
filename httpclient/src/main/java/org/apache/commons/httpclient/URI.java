/*
 * $HeadURL: https://svn.apache.org/repos/asf/jakarta/httpcomponents/oac.hc3x/tags/HTTPCLIENT_3_1/src/java/org/apache/commons/httpclient/URI.java $
 * $Revision: 564973 $
 * $Date: 2007-08-11 22:51:47 +0200 (Sat, 11 Aug 2007) $
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

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;
import java.util.BitSet;
import java.util.Hashtable;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.util.EncodingUtil;

/**
 * The interface for the URI(Uniform Resource Identifiers) version of RFC 2396.
 * This class has the purpose of supportting of parsing a URI reference to
 * extend any specific protocols, the character encoding of the protocol to
 * be transported and the charset of the document.
 * <p>
 * A URI is always in an "escaped" form, since escaping or unescaping a
 * completed URI might change its semantics.
 * <p>
 * Implementers should be careful not to escape or unescape the same string
 * more than once, since unescaping an already unescaped string might lead to
 * misinterpreting a percent data character as another escaped character,
 * or vice versa in the case of escaping an already escaped string.
 * <p>
 * In order to avoid these problems, data types used as follows:
 * <p><blockquote><pre>
 *   URI character sequence: char
 *   octet sequence: byte
 *   original character sequence: String
 * </pre></blockquote><p>
 *
 * So, a URI is a sequence of characters as an array of a char type, which
 * is not always represented as a sequence of octets as an array of byte.
 * <p>
 *
 * URI Syntactic Components
 * <p><blockquote><pre>
 * - In general, written as follows:
 *   Absolute URI = &lt;scheme&gt:&lt;scheme-specific-part&gt;
 *   Generic URI = &lt;scheme&gt;://&lt;authority&gt;&lt;path&gt;?&lt;query&gt;
 *
 * - Syntax
 *   absoluteURI   = scheme ":" ( hier_part | opaque_part )
 *   hier_part     = ( net_path | abs_path ) [ "?" query ]
 *   net_path      = "//" authority [ abs_path ]
 *   abs_path      = "/"  path_segments
 * </pre></blockquote><p>
 *
 * The following examples illustrate URI that are in common use.
 * <pre>
 * ftp://ftp.is.co.za/rfc/rfc1808.txt
 *    -- ftp scheme for File Transfer Protocol services
 * gopher://spinaltap.micro.umn.edu/00/Weather/California/Los%20Angeles
 *    -- gopher scheme for Gopher and Gopher+ Protocol services
 * http://www.math.uio.no/faq/compression-faq/part1.html
 *    -- http scheme for Hypertext Transfer Protocol services
 * mailto:mduerst@ifi.unizh.ch
 *    -- mailto scheme for electronic mail addresses
 * news:comp.infosystems.www.servers.unix
 *    -- news scheme for USENET news groups and articles
 * telnet://melvyl.ucop.edu/
 *    -- telnet scheme for interactive services via the TELNET Protocol
 * </pre>
 * Please, notice that there are many modifications from URL(RFC 1738) and
 * relative URL(RFC 1808).
 * <p>
 * <b>The expressions for a URI</b>
 * <p><pre>
 * For escaped URI forms
 *  - URI(char[]) // constructor
 *  - char[] getRawXxx() // method
 *  - String getEscapedXxx() // method
 *  - String toString() // method
 * <p>
 * For unescaped URI forms
 *  - URI(String) // constructor
 *  - String getXXX() // method
 * </pre><p>
 *
 * @author <a href="mailto:jericho@apache.org">Sung-Gu</a>
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @version $Revision: 564973 $ $Date: 2002/03/14 15:14:01
 */
public class URI implements Cloneable, Comparable, Serializable {


    // ----------------------------------------------------------- Constructors

    /** Create an instance as an internal use */
    protected URI() {
    }

    /**
     * Construct a URI from a string with the given charset. The input string can
     * be either in escaped or unescaped form.
     *
     * @param s URI character sequence
     * @param escaped <tt>true</tt> if URI character sequence is in escaped form.
     *                <tt>false</tt> otherwise.
     * @param charset the charset string to do escape encoding, if required
     *
     * @throws URIException If the URI cannot be created.
     * @throws NullPointerException if input string is <code>null</code>
     *
     * @see #getProtocolCharset
     *
     * @since 3.0
     */
    public URI(String s, boolean escaped, String charset)
            throws URIException, NullPointerException {
        protocolCharset = charset;
        parseUriReference(s, escaped);
    }


}
