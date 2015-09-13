package ca.cgagnier.lanadept.repositories;

import java.util.ArrayList;
import java.util.List;

import ca.cgagnier.lanadept.models.GenericModel;

public abstract class GenericRepository<T extends GenericModel> implements IGenericRepository<T> {

    public List<T> listRecord = new ArrayList<>();

    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        list.addAll(listRecord);
        return list;
    }

    @Override
    public T getById(long id) {
        for(T record : listRecord) {
            if(record.id == id) {
                return record;
            }
        }

        return null;
    }

    @Override
    public long save(T obj) {
        if(obj == null)
            throw new NullPointerException();

        if(obj.id == null) {
            listRecord.add(obj);
            obj.id = (long)listRecord.size();
            return obj.id;
        }
        else {
            for(T record : listRecord) {
                if(record.id == obj.id) {
                    listRecord.set(record.id.intValue(), obj);
                    return obj.id;
                }
            }
        }

        throw new NullPointerException(); //TODO: Remplacer par mon exception
    }

    @Override
    public void deleteAll() {
        listRecord = new ArrayList<>();
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
