package com.enfermeria.enfermeria_app.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "atenciones")
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String motivo;


    @Column(nullable = false)
    private String diagnostico;

    private String tratamiento;


    private Long pacienteId;
    private Long enfermeroId;

    public Atencion() {
    }

    // --- GETTERS Y SETTERS OBLIGATORIOS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public Long getEnfermeroId() { return enfermeroId; }
    public void setEnfermeroId(Long enfermeroId) { this.enfermeroId = enfermeroId; }
}