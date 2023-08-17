package com.project.TaskTrackingAPI.repository;

import com.project.TaskTrackingAPI.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
