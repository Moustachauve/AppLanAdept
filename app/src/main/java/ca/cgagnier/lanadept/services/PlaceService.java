package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.repositories.PlaceRepository;
import ca.cgagnier.lanadept.services.exceptions.InvalidIdException;
import ca.cgagnier.lanadept.services.exceptions.PlaceReservedException;

public class PlaceService implements IPlaceService {

    PlaceRepository placeRepo = new PlaceRepository();

    //region Singleton things

    private static PlaceService current;

    public static PlaceService getCurrent() {
        if(current == null)
            current = new PlaceService();
        return current;
    }

    static void reset() {
        current = null;
    }

    private PlaceService() {}

    //endregion

    @Override
    public Place getById(long id) throws InvalidIdException {
        return null;
    }

    @Override
    public Place addToSection(PlaceSection section) {
        return null;
    }

    @Override
    public void removeFromSection(PlaceSection section) throws PlaceReservedException {

    }

    @Override
    public void deleteMany(List<Place> places) {

    }

}
