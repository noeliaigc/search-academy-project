package co.empathy.academy.search.service;

import co.empathy.academy.search.models.User;
import co.empathy.academy.search.repositories.UserEngine;
import co.empathy.academy.search.repositories.UserEngineImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserServiceImpl implements UserService{

    private ConcurrentHashMap users = new ConcurrentHashMap();

    private final UserEngine userEngine;
    private final UserEngineImpl userEngineImpl;

    @Autowired
    public UserServiceImpl(UserEngine userEngine, UserEngineImpl userEngineImpl) {
        this.userEngine = userEngine;
        this.userEngineImpl = userEngineImpl;
    }


    @Override
    public void save(User user) throws IOException {
        userEngine.save(user);
        users.put(user.getId(), user);
    }

    @Override
    public ConcurrentHashMap getUsers() throws IOException {
        return users;
    }

    @Override
    public User getUserById(String id) {
        return (User) users.get(id);
    }

    @Override
    public void deleteUser(String id) {
        userEngine.deleteById(id);
        users.remove(id);
    }

    @Override
    public void create(User user) throws IOException {
        userEngineImpl.create(user);
        users.put(user.getId(), user);
        System.out.println(user.getName());
        //return users;
    }


}
