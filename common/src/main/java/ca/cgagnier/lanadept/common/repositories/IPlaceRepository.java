package ca.cgagnier.lanadept.common.repositories;

import ca.cgagnier.lanadept.common.models.Place;

public interface IPlaceRepository extends IGenericRepository<Place> {

    /**
     * Delete a place
     * @param section Place to delete
     */
    public void delete(Place section);

}
