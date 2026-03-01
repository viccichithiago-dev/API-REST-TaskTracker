package com.thiago.tasktracker.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.thiago.tasktracker.model.TaskStatus;
import com.thiago.tasktracker.model.Task;
import com.thiago.tasktracker.repository.TaskRepository;
import com.thiago.tasktracker.dto.TaskRequest;
import com.thiago.tasktracker.dto.TaskResponse;
import com.thiago.tasktracker.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = new Task(taskRequest.getDescription());
        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }
    public Page<TaskResponse> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).map(this::mapToResponse); // Esta linea usando o método map para converter cada Task em TaskResponse usando o método mapToResponse.
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public TaskResponse updateTask(Long id, String description) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setDescription(description);
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return mapToResponse(task);
    }
    public TaskResponse updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(status);
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }
    private TaskResponse mapToResponse(Task task) {
    return new TaskResponse(task.getId(),task.getDescription(),task.getStatus(),task.getPriority(),task.getCreatedAt(),task.getUpdatedAt());
    }
    public Page<TaskResponse> getTasks(TaskStatus status, Pageable pageable) {
        Page<Task> tasks;
        if (status != null) {
            tasks = taskRepository.findByStatus(status, pageable);
        } else {
            tasks = taskRepository.findAll(pageable);
        }
        return tasks.map(this::mapToResponse);
    }
}
