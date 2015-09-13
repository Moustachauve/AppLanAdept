package ca.cgagnier.lanadept.services;

import android.test.AndroidTestCase;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.services.exceptions.NoLanInFutureException;

public class LanServiceTest extends AndroidTestCase {

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
        lanService.lanRepo.save(createTestLan());
        lanService.lanRepo.save(createTestLan());
        lanService.lanRepo.save(createTestLan());

        assertEquals(3, lanService.getAllLans().size());
    }

    public void testSelectLanAndGetSelectedLan() { //Lan selectedLan
        Lan testLan = createTestLan();
        lanService.lanRepo.save(testLan);
        lanService.lanRepo.save(createTestLan());
        lanService.lanRepo.save(createTestLan());

        lanService.selectLan(testLan);

        assertEquals(testLan, lanService.getSelectedLan());
    }

    public void testGetClosestNextLan() throws Exception {
        Lan testLan = createTestLan();
        testLan.dateDebut = new DateTime(2015, 12, 4, 2, 1);

        lanService.lanRepo.save(testLan);
        lanService.lanRepo.save(createTestLan());
        lanService.lanRepo.save(createTestLan());

        assertEquals(testLan, lanService.getClosestNextLan());
    }

    public void testGetClosestNextLanNoFutureLan() throws Exception {
        lanService.lanRepo.save(createTestLan());
        lanService.lanRepo.save(createTestLan());
        lanService.lanRepo.save(createTestLan());

        try {
            lanService.getClosestNextLan();
            fail();
        }
        catch (NoLanInFutureException ex)
        {}
    }

    private Lan createTestLan() {
        Random rand = new Random(424242);

        Lan lan = new Lan();
        lan.dateDebut = new DateTime(2014, rand.nextInt(12), rand.nextInt(25), 2, 2);
        lan.emplacement = "ici";
        lan.emplacementGoogleMap = "là";
        lan.sections = new ArrayList<>();

        return lan;
    }
}
