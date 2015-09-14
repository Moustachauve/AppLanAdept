package ca.cgagnier.lanadept.services;

import android.test.AndroidTestCase;

import org.joda.time.DateTime;

import java.util.LinkedList;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.models.Reservation;
import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.services.exceptions.PlaceReservedException;
import ca.cgagnier.lanadept.services.exceptions.TooManyReservationException;

public class ReservationServiceTest extends AndroidTestCase {

    ReservationService reservationService;
    PlaceSection sectionTest;

    public ReservationServiceTest() throws Exception {
        Lan lan = LanService.getCurrent().create(DateTime.now(), "ici", "là");
        sectionTest = PlaceSectionService.getCurrent().create(lan, "test_reservations");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        reservationService = ReservationService.getCurrent();
        reservationService.reservationRepo.deleteAll();
        sectionTest.placeList = new LinkedList<>();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        reservationService.reservationRepo.deleteAll();
        reservationService.reset();
        sectionTest.placeList = null;
    }

    public void testReserve() throws Exception { //User user, Place place
        UserService userService = UserService.getCurrent();

        User user = userService.register("a@b.c", "abc123", "abc123", "testing user");

        Place place = PlaceService.getCurrent().addToSection(sectionTest);

        Reservation reservation = reservationService.reserve(user, place);

        assertEquals(1, userService.userRepo.getById(reservation.user.id).reservations.size());
        assertNotNull(PlaceService.getCurrent().placeRepo.getById(reservation.place.id).reservation);
    }

    public void testReserveNullUser() throws PlaceReservedException, TooManyReservationException { //User user, Place place
        PlaceSection section = new PlaceSection();
        section.name = "TEST";

        Place place = new Place();
        place.numero = 1;
        place.placeSection = section;
        try {
            Reservation reservation = reservationService.reserve(null, new Place());
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testReserveNullPlace() throws Exception { //User user, Place place
        User user = UserService.getCurrent().register("a@b.c", "abc123", "abc123", "testing user");

        try {
            Reservation reservation = reservationService.reserve(user, null);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testReservePlaceDejaReserver() throws Exception {
        try {
            UserService userService = UserService.getCurrent();

            User user = userService.register("a@b.c", "abc123", "abc123", "testing user");

            Place place = PlaceService.getCurrent().addToSection(sectionTest);

            reservationService.reserve(user, place);
            reservationService.reserve(user, place);

            fail();
        }
        catch (PlaceReservedException ex) {}
    }

    public void testReserveUserDeuxPlaceMemeLan() throws Exception {
        try {
            UserService userService = UserService.getCurrent();

            User user = userService.register("a@b.c", "abc123", "abc123", "testing user");

            Place place1 = PlaceService.getCurrent().addToSection(sectionTest);
            Place place2 = PlaceService.getCurrent().addToSection(sectionTest);

            reservationService.reserve(user, place1);
            reservationService.reserve(user, place2);

            fail();
        }
        catch (TooManyReservationException ex) {}
    }


    public void testCancel() { //Reservation reservation
        fail();
    }
}
