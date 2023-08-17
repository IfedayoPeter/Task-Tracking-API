package com.project.TaskTrackingAPI.converter;

import com.project.TaskTrackingAPI.dto.TaskDto;
import com.project.TaskTrackingAPI.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {

    public Task convertTaskDtoToEntity(TaskDto taskDto){
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setCompleted(false);
        return task;
    }

    public TaskDto convertTaskToDto(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setCompleted(task.isCompleted());
        return taskDto;
    }
}
