package ca.cgagnier.lanadept.models;

import org.joda.time.DateTime;

/**
 * Created by 1349423 on 2015-08-31.
 */
public class Reservation {

    public Long id;
    public Place place;
    public User user;
    public DateTime dateReservation;
    public DateTime dateAnnulation;

}
