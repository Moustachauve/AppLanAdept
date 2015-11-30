package ca.cgagnier.lanadept.common.repositories;

import ca.cgagnier.lanadept.common.models.PlaceSection;
import ca.cgagnier.lanadept.common.repositories.exceptions.NotFoundException;

public class PlaceSectionRepository extends GenericRepository<PlaceSection> implements IPlaceSectionRepository {

    public void delete(PlaceSection section) {
        if(section == null)
            throw new NullPointerException();
        if(section.id == null)
            throw new NotFoundException();

        int indexADelete = -1;

        for(int i = 0; i < listRecord.size(); i++) {
            PlaceSection record = listRecord.get(i);
            if(section.id == record.id)
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
