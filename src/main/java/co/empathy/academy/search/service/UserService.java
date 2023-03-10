package co.empathy.academy.search.service;

import co.empathy.academy.search.models.User;
import net.minidev.json.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public interface UserService {

    void save(User user) throws Exception;

    ConcurrentHashMap getUsers();

    User getUserById(String id) throws Exception;

    void deleteUser(String id) throws Exception;


    void updateUser(String id, User user) throws Exception;

    ConcurrentHashMap<String, User> getUsersFromFile(MultipartFile file) throws Exception;

    ConcurrentHashMap<String, User> getUsersFromFileAsync(MultipartFile file) throws IOException;
}
