package org.apache.commons.httpclient.params;

public class DefaultHttpParamsFactory implements HttpParamsFactory {

    private HttpParams httpParams;

    public DefaultHttpParamsFactory() {
        super();
    }

    /**
     * @see HttpParamsFactory#getDefaultParams()
     */
    @Override
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
