package ca.cgagnier.lanadept.services;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.services.exceptions.InvalidIdException;

public interface IPlaceService {

    public Place getById(long id) throws InvalidIdException;


}
