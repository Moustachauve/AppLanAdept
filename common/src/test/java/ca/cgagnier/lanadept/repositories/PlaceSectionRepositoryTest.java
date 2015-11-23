package ca.cgagnier.lanadept.repositories;

import java.util.LinkedList;

import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;
import junit.framework.TestCase;

public class PlaceSectionRepositoryTest extends TestCase {

    IPlaceSectionRepository sectionRepo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sectionRepo = new PlaceSectionRepository();
        sectionRepo.deleteAll();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        sectionRepo.deleteAll();
        sectionRepo = null;
    }

    public void testDelete() throws Exception {
        PlaceSection section = new PlaceSection();
        section.name = "Section_Test_Repo";
        section.placeList = new LinkedList<>();

        section.id = sectionRepo.save(section);

        sectionRepo.delete(section);

        try {
            sectionRepo.getById(section.id);
            fail();
        }
        catch (NotFoundException ex) {}
    }

    public void testDeleteInexistant() throws Exception {
        try {
            PlaceSection fakeSection = new PlaceSection();
            fakeSection.id = 1525l;
            fakeSection.name = "Test_Fake_Section";

            sectionRepo.delete(fakeSection);
            fail();
        }
        catch (NotFoundException ex) {}
    }

    public void testDeleteNoId() throws Exception {
        try {
            PlaceSection fakeSection = new PlaceSection();
            fakeSection.id = 1525l;
            fakeSection.name = "Test_Fake_Section";

            sectionRepo.delete(fakeSection);
            fail();
        }
        catch (NotFoundException ex) {}
    }

}
