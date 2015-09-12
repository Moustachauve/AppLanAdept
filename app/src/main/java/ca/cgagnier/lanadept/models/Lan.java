package ca.cgagnier.lanadept.models;

import org.joda.time.DateTime;

import java.util.List;

public class Lan extends GenericModel {

    public DateTime dateDebut;
    public String emplacement;
    public String emplacementGoogleMap;
    public List<PlaceSection> sections;

}
