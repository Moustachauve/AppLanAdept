package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.repositories.PlaceSectionRepository;
import ca.cgagnier.lanadept.services.exceptions.InvalidNameException;

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
        return null;
    }

    @Override
    public PlaceSection create(Lan lan, String name) throws InvalidNameException {
        return null;
    }

    @Override
    public void update(PlaceSection section) throws InvalidNameException {

    }

    @Override
    public void delete(PlaceSection section) {

    }

}
