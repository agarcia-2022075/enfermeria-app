package com.enfermeria.enfermeria_app.controller;

import com.enfermeria.enfermeria_app.entity.Atencion;
import com.enfermeria.enfermeria_app.entity.Paciente;
import com.enfermeria.enfermeria_app.entity.Personal;
import com.enfermeria.enfermeria_app.entity.Insumo;
import com.enfermeria.enfermeria_app.repository.AtencionRepository;
import com.enfermeria.enfermeria_app.repository.PacienteRepository;
import com.enfermeria.enfermeria_app.repository.PersonalRepository;
import com.enfermeria.enfermeria_app.repository.InsumoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/atenciones")
public class AtencionController {

    private final AtencionRepository atencionRepo;
    private final PacienteRepository pacienteRepo;
    private final PersonalRepository personalRepo;
    private final InsumoRepository insumoRepo;

    // Inyectamos TODOS los repositorios necesarios
    public AtencionController(AtencionRepository atencionRepo,
                              PacienteRepository pacienteRepo,
                              PersonalRepository personalRepo,
                              InsumoRepository insumoRepo) {
        this.atencionRepo = atencionRepo;
        this.pacienteRepo = pacienteRepo;
        this.personalRepo = personalRepo;
        this.insumoRepo = insumoRepo;
    }

    @GetMapping
    public List<Atencion> listar() {
        return atencionRepo.findAll();
    }

    // GUARDAR SIN DTO (Usando Map para recibir los IDs)
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Map<String, Object> datos) {
        try {
            Atencion nuevaAtencion = new Atencion();

            // 1. Llenar datos básicos (haciendo conversiones seguras)
            nuevaAtencion.setMotivo((String) datos.get("motivo"));
            nuevaAtencion.setDiagnostico((String) datos.get("diagnostico"));
            nuevaAtencion.setTratamiento((String) datos.get("tratamiento"));

            // Convertir fecha de String a LocalDate si viene como texto
            if (datos.get("fecha") != null) {
                nuevaAtencion.setFecha(LocalDate.parse((String) datos.get("fecha")));
            }

            // 2. BUSCAR Y ASIGNAR EL PACIENTE (Relación)
            Long pacienteId = Long.valueOf(datos.get("pacienteId").toString());
            Paciente p = pacienteRepo.findById(pacienteId)
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            nuevaAtencion.setPaciente(p);

            // 3. BUSCAR Y ASIGNAR EL ENFERMERO (Relación)
            Long enfermeroId = Long.valueOf(datos.get("enfermeroId").toString());
            Personal per = personalRepo.findById(enfermeroId)
                    .orElseThrow(() -> new RuntimeException("Enfermero no encontrado"));
            nuevaAtencion.setPersonal(per);

            // 4. BUSCAR Y ASIGNAR INSUMOS (Si vienen en la lista)
            if (datos.get("insumoIds") != null) {
                List<Integer> idsRaw = (List<Integer>) datos.get("insumoIds");
                // Convertir Integer a Long
                List<Long> ids = idsRaw.stream().map(Integer::longValue).toList();
                List<Insumo> listaInsumos = insumoRepo.findAllById(ids);
                nuevaAtencion.setInsumos(listaInsumos);
            }

            return ResponseEntity.ok(atencionRepo.save(nuevaAtencion));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar: " + e.getMessage());
        }
    }
}