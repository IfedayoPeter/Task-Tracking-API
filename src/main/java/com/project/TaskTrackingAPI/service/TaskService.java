package com.project.TaskTrackingAPI.service;

import com.project.TaskTrackingAPI.dto.TaskDto;
import com.project.TaskTrackingAPI.model.Task;
import com.project.TaskTrackingAPI.model.User;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto, User createdByUser);
    TaskDto updateTask(Long taskId, TaskDto taskDTO);
    void DeleteTask(Long taskId);
    Task assignTask(Long taskId, User assignedToUser);
}
