package ca.cgagnier.lanadept.services;

import org.joda.time.DateTime;

import java.util.List;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.services.exceptions.NoLanInFutureException;

public interface ILanService {

    public List<Lan> getAllLans();

    public void selectLan(Lan selectedLan);

    public Lan getSelectedLan();

    public Lan create(DateTime startingDate, String position, String positionMap);

    public Lan getById(long id) throws NotFoundException;

    public void update(Lan lan);

    public void delete(Lan lan);

    public Lan getClosestNextLan() throws NoLanInFutureException;
}
