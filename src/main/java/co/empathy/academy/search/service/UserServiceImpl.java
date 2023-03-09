package co.empathy.academy.search.service;

import co.empathy.academy.search.models.User;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
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

    @Override
    public ConcurrentHashMap<String, User> getUsersFromFile(MultipartFile file) throws Exception {
        JSONArray fileContent =
                null;
        try {
            fileContent = (JSONArray) new JSONParser().parse(new String(file.getBytes()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Object obj : fileContent){
            JSONObject userObj = (JSONObject) obj;
            String id = userObj.getAsString("id");
            User user = new User(id, userObj.getAsString("name"),
                    userObj.getAsString(
                            "email"));
            save(user);
        }
        return users;
    }


}
