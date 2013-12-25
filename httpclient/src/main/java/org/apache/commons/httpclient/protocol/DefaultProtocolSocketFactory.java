package org.apache.commons.httpclient.protocol;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class DefaultProtocolSocketFactory implements ProtocolSocketFactory {

    /**
     * The factory singleton.
     */
    private static final DefaultProtocolSocketFactory factory = new DefaultProtocolSocketFactory();

    /**
     * Gets an singleton instance of the DefaultProtocolSocketFactory.
     * @return a DefaultProtocolSocketFactory
     */
    public static DefaultProtocolSocketFactory getFactory() {
        return factory;
    }

    /**
     * Constructor for DefaultProtocolSocketFactory.
     */
    public DefaultProtocolSocketFactory() {
        super();
    }

    /**
     * @see #createSocket(String, int, java.net.InetAddress, int)
     */
    @Override
    public Socket createSocket(
            String host,
            int port,
            InetAddress localAddress,
            int localPort
    ) throws IOException, UnknownHostException {
        return new Socket(host, port, localAddress, localPort);
    }

    /**
     * Attempts to get a new socket connection to the given host within the given time limit.
     * <p>
     * This method employs several techniques to circumvent(用计谋战胜) the limitations of older JREs that
     * do not support connect timeout. When running in JRE 1.4 or above reflection is used to
     * call Socket#connect(SocketAddress endpoint, int timeout) method. When executing in older
     * JREs a controller thread is executed.
     * </p>
     * @param host the host name/IP
     * @param port the port on the host
     * @param localAddress the local host name/IP to bind the socket to
     * @param localPort the port on the local machine
     * @param params {@link HttpConnectionParams Http connection parameters}
     *
     * @return
     * @throws IOException
     * @throws UnknownHostException
     * @throws ConnectionTimeoutException
     */
    @Override
    public Socket createSocket(
            String host,
            int port,
            InetAddress localAddress,
            int localPort,
            HttpConnectionParams params
    ) throws IOException, UnknownHostException, ConnectionTimeoutException {
        if (params == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        int timeout = params.
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
