package ca.cgagnier.lanadept.services;

import android.test.AndroidTestCase;

import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.services.exceptions.InvalidEmailException;
import ca.cgagnier.lanadept.services.exceptions.InvalidNameException;
import ca.cgagnier.lanadept.services.exceptions.InvalidLoginException;
import ca.cgagnier.lanadept.services.exceptions.InvalidPasswordConfirmationException;
import ca.cgagnier.lanadept.services.exceptions.InvalidPasswordException;
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

    public void testRegister() throws Exception //String email, String password, String passwordConfirmation, String fullName
    {
        String email = "abc@def.ghi";
        String password = "abcd1234";
        String fullName = "Pipo Popi";
        User regUser = userService.register(email, password, password, fullName);

        assertNotNull(regUser);
        assertNotNull(regUser.id);
        assertEquals(email, regUser.email);
        assertEquals(fullName, regUser.fullName);
        assertNotNull(regUser.reservations);
        assertEquals(0, regUser.reservations.size());
        assertNotNull(regUser.dateInscription);

        assertEquals(regUser, userService.userRepo.getById(regUser.id));
    }

    public void testRegisterSpecialCharacters() throws Exception {
        String email = "a_b-c.d+e!#$%&'*éé漢字仮@def.ghi";
        String password = "a_b-c.d+e!#$%&'*éé漢字仮";
        String fullName = "a_b-c.d+e!#$ %&'*éé漢字仮";
        User regUser = userService.register(email, password, password, fullName);

        assertNotNull(regUser);
        assertNotNull(regUser.id);
    }

    public void testRegisterNoEmail() throws Exception //String email, String password, String passwordConfirmation, String fullName
    {
        try {
            User regUser = userService.register(" ", "abcdefg", "abcdefg", "Jean Pol");
            fail();
        }
        catch (InvalidEmailException ex) {}

        try {
            User regUser = userService.register("", "abcdefg", "abcdefg", "Jean Pol");
            fail();
        }
        catch (InvalidEmailException ex) {}

        try {
            User regUser = userService.register(null, "abcdefg", "abcdefg", "Jean Pol");
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testRegisterInvalidEmail() throws Exception
    {
        try {
            User regUser = userService.register("invalidEmail.ca", "abcdefg", "abcdefg", "Jean Pol");
            fail();
        }
        catch (InvalidEmailException ex) {}
    }

    public void testRegisterNoPassword() throws Exception //String email, String password, String passwordConfirmation, String fullName
    {
        try {
            User regUser = userService.register("abc@def.com", " ", "abcdefg", "Jean Pol");
            fail();
        }
        catch (InvalidPasswordException ex) {}

        try {
            User regUser = userService.register("abc@def.com", "", "abcdefg", "Jean Pol");
            fail();
        }
        catch (InvalidPasswordException ex) {}

        try {
            User regUser = userService.register("abc@def.com", null, "abcdefg", "Jean Pol");
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testRegisterPasswordTooShort() throws Exception {

        try {
            User regUser = userService.register("abc@def.com", "123", "123", "Jean Pol");
            fail();
        }
        catch (InvalidPasswordException ex) {}

    }

    public void testRegisterInvalidPasswordConfirmation() throws Exception //String email, String password, String passwordConfirmation, String fullName
    {
        try {
            User regUser = userService.register("abc@def.com", "abcdefg", "123", "Jean Pol");
            fail();
        }
        catch (InvalidPasswordConfirmationException ex) {}

        try {
            User regUser = userService.register("abc@def.com", "abcdefg", null, "Jean Pol");
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testRegisterNoFullName() throws Exception {
        try {
            User regUser = userService.register("abc@def.com", "abcdefg", "abcdefg", " ");
            fail();
        }
        catch (InvalidNameException ex) {}

        try {
            User regUser = userService.register("abc@def.com", "abcdefg", "abcdefg", "");
            fail();
        }
        catch (InvalidNameException ex) {}

        try {
            User regUser = userService.register("abc@def.com", "abcdefg", "abcdefg", null);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testLogin() throws Exception //String email, String password
    {
        String email = "abc@def.ghi";
        String password = "abcd1234";
        String fullName = "Pipo Popi";
        User regUser = userService.register(email, password, password, fullName);

        User conUser = userService.login(email, password);

        assertEquals(regUser.id, conUser.id);
        assertEquals(true, userService.isUserLoggedIn());
    }

    public void testLoginNoEmail() throws Exception {
        String email = "abc@def.ghi";
        String password = "abcd1234";
        String fullName = "Pipo Popi";
        User regUser = userService.register(email, password, password, fullName);

        try {
            userService.login(" ", password);
            fail();
        }
        catch (InvalidLoginException ex) {}

        try {
            userService.login("", password);
            fail();
        }
        catch (InvalidLoginException ex) {}

        try {
            userService.login(null, password);
            fail();
        }
        catch (NullPointerException ex) {}
    }

    public void testLoginInvalidLogin() throws Exception {
        String email = "abc@def.ghi";
        String password = "abcd1234";
        String fullName = "Pipo Popi";
        User regUser = userService.register(email, password, password, fullName);

        try {
            userService.login(email, "abcdef");
            fail();
        }
        catch (InvalidLoginException ex) {
            assertEquals(false, userService.isUserLoggedIn());
        }

        try {
            userService.login("abc@def.com", password);
            fail();
        }
        catch (InvalidLoginException ex) {
            assertEquals(false, userService.isUserLoggedIn());
        }
    }

    public void testLoginInvalidEmail() throws Exception
    {
        try {
            User regUser = userService.register("invalidEmail.ca", "abcdefg", "abcdefg", "Jean Pol");
            fail();
        }
        catch (InvalidEmailException ex) {}
    }


    public void testLogout() throws Exception
    {
        String email = "abc@def.ghi";
        String password = "abcd1234";
        String fullName = "Pipo Popi";
        User regUser = userService.register(email, password, password, fullName);

        userService.login(email, password);
        userService.logout();

        assertEquals(false, userService.isUserLoggedIn());
    }

    public void testGetLoggedInUser() throws Exception
    {
        String email = "abc@def.ghi";
        String password = "abcd1234";
        String fullName = "Pipo Popi";
        User regUser = userService.register(email, password, password, fullName);

        userService.login(email, password);

        User conUser = userService.getLoggedInUser();

        assertEquals(regUser.id, conUser.id);
    }

    public void testGetLoggedInUserNotLoggedIn() throws Exception {
        try {
            User conUser = userService.getLoggedInUser();
            fail();
        }
        catch (UserNotLoggedInException ex) {}
    }

    public void testIsUserLoggedIn() throws Exception {
        String email = "abc@def.ghi";
        String password = "abcd1234";
        String fullName = "Pipo Popi";
        User regUser = userService.register(email, password, password, fullName);

        assertEquals(false, userService.isUserLoggedIn());
        userService.login(email, password);
        assertEquals(true, userService.isUserLoggedIn());
        userService.logout();
        assertEquals(false, userService.isUserLoggedIn());
    }

}
