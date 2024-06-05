package com.volt.api.model;

public record DatosMedico(
        String nombre,
        String email,
        String documenton,
        Especialidad especialidad,
        Direccion direccion

) {
}
