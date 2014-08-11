package com.marmotlabs.ticketcenter.webmvc.controllers;

import com.marmotlabs.ticketcenter.domain.Event;
import com.marmotlabs.ticketcenter.domain.Order;
import com.marmotlabs.ticketcenter.domain.OrderEntry;
import com.marmotlabs.ticketcenter.service.EventService;
import com.marmotlabs.ticketcenter.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author Zuui
 */
@Controller
@RequestMapping(value = "/order")
@SessionAttributes(value = {"searchCriteria", "order"})
public class OrderController {

    private static final Logger logger = Logger.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getOrder(@ModelAttribute("order") Order order, Model model) {
        return Pages.ORDER;
    }

    @RequestMapping(value = "/addOrderEntry", method = RequestMethod.POST)
    public String addOrderEntry(@ModelAttribute("order") Order order, @ModelAttribute("orderEntry") OrderEntry orderEntry, Model model) {
        if (logger.isDebugEnabled()) {
            logger.debug("Called addOrderEntry() with order = " + order + ", orderEntry=" + orderEntry);
        }

        if (orderEntry.getEvent() == null || orderEntry.getEvent().getId() == null) {
            // The event id should be provided
            return Pages.ERROR;
        }

        // At this point, we only have the event.id stored in the orderEntry
        // We need to obtain the event once again, and store it into the orderEntry
        Event event = eventService.getEventById(orderEntry.getEvent().getId());
        if (event == null) {
            // The provided event ID was wrong
            return Pages.ERROR;
        }

        orderEntry.setEvent(event);

        order.addOrderEntry(orderEntry);

        return Pages.ORDER;
    }

    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    public String submitOrder(@ModelAttribute("order") Order order, Model model, SessionStatus sessionStatus) {
        if (logger.isDebugEnabled()) {
            logger.debug("Called submitOrder() with order = " + order);
        }
        orderService.storeOrder(order);

        // Ivalidate the session - remove the already saved order from the session
        sessionStatus.setComplete();
        return "redirect:/orderConfirmation";
    }

}
