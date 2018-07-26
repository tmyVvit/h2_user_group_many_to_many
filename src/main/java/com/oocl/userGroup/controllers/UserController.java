package com.oocl.userGroup.controllers;

import com.oocl.userGroup.controllers.DTO.UserDTO;
import com.oocl.userGroup.entities.User;
import com.oocl.userGroup.exceptions.ResourceNotFoundException;
import com.oocl.userGroup.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        List<UserDTO> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(new UserDTO(user)));
        return users;
    }

    @Transactional
    @GetMapping(path="/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserById(@PathVariable Long userID){
        User user = userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("user not exist"));
        return new UserDTO(user);
    }

    @Transactional
    @PostMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
