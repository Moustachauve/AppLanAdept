package ca.cgagnier.lanadept.services;

import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.services.exceptions.InvalidEmailException;
import ca.cgagnier.lanadept.services.exceptions.InvalidLoginException;
import ca.cgagnier.lanadept.services.exceptions.InvalidPasswordException;
import ca.cgagnier.lanadept.services.exceptions.UserAlreadyLoggedInException;
import ca.cgagnier.lanadept.services.exceptions.UserNotLoggedInException;

public interface IUserService {

    public User login(String email, String password) throws UserAlreadyLoggedInException, InvalidLoginException;

    public void logout();

    public User register(String email, String password, String passwordConfirmation, String fullName) throws InvalidEmailException, InvalidPasswordException;

    public User getLoggedInUser() throws UserNotLoggedInException;

    public boolean isUserLoggedIn();

}
