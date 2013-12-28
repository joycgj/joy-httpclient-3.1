package org.apache.commons.httpclient;

/**
 * Represents a Status-Line as returned from a HTTP server.
 *
 * <a href="http://www.ietf.org/rfc/rfc2616.txt">RFC2616</a> states
 * the following regarding the Status-Line:
 * <pre>
 * 6.1 Status-Line
 *
 *  The first line of a Response message is the Status-Line, consisting
 *  of the protocol version followed by a numeric status code and its
 *  associated textual phrase, with each element separated by SP
 *  characters. No CR or LF is allowed except in the final CRLF sequence.
 *
 *      Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
 * </pre>
 * <p>
 * This class is immutable and is inherently thread safe.(内在线程安全)
 *
 * @see HttpStatus
 * @author <a href="mailto:jsdever@apache.org">Jeff Dever</a>
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @version $Id: StatusLine.java 480424 2006-11-29 05:56:49Z bayard $
 * @since 2.0
 */
public class StatusLine {

    // ----------------------------------------------------- Instance Variables

    /** The original Status-Line. */
    private final String statusLine;

    /** The HTTP-Version. */
    private final String httpVersion;

    /** The Status-Code. */
    private final int statusCode;

    /** The Reason-Phrase. */
    private final String reasonPhrase;


    // ----------------------------------------------------------- Constructors

    /**
     * Default constructor.
     *
     * @param statusLine the status line returned from the HTTP server
     * @throws HttpException if the status line is invalid
     */
    public StatusLine(final String statusLine) throws HttpException {

        int length = statusLine.length();
        int at = 0;
        int start = 0;
        try {
            while (Character.isWhitespace(statusLine.charAt(at))) {
                ++at;
                ++start;
            }
            if (!"HTTP".equals(statusLine.substring(at, at += 4))) {
                throw new HTTPException("Status-Line '" + statusLine
                    + "' does not start with HTTP");
            }
            // handle the HTTP-Version
            at = statusLine.indexOf(" ", at);
            if (at <= 0) {
                throw new ProtocolException(
                        "Unable to parse HTTP-Version from the status line: '"
                        + statusLine + "'");
            }
            this.httpVersion = (statusLine.substring(start, at)).toUpperCase();


        } catch (StringIndexOutOfBoundsException e) {

        }
    }
}
