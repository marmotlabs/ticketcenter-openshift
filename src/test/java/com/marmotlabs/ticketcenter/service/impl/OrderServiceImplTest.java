package com.nine.plus.ticketcenter.service.impl;

import com.marmotlabs.ticketcenter.dao.OrderDao;
import com.marmotlabs.ticketcenter.domain.Order;
import com.marmotlabs.ticketcenter.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import org.junit.Test;

/**
 *
 * @author Zuui
 */
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl underTest;

    @Mock
    private OrderDao orderDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // test a method Which send data to a dependency
    @Test
    public void storeOrderTest() {
        // given
        Order order = new Order();
        // when
        underTest.storeOrder(order);
        // then 
        verify(orderDao, times(1)).saveOrUpdate(eq(order));
        //  here we use Mockito to check if a method is invoked or not and how many times.

    }
}
