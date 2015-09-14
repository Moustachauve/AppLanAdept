package ca.cgagnier.lanadept.models;

import org.joda.time.DateTime;

public class Reservation extends GenericModel {

    public Place place;
    public User user;
    public DateTime dateReservation;

}
