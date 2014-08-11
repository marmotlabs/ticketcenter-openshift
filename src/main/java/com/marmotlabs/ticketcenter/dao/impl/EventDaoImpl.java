package com.marmotlabs.ticketcenter.dao.impl;

import com.marmotlabs.ticketcenter.dao.EventDao;
import com.marmotlabs.ticketcenter.dao.utils.SearchDaoUtils;
import com.marmotlabs.ticketcenter.dao.utils.EventSearchQuery;
import com.marmotlabs.ticketcenter.dao.utils.EventSearchQueryBuilder;
import com.marmotlabs.ticketcenter.domain.Event;
import com.marmotlabs.ticketcenter.vo.search.EventSearchCriteriaVO;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Zuui
 */
@Repository
public class EventDaoImpl extends AbstractDaoImpl<Event, Long> implements EventDao {

    public EventDaoImpl() {
        super(Event.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Event> findEventsByCriteria(EventSearchCriteriaVO searchCriteria, Pageable pageable) {
        EventSearchQuery searchQuery = EventSearchQueryBuilder.getSearchQuery(searchCriteria);

        StringBuilder queryString = searchQuery.getQueryString();
        Map<String, Object> parameters = searchQuery.getParameters();

        SearchDaoUtils.applySorting(queryString, pageable);

        Query query = getCurrentSession().createQuery(queryString.toString());
        SearchDaoUtils.applyParameters(query, parameters);
        SearchDaoUtils.applyPaging(query, pageable);

        return (List<Event>) query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long countEventsByCriteria(EventSearchCriteriaVO searchCriteria) {
        // Build the JPA-QL count query, with parameters to be set
        EventSearchQuery countQuery = EventSearchQueryBuilder.getCountQuery(searchCriteria);

        StringBuilder queryString = countQuery.getQueryString();
        Map<String, Object> parameters = countQuery.getParameters();

        Query query = getCurrentSession().createQuery(queryString.toString());
        SearchDaoUtils.applyParameters(query, parameters);

        return ((Number) query.uniqueResult()).longValue();
    }

}
