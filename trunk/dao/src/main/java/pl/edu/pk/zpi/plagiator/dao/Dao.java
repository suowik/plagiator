package pl.edu.pk.zpi.plagiator.dao;

import java.util.List;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 11:15
 */
public interface Dao<E> {

    List<E> findAll();

    void save(E entity);


}
