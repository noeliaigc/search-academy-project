package co.empathy.academy.search.controller;

import co.empathy.academy.search.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.mock;

import static org.mockito.BDDMockito.given;

public class UserControllerTests {

    @Test
    void givenUser_whenAdding_returnDesiredResponseEntity(){
        UserController userController = mock(UserController.class);
        User user = new User("1", "user1", "user1@email.com");
        given(userController.saveUser(user)).willReturn(ResponseEntity.ok().body("User added"));
    }

    @Test
    void givenUsersAdded_whenRequestingAllUsers_returnAllUsersAdded(){
        UserController userController = mock(UserController.class);
        User user = new User("1", "user1", "user1@email.com");
        User user2 = new User("2", "user2", "user2@email.com");
        userController.saveUser(user);
        userController.saveUser(user2);

        ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
        users.put(user.getId(), user);
        users.put(user2.getId(), user2);

        given(userController.getUsers()).willReturn(ResponseEntity.status(HttpStatus.OK).body(users));
    }

    @Test
    void givenUsersAdded_whenRequestingUserById_returnUser(){
        UserController userController = mock(UserController.class);
        User user = new User("1", "user1", "user1@email.com");
        User user2 = new User("2", "user2", "user2@email.com");
        userController.saveUser(user);
        userController.saveUser(user2);

        given(userController.getUserById(user2.getId())).willReturn(ResponseEntity.status(HttpStatus.OK).body(user2));
    }

    @Test
    void givenUser_whenUpdated_userUpdated(){
        UserController userController = mock(UserController.class);
        User user = new User("1", "user1", "user1@email.com");
        userController.saveUser(user);

        ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
        users.put(user.getId(), user);

        given(userController.getUsers()).willReturn(ResponseEntity.status(HttpStatus.OK).body(users));

        User userUpdated = new User("1", "user1", "no-email@email.com");

        ResponseEntity r = userController.updateUser(userUpdated.getId(),
                userUpdated);

        users.replace(userUpdated.getId(), userUpdated);

        given(userController.getUsers()).willReturn(ResponseEntity.status(HttpStatus.OK).body(users));
    }

    @Test
    void givenUsers_whenDeleting_userDeleted(){
        UserController userController = mock(UserController.class);
        User user = new User("1", "user1", "user1@email.com");
        User user2 = new User("2", "user2", "user2@email.com");
        userController.saveUser(user);
        userController.saveUser(user2);

        ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
        users.put(user.getId(), user);

       ResponseEntity r = userController.deleteUser("2");

       given(userController.getUsers()).willReturn(ResponseEntity.status(HttpStatus.OK).body(users));
    }

}
