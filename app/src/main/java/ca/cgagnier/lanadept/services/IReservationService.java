package ca.cgagnier.lanadept.services;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.Reservation;
import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.services.exceptions.AlreadyReservedException;
import ca.cgagnier.lanadept.services.exceptions.TooManyReservationException;

public interface IReservationService {

    public Reservation reserve(User user, Place place) throws AlreadyReservedException, TooManyReservationException;

    public void cancel(Reservation reservation);
}
