package co.empathy.academy.search.service;

import co.empathy.academy.search.models.User;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface UserService {

    void save(User user) throws IOException;

    ConcurrentHashMap getUsers() throws IOException;

    User getUserById(String id);

    void deleteUser(String id);


    void create(User user) throws IOException;
}
