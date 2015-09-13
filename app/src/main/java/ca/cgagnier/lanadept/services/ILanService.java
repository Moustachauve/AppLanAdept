package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.services.exceptions.NoLanInFutureException;

public interface ILanService {

    public List<Lan> getAllLans();

    public void selectLan(Lan selectedLan);

    public Lan getSelectedLan();

    public Lan getClosestNextLan() throws NoLanInFutureException;
}
