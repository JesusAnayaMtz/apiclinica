package com.volt.api.controller;

import com.volt.api.model.*;
import com.volt.api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosMedico datosMedico, UriComponentsBuilder uriComponentsBuilder){
        System.out.println("Se registra correctamente");
        System.out.println(datosMedico);
        Medico medico = medicoRepository.save(new Medico(datosMedico));
        //Return 201 created
        //URl donde encontrar al medico
        // Get http://localhost:8080/medicos/xx
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(),
                medico.getDocumento(),medico.getEspecialidad().toString(), new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();  //crear una url para pasar al created
        return ResponseEntity.created(url).body(datosRespuestaMedico);

    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> obtenerMedicos(@PageableDefault(size = 4, sort = "nombre")  Pageable paginacion){
        //retornamos un datos listado medico poara solo obtener lo que nos piden y con un stream recorremos datos listado medico
        // y creamos uno por cada medioc que encoentre y lo devolvemoa a una lista
        //se modifica a Page para usar la paginacion y asi ya no se retorna con un stream si no pasamos como parametro en el find all la paginacion
        //y con map retornamos el listado de datoslistado medico
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> obtenerMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        var datosmedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(),
                medico.getDocumento(),medico.getEspecialidad().toString(), new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));
        return  ResponseEntity.ok(datosmedico);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizaMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(),
                medico.getDocumento(),medico.getEspecialidad().toString(), new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento())));
    }

    //desactivar medico o eliminacion logica
//    @DeleteMapping("/eliminar/{id}")
//    @Transactional
//    public void eliminarMedico(@PathVariable Long id){
//        Medico medico = medicoRepository.getReferenceById(id);
//        medico.desactivarMedico();
//    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    //eliminar medico totalmente en base de datos
//    @DeleteMapping("/eliminar/{id}")
//    @Transactional
//    public void eliminarMedicoTotalmente(@PathVariable Long id){
//        Medico medico = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico);
//    }
}
