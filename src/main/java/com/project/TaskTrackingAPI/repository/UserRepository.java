package com.project.TaskTrackingAPI.repository;

import com.project.TaskTrackingAPI.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    Optional<User> findByName(String username);

}
