package ca.cgagnier.lanadept.services;

import java.util.List;

import ca.cgagnier.lanadept.models.Lan;

public class LanService implements ILanService {

    //region Singleton things

    private LanService current;

    public LanService getCurrent() {
        if(current == null)
            current = new LanService();
        return current;
    }

    private LanService() {}

    //endregion

    @Override
    public List<Lan> getAllLans() {
        return null;
    }

    @Override
    public void selectLan(Lan selectedLan) {

    }

    @Override
    public Lan getSelectedLan() {
        return null;
    }

    @Override
    public Lan getNextLan() {
        return null;
    }
}
