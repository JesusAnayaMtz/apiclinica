package com.volt.api.controller;

import com.volt.api.model.DatosMedico;
import com.volt.api.model.Medico;
import com.volt.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/registrar")
    public void registrarMedico(@RequestBody DatosMedico datosMedico){
        System.out.println("Se registra correctamente");
        System.out.println(datosMedico);
        medicoRepository.save(new Medico(datosMedico));
    }
}
