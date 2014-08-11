package com.marmotlabs.ticketcenter.webmvc.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Zuui
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    private static final Logger logger = Logger.getLogger(ErrorController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getErrorPage() {
        if (logger.isDebugEnabled()) {
            logger.debug("Called /error");
        }
        return Pages.ERROR;
    }

}
