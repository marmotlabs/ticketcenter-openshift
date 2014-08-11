package com.marmotlabs.ticketcenter.dao;

import com.marmotlabs.ticketcenter.domain.City;
import java.util.List;

/**
 *
 * @author Zuui
 */
public interface CityDao extends AbstractDao<City, Long> {

    /**
     * Returns all the cities in the database, or an empty list if none found
     * 
     * @return 
     */
    List<City> findCities();

}
