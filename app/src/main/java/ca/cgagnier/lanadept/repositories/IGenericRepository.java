package ca.cgagnier.lanadept.repositories;

import java.util.List;

public interface IGenericRepository<T> {

    /**
     * Get all element of type T
     * @return A list of element
     */
    public List<T> getAll();

    /**
     * Get an element by its Id
     * @param id Id of the element to search
     * @return Element found
     */
    public T getById(long id);

    /**
     * Save an element in the storage
     * @param obj Element to save
     * @return the Id of the element saved
     */
    public long save(T obj);

    /**
     * Deletes all element from the storage
     */
    public void deleteAll();

//    public void delete(T obj);
//    public void saveMany(Iterable<T> list);
//    public void saveMany(T... list);


}
