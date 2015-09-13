package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.services.exceptions.InvalidIdException;

public interface IPlaceService {

    public Place getById(long id) throws InvalidIdException;

    public long save(Place place);

    public void saveMany(List<Place> places);

    public void delete(Place place);

    public void deleteMany(List<Place> places);
}
