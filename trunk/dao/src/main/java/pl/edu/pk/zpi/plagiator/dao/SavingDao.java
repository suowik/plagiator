package pl.edu.pk.zpi.plagiator.dao;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 18:16
 */
public interface SavingDao<E> {
    void save(E entity);
}
