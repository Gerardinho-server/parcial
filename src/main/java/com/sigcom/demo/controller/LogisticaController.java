package com.sigcom.demo.controller;

import com.sigcom.demo.model.Recurso;
import com.sigcom.demo.repository.RecursoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/logistica")
public class LogisticaController {

    private final RecursoRepository recursoRepo;

    public LogisticaController(RecursoRepository recursoRepo) {
        this.recursoRepo = recursoRepo;
    }

    @GetMapping("/recursos")
    public String listarRecursos(Model model) {
        model.addAttribute("recursos", recursoRepo.findAll());
        model.addAttribute("recurso", new Recurso());
        return "logistica-recursos";
    }

    @PostMapping("/guardar")
    public String guardarRecurso(@ModelAttribute Recurso recurso) {
        recursoRepo.save(recurso);
        return "redirect:/logistica/recursos";
    }

    @GetMapping("/editar/{id}")
    public String editarRecurso(@PathVariable Long id, Model model) {
        Recurso recurso = recursoRepo.findById(id).orElse(null);
        model.addAttribute("recurso", recurso);
        return "logistica-editar";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Recurso recurso) {
        recursoRepo.save(recurso);
        return "redirect:/logistica/recursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        recursoRepo.deleteById(id);
        return "redirect:/logistica/recursos";
    }
}
