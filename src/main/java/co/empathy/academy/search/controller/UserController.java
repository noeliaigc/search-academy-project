package co.empathy.academy.search.controller;

import co.empathy.academy.search.models.User;
import co.empathy.academy.search.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Post request to add a new user.
     *
     * @param user
     * @return ResponseEntity with a bad or ok answer
     */
    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@RequestBody User user){

        try {
           userService.save(user);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body("User added");
    }

    /**
     * Get request to find the desired user by its id
     *
     * @param id
     * @return ResponseEntity with the user or with a not found answer
     */
    @GetMapping("/getUser")
    public ResponseEntity<User> getUserById(@RequestParam String id){
        User user;
        try{
            user = userService.getUserById(id);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.created(null).body(user);
    }

    /**
     * Get request to ask for all the users
     *
     * @return ResponseEntity containin all the users
     */
    @GetMapping("/getUsers")
    public ResponseEntity<ConcurrentHashMap> getUsers(){
        return ResponseEntity.created(null).body(userService.getUsers());
    }

    /**
     * Delete request which will remove the user specified by its id
     * @param id
     * @return ResponseEntity with a ok or notFound answer
     */
    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestParam String id){
        try{
            userService.deleteUser(id);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("User deleted");
    }

    /**
     * Put request which will update the user specified by its id
     *
     * @param id
     * @param user
     * @return ResponseEntity with a ok or notFound answer
     */
    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@PathParam(value = "id") String id, @RequestBody User user){
        try{
            userService.updateUser(id, user);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("User updated");
    }



}
