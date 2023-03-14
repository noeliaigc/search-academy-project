package co.empathy.academy.search.service;

import co.empathy.academy.search.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserServiceImpl implements UserService{

    private final ConcurrentHashMap<String, User> users =
            new ConcurrentHashMap<>();


    /**
     * Saves an user into the ConcurrentHashMap
     *
     * @param user User
     * @throws Exception if user already exists
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
     * @return users
     */
    @Override
    public ConcurrentHashMap<String, User> getUsers() {
        return users;
    }

    /**
     * Finds the user specified by an id, if it is not contained and exception is thrown.
     *
     * @param id User id
     * @return User
     * @throws Exception if user is not found
     */
    @Override
    public User getUserById(String id) throws Exception {
        if(!users.containsKey(id)){
            throw new Exception("User not found");
        }
        return users.get(id);
    }

    /**
     * Deletes an user specified by its id
     * @param id User id
     * @throws Exception if user is not found
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
     * @param id User id
     * @param user User
     * @throws Exception if user is not found
     */
    @Override
    public void updateUser(String id, User user) throws Exception {
        if(!users.containsKey(id)){
            throw new Exception("User not found to be updated");
        }else {
            users.replace(id, user);
        }
    }

    /**
     * From a MultipartFile passed as a parameter it will be saved the
     * content that are Users into the ConcurrentHashMap of users.
     *
     * @param file Multipartfile
     * @return users
     * @throws IOException if file is not valid
     */
    @Override
    public ConcurrentHashMap<String, User> getUsersFromFile(MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> usersInFile = objectMapper.readValue(file.getBytes(), new TypeReference<>() {});
        for(User u : usersInFile){
            this.users.put(u.getId(), u);
        }
        return this.users;
    }

    /**
     * From a MultipartFile passed as a parameter it will be saved the
     * content that are Users into the ConcurrentHashMap of users, but this
     * time asynchronous.
     *
     * @param file Multipartfile
     * @return users
     * @throws IOException if file is not valid
     */
    @Override
    @Async
    public ConcurrentHashMap<String, User> getUsersFromFileAsync(MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> usersInFile = objectMapper.readValue(file.getBytes(), new TypeReference<>() {});
        for(User u : usersInFile){
            this.users.put(u.getId(), u);
        }
        return this.users;
    }
}
