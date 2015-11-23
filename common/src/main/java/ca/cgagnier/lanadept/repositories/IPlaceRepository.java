package ca.cgagnier.lanadept.repositories;

import ca.cgagnier.lanadept.models.Place;

public interface IPlaceRepository extends IGenericRepository<Place> {

    /**
     * Delete a place
     * @param section Place to delete
     */
    public void delete(Place section);

}
