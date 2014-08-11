package com.marmotlabs.ticketcenter.dao.impl;

import com.marmotlabs.ticketcenter.dao.CityDao;
import com.marmotlabs.ticketcenter.domain.City;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Zuui
 */
@Repository
public class CityDaoImpl extends AbstractDaoImpl<City, Long> implements CityDao {

    public CityDaoImpl() {
        super(City.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<City> findCities() {
        Query query = getCurrentSession().createQuery("from City");
        return (List<City>) query.list();
    }
}
