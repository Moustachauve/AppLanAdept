package ca.cgagnier.lanadept.repositories;

import java.util.List;

import ca.cgagnier.lanadept.models.Place;

public class PlaceRepository implements IPlaceRepository  {
    @Override
    public List<Place> getAll() {
        return null;
    }

    @Override
    public Place getById(Long id) {
        return null;
    }

    @Override
    public void save(Place obj) {

    }

    @Override
    public void saveMany(Iterable<Place> list) {

    }

    @Override
    public void saveMany(Place... list) {

    }

    @Override
    public void delete(Place obj) {

    }

    @Override
    public void deleteAll() {

    }
}
