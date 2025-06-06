package com.sigcom.demo.controller;


import com.sigcom.demo.model.Mision;
import com.sigcom.demo.model.Reporte;
import com.sigcom.demo.repository.ReporteRepository;
import com.sigcom.demo.repository.MisionRepository;
import com.sigcom.demo.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;
import com.sigcom.demo.model.Rol;
import com.sigcom.demo.model.Usuario;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteRepository repo;
    private final MisionRepository misionRepo;
    private final UsuarioRepository usuarioRepo;

    public ReporteController(ReporteRepository repo, MisionRepository misionRepo, UsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.misionRepo = misionRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reportes", repo.findAll());
        model.addAttribute("reporte", new Reporte());
        model.addAttribute("misiones", misionRepo.findAll());

        List<Usuario> sargentos = StreamSupport
            .stream(usuarioRepo.findAll().spliterator(), false)
            .filter(u -> u.getRol() == Rol.SARGENTO_DE_TROPAS)
            .collect(Collectors.toList());

        model.addAttribute("usuarios", sargentos);

        return "reportes";
    }

    @PostMapping("/guardar")
    public String guardar(
            @RequestParam("contenido") String contenido,
            @RequestParam("misionId") Long misionId,
            @RequestParam("autorId") Long autorId) {

        Reporte reporte = new Reporte();
        reporte.setContenido(contenido);
        reporte.setFecha(LocalDate.now()); // si tienes un campo fecha

        Mision mision = misionRepo.findById(misionId).orElse(null);
        Usuario autor = usuarioRepo.findById(autorId).orElse(null);

        reporte.setMision(mision);
        reporte.setAutor(autor);

        repo.save(reporte);
        return "redirect:/reportes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/reportes";
    }
}
