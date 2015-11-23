package ca.cgagnier.lanadept.events;

import ca.cgagnier.lanadept.models.Place;

public class PlaceClickedEvent {

    public Place place;

    public PlaceClickedEvent(Place place) {
        this.place = place;
    }
}
