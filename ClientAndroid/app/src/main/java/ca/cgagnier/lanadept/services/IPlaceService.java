package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.services.exceptions.PlaceReservedException;

public interface IPlaceService {

    public Place getById(long id);

    /**
     * Add a new place to the section
     * @param section Section in which to add a new place
     * @return The place created
     */
    public Place addToSection(PlaceSection section);

    /**
     * Remove the last place in a section
     * @param section The section in which to remove a place
     * @throws PlaceReservedException Thrown if the place to remove is already reserved by somebody
     */
    public void removeFromSection(PlaceSection section) throws PlaceReservedException;

    /**
     * Delete many places from the storage
     * @param places Places to remove
     */
    public void deleteMany(List<Place> places);
}
