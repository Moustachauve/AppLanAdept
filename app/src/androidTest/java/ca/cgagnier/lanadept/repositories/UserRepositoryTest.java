package ca.cgagnier.lanadept.repositories;

import android.test.AndroidTestCase;

import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;

public class UserRepositoryTest extends AndroidTestCase {

    IUserRepository userRepo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        userRepo = new UserRepository();
        userRepo.deleteAll();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        userRepo.deleteAll();
        userRepo = null;
    }

    public void testGetByEmail() { //String email
        String email = "abc@def.com";
        User user = new User();
        user.email = email;

        userRepo.save(user);

        User userFound = userRepo.getByEmail(email);

        assertNotNull(userFound);
        assertEquals(user.id, userFound.id);
    }

    public void testGetByEmailNull() { //String email
        try {
            userRepo.getByEmail(null);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testGetByEmailNotFound() { //String email
        try {
            userRepo.getByEmail("asfasfas_test_not_found@abc.def");
            fail();
        }
        catch (NotFoundException ex) {}
    }

}
