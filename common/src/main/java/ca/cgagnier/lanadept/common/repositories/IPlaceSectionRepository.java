package ca.cgagnier.lanadept.common.repositories;

import ca.cgagnier.lanadept.common.models.PlaceSection;

public interface IPlaceSectionRepository extends IGenericRepository<PlaceSection> {

    /**
     * Delete a placeSection
     * @param section PlaceSection to delete
     */
    public void delete(PlaceSection section);

}
