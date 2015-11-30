package ca.cgagnier.lanadept.common.repositories;

import ca.cgagnier.lanadept.common.models.Lan;
import ca.cgagnier.lanadept.common.repositories.exceptions.NotFoundException;

public class LanRepository extends GenericRepository<Lan> implements ILanRepository {

    public void delete(Lan lan) {
        if(lan == null)
            throw new NullPointerException();
        if(lan.id == null)
            throw new NotFoundException();

        int indexADelete = -1;

        for(int i = 0; i < listRecord.size(); i++) {
            Lan record = listRecord.get(i);
            if(lan.id == record.id)
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
