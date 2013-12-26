package org.apache.commons.httpclient.params;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represents a collection of HTTP protocol parameters. Protocol parameters
 * may be linked together to from a hierarchy. If a particular parameter value has not been
 * explicitly defined in the collection itself, its value will be drawn from the parent
 * collection of parameters.
 */
public class DefaultHttpParams implements HttpParams, Serializable, Cloneable {

    /** Log objects for this class. */
    private static final Log LOG = LogFactory.getLog(DefaultHttpParams.class);

    private static HttpParamsFactory httpParamsFactory = new DefaultHttpParamsFactory();

    /**
     * Gets the default HttpParams to be used.
     *
     * @return the value returned from <code>HttpParamsFactory#getDefaultParams()</code>
     *
     * @see org.apache.commons.httpclient.params.HttpParamsFactory#getDefaultParams()
     */
    public static HttpParams getDefaultParams() {
        return httpParamsFactory.getDefaultParams();
    }

    /**
     * Sets the factory that will provide the default HttpParams.
     *
     * @param httpParamsFactory an instance of HttpParamsFactory
     *
     * @see #getDefaultParams()
     */
    public static void setHttpParamsFactory(HttpParamsFactory httpParamsFactory) {
        if (httpParamsFactory == null) {
            throw new IllegalArgumentException("httpParamsFactory may not be null");
        }
        DefaultHttpParams.httpParamsFactory = httpParamsFactory;
    }

    /** The set of default values to defer to */
    private HttpParams defaults = null;

    /** Hash map of HTTP parameters that this collection contains */
    private HashMap parameters = null;

    /**
     * Creates a new collection of parameters with the given parent.
     * The collection will defer to its parent for a default value
     * if a particular parameter is not explicitly set in the collection
     * itself.
     *
     * @param defaults the parent collection to defer to, if a parameter
     * is not explicitly set in the collection itself.
     */
    public DefaultHttpParams(final HttpParams defaults) {
        super();
        this.defaults = defaults;
    }

    /**
     * Creates a new collection of parameters with the collection returned
     * by {@link #getDefaultParams()} as a parent. The collection will defer
     * to its parent for a default value if a particular parameter is not
     * explicitly set in the collection itself.
     *
     * @see #getDefaultParams()
     */
    public DefaultHttpParams() {
        this(getDefaultParams());
    }

    @Override
    public synchronized HttpParams getDefaults() {
        return this.defaults;
    }

    @Override
    public void setDefaults(HttpParams params) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public synchronized Object getParameter(final String name) {
        // See if the parameter has been explicitly defined
        Object param = null;
        if (this.parameters != null) {
            param = this.parameters.get(name);
        }
        if (param != null) {
            // If so, return

        } else {return param;
            // If not, see if defaults are available
            if (this.defaults != null) {
                // Return default parameter value
                return this.defaults.getParameter(name);
            } else {
                // Otherwise, return null
                return null;
            }
        }

    }

    @Override
    public synchronized void setParameter(final String name, final Object value) {
        if (this.parameters == null) {
            this.parameters = new HashMap();
        }
        this.parameters.put(name, value);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Set parameter " + name + " = " + value);
        }
    }

    /**
     * Assign the value to all the parameter with the given names
     *
     * @param names array of parameter name
     * @param value parameter value
     */
    public synchronized void setParameters(final String[] names, final Object value) {
        for (int i = 0; i < names.length; i++) {
            setParameter(names[i], value);
        }
    }

    @Override
    public long getLongParameter(final String name, long defaultValue) {
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Long)param).longValue();
    }

    @Override
    public void setLongParameter(String name, long value) {
        setParameter(name, new Long(value));
    }

    @Override
    public int getIntParameter(String name, int defaultValue) {
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Integer)param).intValue();
    }

    @Override
    public void setIntParameter(String name, int value) {
        setParameter(name, new Integer(value));
    }

    @Override
    public double getDoubleParameter(String name, double defaultValue) {
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Double)param).doubleValue();
    }

    @Override
    public void setDoubleParameter(String name, double value) {
        setParameter(name, new Double(value));
    }

    @Override
    public boolean getBooleanParameter(String name, boolean defaultValue) {
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Boolean)param).booleanValue();
    }

    @Override
    public void setBooleanParameter(String name, boolean value) {
        setParameter(name, value ? Boolean.TRUE : Boolean.FALSE);// Boolean.valueOf() = Java 1.4+
    }

    @Override
    public boolean isParameterSet(String name) {
        return getParameter(name) != null;
    }

    @Override
    public boolean isParameterSetLocally(String name) {
        return this.parameters != null && this.parameters.get(name) != null;
    }

    @Override
    public boolean isParameterTrue(String name) {
        return getBooleanParameter(name, false);
    }

    @Override
    public boolean isParameterFalse(String name) {
        return !getBooleanParameter(name, false);
    }

    /**
     * Removes all parameters from this collection.
     */
    public void clear() {
        this.parameters = null;
    }

    /**
     * Clones this collection of parameters. Please note that parameter values
     * themselves are not cloned.
     *
     * @see java.io.Serializable
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        DefaultHttpParams clone = (DefaultHttpParams) super.clone();
        if (this.parameters != null) {
            clone.parameters = (HashMap) this.parameters.clone();
        }
        clone.setDefaults(this.defaults);
        return clone;
    }
}
