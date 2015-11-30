package ca.cgagnier.lanadept.common.services;

import ca.cgagnier.lanadept.common.models.Lan;
import ca.cgagnier.lanadept.common.models.Place;
import ca.cgagnier.lanadept.common.models.Reservation;
import ca.cgagnier.lanadept.common.models.User;
import ca.cgagnier.lanadept.common.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.common.services.exceptions.PlaceReservedException;
import ca.cgagnier.lanadept.common.services.exceptions.TooManyReservationException;
import junit.framework.TestCase;
import org.joda.time.DateTime;

import java.util.LinkedList;

import ca.cgagnier.lanadept.common.models.PlaceSection;

public class ReservationServiceTest extends TestCase {

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
        UserService.reset();
        sectionTest.placeList = null;
    }

    public void testReserve() throws Exception { //User user, Place place
        UserService userService = UserService.getCurrent();

        User user = userService.register("ab@cd.ca", "abc123", "abc123", "testing user");

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
        User user = UserService.getCurrent().register("ab@cd.ca", "abc123", "abc123", "testing user");

        try {
            Reservation reservation = reservationService.reserve(user, null);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testReservePlaceDejaReserver() throws Exception {
        try {
            UserService userService = UserService.getCurrent();

            User user = userService.register("ab@cd.ca", "abc123", "abc123", "testing user");

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

            User user = userService.register("ab@cd.com", "abc123", "abc123", "testing user");

            Place place1 = PlaceService.getCurrent().addToSection(sectionTest);
            Place place2 = PlaceService.getCurrent().addToSection(sectionTest);

            reservationService.reserve(user, place1);
            reservationService.reserve(user, place2);

            fail();
        }
        catch (TooManyReservationException ex) {}
    }

    public void testCancel() throws Exception { //Reservation reservation
        UserService userService = UserService.getCurrent();

        User user = userService.register("ab@cd.ca", "abc123", "abc123", "testing user");

        Place place = PlaceService.getCurrent().addToSection(sectionTest);
        Reservation reservation = reservationService.reserve(user, place);

        reservationService.cancel(reservation);

        assertNull(place.reservation);
    }

    public void testCancelNull() throws Exception { //Reservation reservation
        try {
            reservationService.cancel(null);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testCancelReservationFake() throws Exception { //Reservation reservation
        User user = UserService.getCurrent().register("ab@cd.ca", "abc123", "abc123", "testing user");
        Place place = PlaceService.getCurrent().addToSection(sectionTest);

        Reservation reservationFake = new Reservation();
        reservationFake.id = 145612l;
        reservationFake.user = user;
        reservationFake.place = place;

        try {
            reservationService.cancel(reservationFake);
            fail();
        }
        catch (NotFoundException ex) {}
    }
}
