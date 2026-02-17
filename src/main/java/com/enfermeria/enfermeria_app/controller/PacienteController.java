package com.enfermeria.enfermeria_app.controller;

import com.enfermeria.enfermeria_app.entity.Paciente;
import com.enfermeria.enfermeria_app.repository.PacienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository repo;

    public PacienteController(PacienteRepository repo) {
        this.repo = repo;
    }

    // GET todos
    @GetMapping
    public List<Paciente> listar() {
        return repo.findAll();
    }

    // GET por ID
    @GetMapping("/{id}")
    public Optional<Paciente> buscarPorId(@PathVariable Long id) {
        return repo.findById(id);
    }

    // POST crear
    @PostMapping
    public Paciente guardar(@RequestBody Paciente p) {
        return repo.save(p);
    }

    // PUT actualizar
    @PutMapping("/{id}")
    public Paciente actualizar(@PathVariable Long id, @RequestBody Paciente p) {
        p.setId(id);
        return repo.save(p);
    }

    // DELETE eliminar
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "Paciente eliminado correctamente";
    }
}