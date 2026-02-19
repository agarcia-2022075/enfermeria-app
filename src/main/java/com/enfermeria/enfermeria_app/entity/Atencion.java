package com.enfermeria.enfermeria_app.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "atenciones")
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String motivo;
    private String diagnostico;
    private String tratamiento;

    // --- MAGIA RELACIONAL ---

    // Muchos atenciones -> 1 Paciente
    @ManyToOne
    @JoinColumn(name = "paciente_id") // Esto crea la Foreign Key en BD
    private Paciente paciente;

    // Muchos atenciones -> 1 Personal (Enfermero)
    @ManyToOne
    @JoinColumn(name = "personal_id") // Esto crea la Foreign Key en BD
    private Personal personal;

    // Muchos atenciones <-> Muchos Insumos
    @ManyToMany
    @JoinTable(
            name = "atencion_insumos", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "atencion_id"),
            inverseJoinColumns = @JoinColumn(name = "insumo_id")
    )
    private List<Insumo> insumos;

    public Atencion() {
    }

    // --- GETTERS Y SETTERS (Actualizados para Objetos) ---

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

    // Ojo a estos nuevos Getters/Setters
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Personal getPersonal() { return personal; }
    public void setPersonal(Personal personal) { this.personal = personal; }

    public List<Insumo> getInsumos() { return insumos; }
    public void setInsumos(List<Insumo> insumos) { this.insumos = insumos; }
}