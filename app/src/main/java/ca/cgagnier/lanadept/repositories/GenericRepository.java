package ca.cgagnier.lanadept.repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1349423 on 2015-09-03.
 */
public class GenericRepository<T> implements IGenericRepository<T> {

    public List<T> listRecord = new ArrayList<>();

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public T getById(Long id) {
        return null;
    }

    @Override
    public void save(T obj) {

    }

    @Override
    public void saveMany(Iterable<T> list) {

    }

    @Override
    public void saveMany(T... list) {

    }

    @Override
    public void delete(T obj) {

    }

    @Override
    public void deleteAll() {

    }
}
