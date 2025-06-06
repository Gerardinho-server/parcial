package com.sigcom.demo.controller;
import java.util.ArrayList;

import com.sigcom.demo.model.Mision;
import com.sigcom.demo.model.Recurso;
import com.sigcom.demo.model.Rol;
import com.sigcom.demo.model.Usuario;
import com.sigcom.demo.repository.MisionRepository;
import com.sigcom.demo.repository.RecursoRepository;
import com.sigcom.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/misiones")
public class MisionController {

    private final MisionRepository misionRepo;
    private final UsuarioRepository usuarioRepo;
    private final RecursoRepository recursoRepo;

    public MisionController(MisionRepository misionRepo, UsuarioRepository usuarioRepo, RecursoRepository recursoRepo) {
        this.misionRepo = misionRepo;
        this.usuarioRepo = usuarioRepo;
        this.recursoRepo = recursoRepo;
    }

    // Mostrar todas las misiones
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("misiones", misionRepo.findAll());
        return "misiones"; // Página que muestra la tabla
    }

    // Mostrar formulario para crear misión
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        List<Usuario> oficiales = usuarioRepo.findByRol(Rol.OFICIAL_DE_OPERACIONES);
        model.addAttribute("usuarios", oficiales);
        model.addAttribute("recursos", recursoRepo.findAll());
        return "misiones-crear";
    }


    // Guardar la nueva misión
    @PostMapping("/guardar")
    public String guardar(@RequestParam("nombre") String nombre,
                          @RequestParam("descripcion") String descripcion,
                          @RequestParam("estado") String estado,
                          @RequestParam("creadorId") Long creadorId,
                          @RequestParam("recursos") List<Long> recursosIds) {

        Mision mision = new Mision();
        mision.setNombre(nombre);
        mision.setDescripcion(descripcion);
        mision.setEstado(estado);

        Usuario creador = usuarioRepo.findById(creadorId).orElse(null);
        mision.setCreador(creador);

        List<Recurso> recursos = new ArrayList<>();
        recursoRepo.findAllById(recursosIds).forEach(recursos::add);
        mision.setRecursos(recursos);

        misionRepo.save(mision);
        return "redirect:/misiones";
    }


    // Eliminar misión
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        misionRepo.deleteById(id);
        return "redirect:/misiones";
    }
    
    
}
