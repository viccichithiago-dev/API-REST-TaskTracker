package com.thiago.tasktracker.controller;

import java.util.List;
import com.thiago.tasktracker.dto.TaskRequest;

import org.springframework.web.bind.annotation.*;

import com.thiago.tasktracker.model.Task;
import com.thiago.tasktracker.service.TaskService;
@RestController // Anotación para indicar que esta clase es un controlador REST
@RequestMapping("/tasks") // Ruta base para todas las operaciones relacionadas con tareas
public class TaskController { // Controlador para manejar las solicitudes relacionadas con las tareas
    private final TaskService taskService; // Servicio para manejar la lógica de negocio de las tareas
    public TaskController(TaskService taskService) { // Constructor para inyectar el servicio de tareas
        this.taskService = taskService;
    }
    @PostMapping // Anotación para manejar solicitudes POST (crear una nueva tarea)
    public Task createTask(@RequestBody TaskRequest taskRequest) { // Método para crear una nueva tarea a partir de la descripción proporcionada en el cuerpo de la solicitud
        return taskService.createTask(taskRequest.getDescription()); // Llama al servicio para crear la tarea y devuelve la tarea creada
    }
    @GetMapping // Anotación para manejar solicitudes GET (obtener todas las tareas)
    public List<Task> getAllTasks() { // Método para obtener todas las tareas
        return taskService.getAllTasks(); // Llama al servicio para obtener todas las tareas y devuelve la lista de tareas
    }
    @DeleteMapping("/{id}") // Anotación para manejar solicitudes DELETE (eliminar una tarea por su ID)
    public void deleteTask(@PathVariable Long id) { // Método para eliminar una tarea a partir de su ID proporcionada en la ruta
        taskService.deleteTask(id); // Llama al servicio para eliminar la tarea con el ID especificado
    }
    @PutMapping("/{id}") // Anotación para manejar solicitudes PUT (actualizar una tarea por su ID)
    public Task updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) { // Método para actualizar una tarea a partir de su ID y la nueva descripción y estado proporcionados en el cuerpo de la solicitud
        return taskService.updateTask(id, taskRequest.getDescription()); // Llama al servicio para actualizar la tarea con el ID especificado y devuelve la tarea actualizada
    }
    @GetMapping("/{id}") // Anotación para manejar solicitudes GET con parámetros de consulta (buscar tareas por ID)
    public Task getTaskById(@PathVariable Long id) { // Método para obtener una tarea por su ID
        return taskService.getTaskById(id); // Llama al servicio para obtener la tarea con el ID especificado y devuelve la tarea
    }
}
