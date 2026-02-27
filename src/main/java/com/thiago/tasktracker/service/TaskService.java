package com.thiago.tasktracker.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.thiago.tasktracker.model.TaskStatus;
import com.thiago.tasktracker.model.Task;
import com.thiago.tasktracker.repository.TaskRepository;
import com.thiago.tasktracker.dto.TaskRequest;
import com.thiago.tasktracker.dto.TaskResponse;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = new Task(taskRequest.getDescription());
        Task savedTask = taskRepository.save(task);
        return new TaskResponse(savedTask.getId(), savedTask.getDescription(), savedTask.getStatus(), savedTask.getCreatedAt(), savedTask.getUpdatedAt());
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public Task updateTask(Long id, String description) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setDescription(description);
        return taskRepository.save(task);
    }
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }
    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }
    private TaskResponse mapToResponse(Task task) {
    return new TaskResponse(task.getId(),task.getDescription(),task.getStatus(),task.getCreatedAt(),task.getUpdatedAt());
    }
}
