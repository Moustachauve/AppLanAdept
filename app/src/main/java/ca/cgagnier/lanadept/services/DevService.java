package ca.cgagnier.lanadept.services;

import org.joda.time.DateTime;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.models.User;

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

            Lan lan = LanService.getCurrent().create(DateTime.now().plusDays(10), "Cafétéria orange", "Cégep Édouard-Montpetit, 945 chemin de Chambly, Longueui");
            User mainUser = UserService.getCurrent().register("to@to.to", "toto", "toto", "Testing User");

            for (int i = 'A'; i < 'J'; i++) {
                PlaceSection section = PlaceSectionService.getCurrent().create(lan, String.valueOf(i));
                for(int j = 1; i < 25; i++) {
                    PlaceService.getCurrent().addToSection(section);
                }
            }

        }
        catch (Exception ex) {
            throw new NullPointerException("Erreur lors du resetEverything: " + ex.getMessage());
        }
    }
}
