package co.empathy.academy.search.service;

import co.empathy.academy.search.models.User;

import java.util.concurrent.ConcurrentHashMap;

public interface UserService {

    void save(User user) throws Exception;

    ConcurrentHashMap getUsers();

    User getUserById(String id) throws Exception;

    void deleteUser(String id) throws Exception;


    void updateUser(String id, User user) throws Exception;
}
