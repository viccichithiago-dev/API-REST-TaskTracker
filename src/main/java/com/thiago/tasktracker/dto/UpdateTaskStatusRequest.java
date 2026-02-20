package com.thiago.tasktracker.dto;
import com.thiago.tasktracker.model.TaskStatus;
public class UpdateTaskStatusRequest {
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
