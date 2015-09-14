package ca.cgagnier.lanadept.services;

import android.test.AndroidTestCase;

import org.joda.time.DateTime;

import java.util.LinkedList;
import java.util.Random;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.services.exceptions.InvalidNameException;

public class PlaceSectionServiceTest extends AndroidTestCase {

    PlaceSectionService sectionService;
    Lan testingLan;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sectionService = PlaceSectionService.getCurrent();
        sectionService.placeSectionRepo.deleteAll();

        testingLan = LanService.getCurrent().create(DateTime.now(), "ici", "là");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        sectionService.placeSectionRepo.deleteAll();
        sectionService.reset();
        sectionService = null;
    }

    public void testCreateAndGetAllSection() throws Exception {
        Random rand = new Random(4242421);
        int nbSections = 12;

        assertEquals(0, sectionService.getAllSection().size());

        for(int i = 0; i < nbSections; i++) {
            sectionService.create(testingLan, "Test_" + rand.nextInt(1000));
        }

        assertEquals(nbSections, sectionService.getAllSection().size());
        assertEquals(nbSections, testingLan.sections.size());

        String name = "test_section_correct";
        PlaceSection sectionTest = sectionService.create(testingLan, name);

        assertNotNull(sectionTest);
        assertNotNull(sectionTest.placeList);
        assertNotNull(sectionTest.id);
        assertEquals(name, sectionTest.name);
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
        section.name = newName;
        sectionService.update(section);

        PlaceSection sectionChercher = sectionService.placeSectionRepo.getById(section.id);

        assertEquals(newName, sectionChercher.name);
    }

    public void testUpdateNull() throws Exception {
        try {
            sectionService.update(null);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testUpdateInexistant() throws Exception {
        try {
            PlaceSection fakeSection = new PlaceSection();
            fakeSection.id = 1525l;
            fakeSection.name = "Test_Fakesection";
            fakeSection.placeList = new LinkedList<>();

            sectionService.update(fakeSection);
            fail();
        }
        catch (NotFoundException ex) {}
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

    public void testDeleteNull() throws Exception {
        try {
            sectionService.delete(null);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testDeleteInexistant() throws Exception {
        try {
            PlaceSection fakeSection = new PlaceSection();
            fakeSection.id = 1525l;
            fakeSection.name = "Test_Fakesection";
            fakeSection.placeList = new LinkedList<>();

            sectionService.delete(fakeSection);
            fail();
        }
        catch (NotFoundException ex) {}
    }
}
