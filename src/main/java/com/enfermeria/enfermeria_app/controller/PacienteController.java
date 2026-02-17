package com.enfermeria.enfermeria_app.controller;

import com.enfermeria.enfermeria_app.entity.Paciente;
import com.enfermeria.enfermeria_app.repository.PacienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository repo;

    public PacienteController(PacienteRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Paciente> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Paciente guardar(@RequestBody Paciente p) {
        return repo.save(p);
    }
}
