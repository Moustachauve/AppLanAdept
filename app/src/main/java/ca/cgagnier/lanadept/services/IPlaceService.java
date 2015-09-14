package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.services.exceptions.InvalidIdException;
import ca.cgagnier.lanadept.services.exceptions.PlaceReservedException;

public interface IPlaceService {

    public Place getById(long id);

    public Place addToSection(PlaceSection section);

    public void removeFromSection(PlaceSection section) throws PlaceReservedException;

    public void deleteMany(List<Place> places);
}
