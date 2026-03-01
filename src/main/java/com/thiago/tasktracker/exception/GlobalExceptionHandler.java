package com.thiago.tasktracker.exception;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest; // Import para obtener la URL de la solicitud
@ControllerAdvice // Esta anotación indica que esta clase manejará excepciones de forma global en toda la aplicación
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class) // Esta anotación indica que este método manejará las excepciones de tipo TaskNotFoundException
    public ResponseEntity<Map<String, Object>> handleTaskNotFoundException(TaskNotFoundException ex, HttpServletRequest request) { // Método para manejar la excepción TaskNotFoundException, recibe la excepción y el objeto HttpServletRequest para obtener información de la solicitud
        Map<String, Object> errorResponse = new HashMap<>(); // Crea un mapa para almacenar la información de la respuesta de error
        errorResponse.put("timestamp", LocalDateTime.now()); // Agrega la marca de tiempo actual al mapa de respuesta
        errorResponse.put("status", HttpStatus.NOT_FOUND.value()); // Agrega el código de estado HTTP 404 al mapa de respuesta
        errorResponse.put("error", "Not Found"); // Agrega una descripción del error al mapa de respuesta
        errorResponse.put("message", ex.getMessage()); // Agrega el mensaje de la excepción al mapa de respuesta
        errorResponse.put("path", request.getRequestURI()); // Agrega la URL de la solicitud que causó la excepción al mapa de respuesta
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); // Devuelve la respuesta de error con el mapa de información y el código de estado HTTP 404
    }
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class) // Esta anotación indica que este método manejará las excepciones de tipo MethodArgumentNotValidException, que ocurren cuando la validación de los argumentos de un método falla
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex) { // Método para manejar la excepción MethodArgumentNotValidException, recibe la excepción y el objeto HttpServletRequest para obtener información de la solicitud
       Map<String, Object> errorResponse = new HashMap<>(); // Crea un mapa para almacenar la información de la respuesta de error
       errorResponse.put("timestamp", LocalDateTime.now()); // Agrega la marca de tiempo
       ex.getBindingResult().getFieldErrors().forEach(error -> errorResponse.put(error.getField(), error.getDefaultMessage())); // Agrega los errores de validación al mapa de respuesta, utilizando el nombre del campo como clave y el mensaje de error como valor
       return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); // Devuelve la respuesta de error con el mapa de información y el código de estado HTTP 400 (Bad Request)
    }
}
