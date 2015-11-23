package ca.cgagnier.lanadept.repositories;

import ca.cgagnier.lanadept.models.Lan;

public interface ILanRepository extends IGenericRepository<Lan> {

    /**
     * Delete a Lan
     * @param lan Lan to delete
     */
    public void delete(Lan lan);

}
