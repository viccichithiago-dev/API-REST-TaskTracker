package com.thiago.tasktracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thiago.tasktracker.model.User;
public interface UserRepository extends JpaRepository<User, Long> {
    // Estos métodos ya están implementados por JpaRepository, no es necesario escribirlos explícitamente
    // List<User> findAll();
    // Optional<User> findById(Long id);
    // User save(User user);
    // void deleteById(Long id);
    // Puedes agregar métodos personalizados si es necesario

}
