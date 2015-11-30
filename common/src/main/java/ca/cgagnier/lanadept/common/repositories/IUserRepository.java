package ca.cgagnier.lanadept.common.repositories;

import ca.cgagnier.lanadept.common.models.User;

public interface IUserRepository extends  IGenericRepository<User> {

    /**
     * Get an user by its email
     * @param email Email of the user
     * @return The user found
     */
    public User getByEmail(String email);

}
