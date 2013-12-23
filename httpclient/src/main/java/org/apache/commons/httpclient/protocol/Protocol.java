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

    /**
     * Registers a new protocol with the given identifier. If a protocol with
     * the given ID already exists it will be overridden. This ID is the same
     * one used to retrieve the protocol from getProtocol(String).
     *
     * @param id the identifier for this protocol
     * @param protocol the protocol to register
     *
     * @see #getProtocol(String)
     */
    public static void registerProtocol(String id, Protocol protocol) {

        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        if (protocol == null) {
            throw new IllegalArgumentException("protocol is null");
        }

        PROTOCOLS.put(id, protocol);
    }

    /**
     * Unregisters the protocol with the given ID.
     *
     * @param id the ID of the protocol to remove
     */
    public static void unregisterProtocol(String id) {

        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        PROTOCOLS.remove(id);
    }

    public static Protocol getProtocol(String id) throws IllegalStateException {

        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        Protocol protocol = (Protocol) PROTOCOLS.get(id);

        if (protocol == null) {
            protocol = lazyRegisterProtocol(id);
        }

        return protocol;
    }

    /**
     * Lazily registers the protocol with the given id.
     *
     * @param id teh protocol ID
     *
     * @return the lazily registered protocol
     *
     * @throws IllegalStateException if the protocol with id is not recognized
     */
    private static Protocol lazyRegisterProtocol(String id) throws IllegalStateException {

        if ("http".equals(id)) {
            final Protocol http
                    = new Protocol("http", DefaultProtocolSocketFactory.getSocketFactory(), 80);
            Protocol.registerProtocol("http", http);
            return http;
        }

        if ("https".equals(id)) {
            final Protocol https
                = new Protocol("https", SSLProtocolSocketFactory.getSocketFactory(), 443);
            Protocol.registerProtocol("https", https);
            return https;
        }

        throw new IllegalStateException("unsupported protocol: '" + id + "'");
    }

    /** the scheme of this protocol (e.g. http, https) */
    private String scheme;

    /** The socket factory for this protocol */
    private ProtocolSocketFactory socketFactory;

    /** The default port for this protocol */
    private int defaultPort;

    /** True if this protocol is secure */
    private boolean secure;

    public Protocol(String scheme, ProtocolSocketFactory factory, int defaultPort) {

        if (scheme == null) {
            throw new IllegalArgumentException("scheme is null");
        }
        if (factory == null) {
            throw new IllegalArgumentException("socketFactory is null");
        }
        if (defaultPort <= 0) {
            throw new IllegalArgumentException("port is invalid: " + defaultPort);
        }

        this.scheme = scheme;
        this.socketFactory = factory;
        this.defaultPort = defaultPort;
        this.secure = (factory instanceof SecureProtocolSocketFactory);
    }
}
