package com.enfermeria.enfermeria_app.repository;

import com.enfermeria.enfermeria_app.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNombreContainingIgnoreCase(String nombre);
}