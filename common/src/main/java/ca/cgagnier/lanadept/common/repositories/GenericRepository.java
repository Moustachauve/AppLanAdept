package ca.cgagnier.lanadept.common.repositories;

import java.util.ArrayList;
import java.util.List;

import ca.cgagnier.lanadept.common.models.GenericModel;
import ca.cgagnier.lanadept.common.repositories.exceptions.NotFoundException;

public abstract class GenericRepository<T extends GenericModel> implements IGenericRepository<T> {

    public List<T> listRecord = new ArrayList<>();
    private long currentId = 1;

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

        throw new NotFoundException();
    }

    @Override
    public long save(T obj) {
        if(obj == null)
            throw new NullPointerException();

        if(obj.id == null) {    //Insert
            listRecord.add(obj);
            obj.id = currentId;
            currentId++;
            return obj.id;
        }
        else {                  //Update
            for(int i = 0; i < listRecord.size(); i++) {
                T record = listRecord.get(i);
                if(record.id == obj.id) {
                    listRecord.set(i, obj);
                    return obj.id;
                }
            }
        }

        throw new NotFoundException();
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
