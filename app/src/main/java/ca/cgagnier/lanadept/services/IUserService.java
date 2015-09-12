package ca.cgagnier.lanadept.services;

import ca.cgagnier.lanadept.models.User;

public interface IUserService {

    public User tryLogin(String email, String password);

    public User subscribe(String email, String password, String passwordConfirmation, String fullName);
}
