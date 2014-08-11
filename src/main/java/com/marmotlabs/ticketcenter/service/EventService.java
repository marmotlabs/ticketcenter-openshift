package com.marmotlabs.ticketcenter.service;

import com.marmotlabs.ticketcenter.domain.Event;
import com.marmotlabs.ticketcenter.vo.search.EventSearchCriteriaVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Zuui
 */
public interface EventService {

    /**
     * Returns the event with the given id, or null if none found.
     *
     * @param id
     * @return
     */
    Event getEventById(Long id);

    /**
     * Returns a paginated list of events that match the given criteria.
     *
     * @param searchCriteria
     * @param pageable
     * @return
     */
    Page<Event> getEventsByCriteria(EventSearchCriteriaVO searchCriteria, Pageable pageable);

}
