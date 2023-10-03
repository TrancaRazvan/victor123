package com.app.projectVictor;

import com.app.projectVictor.Controller.UserController;
import com.app.projectVictor.Services.UserService;
import com.app.projectVictor.Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllUsers() {
        List<User> sampleUsers = new ArrayList<>();
        sampleUsers.add(new User(1, "User1"));
        sampleUsers.add(new User(2, "User2"));
        when(userService.findAllUsers()).thenReturn(sampleUsers);

        List<User> result = userController.findAllUsers();

        verify(userService, times(1)).findAllUsers();
        assertEquals(sampleUsers, result);
    }

    @Test
    public void testFindUserById() {
        User sampleUser = new User(1, "User1");
        when(userService.findUserById(1)).thenReturn(Optional.of(sampleUser));

        ResponseEntity<User> result = userController.findUserById(1);

        verify(userService, times(1)).findUserById(1);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(sampleUser, result.getBody());
    }
    @Test
    public void testCreateUser() {
        User sampleUser = new User(1, "User1");
        when(userService.createUser(any(User.class))).thenReturn(sampleUser);

        ResponseEntity<User> result = userController.createUser(new User());

        verify(userService, times(1)).createUser(any(User.class));
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(sampleUser, result.getBody());
    }
    @Test
    public void testUpdateUser() {
        User sampleUser = new User(1, "User1");
        User updatedUser = new User(1, "UpdatedUser");
        when(userService.updateUser(1, updatedUser)).thenReturn(sampleUser);

        ResponseEntity<User> result = userController.updateUser(1, updatedUser);

        verify(userService, times(1)).updateUser(1, updatedUser);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(sampleUser, result.getBody());
    }
    @Test
    public void testDeleteUser() {
        ResponseEntity<Void> result = userController.deleteUser(1);

        verify(userService, times(1)).deleteUser(1);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}


