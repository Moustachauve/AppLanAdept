package ca.cgagnier.lanadept.common.models;

import org.joda.time.DateTime;

import java.util.List;

public class Lan extends GenericModel implements Comparable<Lan> {

    public DateTime startingDate;
    public String position;
    public String positionMap;
    public List<PlaceSection> sections;


    @Override
    public int compareTo(Lan compareTo) {
        return startingDate.compareTo(compareTo.startingDate);
    }
}
