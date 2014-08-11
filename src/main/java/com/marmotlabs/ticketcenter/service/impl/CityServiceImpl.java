package com.marmotlabs.ticketcenter.service.impl;

import com.marmotlabs.ticketcenter.dao.CityDao;
import com.marmotlabs.ticketcenter.domain.City;
import com.marmotlabs.ticketcenter.service.CityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zuui
 */
@Service("cityService")
@Transactional(readOnly = true)
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<City> getCities() {
        List<City> cities = cityDao.findCities();

        return cities;
    }
}
