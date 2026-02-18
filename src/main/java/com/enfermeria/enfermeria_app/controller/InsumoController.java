package com.enfermeria.enfermeria_app.controller;

import com.enfermeria.enfermeria_app.entity.Insumo;
import com.enfermeria.enfermeria_app.repository.InsumoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/insumos")
public class InsumoController {

    private final InsumoRepository repo;

    public InsumoController(InsumoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Insumo> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Insumo guardar(@RequestBody Insumo insumo) {
        return repo.save(insumo);
    }
}