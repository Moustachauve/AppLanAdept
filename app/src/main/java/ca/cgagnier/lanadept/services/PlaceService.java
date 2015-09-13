package ca.cgagnier.lanadept.services;

import ca.cgagnier.lanadept.models.Place;

public class PlaceService implements IPlaceService {

    //region Singleton things

    private PlaceService current;

    public PlaceService getCurrent() {
        if(current == null)
            current = new PlaceService();
        return current;
    }

    private PlaceService() {}

    //endregion

    @Override
    public Place getById(long id) {
        return null;
    }
}
