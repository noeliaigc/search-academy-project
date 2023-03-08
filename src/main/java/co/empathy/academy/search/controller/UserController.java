package co.empathy.academy.search.controller;

import co.empathy.academy.search.models.User;
import co.empathy.academy.search.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Operation(description = "Adds the given user", responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = User.class)), responseCode = "200", description = "User added"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@Parameter(description = "User to be added", required = true) @RequestBody User user) {
        try {
            userService.save(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.valueOf(409)).build();
        }
        return ResponseEntity.ok().body("User added");
    }

    /**
     * Get request to find the desired user by its id
     *
     * @param id
     * @return ResponseEntity with the user or with a not found answer
     */
    @Operation(description = "Get the user by id", responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = User.class)), responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "Person with such id doesn't exists")
    })
    @GetMapping("/getUser")
    public ResponseEntity<User> getUserById(@Parameter(description = "Id to look up the user", required = true) @RequestParam String id) {
        User user;
        try {
            user = userService.getUserById(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    /**
     * Get request to ask for all the users
     *
     * @return ResponseEntity containin all the users
     */
    @Operation(description = "Get all the users", responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = User.class)), responseCode = "200")
    })
    @GetMapping("/getUsers")
    public ResponseEntity<ConcurrentHashMap> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    /**
     * Delete request which will remove the user specified by its id
     *
     * @param id
     * @return ResponseEntity with a ok or notFound answer
     */
    @Operation(description = "Delete the user by id", responses = {
            @ApiResponse(responseCode = "204", description = "User has been deleted"),
            @ApiResponse(responseCode = "404", description = "User with such id doesn't exists")
    })
    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@Parameter(description = "Id to delete the user", required = true) @RequestParam String id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
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
    @Operation(description = "Updates the user by id", responses = {
            @ApiResponse(responseCode = "200", description = "User has been updated"),
            @ApiResponse(responseCode = "404", description = "User with such id doesn't exists")
    })
    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@Parameter(description = "Id to update the user", required = true)  String id, @Parameter(description = "User to be updated", required = true) @RequestBody User user) {
        try {
            userService.updateUser(id, user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("User updated");
    }


}
