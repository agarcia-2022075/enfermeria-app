package com.enfermeria.enfermeria_app.controller;

import com.enfermeria.enfermeria_app.entity.Atencion;
import com.enfermeria.enfermeria_app.repository.AtencionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/atenciones")
public class AtencionController {

    private final AtencionRepository repo;

    public AtencionController(AtencionRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Atencion> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atencion> obtenerPorId(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<Atencion> listarPorPaciente(@PathVariable Long pacienteId) {
        return repo.findByPacienteId(pacienteId);
    }

    @GetMapping("/enfermero/{enfermeroId}")
    public List<Atencion> listarPorEnfermero(@PathVariable Long enfermeroId) {
        return repo.findByEnfermeroId(enfermeroId);
    }

    @PostMapping
    public ResponseEntity<Atencion> crear(@RequestBody Atencion nueva) {
        Atencion guardada = repo.save(nueva);
        return ResponseEntity.created(URI.create("/api/atenciones/" + guardada.getId()))
                .body(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atencion> actualizar(@PathVariable Long id, @RequestBody Atencion cambios) {
        return repo.findById(id)
                .map(actual -> {
                    actual.setFecha(cambios.getFecha());
                    actual.setMotivo(cambios.getMotivo());
                    actual.setDiagnostico(cambios.getDiagnostico());
                    actual.setTratamiento(cambios.getTratamiento());
                    actual.setPacienteId(cambios.getPacienteId());
                    actual.setEnfermeroId(cambios.getEnfermeroId());
                    return ResponseEntity.ok(repo.save(actual));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        return repo.findById(id)
                .map(ent -> {
                    repo.delete(ent);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
