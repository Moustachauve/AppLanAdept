package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.repositories.PlaceRepository;
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
    public Place getById(long id) {
        return placeRepo.getById(id);
    }

    @Override
    public Place addToSection(PlaceSection section) {
        int numPlace = section.placeList.size() + 1;

        Place place = new Place();
        place.numero = numPlace;
        place.placeSection = section;

        placeRepo.save(place);
        section.placeList.add(place);

        return place;
    }

    @Override
    public void removeFromSection(PlaceSection section) throws PlaceReservedException {
        int numPlace = section.placeList.size() - 1;

        Place placeToRemove = section.placeList.get(numPlace);

        if(placeToRemove.reservation != null)
            throw new PlaceReservedException();

        placeRepo.delete(placeToRemove);
        section.placeList.remove(numPlace);
    }

    @Override
    public void deleteMany(List<Place> places) {
        for(Place place : places) {
            if(place == null)
                continue;

            placeRepo.delete(place);
            place.placeSection.placeList.remove(place);
        }
    }

}
