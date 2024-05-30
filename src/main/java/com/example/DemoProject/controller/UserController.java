package com.example.DemoProject.controller;

import com.example.DemoProject.model.User;
import com.example.DemoProject.reo.UserRepo;
import com.example.DemoProject.service.UserService;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import org.aspectj.weaver.patterns.IToken;
import org.aspectj.weaver.patterns.ITokenSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger
            = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
//    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoibWVyY2hhbnQifQ.6ug8DIW0lGyuZdIQm-kw9c70bnoV72V3R-XTQ0QYMx4";
//    public String decodeJwt( ){
//        String[] chunks = token.split("\\.");
//        Base64.Decoder decoder = Base64.getUrlDecoder();
//
//        String header = new String(decoder.decode(chunks[0]));
//        String payload = new String(decoder.decode(chunks[1]));
//        return payload;
//
//    }
//    String jsonObj = decodeJwt(token);
//    if(jsonObj.getString() == "merchant"){
//
//    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader ("Authorization") String token) throws Exception {
//        logger.info("Headers : "+ Hea("Authorization"));

        return userService.allusers(token , "GET");


    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getBookById(@PathVariable Long id ,@RequestHeader ("Authorization") String token ) throws Exception {
        return userService.getByUserId(id , token ,"GET");
    }
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user, @RequestHeader("Authorization") String token) throws Exception {
        return userService.add(user, token, "POST");

    }
    @PutMapping("/updateUserById/{id}")
    public ResponseEntity<User > updateUserById( @RequestBody User newUserData ,@PathVariable Long id , @RequestHeader("Authorization") String token)throws Exception {
        return userService.update(newUserData , id ,token,"PUT");

    }
    @DeleteMapping("/deleteUserById/{id} ")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id , @RequestHeader("Authorization") String token) throws Exception {
        try {
            return userService.delete(id , token , "DELETE");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
//
//    public String getJsonObj() {
//        return jsonObj;
//    }
}
