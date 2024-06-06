package com.volt.api.controller;

import com.volt.api.model.DatosActualizarMedico;
import com.volt.api.model.DatosListadoMedico;
import com.volt.api.model.DatosMedico;
import com.volt.api.model.Medico;
import com.volt.api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/registrar")
    public void registrarMedico(@RequestBody @Valid DatosMedico datosMedico){
        System.out.println("Se registra correctamente");
        System.out.println(datosMedico);
        medicoRepository.save(new Medico(datosMedico));
    }

    @GetMapping
    public Page<DatosListadoMedico> obtenerMedicos(@PageableDefault(size = 4, sort = "nombre")  Pageable paginacion){
        //retornamos un datos listado medico poara solo obtener lo que nos piden y con un stream recorremos datos listado medico
        // y creamos uno por cada medioc que encoentre y lo devolvemoa a una lista
        //se modifica a Page para usar la paginacion y asi ya no se retorna con un stream si no pasamos como parametro en el find all la paginacion
        //y con map retornamos el listado de datoslistado medico
        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
    }

    @PutMapping("/actualizar")
    @Transactional
    public void actualizaMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
    }
}
