package com.enfermeria.enfermeria_app.controller;

import com.enfermeria.enfermeria_app.entity.Atencion;
import com.enfermeria.enfermeria_app.repository.AtencionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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
        Optional<Atencion> atencion = repo.findById(id);
        if (atencion.isPresent()) {
            return ResponseEntity.ok(atencion.get());
        } else {return ResponseEntity.notFound().build();}
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
        return ResponseEntity.ok(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Atencion cambios) {
        Optional<Atencion> oAtencion = repo.findById(id);
        if (oAtencion.isEmpty()) {
            return ResponseEntity.notFound().build();}

                    Atencion actual = oAtencion.get();
                    actual.setFecha(cambios.getFecha());
                    actual.setMotivo(cambios.getMotivo());
                    actual.setDiagnostico(cambios.getDiagnostico());
                    actual.setTratamiento(cambios.getTratamiento());
                    actual.setPacienteId(cambios.getPacienteId());
                    actual.setEnfermeroId(cambios.getEnfermeroId());
                    return ResponseEntity.ok(repo.save(actual));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
