package ca.cgagnier.lanadept.events;

import ca.cgagnier.lanadept.models.Place;

public class PlaceChangedEvent {

    public Place place;

    public PlaceChangedEvent(Place place) {
        this.place = place;
    }
}
