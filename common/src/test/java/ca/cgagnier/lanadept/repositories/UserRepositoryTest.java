package ca.cgagnier.lanadept.repositories;


import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;
import junit.framework.TestCase;

public class UserRepositoryTest extends TestCase {

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
