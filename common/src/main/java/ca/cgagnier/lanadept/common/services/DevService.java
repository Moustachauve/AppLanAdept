package ca.cgagnier.lanadept.common.services;

import ca.cgagnier.lanadept.common.models.Place;
import ca.cgagnier.lanadept.common.models.User;
import org.joda.time.DateTime;

import ca.cgagnier.lanadept.common.models.Lan;
import ca.cgagnier.lanadept.common.models.PlaceSection;

public class DevService implements IDevService {

    //region Singleton things

    private static DevService current;

    public static DevService getCurrent() {
        if(current == null)
            current = new DevService();
        return current;
    }

    static void reset() {
        current = null;
    }

    private DevService() {}

    //endregion


    public void ResetEverything() {
        try {
            LanService.reset();
            PlaceSectionService.reset();
            PlaceService.reset();
            ReservationService.reset();
            UserService.reset();

            Lan lan = LanService.getCurrent().create(DateTime.now().plusDays(10), "Cafétéria orange", "Cégep Édouard-Montpetit, 945 chemin de Chambly, Longueuil");
            User mainUser = UserService.getCurrent().register("to@to.to", "toto", "toto", "Testing User 1");
            User secondUser = UserService.getCurrent().register("ta@to.to", "toto", "toto", "Testing User 2");

            Place lastPlace = null;
            for (int i = 'A'; i < 'J'; i++) {
                PlaceSection section = PlaceSectionService.getCurrent().create(lan, String.valueOf((char)i));
                for(int j = 1; j < 3; j++) {
                    lastPlace = PlaceService.getCurrent().addToSection(section);
                }

                PlaceSectionService.getCurrent().update(section);
            }

            ReservationService.getCurrent().reserve(secondUser, lastPlace);
            LanService.getCurrent().update(lan);
        }
        catch (Exception ex) {
            throw new NullPointerException("Erreur lors du resetEverything: " + ex);
        }
    }
}
