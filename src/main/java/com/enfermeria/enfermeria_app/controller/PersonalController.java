package com.enfermeria.enfermeria_app.controller;

import com.enfermeria.enfermeria_app.entity.Personal;
import com.enfermeria.enfermeria_app.repository.PersonalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personal")
//i hope this will work... :,)
//
public class PersonalController {
    private final PersonalRepository repo;

    public PersonalController(PersonalRepository repo) {
        this.repo = repo;
    }
// 1. LISTAR TODOS
    @GetMapping
    public List<Personal> listar() {
        return repo.findAll();
    }

    // 2. BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Personal> buscarPorId(@PathVariable Long id) {
        Optional<Personal> personal = repo.findById(id);
        if (personal.isPresent()) {
            return ResponseEntity.ok(personal.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // 3 BUSCADOR POR NOMBRE
    // for example:/personal/buscar?nombre=Pako Alkachofa
    @GetMapping("/buscar")
    public List<Personal> buscarPorNombre(@RequestParam String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    // 4. GUARDAR (CREAR)
    @PostMapping
    public Personal guardar(@RequestBody Personal p) {
        return repo.save(p);
    }



    // 5 ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Personal> actualizar(@PathVariable Long id, @RequestBody Personal p) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        p.setId(id);
        return ResponseEntity.ok(repo.save(p));
    }




    // 6 ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}