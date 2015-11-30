package ca.cgagnier.lanadept.common.services;

import ca.cgagnier.lanadept.common.services.exceptions.NoLanException;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.LinkedList;
import java.util.List;

import ca.cgagnier.lanadept.common.models.Lan;
import ca.cgagnier.lanadept.common.repositories.LanRepository;
import ca.cgagnier.lanadept.common.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.common.services.exceptions.NoLanInFutureException;

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
    public Lan getSelectedLan() throws NoLanException {
        if(selectedLan == null) {
            try {
                selectedLan = getClosestNextLan();
            }
            catch (NoLanInFutureException ex) {
                List<Lan> lans = lanRepo.getAll();
                if(lans.size() == 0)
                    throw new NoLanException();

                selectedLan = lans.get(lans.size() - 1);
            }
        }

        return selectedLan;
    }

    @Override
    public Lan create(DateTime startingDate, String position, String positionMap)  {
        if(startingDate == null || position == null || positionMap == null)
            throw new NullPointerException();

        Lan lan = new Lan();
        lan.startingDate = startingDate;
        lan.position = position;
        lan.positionMap = positionMap;
        lan.sections = new LinkedList<>();

        lanRepo.save(lan);

        return lan;
    }

    @Override
    public Lan getById(long id) throws NotFoundException {
        return lanRepo.getById(id);
    }

    @Override
    public void update(Lan lan) {
        if(lan.id == null)
            throw new NotFoundException();

        lanRepo.save(lan);
    }

    @Override
    public void delete(Lan lan) {
        if(lan.id == null)
            throw new NotFoundException();

        lanRepo.delete(lan);
    }

    @Override
    public Lan getClosestNextLan() throws NoLanInFutureException {
        List<Lan> lans = getAllLans();
        int bestDaysBetween = Integer.MAX_VALUE;
        Lan closestLan = null;

        DateTime today = DateTime.now();

        for(Lan currentLan : lans)
        {
            if(currentLan.startingDate.isAfter(today)) {
                int daysBetween = Days.daysBetween(currentLan.startingDate, today).getDays() + 1;
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
