package ca.cgagnier.lanadept.services;

import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.repositories.UserRepository;
import ca.cgagnier.lanadept.services.exceptions.InvalidEmailException;
import ca.cgagnier.lanadept.services.exceptions.InvalidLoginException;
import ca.cgagnier.lanadept.services.exceptions.InvalidPasswordException;
import ca.cgagnier.lanadept.services.exceptions.UserAlreadyLoggedInException;
import ca.cgagnier.lanadept.services.exceptions.UserNotLoggedInException;

public class UserService implements IUserService {

    UserRepository userRepo = new UserRepository();

    //region Singleton things

    private static UserService current;

    public static UserService getCurrent() {
        if(current == null)
            current = new UserService();
        return current;
    }

    static void reset() {
        current = null;
    }

    private UserService() {}

    //endregion

    @Override
    public User login(String email, String password) throws UserAlreadyLoggedInException, InvalidLoginException {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public User register(String email, String password, String passwordConfirmation, String fullName) throws InvalidEmailException, InvalidPasswordException {
        return null;
    }

    @Override
    public User getLoggedInUser() throws UserNotLoggedInException {
        return null;
    }

    @Override
    public boolean isUserLoggedIn() {
        return false;
    }
}
