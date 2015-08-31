package ca.cgagnier.lanadept.repositories;

import java.util.List;

import ca.cgagnier.lanadept.models.Reservation;

public class ReservationRepository implements IReservationRepository {
    @Override
    public List<Reservation> getAll() {
        return null;
    }

    @Override
    public Reservation getById(Long id) {
        return null;
    }

    @Override
    public void save(Reservation obj) {

    }

    @Override
    public void saveMany(Iterable<Reservation> list) {

    }

    @Override
    public void saveMany(Reservation... list) {

    }

    @Override
    public void delete(Reservation obj) {

    }

    @Override
    public void deleteAll() {

    }
}
