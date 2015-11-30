package ca.cgagnier.lanadept.common.repositories;

import ca.cgagnier.lanadept.common.models.Place;
import ca.cgagnier.lanadept.common.repositories.exceptions.NotFoundException;

public class PlaceRepository extends GenericRepository<Place> implements IPlaceRepository  {

    public void delete(Place place) {
        if(place == null)
            throw new NullPointerException();
        if(place.id == null)
            throw new NotFoundException();

        int indexADelete = -1;

        for(int i = 0; i < listRecord.size(); i++) {
            Place record = listRecord.get(i);
            if(place.id == record.id)
            {
                indexADelete = i;
                break;
            }
        }

        if(indexADelete == -1)
            throw new NotFoundException();

        listRecord.remove(indexADelete);
    }

}
