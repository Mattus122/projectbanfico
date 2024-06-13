package com.example.DemoProject.service;

import com.example.DemoProject.model.User;
import com.example.DemoProject.reository.UserRepo;
import com.example.DemoProject.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    ValidationService validationService;

    public ResponseEntity<List<User>> allusers(String token , String requestType) throws Exception {
//        SecurityContextHolder.getContext().getAuthentication() //this method gets u current authenticated user
        //call vsalidation
        validationService.validaeteroles(token,requestType);
        try {
            List<User> userlist = new ArrayList<User>();
            userRepo.findAll().forEach(userlist::add);
//            for (User u : userRepo.findAll()) {
//                userlist.add(u);
//            }
            if(userlist.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userlist,HttpStatus.ACCEPTED);

        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    public ResponseEntity<User> getByUserId(Long id, String token , String requestType) throws Exception {
        validationService.validaeteroles(token , requestType);
        Optional<User> userdata  = userRepo.findById(id);
        if (userdata.isPresent()){
            return new ResponseEntity<>(userdata.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<User> add(User user, String token, String requestType) throws Exception {
        validationService.validaeteroles(token, requestType);
        User obj = userRepo.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    public ResponseEntity<User> update(User newUserData, Long id, String token,String requesttype) throws Exception {
        validationService.validaeteroles("token" , requesttype);
        Optional<User> oldUserData = userRepo.findById(id);
        if (oldUserData.isPresent()){
            User updateuser = oldUserData.get();
            updateuser.setName(newUserData.getName());
            updateuser.setEmail(newUserData.getEmail()) ;
            User obj = userRepo.save(updateuser);
            return new ResponseEntity<>(obj,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<User> delete(Long id , String  token , String requestType) throws Exception {
        validationService.validaeteroles("token" , requestType);
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()){
            userRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
