package com.project.TaskTrackingAPI.service;

import com.project.TaskTrackingAPI.converter.UserConverter;
import com.project.TaskTrackingAPI.dto.UserDto;
import com.project.TaskTrackingAPI.exception.EtAuthException;
import com.project.TaskTrackingAPI.model.User;
import com.project.TaskTrackingAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverter userConverter;

    @Override
    public UserDto CreateAccount(UserDto userDto) {

        User user = userConverter.convertDtoToEntity(userDto);
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EtAuthException("Email already exists");
        }
         user = userRepository.save(user);
        userDto = userConverter.convertEntityTODto(user);
        return userDto;
    }

    @Override
    public UserDto Login(UserDto userDto)throws EtAuthException {
        User user = userConverter.convertDtoToEntity(userDto);
        user = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (user == null || !user.getPassword().equals(userDto.getPassword())){
            throw new EtAuthException("Invalid Email or Password");
        }
        userDto = userConverter.convertEntityTODto(user);
        return userDto;
    }

    @Override
    public UserDto UpdateAccount(Long userId, UserDto userDto) {
        User user = userConverter.convertDtoToEntity(userDto);
        user = userRepository.findById(userId)
                .orElseThrow(() ->  new EtAuthException("User Not found"));
        if(user != null){
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
            userDto = userConverter.convertEntityTODto(user);
        }
        return userDto;
    }

    @Override
    public void DeleteUser(Long UserId) {
        userRepository.deleteById(UserId);
    }
}
