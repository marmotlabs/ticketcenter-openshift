package com.marmotlabs.ticketcenter.webmvc.controllers;

import com.marmotlabs.ticketcenter.domain.Event;
import com.marmotlabs.ticketcenter.domain.OrderEntry;
import com.marmotlabs.ticketcenter.service.EventService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Zuui
 */
@Controller
@RequestMapping(value = "/event")
@SessionAttributes(value = {"searchCriteria", "order"})
public class EventController {

    private static final Logger logger = Logger.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEventById(@PathVariable Long id, Model model) {
        if (logger.isDebugEnabled()) {
            logger.debug("Called getEventById() with id = " + id);
        }
        Event event = eventService.getEventById(id);

        if (event == null) {
            // The provided event ID was wrong
            return Pages.ERROR;
        }

        // From this point, the user could buy tickets to this event
        // We create an OrderEntry for this purpose, that will be the backing bean for the buying form
        OrderEntry orderEntry = new OrderEntry();
        // This event will only be visible to the next page, as the orderEntry is a request-scoped attribute (NOT a session one)
        orderEntry.setEvent(event);

        model.addAttribute("event", event);
        model.addAttribute("orderEntry", orderEntry);

        return Pages.EVENT;
    }

}
