package com.marmotlabs.ticketcenter.service.impl;

import com.marmotlabs.ticketcenter.dao.OrderDao;
import com.marmotlabs.ticketcenter.domain.Order;
import com.marmotlabs.ticketcenter.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zuui
 */
@Service("orderService")
@Transactional(readOnly = false)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void storeOrder(final Order order) {
        orderDao.saveOrUpdate(order);
    }
}
