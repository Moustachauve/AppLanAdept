package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Place;

public class PlaceSectionService implements IPlaceSectionService {

    //region Singleton things
    private static PlaceSectionService current;

    public static PlaceSectionService getCurrent() {
        if(current == null)
            current = new PlaceSectionService();
        return current;
    }

    static void reset() {
        current = null;
    }

    private PlaceSectionService() {}

    //endregion

    @Override
    public List<Place> getAllSection() {
        return null;
    }
}
