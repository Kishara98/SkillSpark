package com.skillspark.user_service.service;

import com.skillspark.user_service.dto.UserDTO;
import com.skillspark.user_service.model.User;
import com.skillspark.user_service.repository.UserRepository;
import com.skillspark.user_service.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;


    ModelMapper modelMapper = new ModelMapper();



    // create a new user
    public UserDTO createAUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = repository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }
    // get a single user by id
    public Optional<UserDTO> getUserById(int id) {
        Optional<User> existingUser = repository.findById(id);
        return  existingUser.map(user -> new UserDTO(user.getName(), user.getEmail(), user.getRole()));
    }
    // get users by role
    public List<UserDTO> getUsersByRole(String role) {
        List<UserDTO> existingUser = repository.findByRole(role);
        if(!existingUser.isEmpty()) {
            return existingUser;
        } else {
            throw new RuntimeException("No user available under role: " + role);
        }

    }

    public String login(User user) {
        Optional<User> dbUser = repository.findByName(user.getName());
        String encodedPassword = dbUser.get().getPassword();
        boolean isPasswordMatch = passwordEncoder.matches(user.getPassword(), encodedPassword);
        if (isPasswordMatch) {
            return jwtUtil.generateJWTToken(user.getName());
        } else {
            throw new RuntimeException("Incorrect credentials for : " + user.getName());
        }
    }


}
