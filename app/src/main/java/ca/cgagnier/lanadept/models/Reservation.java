package ca.cgagnier.lanadept.models;

import org.joda.time.DateTime;

public class Reservation {

    public Long id;
    public Place place;
    public User user;
    public DateTime dateReservation;
    public DateTime dateAnnulation;

}
