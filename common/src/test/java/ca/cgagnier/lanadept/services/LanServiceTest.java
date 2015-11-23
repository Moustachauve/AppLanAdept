package ca.cgagnier.lanadept.services;

import junit.framework.TestCase;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Random;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.services.exceptions.NoLanInFutureException;

public class LanServiceTest extends TestCase {

    LanService lanService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lanService = LanService.getCurrent();
        lanService.lanRepo.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        lanService.lanRepo.deleteAll();
        lanService.reset();
    }

    public void testGetAllLans() {
        createTestingLan();
        createTestingLan();
        createTestingLan();

        assertEquals(3, lanService.getAllLans().size());
    }

    public void testGetAllLansOrderedByDate() {
        Random rand = new Random(4242);

        for(int i = 0; i < 20; i++) {
            lanService.create(new DateTime(rand.nextInt(6) + 2010, rand.nextInt(11) + 1, rand.nextInt(26) + 1, 12, 0), "test_order_pos" + i, "test_order_pos_map" + i);
        }

        List<Lan> lans = lanService.getAllLans();

        DateTime prevDate = null;

        for(Lan lan : lans) {
            if(prevDate != null && prevDate.isAfter(prevDate)) {
                fail();
            }

            prevDate = lan.startingDate;
        }
    }

    public void testSelectLanAndGetSelectedLan() throws Exception { //Lan selectedLan
        Lan testLan = createTestingLan();
        createTestingLan();
        createTestingLan();

        lanService.selectLan(testLan);

        assertEquals(testLan, lanService.getSelectedLan());
    }

    public void testGetClosestNextLan() throws Exception {
        Lan testLan = lanService.create(new DateTime(2015, 12, 4, 2, 1), "test_closest_lan", "test_closest_lan_map");

        lanService.lanRepo.save(testLan);
        createTestingLan();
        createTestingLan();

        assertEquals(testLan.id, lanService.getClosestNextLan().id);
    }

    public void testGetClosestNextLanNoFutureLan() throws Exception {
        createTestingLan();
        createTestingLan();
        createTestingLan();

        try {
            lanService.getClosestNextLan();
            fail();
        }
        catch (NoLanInFutureException ex)
        {}
    }

    public void testCreateAndGetById() throws Exception { //DateTime startingDate, String position, String emplacementGoogleMaps
        DateTime startingDate = DateTime.now();
        String position = "Un endroit à Longueuil";
        String positionMap = "123 boul. taschereau";
        Lan lan = lanService.create(startingDate, position, positionMap);

        assertNotNull(lan);
        assertNotNull(lan.id);
        assertNotNull(lan.sections);

        Lan lanCheck = lanService.getById(lan.id);

        assertNotNull(lanCheck);
        assertEquals(startingDate, lanCheck.startingDate);
        assertEquals(position, lanCheck.position);
        assertEquals(positionMap, lanCheck.positionMap);
    }

    public void getByIdInvalidId() throws Exception {
        try {
            lanService.getById(162625l);
            fail();
        }
        catch (NotFoundException ex) {}
    }

    public void testUpdate() throws Exception {
        Lan lan = lanService.create(DateTime.now(), "avant1", "avant2");

        DateTime newDate = DateTime.now().plusDays(10);
        String newPosition = "apres1";
        String newPositionMap = "apres2";

        lan.startingDate = newDate;
        lan.position = newPosition;
        lan.positionMap = newPositionMap;

        lanService.update(lan);
        Lan lanCheck = lanService.getById(lan.id);

        assertEquals(newDate, lanCheck.startingDate);
        assertEquals(newPosition, lanCheck.position);
        assertEquals(newPositionMap, lanCheck.positionMap);
    }

    public void testUpdateInexistant() throws Exception {
        try {
            Lan fakeLan = new Lan();
            fakeLan.id = 1525l;
            fakeLan.startingDate = DateTime.now();
            fakeLan.position = "fake_pos";
            fakeLan.positionMap = "fake_pos_map";

            lanService.update(fakeLan);
            fail();
        }
        catch (NotFoundException ex) {}
    }

    public void testUpdateNoId() throws Exception {
        try {
            Lan fakeLan = new Lan();
            fakeLan.startingDate = DateTime.now();
            fakeLan.position = "fake_pos";
            fakeLan.positionMap = "fake_pos_map";

            lanService.update(fakeLan);
            fail();
        }
        catch (NotFoundException ex) {}
    }

    public void testDelete() throws Exception {
        Lan lan = lanService.create(DateTime.now(), "pos", "posMap");

        lanService.delete(lan);

        try {
            lanService.getById(lan.id);
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

            lanService.delete(fakeLan);
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

            lanService.delete(fakeLan);
            fail();
        }
        catch (NotFoundException ex) {}
    }

    // -----------------------------------------------------------

    private Lan createTestingLan() {
        Random rand = new Random(424242);
        DateTime startingDate = new DateTime(2014, rand.nextInt(11) + 1, rand.nextInt(26) + 1, 2, 2);

        return lanService.create(startingDate, "test_pos", "test_pos_map");
    }
}
