package ca.cgagnier.lanadept.repositories;

import ca.cgagnier.lanadept.models.Place;

public interface IPlaceRepository extends IGenericRepository<Place> {

    public void delete(Place section);

}
