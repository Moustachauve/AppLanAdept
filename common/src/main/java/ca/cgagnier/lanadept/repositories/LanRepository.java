package ca.cgagnier.lanadept.repositories;

import org.joda.time.DateTime;

import java.security.PublicKey;
import java.util.Collections;
import java.util.List;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;

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
