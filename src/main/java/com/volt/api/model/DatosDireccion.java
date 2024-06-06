package com.volt.api.model;

public record DatosDireccion(
        String calle,
        String distrito,
        String ciudad,
        String numero,
        String complemento
) {
}
