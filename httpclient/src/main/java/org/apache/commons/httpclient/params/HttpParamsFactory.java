package org.apache.commons.httpclient.params;

/**
 * A factory for getting the default set of parameters to use when creating an instance of
 * <code>HttpParams</code>
 *
 * @see org.apache.commons.httpclient.params.DefaultHttpParams#setHttpParamsFactory(HttpParamsFactory)
 */
public interface HttpParamsFactory {

    /**
     * Gets the default parameters. This method may be called more than once and is not required
     * to always return the same value.
     *
     * @return an instance of HttpParams
     */
    HttpParams getDefaultParams(); // OK
}
