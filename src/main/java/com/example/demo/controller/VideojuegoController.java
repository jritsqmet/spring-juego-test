package com.example.demo.controller;

import com.example.demo.model.Videojuego;
import com.example.demo.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VideojuegoController {

    @Autowired
    private VideojuegoRepository repositorio;

    // Listar todos
    @GetMapping("/")
    public String listarVideojuegos(Model model) {
        model.addAttribute("videojuegos", repositorio.findAll());
        model.addAttribute("nuevoVideojuego", new Videojuego());
        return "index";
    }

    // Crear
    @PostMapping("/crear")
    public String crearVideojuego(@ModelAttribute Videojuego videojuego) {
        repositorio.save(videojuego);
        return "redirect:/";
    }

    // Editar (formulario)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Videojuego v = repositorio.findById(id).orElse(null);
        model.addAttribute("videojuegoEditar", v);
        model.addAttribute("videojuegos", repositorio.findAll());
        model.addAttribute("nuevoVideojuego", new Videojuego());
        return "index";
    }

    // Actualizar
    @PostMapping("/actualizar")
    public String actualizarVideojuego(@ModelAttribute Videojuego videojuego) {
        repositorio.save(videojuego);
        return "redirect:/";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarVideojuego(@PathVariable Long id) {
        repositorio.deleteById(id);
        return "redirect:/";
    }
}