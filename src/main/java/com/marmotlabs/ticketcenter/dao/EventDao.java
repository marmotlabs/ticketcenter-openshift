package com.marmotlabs.ticketcenter.dao;

import com.marmotlabs.ticketcenter.domain.Event;
import com.marmotlabs.ticketcenter.vo.search.EventSearchCriteriaVO;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Zuui
 */
public interface EventDao extends AbstractDao<Event, Long> {

    /**
     * Returns the events matching the given criteria, and from the page specified by the pageable object.
     * 
     * If no events are found it returns an empty list.
     * 
     * @param searchCriteria
     * @param pageable
     * @return 
     */
    List<Event> findEventsByCriteria(EventSearchCriteriaVO searchCriteria, Pageable pageable);

    /**
     * Returns the number of events matching the given criteria.
     * 
     * @param searchCriteria
     * @return 
     */
    Long countEventsByCriteria(EventSearchCriteriaVO searchCriteria);
}
