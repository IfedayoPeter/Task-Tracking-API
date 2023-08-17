package com.project.TaskTrackingAPI.service;

import com.project.TaskTrackingAPI.converter.TaskConverter;
import com.project.TaskTrackingAPI.dto.TaskDto;
import com.project.TaskTrackingAPI.exception.EtAuthException;
import com.project.TaskTrackingAPI.model.Task;
import com.project.TaskTrackingAPI.model.User;
import com.project.TaskTrackingAPI.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskConverter taskConverter;
    @Override
    public TaskDto createTask(TaskDto taskDto, User createdByUser) {
        Task task = taskConverter.convertTaskDtoToEntity(taskDto);
        task.setAssignedTo(createdByUser);
        task = taskRepository.save(task);
        taskDto = taskConverter.convertTaskToDto(task);
        return taskDto;
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto taskDTO) {
        Task task = taskConverter.convertTaskDtoToEntity(taskDTO);
        task = taskRepository.findById(taskId)
                .orElseThrow(() ->  new EtAuthException("Task Not found"));
        if (task != null) {
            task = taskRepository.save(task);
            taskDTO = taskConverter.convertTaskToDto(task);
        }
        return taskDTO;
    }

    @Override
    public void DeleteTask(Long taskId) {
         taskRepository.deleteById(taskId);

    }

    @Override
    public Task assignTask(Long taskId, User assignedToUser) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->  new EtAuthException("Task Not found"));
        if (task != null) {
            task.setAssignedTo(assignedToUser);
            return taskRepository.save(task);
        }
        return null;
    }
}
