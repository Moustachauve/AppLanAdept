package ca.cgagnier.lanadept.repositories;

import java.util.List;

import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;

public class UserRepository extends GenericRepository<User> implements IUserRepository {
    @Override
    public User getByEmail(String email) {
        for(User user : listRecord) {
            if(user.email.equals(email))
                return user;
        }

        throw new NotFoundException();
    }
}
