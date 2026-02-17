package com.enfermeria.enfermeria_app.repository;

import com.enfermeria.enfermeria_app.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
