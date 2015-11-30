package ca.cgagnier.lanadept.common.services;

import ca.cgagnier.lanadept.common.models.User;
import ca.cgagnier.lanadept.common.repositories.UserRepository;
import ca.cgagnier.lanadept.common.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.common.services.exceptions.InvalidEmailException;
import ca.cgagnier.lanadept.common.services.exceptions.InvalidNameException;
import ca.cgagnier.lanadept.common.services.exceptions.UserAlreadyLoggedInException;
import ca.cgagnier.lanadept.common.services.exceptions.UserNotLoggedInException;
import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;

import java.util.LinkedList;

import ca.cgagnier.lanadept.common.services.exceptions.InvalidLoginException;
import ca.cgagnier.lanadept.common.services.exceptions.InvalidPasswordConfirmationException;
import ca.cgagnier.lanadept.common.services.exceptions.InvalidPasswordException;

public class UserService implements IUserService {

    UserRepository userRepo = new UserRepository();
    private User loggedInUser = null;

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
        if(isUserLoggedIn())
            throw new UserAlreadyLoggedInException();

        if(email == null || password == null)
            throw new NullPointerException();

        try {
            User user = userRepo.getByEmail(email);
            if(user.password.equals(password)) {
                loggedInUser = user;
                return user;
            }
        }
        catch (NotFoundException ex) { }

        throw new InvalidLoginException();
    }

    @Override
    public void logout() {
        loggedInUser = null;
    }

    @Override
    public User register(String email, String password, String passwordConfirmation, String fullName)
            throws InvalidEmailException, InvalidPasswordException, InvalidPasswordConfirmationException, InvalidNameException {
        if(email == null || password == null || passwordConfirmation == null || fullName == null)
            throw new NullPointerException();


        if(email.trim().length() == 0 || !EmailValidator.getInstance().isValid((email.trim())))
            throw new InvalidEmailException();

        if(password.trim().length() < 4)
            throw new InvalidPasswordException();

        if(passwordConfirmation.trim().length() == 0 || !passwordConfirmation.equals(password))
            throw new InvalidPasswordConfirmationException();

        if(fullName.trim().length() == 0)
            throw new InvalidNameException();

        User user = new User();
        user.email = email;
        user.password = password;
        user.dateInscription = DateTime.now();
        user.fullName = fullName;
        user.reservations = new LinkedList<>();

        user.id = userRepo.save(user);

        return user;
    }

    @Override
    public User getLoggedInUser() throws UserNotLoggedInException {
        if(!isUserLoggedIn())
            throw new UserNotLoggedInException();

        return loggedInUser;
    }

    @Override
    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }
}
