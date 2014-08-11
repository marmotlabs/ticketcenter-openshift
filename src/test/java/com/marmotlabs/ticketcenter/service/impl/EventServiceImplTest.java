package com.marmotlabs.ticketcenter.service.impl;

import com.marmotlabs.ticketcenter.dao.EventDao;
import com.marmotlabs.ticketcenter.domain.Event;
import com.marmotlabs.ticketcenter.domain.Location;
import com.marmotlabs.ticketcenter.service.impl.EventServiceImpl;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl underTest;

    @Mock
    private EventDao eventDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEventByIdTest() {
        Long eventId = 1L;
        Long locationId = 1L;

        Event expectedEvent = new Event();
        expectedEvent.setId(eventId);
        
        Location location = new Location();
        location.setId(locationId);
        
        expectedEvent.setLocation(location);

        // Mocking the findById(Long) method
        when(eventDao.findById(eq(eventId))).thenReturn(expectedEvent);

        Event result = underTest.getEventById(eventId);

        assertEquals(expectedEvent, result);
        assertEquals(expectedEvent.getId(), result.getId());
        assertEquals(expectedEvent.getLocation(), location);
        assertEquals(expectedEvent.getLocation().getId(), locationId);
    }
}
