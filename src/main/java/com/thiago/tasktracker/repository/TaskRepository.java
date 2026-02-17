package com.thiago.tasktracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.tasktracker.model.Task;
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Estos métodos ya están implementados por JpaRepository, no es necesario escribirlos explícitamente
    // List<Task> findAll();
    // Optional<Task> findById(Long id);
    // Task save(Task task);
    // void deleteById(Long id);
    // Puedes agregar métodos personalizados si es necesario
    // Actualizar una tarea
}
