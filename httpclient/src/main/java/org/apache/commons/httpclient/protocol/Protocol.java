package org.apache.commons.httpclient.protocol;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to encapsulate the specifics of a protocol. This class class also
 * provides the ability t customize the set and characteristics of the
 * protocols used.
 *
 * <p>One use case for modifying the default set of protocols would be to set a
 * custom SSL socket factory. This would look something like the following:
 * <pre>
 * Protocol myHTTPS = new Protocol("https", new MySSLSocketFactory(), 443);
 *
 * Protocol.registerProtocol("https", myHTTPS);
 * </pre>
 */
public class Protocol {
     /** The available protocols */
    private static final Map PROTOCOLS = Collections.synchronizedMap(new HashMap());
}
