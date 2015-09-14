package ca.cgagnier.lanadept.services;

import android.test.AndroidTestCase;

import org.joda.time.DateTime;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ServiceConfigurationError;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.services.exceptions.InvalidNameException;

public class PlaceSectionServiceTest extends AndroidTestCase {

    PlaceSectionService sectionService;
    Lan testingLan;

    public PlaceSectionServiceTest() {
        LanService.getCurrent().lanRepo.deleteAll();

        testingLan = new Lan();
        testingLan.dateDebut = DateTime.now();
        testingLan.emplacementGoogleMap = "ici";
        testingLan.emplacement = "Là";

        LanService.getCurrent().lanRepo.save(testingLan);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sectionService = PlaceSectionService.getCurrent();
        sectionService.placeSectionRepo.deleteAll();
        testingLan.sections = new LinkedList<>();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        sectionService.placeSectionRepo.deleteAll();
        sectionService.reset();
        sectionService = null;
        testingLan.sections = null;
    }

    public void testCreateAndGetAllSection() throws Exception {
        Random rand = new Random(4242421);
        int nbSections = 12;

        assertEquals(0, sectionService.getAllSection());

        for(int i = 0; i < nbSections; i++) {
            sectionService.create(testingLan, "Test_" + rand.nextInt(1000));
        }

        assertEquals(nbSections, sectionService.getAllSection());
    }

    public void testCreateLanNull() throws Exception {
        try {
            sectionService.create(null, "Test_null");
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testCreateNomNullOrEmpty() throws Exception {
        try {
            sectionService.create(testingLan, " ");
            fail();
        }
        catch (InvalidNameException ex) {}

        try {
            sectionService.create(testingLan, "");
            fail();
        }
        catch (InvalidNameException ex) {}

        try {
            sectionService.create(testingLan, null);
            fail();
        }
        catch (NullPointerException ex) {}

    }

    public void testUpdate() throws Exception {
        PlaceSection section = sectionService.create(testingLan, "Test_Update_part1");

        String newName = "Test_Update_part2";
        section.nom = newName;
        sectionService.update(section);

        PlaceSection sectionChercher = sectionService.placeSectionRepo.getById(section.id);

        assertEquals(newName, sectionChercher.nom);
    }

    public void testDelete() throws Exception {

        PlaceSection section = sectionService.create(testingLan, "Test_Delete");

        sectionService.delete(section);

        try {
            sectionService.placeSectionRepo.getById(section.id);
            fail();
        }
        catch (NotFoundException ex) {}
    }
}
