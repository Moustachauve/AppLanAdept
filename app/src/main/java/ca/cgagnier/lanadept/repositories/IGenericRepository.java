package ca.cgagnier.lanadept.repositories;

import java.util.List;

/**
 * Created by 1349423 on 2015-08-31.
 */
public interface IGenericRepository<T> {

    public List<T> getAll();
    public T getById(Long id);
    public void save(T obj);
    public void saveMany(Iterable<T> list);
    public void saveMany(T... list);
    public void delete(T obj);
    public void deleteAll();

}
