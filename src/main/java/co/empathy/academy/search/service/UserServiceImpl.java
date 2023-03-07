package co.empathy.academy.search.service;

import co.empathy.academy.search.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserServiceImpl implements UserService{

    private ConcurrentHashMap users;


    @Autowired
    public UserServiceImpl(ConcurrentHashMap users) {
        this.users = users;
    }


    @Override
    public void save(User user) throws Exception {
        if(users.containsKey(user.getId())){
            throw new Exception("User already exists");
        }else {
            users.put(user.getId(), user);
        }
    }

    @Override
    public ConcurrentHashMap getUsers() {
        return users;
    }

    @Override
    public User getUserById(String id) throws Exception {
        if(!users.contains(id)){
            throw new Exception("User not found");
        }
        return (User) users.get(id);
    }

    @Override
    public void deleteUser(String id) throws Exception {
        if(!users.contains(id)){
            throw new Exception("User not found to be deleted");
        }else {
            users.remove(id);
        }
    }



}
