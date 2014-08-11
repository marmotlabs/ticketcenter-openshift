package com.marmotlabs.ticketcenter.service.impl;

import com.marmotlabs.ticketcenter.dao.EventDao;
import com.marmotlabs.ticketcenter.domain.Event;
import com.marmotlabs.ticketcenter.service.EventService;
import com.marmotlabs.ticketcenter.vo.search.EventSearchCriteriaVO;
import java.util.Collections;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Stateless service used to get events from the database.
 *
 * @author Zuui
 */
@Service("eventService")
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Event getEventById(Long id) {
        Event event = eventDao.findById(id);
        if (event != null) {
            Hibernate.initialize(event.getLocation());
            Hibernate.initialize(event.getLocation().getCity());
        }

        return event;
    }

    /**
     * {@inheritDoc}
     *
     * WARNING: This implementation performs 2 actual searches against the
     * database: one for counting the events, and one for retrieving the events.
     */
    @Override
    public Page<Event> getEventsByCriteria(final EventSearchCriteriaVO searchCriteria, final Pageable pageable) {
        Page<Event> result = null;
        // Use a (pre-cached) empty list instead of creating a new object
        List<Event> events = Collections.EMPTY_LIST;

        Long totalNumberOfEvents = eventDao.countEventsByCriteria(searchCriteria);

        if (totalNumberOfEvents > 0) {
            // Perform the search only if there are some elements
            events = eventDao.findEventsByCriteria(searchCriteria, pageable);
            // Lazy load the location
            for (Event e : events) {
                Hibernate.initialize(e.getLocation());
            }
        } else {
            //TODO logging - no results found
        }

        result = new PageImpl<Event>(events, pageable, totalNumberOfEvents);

        return result;
    }
}
