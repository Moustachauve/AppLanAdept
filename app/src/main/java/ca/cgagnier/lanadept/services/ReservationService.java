package ca.cgagnier.lanadept.services;

import ca.cgagnier.lanadept.models.Reservation;
import ca.cgagnier.lanadept.services.exceptions.AlreadyReservedException;
import ca.cgagnier.lanadept.services.exceptions.NoReservationException;
import ca.cgagnier.lanadept.services.exceptions.TooManyReservationException;

public class ReservationService implements IReservationService {

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
    public Reservation reserve() throws AlreadyReservedException, TooManyReservationException {
        return null;
    }

    @Override
    public void cancel() throws NoReservationException {

    }
}
