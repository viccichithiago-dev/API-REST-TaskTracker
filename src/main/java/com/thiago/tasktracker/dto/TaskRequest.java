package com.thiago.tasktracker.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

public class TaskRequest {
    @Size(min = 3, max = 50, message = "Debe tener entre 3 y 50 caracteres")
   @Pattern(regexp = "^[\\p{L}0-9 .,-]+$",message = "Solo letras, números y signos básicos")
    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
