package ca.cgagnier.lanadept.repositories;

import java.util.ArrayList;
import java.util.List;

import ca.cgagnier.lanadept.models.GenericModel;

/**
 * Created by 1349423 on 2015-09-03.
 */
public abstract class GenericRepository<T extends GenericModel> implements IGenericRepository<T> {

    public List<T> listRecord = new ArrayList<>();

    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        list.addAll(listRecord);
        return list;
    }

    @Override
    public T getById(Long id) {
        for(T record : listRecord) {
            if(record.id == id) {
                return record;
            }
        }

        return null;
    }

    @Override
    public long save(T obj) {
        return -1;
    }

    @Override
    public void deleteAll() {

    }

//    @Override
//    public void delete(T obj) {
//
//    }
//
//    @Override
//    public void saveMany(Iterable<T> list) {
//
//    }
//
//    @Override
//    public void saveMany(T... list) {
//
//    }

}
