package com.sigcom.demo.controller;

import com.sigcom.demo.model.Rol;
import com.sigcom.demo.model.Usuario;
import com.sigcom.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comandante")
public class ComandanteController {

    private final UsuarioRepository usuarioRepo;

    public ComandanteController(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping("/usuarios")
    public String verUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepo.findAll());
        model.addAttribute("roles", Rol.values());
        return "comandante-usuarios";
    }

    @PostMapping("/actualizarRol")
    public String actualizarRol(@RequestParam Long id, @RequestParam Rol nuevoRol) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setRol(nuevoRol);
            usuarioRepo.save(usuario);
        }
        return "redirect:/comandante/usuarios";
    }
    
    @GetMapping("/crear")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", Rol.values());
        return "comandante-crear";
    }

    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute Usuario usuario) {
        usuarioRepo.save(usuario);
        return "redirect:/comandante/usuarios";
    }

}
