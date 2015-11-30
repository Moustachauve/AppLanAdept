package ca.cgagnier.lanadept.common.services;

import ca.cgagnier.lanadept.common.models.Place;
import ca.cgagnier.lanadept.common.models.User;
import ca.cgagnier.lanadept.common.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.common.services.exceptions.PlaceReservedException;
import ca.cgagnier.lanadept.common.services.exceptions.TooManyReservationException;
import org.joda.time.DateTime;

import ca.cgagnier.lanadept.common.models.Reservation;
import ca.cgagnier.lanadept.common.repositories.ReservationRepository;

public class ReservationService implements IReservationService {

    ReservationRepository reservationRepo = new ReservationRepository();

    //region Singleton things

    private static ReservationService current;

    public static ReservationService getCurrent() {
        if(current == null)
            current = new ReservationService();
        return current;
    }

    static void reset() {
        current = null;
    }

    private ReservationService() {}

    //endregion

    @Override
    public Reservation reserve(User user, Place place) throws PlaceReservedException, TooManyReservationException {
        if(user == null || place == null)
            throw new NullPointerException();

        if(place.reservation != null)
            throw new PlaceReservedException();

        if(user.reservations.size() > 0 && user.reservations.get(0).place.placeSection.lan.id == place.placeSection.lan.id)
            throw new TooManyReservationException();

        Reservation reservation = new Reservation();
        reservation.place = place;
        reservation.dateReservation = DateTime.now();
        reservation.user = user;

        user.reservations.add(reservation);
        place.reservation = reservation;

        reservationRepo.save(reservation);

        return reservation;
    }

    @Override
    public void cancel(Reservation reservation) {
        if(reservation.id == null)
            throw new NotFoundException();

        boolean found = false;

        for(int i = 0; i < reservation.user.reservations.size(); i++) {
            Reservation curReservation = reservation.user.reservations.get(i);

            if(curReservation.id == reservation.id) {
                reservation.user.reservations.remove(i);
                found = true;
                break;
            }
        }

        if(!found)
            throw new NotFoundException();

        reservation.place.reservation = null;
    }

}
