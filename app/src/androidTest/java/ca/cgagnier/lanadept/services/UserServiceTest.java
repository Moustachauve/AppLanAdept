package ca.cgagnier.lanadept.services;

import android.test.AndroidTestCase;

import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.services.exceptions.InvalidEmailException;
import ca.cgagnier.lanadept.services.exceptions.InvalidLoginException;
import ca.cgagnier.lanadept.services.exceptions.InvalidPasswordException;
import ca.cgagnier.lanadept.services.exceptions.UserAlreadyLoggedInException;
import ca.cgagnier.lanadept.services.exceptions.UserNotLoggedInException;

public class UserServiceTest extends AndroidTestCase {

    UserService userService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        userService = UserService.getCurrent();
        userService.userRepo.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        userService.userRepo.deleteAll();
        userService.reset();
    }


    public void testLogin() throws UserAlreadyLoggedInException, InvalidLoginException //String email, String password
    {
        fail();
    }

    public void testLogout()
    {
        fail();
    }

    public void testRegister() throws InvalidEmailException, InvalidPasswordException //String email, String password, String passwordConfirmation, String fullName
    {
        fail();
    }

    public void testGetLoggedInUser() throws UserNotLoggedInException
    {
        fail();
    }

    public void testIsUserLoggedIn()
    {
        fail();
    }

}
