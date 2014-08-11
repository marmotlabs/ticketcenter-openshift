package com.marmotlabs.ticketcenter.dao;

import java.io.Serializable;

/**
 * Provides common database operations
 * 
 * @author Zuui
 * @param <E> the entity type managed by the concrete implementation of this class
 * @param <I> the @Id type managed by the concrete implementation of this class
 */
public interface AbstractDao<E, I extends Serializable> {

    E findById(I id);

    void saveOrUpdate(E e);

    void delete(E e);

}
