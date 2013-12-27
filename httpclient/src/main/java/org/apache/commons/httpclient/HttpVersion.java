package org.apache.commons.httpclient;

/**
 *  <p>HTTP version, as specified in RFC 2616.</p>
 *  <p>
 *  HTTP uses a "&lt;major&gt;.&lt;minor&gt;" numbering scheme to indicate
 *  versions of the protocol. The protocol versioning policy is intended to
 *  allow the sender to indicate the format of a message and its capacity for
 *  understanding further HTTP communication, rather than the features
 *  obtained via that communication. No change is made to the version
 *  number for the addition of message components which do not affect
 *  communication behavior or which only add to extensible field values.
 *  The &lt;minor&gt; number is incremented when the changes made to the
 *  protocol add features which do not change the general message parsing
 *  algorithm, but which may add to the message semantics and imply
 *  additional capabilities of the sender. The &lt;major&gt; number is
 *  incremented when the format of a message within the protocol is
 *  changed. See RFC 2145 [36] for a fuller explanation.
 *  </p>
 *  <p>
 *  The version of an HTTP message is indicated by an HTTP-Version field
 *  in the first line of the message.
 *  </p>
 *  <pre>
 *     HTTP-Version   = "HTTP" "/" 1*DIGIT "." 1*DIGIT
 *  </pre>
 *  <p>
 *   Note that the major and minor numbers MUST be treated as separate
 *   integers and that each MAY be incremented higher than a single digit.
 *   Thus, HTTP/2.4 is a lower version than HTTP/2.13, which in turn is
 *   lower than HTTP/12.3. Leading zeros MUST be ignored by recipients and
 *   MUST NOT be sent.
 *  </p>
 */
public class HttpVersion implements Comparable {

    /** Major version number of the HTTP protocol */
    private int major = 0;

    /** Minor version number of the HTTP protocol */
    private int minor = 0;

    /** HTTP protocol version 0.9 */
    public static final HttpVersion HTTP_0_9 = new HttpVersion(0, 9);

    /** HTTP protocol version 1.0 */
    public static final HttpVersion HTTP_1_0 = new HttpVersion(1, 0);

    /** HTTP protocol version 1.1 */
    public static final HttpVersion HTTP_1_1 = new HttpVersion(1, 1);

    /**
     * Create an HTTP protocol version designator.
     *
     * @param major   the major version number of the HTTP protocol
     * @param minor   the minor version number of the HTTP protocol
     *
     * @throws IllegalArgumentException if either major or minor version number is negative
     */
    public HttpVersion(int major, int minor) {
        if (major < 0) {
            throw new IllegalArgumentException("HTTP major version number may not be negative");
        }
        this.major = major;
        if (minor < 0) {
            throw new IllegalArgumentException("HTTP minor version number may not be negative");
        }
        this.minor = minor;
    }

    /**
     * Returns the major version number of the HTTP protocol.
     *
     * @return the major version number.
     */
    public int getMajor() {
        return major;
    }

    /**
     * Returns the minor version number of the HTTP protocol.
     *
     * @return the minor version number.
     */
    public int getMinor() {
        return minor;
    }


}
