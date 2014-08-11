package com.marmotlabs.ticketcenter.dao.utils;

import com.marmotlabs.ticketcenter.vo.search.EventSearchCriteriaVO;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Zuui
 */
public class EventSearchQueryBuilder {

    private static final String EVENTS = "select e \n";
    private static final String EVENTS_COUNT = "select count(e) \n";

    /**
     * Builds the count JPA-QL query string based on the provided searchCriteria
     * and the map of all the parameters set in the query along with their
     * values.
     *
     * The returned object will be used in the DAO layer to perform the search.
     *
     * @param searchCriteria
     * @return the EventSearchQuery containing a query and its parameters
     * according to the provided searchCriteria
     */
    public static EventSearchQuery getCountQuery(final EventSearchCriteriaVO searchCriteria) {
        return getQuery(EVENTS_COUNT, searchCriteria);
    }

    /**
     * Builds the search JPA-QL query string based on the provided
     * searchCriteria and the map of all the parameters set in the query along
     * with their values.
     *
     * The returned object will be used in the DAO layer to perform the search.
     *
     * @param searchCriteria
     * @return the EventSearchQuery containing a query and its parameters
     * according to the provided searchCriteria
     */
    public static EventSearchQuery getSearchQuery(final EventSearchCriteriaVO searchCriteria) {
        return getQuery(EVENTS, searchCriteria);
    }

    private static EventSearchQuery getQuery(final String mode, final EventSearchCriteriaVO searchCriteria) {
        EventSearchQuery searchQuery = new EventSearchQuery();

        StringBuilder queryString = new StringBuilder();
        Map<String, Object> parameters = new HashMap<String, Object>();

        // "select e" or "select count(e)"
        queryString.append(mode);

        queryString.append("from Event e \n");

        if (searchCriteria.hasFilters()) {
            if (searchCriteria.hasCityId()) {
                // Appending the join tables needed to filter by city
                queryString.append(", Location l \n");
                queryString.append(", City c \n");
            }

            // JPA-QL trick
            queryString.append(" where 1=1 \n");

            if (searchCriteria.hasCityId()) {
                // Appending the join conditions to filter by city
                queryString.append(" and e.location.id = l.id \n");
                queryString.append(" and l.city.id = c.id \n");
            }

            // name
            if (searchCriteria.hasNameKeyword()) {
                // Appending the nameKeyword condition
                queryString.append(" and UPPER(e.name) like :nameKeyword \n");
                String nameKeyword = searchCriteria.getNameKeyword();
                parameters.put("nameKeyword", "%" + nameKeyword.toUpperCase() + "%");
            }

            // city
            if (searchCriteria.hasCityId()) {
                // Appending the city condition
                queryString.append(" and c.id = :cityId \n");
                Long cityId = searchCriteria.getCityId();
                parameters.put("cityId", cityId);
            }

            // dateAfter
            if (searchCriteria.hasDateAfter()) {
                // Appending the dateAfter condition
                queryString.append(" and e.date >= :dateAfter \n");
                Date dateAfter = searchCriteria.getAfterDate();
                parameters.put("dateAfter", dateAfter);
            }

            // dateBefore
              if (searchCriteria.hasDateBefore()) {
                // Appending the dateBefore condition
                queryString.append(" and e.date <= :dateBefore \n");
                Date dateBefore = searchCriteria.getBeforeDate();
                parameters.put("dateBefore", dateBefore);
            }
            
            // Do not group by event ID when counting
            if (EVENTS.equals(mode)) {
                queryString.append(" group by e.id \n");
            }
        }

        searchQuery.setQueryString(queryString);
        searchQuery.setParameters(parameters);
        return searchQuery;
    }

}
