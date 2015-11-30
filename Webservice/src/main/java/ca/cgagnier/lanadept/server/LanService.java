package ca.cgagnier.lanadept.server;

import ca.cgagnier.lanadept.common.models.Lan;
import ca.cgagnier.lanadept.common.repositories.LanRepository;
import ca.cgagnier.lanadept.common.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.common.services.ILanService;
import ca.cgagnier.lanadept.common.services.exceptions.NoLanException;
import ca.cgagnier.lanadept.common.services.exceptions.NoLanInFutureException;
import ca.cgagnier.lanadept.common.transferObjects.jsonMapper.LanModel;
import ca.cgagnier.lanadept.server.utils.JsonTools;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/lan")
public class LanService {
    private Lan selectedLan;
    LanRepository lanRepo = new LanRepository();

    @GET
    @Path("/getAllLans")
    @Produces("text/json")
    public String getAllLans() throws Exception {
        List<LanModel> lanModels = new ArrayList<>();

        for (Lan lan : lanRepo.getAll())
        {
            lanModels.add(new LanModel(lan));
        }

        return JsonTools.toJson(lanModels);
    }

    @GET
    @Path("/test")
    @Produces("text/json")
    public String test() {
        return "test";
    }

    public void selectLan(Lan lanToSelect) {
        if(lanToSelect == null)
            throw new NullPointerException();

        selectedLan = lanToSelect;
    }

//    public Lan getSelectedLan() throws NoLanException {
//        if(selectedLan == null) {
//            try {
//                selectedLan = getClosestNextLan();
//            }
//            catch (NoLanInFutureException ex) {
//                List<Lan> lans = lanRepo.getAll();
//                if(lans.size() == 0)
//                    throw new NoLanException();
//
//                selectedLan = lans.get(lans.size() - 1);
//            }
//        }
//
//        return selectedLan;
//    }

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

    public Lan getById(long id) throws NotFoundException {
        return lanRepo.getById(id);
    }

    public void update(Lan lan) {
        if(lan.id == null)
            throw new NotFoundException();

        lanRepo.save(lan);
    }

    public void delete(Lan lan) {
        if(lan.id == null)
            throw new NotFoundException();

        lanRepo.delete(lan);
    }

//    public Lan getClosestNextLan() throws NoLanInFutureException {
//        List<Lan> lans = getAllLans();
//        int bestDaysBetween = Integer.MAX_VALUE;
//        Lan closestLan = null;
//
//        DateTime today = DateTime.now();
//
//        for(Lan currentLan : lans)
//        {
//            if(currentLan.startingDate.isAfter(today)) {
//                int daysBetween = Days.daysBetween(currentLan.startingDate, today).getDays() + 1;
//                if (daysBetween < bestDaysBetween) {
//                    bestDaysBetween = daysBetween;
//                    closestLan = currentLan;
//                }
//            }
//        }
//
//        if(closestLan == null)
//            throw new NoLanInFutureException();
//
//        return closestLan;
//    }

}
