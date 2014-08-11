package com.marmotlabs.ticketcenter.dao.impl;

import com.marmotlabs.ticketcenter.dao.OrderDao;
import com.marmotlabs.ticketcenter.domain.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Zuui
 */
@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order, Long> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

}
