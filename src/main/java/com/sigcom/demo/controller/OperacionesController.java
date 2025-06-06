package com.sigcom.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/operaciones")
public class OperacionesController {

    @GetMapping("/misiones")
    public String verMisiones(Model model) {
        // Puedes agregar atributos al modelo si es necesario
        return "misiones"; // Asegúrate de que el archivo `misiones.html` exista
    }
}
