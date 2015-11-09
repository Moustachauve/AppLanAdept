package ca.cgagnier.lanadept.repositories;

import ca.cgagnier.lanadept.models.PlaceSection;

public interface IPlaceSectionRepository extends IGenericRepository<PlaceSection> {

    /**
     * Delete a placeSection
     * @param section PlaceSection to delete
     */
    public void delete(PlaceSection section);

}
