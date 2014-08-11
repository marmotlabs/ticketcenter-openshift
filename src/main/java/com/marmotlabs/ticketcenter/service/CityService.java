package com.marmotlabs.ticketcenter.service;

import com.marmotlabs.ticketcenter.domain.City;
import java.util.List;

/**
 *
 * @author Zuui
 */
public interface CityService {

    /**
     * Returns all the cities in the database
     *
     * @return all the cities in the database
     */
    List<City> getCities();
}
