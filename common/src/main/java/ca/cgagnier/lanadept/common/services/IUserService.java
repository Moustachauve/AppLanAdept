package ca.cgagnier.lanadept.common.services;

import ca.cgagnier.lanadept.common.models.User;
import ca.cgagnier.lanadept.common.services.exceptions.InvalidEmailException;
import ca.cgagnier.lanadept.common.services.exceptions.InvalidNameException;
import ca.cgagnier.lanadept.common.services.exceptions.UserAlreadyLoggedInException;
import ca.cgagnier.lanadept.common.services.exceptions.UserNotLoggedInException;
import ca.cgagnier.lanadept.common.services.exceptions.InvalidLoginException;
import ca.cgagnier.lanadept.common.services.exceptions.InvalidPasswordConfirmationException;
import ca.cgagnier.lanadept.common.services.exceptions.InvalidPasswordException;

public interface IUserService {

    /**
     * Login a user in the app
     * @param email Email of the user
     * @param password Password of the user
     * @return The user that successfully logged in
     * @throws UserAlreadyLoggedInException Thrown if someone is already logged in the application
     * @throws InvalidLoginException Thrown if the login information are incorrect
     */
    public User login(String email, String password) throws UserAlreadyLoggedInException, InvalidLoginException;

    /**
     * Logout the currently logged in user. Does nothing if no users are logged in.
     */
    public void logout();

    /**
     * Register a new user to the storage
     * @param email Email of the user
     * @param password Password of the user
     * @param passwordConfirmation Password confirmation to prevent typos
     * @param fullName Full name of the user
     * @return The user that successfully registered
     * @throws InvalidEmailException Thrown if the email the user specified is invalid
     * @throws InvalidPasswordException Thrown if the password the user specified is invalid
     * @throws InvalidPasswordConfirmationException Thrown if the password confirmation the user specified is invalid or does not match
     * @throws InvalidNameException Thrown if the full name the user specified is invalid
     */
    public User register(String email, String password, String passwordConfirmation, String fullName)
            throws InvalidEmailException, InvalidPasswordException, InvalidPasswordConfirmationException, InvalidNameException;

    /**
     * Get the currently logged in user
     * @return Logged in user
     * @throws UserNotLoggedInException Thrown if no user are connected
     */
    public User getLoggedInUser() throws UserNotLoggedInException;

    /**
     * Determine whether an user is currently logged in or not
     * @return True if an user is logged in, else false
     */
    public boolean isUserLoggedIn();

}
