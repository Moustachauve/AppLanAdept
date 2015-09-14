package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.services.exceptions.InvalidNameException;

public interface IPlaceSectionService {

    public List<PlaceSection> getAllSection();

    public PlaceSection create(Lan lan, String name) throws InvalidNameException;

    public void update(PlaceSection section) throws InvalidNameException;

    public void delete(PlaceSection section);

}
