package com.myfinances.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sam Moore
 * @since 8/7/13 9:19 PM
 */
public interface GenericRepo<E, K extends Serializable> {

    void add(E entity);

    void update(E entity);

    void remove(E entity);

    E find(K key);

    List<E> list();
}
