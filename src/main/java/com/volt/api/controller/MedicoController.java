package com.volt.api.controller;

import com.volt.api.model.DatosMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping("/registrar")
    public void registrarMedico(@RequestBody DatosMedico parametro){
        System.out.println("Se registra correctamente");
        System.out.println(parametro);
    }
}
