package ca.cgagnier.lanadept.models;

import org.joda.time.DateTime;

import java.util.List;

public class Lan extends GenericModel implements Comparable<Lan> {

    public DateTime dateDebut;
    public String emplacement;
    public String emplacementGoogleMap;
    public List<PlaceSection> sections;


    @Override
    public int compareTo(Lan compareTo) {
        return dateDebut.compareTo(compareTo.dateDebut);
    }
}
