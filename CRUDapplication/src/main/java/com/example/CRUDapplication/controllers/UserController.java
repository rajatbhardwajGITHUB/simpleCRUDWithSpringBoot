package com.example.CRUDapplication.controllers;
import com.example.CRUDapplication.models.Users;
import com.example.CRUDapplication.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController// to define this class as a restfull web service
public class UserController {

    // the repo object
    @Autowired // for automatic dependency injection
    private userRepo userrepo;

    //defining our views

    @GetMapping("/getall")
    private ResponseEntity<List<Users>> getAllUsers(){
        try{
            List<Users> userList = new ArrayList<>();
            //mark
            userrepo.findAll().forEach(userList::add);

            if(userList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userList,HttpStatus.OK);

        }
        catch(Exception ex){
            //if some error occur
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getbyId/{id}")
    private ResponseEntity<Users> getUserById(@PathVariable Long id) // we need to capture this value of id from the url
    {   //mark
        Optional<Users> userData = userrepo.findById(id);
        //if present
        if(userData.isPresent()){
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/add")
    private ResponseEntity<Users> addUser(@RequestBody Users user) // value of the 'user' should be extracted from the body
    {
        Users usr = userrepo.save(user);

        return new ResponseEntity<>(usr,HttpStatus.OK);

    }

    @PostMapping("/update/{id}")
    private ResponseEntity<Users> updateUserById(@PathVariable Long id, @RequestBody Users newUserData)
    {
        Optional<Users> oldUserData = userrepo.findById(id);

        // if present
        if(oldUserData.isPresent()){
            //get the old data
            Users updatedUserData = oldUserData.get();
            //update the data
            updatedUserData.setName(newUserData.getName());

            Users userObj = userrepo.save(updatedUserData);
            return new ResponseEntity<>(userObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Users> deleteUserById(@PathVariable Long id)
    {
        userrepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
