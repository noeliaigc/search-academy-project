package co.empathy.academy.search.service;

import co.empathy.academy.search.models.User;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UserServiceImplTests {

    @Test
    void givenUser_whenAdded_returnUsersAdded() throws Exception {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        User user = new User("1", "user1", "user1@email.com");

        ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
        users.put(user.getId(), user);

        userService.save(user);
        given(userService.getUsers()).willReturn(users);
    }

    @Test
    void givenUser_whenRequestSpecifiedUser_returnUser() throws Exception {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        User user = new User("1", "user1", "user1@email.com");

        ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
        users.put(user.getId(), user);

        userService.save(user);

        given(userService.getUserById(user.getId())).willReturn(user);
    }

    @Test
    void givenUsers_whenRequestUsers_returnUsers() throws Exception {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        User user = new User("1", "user1", "user1@email.com");
        User user2 = new User("2", "user2", "user2@email.com");

        ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
        users.put(user.getId(), user);
        users.put(user.getId(), user);

        userService.save(user);
        userService.save(user2);

        given(userService.getUsers()).willReturn(users);
    }

}
