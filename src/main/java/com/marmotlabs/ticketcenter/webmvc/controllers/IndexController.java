package com.marmotlabs.ticketcenter.webmvc.controllers;

import com.marmotlabs.ticketcenter.domain.City;
import com.marmotlabs.ticketcenter.domain.Event;
import com.marmotlabs.ticketcenter.service.CityService;
import com.marmotlabs.ticketcenter.service.EventService;
import com.marmotlabs.ticketcenter.vo.search.EventSearchCriteriaVO;
import com.marmotlabs.ticketcenter.webmvc.pagination.PageWrapper;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Zuui
 */
@Controller
@RequestMapping(value = "/")
@SessionAttributes(value = {"searchCriteria", "order"})
public class IndexController {

    private static final Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public String search(@ModelAttribute(value = "searchCriteria") EventSearchCriteriaVO searchCriteria, Pageable pageable, Model model) {
        if (logger.isDebugEnabled()) {
            logger.debug("Called / with searchCriteria = " + searchCriteria + ", pageable=" + pageable);
        }

        Page<Event> searchResult = eventService.getEventsByCriteria(searchCriteria, pageable);
        List<City> cities = cityService.getCities();

        PageWrapper<Event> pageWrapper = new PageWrapper<Event>(searchResult, Pages.INDEX);

        model.addAttribute("cities", cities);
        model.addAttribute("pageWrapper", pageWrapper);
        return Pages.INDEX;
    }
}
