package co.empathy.academy.search.service;

import co.empathy.academy.search.models.User;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserServiceImpl implements UserService{

    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<String, User>();


    /**
     * Saves an user into the ConcurrentHashMap
     *
     * @param user
     * @throws Exception
     */
    @Override
    public void save(User user) throws Exception {
        if(users.containsKey(user.getId())){
            throw new Exception("User already exists");
        }else {
            users.put(user.getId(), user);
        }
    }

    /**
     * Returns the ConcurrentHashMap containing users
     * @return
     */
    @Override
    public ConcurrentHashMap getUsers() {
        return users;
    }

    /**
     * Finds the user specified by an id, if it is not contained and exception is thrown.
     *
     * @param id
     * @return User
     * @throws Exception
     */
    @Override
    public User getUserById(String id) throws Exception {
        if(!users.containsKey(id)){
            throw new Exception("User not found");
        }
        return (User) users.get(id);
    }

    /**
     * Deletes an user specified by its id
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteUser(String id) throws Exception {
        if(!users.containsKey(id)){
            throw new Exception("User not found to be deleted");
        }else {
            users.remove(id);
        }
    }

    /**
     * Updates an user of the ConcurrentHashMap given its id
     * @param id
     * @param user
     * @throws Exception
     */
    @Override
    public void updateUser(String id, User user) throws Exception {
        if(!users.containsKey(id)){
            throw new Exception("User not found to be updated");
        }else {
            users.replace(id, user);
        }
    }


}
