package ca.cgagnier.lanadept.common.services;

import ca.cgagnier.lanadept.common.models.Place;
import ca.cgagnier.lanadept.common.models.Reservation;
import ca.cgagnier.lanadept.common.models.User;
import ca.cgagnier.lanadept.common.services.exceptions.PlaceReservedException;
import ca.cgagnier.lanadept.common.services.exceptions.TooManyReservationException;

public interface IReservationService {

    /**
     * Reserve a place
     * @param user The user that wants to reserve a place
     * @param place The place to reserve
     * @return The reservation object created
     * @throws PlaceReservedException Thrown if this place is already reserved
     * @throws TooManyReservationException Thrown if the user already has a reserved place for this Lan
     */
    public Reservation reserve(User user, Place place) throws PlaceReservedException, TooManyReservationException;

    /**
     * Cancel a reservation
     * @param reservation Reservation to cancel
     */
    public void cancel(Reservation reservation);
}
