package com.example.DemoProject.controller;

import com.example.DemoProject.model.User;
import com.example.DemoProject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aspectj.apache.bcel.generic.LOOKUPSWITCH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User" , description = "User Management Api ")
@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger
            = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String bearerToken) throws Exception {
        return userService.allusers(bearerToken , "GET");
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getBookById(@PathVariable Long id ,@RequestHeader ("Authorization") String token ) throws Exception {
        return userService.getByUserId(id , token ,"GET");
    }

    @Operation(
            summary = "Retrieve a User by Id",
            description = "Get a User object by specifying its id. The response is User object with id, name and email.",
            tags = { "tutorials", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })


    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user, @RequestHeader("Authorization") String token) throws Exception {
        return userService.add(user, token, "POST");
    }

    @PutMapping("/updateUserById/{id}")
    public ResponseEntity<User > updateUserById( @RequestBody User newUserData ,@PathVariable Long id , @RequestHeader("Authorization") String token)throws Exception {
        return userService.update(newUserData , id ,token,"PUT");
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id , @RequestHeader("Authorization") String token) throws Exception {
        return userService.delete(id , token , "DELETE");
    }

}
