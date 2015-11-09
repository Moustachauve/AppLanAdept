package ca.cgagnier.lanadept.repositories;

import android.test.AndroidTestCase;

import org.joda.time.DateTime;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;

public class GenericRepositoryTest extends AndroidTestCase {

    IGenericRepository<Lan> genRepo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genRepo = new LanRepository();
        genRepo.deleteAll();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        genRepo.deleteAll();
        genRepo = null;
    }

    public void testSaveAndGetAll() {
        Lan lan = new Lan();
        lan.position = "pipo";
        lan.positionMap = "popi";
        lan.startingDate = new DateTime(2015, 5, 13, 10, 10);
        genRepo.save(lan);
        assertEquals(1, genRepo.getAll().size());
    }

    public void testGetById() {
        Lan lan = new Lan();
        lan.position = "pipo";
        lan.positionMap = "popi";
        lan.startingDate = new DateTime(2015, 5, 13, 10, 10);
        Long idTested = genRepo.save(lan);

        Lan recov = genRepo.getById(idTested);
        assertEquals(new DateTime(2015, 5, 13, 10, 10), recov.startingDate);
    }

    public void testGetByIdNotFound() {
        try {
            genRepo.getById(12512525l);
            fail();
        }
        catch (NotFoundException ex) {}
    }

    public void testDeleteAll() {
        int size = 20;

        for(int i = 0; i < size; i++) {
            Lan lan = new Lan();
            lan.position = "pipo";
            lan.positionMap = "popi";
            lan.startingDate = new DateTime(2015, 5, 13, 10, 10);

            genRepo.save(lan);
        }

        genRepo.deleteAll();

        assertEquals(0, genRepo.getAll().size());
    }

    public void testSaveVide() {
        try {
            genRepo.save(null);
            fail();
        }
        catch (NullPointerException ex)
        {
            assertEquals(0, genRepo.getAll().size());
        }
    }

//    public void testSaveManyAndGetAll() {
//        int size = 20;
//        List<Lan> lans = new ArrayList<Lan>();
//
//        for(int i = 0; i < size; i++) {
//            Lan lan = new Lan();
//            lan.position = "pipo";
//            lan.positionMap = "popi";
//            lan.startingDate = new DateTime(2015, 5, 13, 10, 10);
//
//            lans.add(lan);
//        }
//
//        genRepo.saveMany(lans);
//        assertEquals(genRepo.getAll().size(), size);
//    }
}
