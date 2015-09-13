package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;

public interface IPlaceSectionService {

    public List<PlaceSection> getAllSection();

    public long save(PlaceSection placeSection);

    public void delete(PlaceSection placeSection);
}
