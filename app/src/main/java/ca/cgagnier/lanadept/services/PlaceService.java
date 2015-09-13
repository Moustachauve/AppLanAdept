package ca.cgagnier.lanadept.services;

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
}
