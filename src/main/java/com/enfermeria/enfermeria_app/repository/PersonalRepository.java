package com.enfermeria.enfermeria_app.repository;
import com.enfermeria.enfermeria_app.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal, Long> {

    // Buscador:Encuentra por nombre (ignorando mayúsculas/minúsculas)
    // Ejemplo:buscar "Juan" encuentra "juan", "JUAN", "Juan Carlos"
    List<Personal> findByNombreContainingIgnoreCase(String nombre);

}
