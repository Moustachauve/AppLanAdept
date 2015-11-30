package ca.cgagnier.lanadept.common.events;

import ca.cgagnier.lanadept.common.models.Place;

public class PlaceClickedEvent {

    public Place place;

    public PlaceClickedEvent(Place place) {
        this.place = place;
    }
}
