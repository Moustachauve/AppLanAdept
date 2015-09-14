package ca.cgagnier.lanadept.repositories;

import ca.cgagnier.lanadept.models.User;

public interface IUserRepository extends  IGenericRepository<User> {

    public User getByEmail(String email);

}
