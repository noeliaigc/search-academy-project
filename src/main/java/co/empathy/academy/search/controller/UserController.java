package co.empathy.academy.search.controller;

import co.empathy.academy.search.models.User;
import co.empathy.academy.search.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@RequestBody User user) throws IOException {

        try {
           userService.save(user);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(null).body("User added");
    }

    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody User user) throws IOException {
        //ConcurrentHashMap users;
        try {
            userService.create(user);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(null).body("User added");
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUserById(@RequestParam String id){
        User user = userService.getUserById(id);
        return ResponseEntity.created(null).body(user);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<ConcurrentHashMap> getUsers() throws IOException {
        return ResponseEntity.created(null).body(userService.getUsers());
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestParam String id){
        try{
            userService.deleteUser(id);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("User deleted");
    }



}
