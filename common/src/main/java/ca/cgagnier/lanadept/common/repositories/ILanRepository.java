package ca.cgagnier.lanadept.common.repositories;

import ca.cgagnier.lanadept.common.models.Lan;

public interface ILanRepository extends IGenericRepository<Lan> {

    /**
     * Delete a Lan
     * @param lan Lan to delete
     */
    public void delete(Lan lan);

}
