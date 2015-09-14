package ca.cgagnier.lanadept.repositories;

import android.test.AndroidTestCase;

import org.joda.time.DateTime;

import java.util.LinkedList;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;

public class LanRepositoryTest extends AndroidTestCase {

    ILanRepository lanRepo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lanRepo = new LanRepository();
        lanRepo.deleteAll();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        lanRepo.deleteAll();
        lanRepo = null;
    }

    public void testDelete() throws Exception {
        Lan lan = new Lan();
        lan.startingDate = DateTime.now();
        lan.position = "pos";
        lan.positionMap = "posMap";
        lan.sections = new LinkedList<>();
        lan.id = lanRepo.save(lan);

        lanRepo.delete(lan);

        try {
            lanRepo.getById(lan.id);
            fail();
        }
        catch (NotFoundException ex) {}
    }

    public void testDeleteInexistant() throws Exception {
        try {
            Lan fakeLan = new Lan();
            fakeLan.id = 1525l;
            fakeLan.startingDate = DateTime.now();
            fakeLan.position = "fake_pos";
            fakeLan.positionMap = "fake_pos_map";

            lanRepo.delete(fakeLan);
            fail();
        }
        catch (NotFoundException ex) {}
    }

    public void testDeleteNoId() throws Exception {
        try {
            Lan fakeLan = new Lan();
            fakeLan.startingDate = DateTime.now();
            fakeLan.position = "fake_pos";
            fakeLan.positionMap = "fake_pos_map";

            lanRepo.delete(fakeLan);
            fail();
        }
        catch (NotFoundException ex) {}
    }


}
