package com.project.TaskTrackingAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private String title;
    private String description;
    private LocalDate dueDate;
    private Boolean completed;
}
