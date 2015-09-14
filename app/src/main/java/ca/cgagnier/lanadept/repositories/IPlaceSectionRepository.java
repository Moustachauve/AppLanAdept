package ca.cgagnier.lanadept.repositories;

import ca.cgagnier.lanadept.models.PlaceSection;

public interface IPlaceSectionRepository extends IGenericRepository<PlaceSection> {

    public void delete(PlaceSection section);

}
