package ca.cgagnier.lanadept.services;

import android.test.AndroidTestCase;

import java.util.List;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.services.exceptions.InvalidIdException;
import ca.cgagnier.lanadept.services.exceptions.PlaceReservedException;

public class PlaceServiceTest extends AndroidTestCase {

    PlaceService placeService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        placeService = PlaceService.getCurrent();
        placeService.placeRepo.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        placeService.placeRepo.deleteAll();
        placeService.reset();
    }

    public void testGetById() throws InvalidIdException { //long id
        fail();
    }

    public void testGetByIdInvalidId() throws InvalidIdException { //long id
        fail();
    }

    public void testAddToSection() { //PlaceSection section
        fail();
    }

    public void testAddToSectionNull() { //PlaceSection section
        fail();
    }

    public void testRemoveFromSection() throws PlaceReservedException { //PlaceSection section
        fail();
    }

    public void testRemoveFromSectionNull() throws PlaceReservedException { //PlaceSection section
        fail();
    }

    public void testDeleteMany() { //List<Place> places
        fail();
    }

    public void testDeleteManyListNull() { //List<Place> places
        fail();
    }

    public void testDeleteManySomeNull() { //List<Place> places
        fail();
    }

}
