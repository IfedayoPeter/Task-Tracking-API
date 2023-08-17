package com.project.TaskTrackingAPI.converter;

import com.project.TaskTrackingAPI.dto.UserDto;
import com.project.TaskTrackingAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User convertDtoToEntity(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;

    }

    public UserDto convertEntityTODto(User user){
        UserDto userDto = new UserDto();
        userDto.setName((user.getName()));
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
