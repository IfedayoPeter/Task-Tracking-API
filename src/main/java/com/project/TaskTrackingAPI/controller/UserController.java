package com.project.TaskTrackingAPI.controller;

import com.project.TaskTrackingAPI.dto.UserDto;
import com.project.TaskTrackingAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> CreateAccount(@RequestBody UserDto userDto) {
        userDto = userService.CreateAccount(userDto);
        String message = "Account Created";
        return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> Login(@RequestBody UserDto userDto) {
        userDto = userService.Login(userDto);
        String message = "Logged in successfully";
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> UpdateAccount(@PathVariable Long userId, @RequestBody UserDto userDto) {
        userDto = userService.UpdateAccount(userId, userDto);
        String message = "User updated successfully";
        return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> DeleteUser(@PathVariable Long userId) {
        userService.DeleteUser(userId);
        return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
    }
}
