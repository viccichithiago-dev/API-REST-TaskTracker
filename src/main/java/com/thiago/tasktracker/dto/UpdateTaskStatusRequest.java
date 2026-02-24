package com.thiago.tasktracker.dto;
import com.thiago.tasktracker.model.TaskStatus;
import jakarta.validation.constraints.NotNull;
public class UpdateTaskStatusRequest {
    @NotNull(message = "El estado no puede ser nulo")
    private TaskStatus status;
    public UpdateTaskStatusRequest() {}
    public UpdateTaskStatusRequest(TaskStatus status) {
        this.status = status;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
