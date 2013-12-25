package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A factory for creating Sockets.
 *
 * <p>Both {@link java.lang.Object#equals(java.lang.Object) Object.equals()} and
 * {@link java.lang.Object#hashCode() Object.hashCode()} should be overridden appropriately.
 * Protocol socket factories are used to uniquely identify <code>Protocol</code>s and
 * <code>HostConfiguration</code>s, and <code>equals()</code> and <code>hashCode()</code> are
 * required for the correct operation of some connection managers.</p>
 *
 * @see Protocol
 */
public interface ProtocolSocketFactory {

    /**
     * Gets a new socket connection to the given host
     *
     * @param host the host name/IP
     * @param port the port on the host
     * @param localAddress the local host name/IP to bind the socket to
     * @param localPort the port on the local machine
     *
     * @return Socket a new socket
     *
     * @throws IOException if an I/O error occurs while creating the socket
     * @throws UnknownHostException if the IP address of the host cannot be
     * determined
     */
    Socket createSocket(
        String host,
        int port,
        InetAddress localAddress,
        int localPort
    ) throws IOException, UnknownHostException;

    /**
     * Gets a new socket connection to the given host
     *
     * @param host the host name/IP
     * @param port the port on the host
     * @param localAddress the local host name/IP to bind the socket to
     * @param localPort the port on the local machine
     * @param params {@link HttpConnectionParams Http connection parameters}
     *
     * @return Socket a new socket
     *
     * @throws IOException if an I/O error occurs while creating the socket
     * @throws UnknownHostException if the IP address of the host cannot be
     * determined
     * @throws ConnectionTimeoutException if socket cannot be connected within the
     * given time limit
     */
    Socket createSocket(
        String host,
        int port,
        InetAddress localAddress,
        int localPort,
        HttpConnectionParams params
    ) throws IOException, UnknownHostException, ConnectionTimeoutException;

    /**
     * Gets a new socket connection to the given host.
     *
     * @param host the host name/IP
     *
     * @param port the port on the host
     *
     * @return Socket a new socket
     *
     * @throws IOException if an I/O error occurs while creating the socket
     * @throws UnknownHostException if the IP address of the host cannot be
     * determined
     */
    Socket createSocket(
        String host,
        int port
    ) throws IOException, UnknownHostException;
}
