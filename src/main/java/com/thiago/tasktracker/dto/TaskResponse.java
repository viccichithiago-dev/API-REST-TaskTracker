package com.thiago.tasktracker.dto;
import java.time.LocalDateTime;

import com.thiago.tasktracker.model.TaskPriority;
import com.thiago.tasktracker.model.TaskStatus;

public class TaskResponse { // Clase encargada de representar la respuesta de una tarea, con todos sus atributos
    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TaskStatus status;
    private TaskPriority priority;

    public TaskResponse() {}

    public TaskResponse(Long id, String description, TaskStatus status, TaskPriority priority, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    // getters y setters generados por Lombok
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
        this.updatedAt = LocalDateTime.now();
    }
    public TaskPriority getPriority() {
        return priority;
    }
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
    public TaskStatus getStatus() {
        return status;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public boolean isCompleted() {
        return status == TaskStatus.TERMINADA;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
