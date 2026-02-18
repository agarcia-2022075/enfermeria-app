package com.enfermeria.enfermeria_app.controller;

import com.enfermeria.enfermeria_app.entity.Atencion;
import com.enfermeria.enfermeria_app.repository.AtencionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Atencion> atencion = repo.findById(id);
        if (atencion.isPresent()) {
            return ResponseEntity.ok(atencion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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
    public Atencion guardar(@RequestBody Atencion a) {
        return repo.save(a);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Atencion a) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        a.setId(id);
        return ResponseEntity.ok(repo.save(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}