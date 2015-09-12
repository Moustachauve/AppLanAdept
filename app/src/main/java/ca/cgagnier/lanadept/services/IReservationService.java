package ca.cgagnier.lanadept.services;

import ca.cgagnier.lanadept.models.Reservation;
import ca.cgagnier.lanadept.services.exceptions.AlreadyReservedException;
import ca.cgagnier.lanadept.services.exceptions.NoReservationException;
import ca.cgagnier.lanadept.services.exceptions.TooManyReservationException;

public interface IReservationService {

    public Reservation reserve() throws AlreadyReservedException, TooManyReservationException;

    public void cancel() throws NoReservationException;

}
