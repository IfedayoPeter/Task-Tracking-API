package com.project.TaskTrackingAPI.controller;

import com.project.TaskTrackingAPI.dto.TaskDto;
import com.project.TaskTrackingAPI.model.Task;
import com.project.TaskTrackingAPI.model.User;
import com.project.TaskTrackingAPI.repository.UserRepository;
import com.project.TaskTrackingAPI.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto, Principal principal) {
        String username = principal.getName();
        User createdByUser = userRepository.findByName(username) // Replace with your repository method
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        TaskDto createdTask = taskService.createTask(taskDto, createdByUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.DeleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/assign/{taskId}")
    public ResponseEntity<Task> assignTask(@PathVariable Long taskId, Principal principal) {
        String username = principal.getName();
        User assignedToUser = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Task assignedTask = taskService.assignTask(taskId, assignedToUser);
        return ResponseEntity.ok(assignedTask);
    }
}
