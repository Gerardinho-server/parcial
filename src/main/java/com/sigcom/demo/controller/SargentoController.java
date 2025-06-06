package com.sigcom.demo.controller;

import com.sigcom.demo.model.Mision;
import com.sigcom.demo.model.Usuario;
import com.sigcom.demo.model.Rol;
import com.sigcom.demo.repository.MisionRepository;
import com.sigcom.demo.repository.UsuarioRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sargento")
public class SargentoController {

    private final MisionRepository misionRepo;
    private final UsuarioRepository usuarioRepo;

    public SargentoController(MisionRepository misionRepo, UsuarioRepository usuarioRepo) {
        this.misionRepo = misionRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping("/asignar")
    public String vistaAsignacion(Model model) {
        List<Usuario> soldados = usuarioRepo.findByRol(Rol.SOLDADO);
        List<Mision> misiones = new ArrayList<>();
        misionRepo.findAll().forEach(misiones::add);

        model.addAttribute("soldados", soldados);
        model.addAttribute("misiones", misiones);
        return "sargento-asignar";
    }

    @PostMapping("/asignar")
    public String asignarSoldado(@RequestParam Long misionId, @RequestParam Long soldadoId) {
        Mision mision = misionRepo.findById(misionId).orElse(null);
        Usuario soldado = usuarioRepo.findById(soldadoId).orElse(null);

        if (mision != null && soldado != null) {
            if (!mision.getSoldados().contains(soldado)) {
                mision.getSoldados().add(soldado);
                misionRepo.save(mision);
            }
        }

        return "redirect:/sargento/asignar";
    }
    @PostMapping("/remover")
    public String removerSoldado(@RequestParam Long misionId, @RequestParam Long soldadoId) {
        Mision mision = misionRepo.findById(misionId).orElse(null);
        Usuario soldado = usuarioRepo.findById(soldadoId).orElse(null);

        if (mision != null && soldado != null) {
            mision.getSoldados().remove(soldado);
            misionRepo.save(mision);
        }

        return "redirect:/sargento/asignar";
    }


}
