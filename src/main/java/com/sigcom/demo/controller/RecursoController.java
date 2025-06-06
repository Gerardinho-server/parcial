package com.sigcom.demo.controller;

import com.sigcom.demo.model.Recurso;
import com.sigcom.demo.model.Rol;
import com.sigcom.demo.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.sigcom.demo.repository.RecursoRepository;
import com.sigcom.demo.repository.UsuarioRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/recursos")
public class RecursoController {

    private final RecursoRepository recursoRepository;
    private final UsuarioRepository usuarioRepository ;

    public RecursoController(RecursoRepository recursoRepository, UsuarioRepository usuarioRepository) {
        this.recursoRepository = recursoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("recursos", recursoRepository.findAll());
        model.addAttribute("recurso", new Recurso());

        // Obtener solo usuarios con rol OFICIAL_DE_LOGISTICA
        List<Usuario> oficiales = StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
                .filter(u -> u.getRol() == Rol.OFICIAL_DE_LOGISTICA)
                .collect(Collectors.toList());
        model.addAttribute("oficiales", oficiales);

        return "recursos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Recurso recurso, HttpSession session) {
        Usuario actual = (Usuario) session.getAttribute("usuario");
        if (actual != null) {
            recurso.setCreador(actual);
        }
        recursoRepository.save(recurso);
        return "redirect:/recursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        recursoRepository.deleteById(id);
        return "redirect:/recursos";
    }
}
