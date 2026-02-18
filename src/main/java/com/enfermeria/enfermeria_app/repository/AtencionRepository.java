package com.enfermeria.enfermeria_app.repository;
import com.enfermeria.enfermeria_app.entity.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtencionRepository extends JpaRepository<Atencion, Long> {
    List<Atencion> findByPacienteId(Long pacienteId);
    List<Atencion> findByEnfermeroId(Long enfermeroId);
}