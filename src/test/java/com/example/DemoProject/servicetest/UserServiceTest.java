package com.example.DemoProject.servicetest;

import com.example.DemoProject.model.User;
import com.example.DemoProject.reository.UserRepo;
import com.example.DemoProject.service.UserService;
import com.example.DemoProject.validation.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepo userRepo;


    @Mock
    private ValidationService validationService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testGetUserByName_ReturnsUser() {
//        // Arrange
//        String name = "John";
//        User user = User.builder().name("Manan").email("manan@gmail.com").build();
//        when(userRepo.findByName(name)).thenReturn(Optional.of(user));
//
//        // Act
//        Optional<User> result = userService.getByUserId(user.getId(), );
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(user, result.get());
//    }

    @Test
    public void testGetUserByName_ReturnsEmpty() {
        // Arrange
        String name = "John";
        when(userRepo.findByName(name)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userService.getUserByName(name);

        // Assert
        assertFalse(result.isPresent());
    }
    @Test
    public void testGetUserByName_ReturnsUser() {
        // Arrange
        String name = "John";
        when(userRepo.findByName(name)).thenReturn(Optional.of(new User(1L , "John" , "mana@gmail.com")));

        // Act
        Optional<User> result = userService.getUserByName(name);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(name , result.get().getName());

    }
    @Test
    public void testAllUsers_ReturnsUsersList() throws Exception {
        // Arrange
        String token = "validToken";
        String requestType = "GET";
        User user1 = new User(1L ,"John" , "Hook");
        User user2 = new User(2L , "Mary" ,"Jane");
        List<User> users = Arrays.asList(user1, user2);

        when(validationService.validaeteroles(any(), any())).thenReturn(true);
        when(userRepo.findAll()).thenReturn(users);

        // Act
        ResponseEntity<List<User>> response = userService.allusers(token, requestType);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }
    @Test
    public void testAllUsers_ReturnsNoContent() throws Exception {
        // Arrange
        String token = "validToken";
        String requestType = "GET";
        List<User> users = new ArrayList<>();

        when(validationService.validaeteroles(any() ,any())).thenReturn(true);
        when(userRepo.findAll()).thenReturn(users);

        // Act
        ResponseEntity<List<User>> response = userService.allusers(any(), any());

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }



}