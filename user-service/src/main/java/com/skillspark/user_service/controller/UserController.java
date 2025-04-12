package com.skillspark.user_service.controller;

import com.skillspark.user_service.dto.UserDTO;
import com.skillspark.user_service.model.User;
import com.skillspark.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/")
    ResponseEntity<UserDTO> newUserSignUp(@RequestBody User user) {
        UserDTO savedUserResponse = service.createAUser(user);
        return new ResponseEntity<>(savedUserResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> retrieveUserByUserId(@PathVariable int id) {
        Optional<UserDTO> existingUser = service.getUserById(id);
        if(existingUser.isPresent()) {
            return new ResponseEntity<>(existingUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    ResponseEntity<List<UserDTO>> retrieveUsersByRole(@RequestParam String role) {
        List<UserDTO> existingUser = service.getUsersByRole(role);
        if(!existingUser.isEmpty()) {
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    ResponseEntity<String> userLogin(@RequestBody User user) {
        String token = service.login(user);
        if(!token.isEmpty()) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
