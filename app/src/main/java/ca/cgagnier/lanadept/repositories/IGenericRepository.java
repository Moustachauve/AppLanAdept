package ca.cgagnier.lanadept.repositories;

import java.util.List;

public interface IGenericRepository<T> {

    public List<T> getAll();
    public T getById(Long id);
    public long save(T obj);
    public void deleteAll();

//    public void delete(T obj);
//    public void saveMany(Iterable<T> list);
//    public void saveMany(T... list);


}
