package com.thiago.tasktracker.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thiago.tasktracker.dto.TaskRequest;
import com.thiago.tasktracker.dto.TaskResponse;
import com.thiago.tasktracker.dto.UpdateTaskStatusRequest;
import com.thiago.tasktracker.model.TaskStatus;
import com.thiago.tasktracker.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;


import jakarta.validation.Valid;

@RestController // Anotación para indicar que esta clase es un controlador REST
@RequestMapping("/tasks") // Ruta base para todas las operaciones relacionadas con tareas
public class TaskController { // Controlador para manejar las solicitudes relacionadas con las tareas
    private final TaskService taskService; // Servicio para manejar la lógica de negocio de las tareas
    public TaskController(TaskService taskService) { // Constructor para inyectar el servicio de tareas
        this.taskService = taskService;
    }
    @Operation(summary = "Crear una nueva tarea", description = "Crea una nueva tarea a partir de la descripción proporcionada en el cuerpo de la solicitud") // Anotación para describir la operación en la documentación de la API
    @PostMapping // Anotación para manejar solicitudes POST (crear una nueva tarea)
    public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) { // Método para crear una nueva tarea a partir de la descripción proporcionada en el cuerpo de la solicitud
        return taskService.createTask(taskRequest); // Llama al servicio para crear la tarea y devuelve la tarea creada
    }
    /*
        @GetMapping // Anotación para manejar solicitudes GET (obtener todas las tareas)
    public Page<TaskResponse> getAllTasks(Pageable pageable) { // Método para obtener todas las tareas
        return taskService.getAllTasks(pageable); // Llama al servicio para obtener todas las tareas y devuelve la lista de tareas
    }
     */
    @Operation(summary = "Eliminar una tarea", description = "Elimina una tarea a partir de su ID proporcionada en la ruta") // Anotación para describir la operación en la documentación de la API
    @DeleteMapping("/{id}") // Anotación para manejar solicitudes DELETE (eliminar una tarea por su ID)
    public void deleteTask(@PathVariable Long id) { // Método para eliminar una tarea a partir de su ID proporcionada en la ruta
        taskService.deleteTask(id); // Llama al servicio para eliminar la tarea con el ID especificado
    }
    @Operation(summary = "Actualizar una tarea", description = "Actualiza la descripción de una tarea a partir de su ID proporcionada en la ruta y la nueva descripción en el cuerpo de la solicitud") // Anotación para describir la operación en la documentación de la API
    @PutMapping("/{id}") // Anotación para manejar solicitudes PUT (actualizar una tarea por su ID)
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) { // Método para actualizar una tarea a partir de su ID y la nueva descripción y estado proporcionados en el cuerpo de la solicitud
        return taskService.updateTask(id, taskRequest.getDescription()); // Llama al servicio para actualizar la tarea con el ID especificado y devuelve la tarea actualizada
    }
    @Operation(summary = "Obtener una tarea por ID", description = "Obtiene una tarea a partir de su ID proporcionada en la ruta") // Anotación para describir la operación en la documentación de la API
    @GetMapping("/{id}") // Anotación para manejar solicitudes GET con parámetros de consulta (buscar tareas por ID)
    public TaskResponse getTaskById(@PathVariable Long id) { // Método para obtener una tarea por su ID
        return taskService.getTaskById(id); // Llama al servicio para obtener la tarea con el ID especificado y devuelve la tarea
    }
    @Operation(summary = "Actualizar el estado de una tarea", description = "Actualiza el estado de una tarea a partir de su ID proporcionada en la ruta y el nuevo estado en el cuerpo de la solicitud") // Anotación para describir la operación en la documentación de la API
    @PatchMapping("/{id}/status") // Anotación para manejar solicitudes PATCH (actualizar el estado de una tarea por su ID)
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable Long id, @RequestBody UpdateTaskStatusRequest request) { // Método para actualizar el estado de una tarea a partir de su ID y el nuevo estado proporcionados en el cuerpo de la solicitud
        TaskResponse updatedTask = taskService.updateTaskStatus(id, request.getStatus()); // Llama al servicio para actualizar el estado de la tarea con el ID especificado y devuelve la tarea actualizada
        return ResponseEntity.ok(updatedTask); // Devuelve la tarea actualizada con un código de estado HTTP 200 OK
    }
    @Operation(summary = "Obtener tareas por estado", description = "Obtiene una lista de tareas filtradas por su estado (opcional) y paginación") // Anotación para describir la operación en la documentación de la API
    @GetMapping // Anotación para manejar solicitudes GET con parámetros de consulta (buscar tareas por estado)
    public Page<TaskResponse> getTasks(@RequestParam(required = false) TaskStatus status, Pageable pageable) { // Método para obtener tareas por estado (opcional) y paginación
        return taskService.getTasks(status, pageable); // Llama al servicio para obtener las tareas filtradas por estado y devuelve la lista de tareas
    } 
}
