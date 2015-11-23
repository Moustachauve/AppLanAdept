package ca.cgagnier.lanadept.models;

import org.joda.time.DateTime;

import java.util.List;

public class User extends GenericModel {

    public String fullName;
    public String email;
    public String password;
    public DateTime dateInscription;
    public List<Reservation> reservations;

}
