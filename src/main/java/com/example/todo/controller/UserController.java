package com.example.todo.controller;

import com.example.todo.dto.UserDto;
import com.example.todo.entity.User;
import com.example.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins ="http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("profile")
    public ResponseEntity<String> getUserProfile() {
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser != null) {
            return ResponseEntity.ok("Username: " + loggedInUser.getUsername() + " - Profile: This is the profile information of the user.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody UserDto userDto) {
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser != null) {
            loggedInUser.setUsername(userDto.getUsername());
            userService.updateUserProfile(loggedInUser);
            return ResponseEntity.ok("Profile updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
