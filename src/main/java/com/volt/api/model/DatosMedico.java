package com.volt.api.model;

public record DatosMedico(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        DatosDireccion datosDireccion

) {
}
