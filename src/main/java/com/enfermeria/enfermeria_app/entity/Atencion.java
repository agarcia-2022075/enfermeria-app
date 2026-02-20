package com.enfermeria.api.entity;  // CAMBIADO

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "atenciones")
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 200)
    private String motivo;

    @Column(nullable = false, length = 500)
    private String diagnostico;

    @Column(nullable = false, length = 500)
    private String tratamiento;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "personal_id", nullable = false)
    private Long personalId;

    // Constructor vacío
    public Atencion() {}

    // Getters y Setters
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

    public Long getPersonalId() { return personalId; }
    public void setPersonalId(Long personalId) { this.personalId = personalId; }
}