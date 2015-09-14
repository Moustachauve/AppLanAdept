package ca.cgagnier.lanadept.services;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Collections;
import java.util.List;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.repositories.LanRepository;
import ca.cgagnier.lanadept.services.exceptions.NoLanInFutureException;

public class LanService implements ILanService {

    private Lan selectedLan;
    LanRepository lanRepo = new LanRepository();

    //region Singleton things

    private static LanService current;

    public static LanService getCurrent() {
        if(current == null)
            current = new LanService();
        return current;
    }

    static void reset() {
            current = null;
    }

    private LanService() {}

    //endregion

    @Override
    public List<Lan> getAllLans() {
        return lanRepo.getAll();
    }

    @Override
    public void selectLan(Lan lanToSelect) {
        if(lanToSelect == null)
            throw new NullPointerException();

        selectedLan = lanToSelect;
    }

    @Override
    public Lan getSelectedLan() {
        return selectedLan;
    }

    @Override
    public Lan create(DateTime dateDebut, String emplacement, String emplacementGoogleMaps) {
        return null;
    }

    @Override
    public Lan getClosestNextLan() throws NoLanInFutureException {
        List<Lan> lans = getAllLans();
        int bestDaysBetween = Integer.MAX_VALUE;
        Lan closestLan = null;

        DateTime today = DateTime.now();

        for(Lan currentLan : lans)
        {
            if(currentLan.dateDebut.isAfter(today)) {
                int daysBetween = Days.daysBetween(currentLan.dateDebut, today).getDays() + 1;
                if (daysBetween < bestDaysBetween) {
                    bestDaysBetween = daysBetween;
                    closestLan = currentLan;
                }
            }
        }

        if(closestLan == null)
            throw new NoLanInFutureException();

        return closestLan;
    }
}
