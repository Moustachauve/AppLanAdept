package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.repositories.PlaceSectionRepository;

public class PlaceSectionService implements IPlaceSectionService {

    PlaceSectionRepository placeSectionRepo = new PlaceSectionRepository();

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
    public List<PlaceSection> getAllSection() {
        return placeSectionRepo.getAll();
    }

    @Override
    public long save(PlaceSection placeSection) {
        return 0;
    }

    @Override
    public void delete(PlaceSection placeSection) {

    }
}
