package ca.cgagnier.lanadept.services;

import android.test.AndroidTestCase;

import org.joda.time.DateTime;

import java.util.LinkedList;
import java.util.List;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.services.exceptions.InvalidIdException;
import ca.cgagnier.lanadept.services.exceptions.PlaceReservedException;

public class PlaceServiceTest extends AndroidTestCase {

    PlaceService placeService;
    PlaceSection sectionTest;

    public PlaceServiceTest() throws Exception {
        Lan lan = LanService.getCurrent().create(DateTime.now(), "ici", "là");
        sectionTest = PlaceSectionService.getCurrent().create(lan, "test_reservations");
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        placeService = PlaceService.getCurrent();
        placeService.placeRepo.deleteAll();
        sectionTest.placeList = new LinkedList<>();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        placeService.placeRepo.deleteAll();
        placeService.reset();
        sectionTest.placeList = null;
    }

    public void testAddAndGetById() throws InvalidIdException { //long id
        Place place = placeService.addToSection(sectionTest);

        assertEquals(1, sectionTest.placeList.size());
        assertNotNull(place);

        Place place2 = placeService.getById(place.id);

        assertNotNull(place2);
        assertEquals(place.id, place2.id);
    }

    public void testGetByIdInvalidId() throws InvalidIdException { //long id
        try {
            placeService.getById(126536362);
            fail();
        }
        catch (InvalidIdException ex) {}
    }

    public void testAddToSectionNull() { //PlaceSection section
        try {
            placeService.addToSection(null);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testRemoveFromSection() throws PlaceReservedException { //PlaceSection section
        placeService.addToSection(sectionTest);
        placeService.addToSection(sectionTest);
        placeService.addToSection(sectionTest);

        placeService.removeFromSection(sectionTest);
        placeService.removeFromSection(sectionTest);

        assertEquals(1, sectionTest.placeList.size());
    }

    public void testRemoveFromSectionNull() throws PlaceReservedException { //PlaceSection section
        try {
            placeService.removeFromSection(null);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testDeleteMany() { //List<Place> places
        placeService.addToSection(sectionTest);
        placeService.addToSection(sectionTest);

        List<Place> listToRemove = new LinkedList<>();
        listToRemove.add(placeService.addToSection(sectionTest));
        listToRemove.add(placeService.addToSection(sectionTest));

        placeService.deleteMany(listToRemove);

        assertEquals(2, sectionTest.placeList.size());
    }

    public void testDeleteManyListNull() { //List<Place> places
        try {
            placeService.deleteMany(null);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testDeleteManySomeNull() { //List<Place> places
        placeService.addToSection(sectionTest);
        placeService.addToSection(sectionTest);

        List<Place> listToRemove = new LinkedList<>();
        listToRemove.add(placeService.addToSection(sectionTest));
        listToRemove.add(placeService.addToSection(sectionTest));
        listToRemove.add(null);
        listToRemove.add(placeService.addToSection(sectionTest));

        placeService.deleteMany(listToRemove);

        assertEquals(2, sectionTest.placeList.size());
    }

}
