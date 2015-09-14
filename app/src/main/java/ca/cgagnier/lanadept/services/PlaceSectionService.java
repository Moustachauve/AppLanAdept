package ca.cgagnier.lanadept.services;

import java.util.LinkedList;
import java.util.List;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.repositories.IPlaceSectionRepository;
import ca.cgagnier.lanadept.repositories.LanRepository;
import ca.cgagnier.lanadept.repositories.PlaceSectionRepository;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.services.exceptions.InvalidNameException;

public class PlaceSectionService implements IPlaceSectionService {

    IPlaceSectionRepository placeSectionRepo = new PlaceSectionRepository();

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
    public PlaceSection create(Lan lan, String name) throws InvalidNameException {
        if(lan == null || name == null)
            throw new NullPointerException();

        if(name.trim().length() == 0)
            throw new InvalidNameException();

        PlaceSection placeSection = new PlaceSection();
        placeSection.placeList = new LinkedList<>();
        placeSection.name = name;
        placeSection.lan = lan;

        placeSectionRepo.save(placeSection);

        lan.sections.add(placeSection);
        LanService.getCurrent().update(lan);

        return placeSection;
    }

    @Override
    public void update(PlaceSection section) throws InvalidNameException {
        if(section.id == null)
            throw new NotFoundException();

        placeSectionRepo.save(section);
    }

    @Override
    public void delete(PlaceSection section) {
        if(section.id == null)
            throw new NotFoundException();

        placeSectionRepo.delete(section);
        PlaceService.getCurrent().deleteMany(section.placeList);
    }

}
