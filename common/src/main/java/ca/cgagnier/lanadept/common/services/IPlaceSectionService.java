package ca.cgagnier.lanadept.common.services;

import ca.cgagnier.lanadept.common.models.Lan;
import ca.cgagnier.lanadept.common.services.exceptions.InvalidNameException;
import ca.cgagnier.lanadept.common.models.PlaceSection;

public interface IPlaceSectionService {

    /**
     * Create a new PlaceSection and add it in the storage
     * @param lan Lan in which the section is
     * @param name Name of the section
     * @return The lan created
     * @throws InvalidNameException thrown if the name of the Lan is not valid
     */
    public PlaceSection create(Lan lan, String name) throws InvalidNameException;

    /**
     * Update the Lan information
     * @param section section to update
     * @throws InvalidNameException thrown if the name of the Lan is not valid
     */
    public void update(PlaceSection section) throws InvalidNameException;

    /**
     * Delete the specified section
     * @param section Section to delete
     */
    public void delete(PlaceSection section);

}
