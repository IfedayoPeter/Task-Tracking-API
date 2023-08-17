package com.project.TaskTrackingAPI.service;

import com.project.TaskTrackingAPI.dto.UserDto;

public interface UserService {

    UserDto CreateAccount(UserDto userDto);
    UserDto Login(UserDto userDto);
    UserDto UpdateAccount(Long userId, UserDto userDto);
    void DeleteUser(Long UserId);
}
