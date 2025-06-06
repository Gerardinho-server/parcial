package com.sigcom.demo.controller;

import com.sigcom.demo.model.Mision;
import com.sigcom.demo.repository.MisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/soldado")
public class SoldadoController {

    @Autowired
    private MisionRepository misionRepo;

    @GetMapping("/misiones")
    public String verMisiones(Model model) {
        List<Mision> misiones = new ArrayList<>();
        misionRepo.findAll().forEach(misiones::add);
        model.addAttribute("misiones", misiones);
        return "soldado-misiones";
    }
}
