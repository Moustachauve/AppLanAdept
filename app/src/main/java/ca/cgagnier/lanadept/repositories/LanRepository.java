package ca.cgagnier.lanadept.repositories;

import java.util.List;

import ca.cgagnier.lanadept.models.Lan;

public class LanRepository implements ILanRepository {
    @Override
    public List<Lan> getAll() {
        return null;
    }

    @Override
    public Lan getById(Long id) {
        return null;
    }

    @Override
    public void save(Lan obj) {

    }

    @Override
    public void saveMany(Iterable<Lan> list) {

    }

    @Override
    public void saveMany(Lan... list) {

    }

    @Override
    public void delete(Lan obj) {

    }

    @Override
    public void deleteAll() {

    }
}
