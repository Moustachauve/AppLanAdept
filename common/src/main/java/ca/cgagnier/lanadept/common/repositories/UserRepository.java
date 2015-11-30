package ca.cgagnier.lanadept.common.repositories;

import ca.cgagnier.lanadept.common.models.User;
import ca.cgagnier.lanadept.common.repositories.exceptions.NotFoundException;

public class UserRepository extends GenericRepository<User> implements IUserRepository {
    @Override
    public User getByEmail(String email) {
        if(email == null)
            throw new NullPointerException();

        for(User user : listRecord) {
            if(user.email.equals(email))
                return user;
        }

        throw new NotFoundException();
    }
}
