package com.example.demo.controller;

import com.example.demo.model.Videojuego;
import com.example.demo.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videojuegos")
@CrossOrigin(origins = "*") // Solo para desarrollo; en producción, limita a tu dominio/app
public class VideojuegoRestController {

    @Autowired
    private VideojuegoRepository repositorio;

    // Listar todos
    @GetMapping
    public List<Videojuego> listarTodos() {
        return repositorio.findAll();
    }

    // Obtener uno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> obtenerPorId(@PathVariable Long id) {
        Optional<Videojuego> videojuego = repositorio.findById(id);
        return videojuego.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear
    @PostMapping
    public Videojuego crear(@RequestBody Videojuego videojuego) {
        return repositorio.save(videojuego);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Videojuego> actualizar(@PathVariable Long id, @RequestBody Videojuego videojuegoActualizado) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        videojuegoActualizado.setId(id); // Asegura que se actualice el mismo ID
        return ResponseEntity.ok(repositorio.save(videojuegoActualizado));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}