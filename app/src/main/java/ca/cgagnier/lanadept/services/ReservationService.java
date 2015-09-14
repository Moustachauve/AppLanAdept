package ca.cgagnier.lanadept.services;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.Reservation;
import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.repositories.ReservationRepository;
import ca.cgagnier.lanadept.services.exceptions.PlaceReservedException;
import ca.cgagnier.lanadept.services.exceptions.TooManyReservationException;

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
        return null;
    }

    @Override
    public void cancel(Reservation reservation) {

    }

}
