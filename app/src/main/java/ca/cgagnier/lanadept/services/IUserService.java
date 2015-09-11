package ca.cgagnier.lanadept.services;

import ca.cgagnier.lanadept.models.User;

public interface IUserService {

    public User tryLogin(String username, String password);

    public User subscribe(String username, String password, String passwordConfirmation, String fullName);

    public
}
