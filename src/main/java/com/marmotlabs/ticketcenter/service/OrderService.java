package com.marmotlabs.ticketcenter.service;

import com.marmotlabs.ticketcenter.domain.Order;

/**
 *
 * @author Zuui
 */
public interface OrderService {

    /**
     * Stores an order in the database.
     *
     * @param order
     */
    void storeOrder(Order order);

}
