package com.marmotlabs.ticketcenter.dao.utils;

import java.util.Map;

/**
 * Search helper class, containing the JPA-QL search string along with all the
 * parameters that will be set when performing the query.
 *
 * @author Zuui
 */
public class EventSearchQuery {

    private StringBuilder queryString;
    private Map<String, Object> parameters;

    public StringBuilder getQueryString() {
        return queryString;
    }

    public void setQueryString(StringBuilder queryString) {
        this.queryString = queryString;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

}
