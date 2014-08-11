package com.marmotlabs.ticketcenter.interceptor;

import com.marmotlabs.ticketcenter.domain.Order;
import com.marmotlabs.ticketcenter.domain.OrderEntry;
import com.marmotlabs.ticketcenter.vo.search.EventSearchCriteriaVO;
import java.util.HashSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * The session attributes interceptor that is called before every controller
 * method to ensure that the session has been properly populated with the
 * required objects.
 *
 * @author Zuui
 */
public class SessionAttributesInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (request.getSession().getAttribute("searchCriteria") == null) {
            request.getSession().setAttribute("searchCriteria", createSearchCriteria());
        }
        if (request.getSession().getAttribute("order") == null) {
            request.getSession().setAttribute("order", createOrder());
        }

        return true;
    }

    public EventSearchCriteriaVO createSearchCriteria() {
        // If no filters have been (yet) provided, then create an empty searchCriteria. This will cause all the events to be displayed
        return new EventSearchCriteriaVO();
    }

    public Order createOrder() {
        // Creates the "order" session object.
        Order order = new Order();

        order.setEntries(new HashSet<OrderEntry>());
        return order;
    }

}
