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
        return mapToResponse(savedTask);
    }
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll() // Obtiene todas las tareas de la base de datos utilizando el repositorio
        .stream() // Convierte la lista de tareas en un flujo para poder aplicar operaciones funcionales
        .map(this::mapToResponse) // Mapea cada tarea a un objeto TaskResponse utilizando el método mapToResponse
        .toList(); // Convierte el flujo de TaskResponse de nuevo a una lista y la devuelve
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public TaskResponse updateTask(Long id, String description) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setDescription(description);
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToResponse(task);
    }
    public TaskResponse updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }
    private TaskResponse mapToResponse(Task task) {
    return new TaskResponse(task.getId(),task.getDescription(),task.getStatus(),task.getPriority(),task.getCreatedAt(),task.getUpdatedAt());
    
    }
}
