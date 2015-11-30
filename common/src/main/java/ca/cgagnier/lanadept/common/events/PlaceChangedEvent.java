package ca.cgagnier.lanadept.common.events;

import ca.cgagnier.lanadept.common.models.Place;

public class PlaceChangedEvent {

    public Place place;

    public PlaceChangedEvent(Place place) {
        this.place = place;
    }
}
