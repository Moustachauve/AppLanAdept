package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Place;

public class PlaceService implements IPlaceService {

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
    public Place getById(long id) {
        return null;
    }

    @Override
    public long save(Place place) {
        return 0;
    }

    @Override
    public void saveMany(List<Place> places) {

    }

    @Override
    public void delete(Place place) {

    }

    @Override
    public void deleteMany(List<Place> places) {

    }
}
