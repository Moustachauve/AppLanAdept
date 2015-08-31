package ca.cgagnier.lanadept.repositories;

import java.util.List;

import ca.cgagnier.lanadept.models.User;

public class UserRepository implements IUserRepository {
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public void save(User obj) {

    }

    @Override
    public void saveMany(Iterable<User> list) {

    }

    @Override
    public void saveMany(User... list) {

    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public void deleteAll() {

    }
}
