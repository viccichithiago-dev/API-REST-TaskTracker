package com.thiago.tasktracker.dto;
import com.thiago.tasktracker.model.TaskStatus;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para actualizar el estado de una tarea") // Anotación para describir la clase en la documentación de la API
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
