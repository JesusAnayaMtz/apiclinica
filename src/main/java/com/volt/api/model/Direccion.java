package com.volt.api.model;

public record Direccion(
        String calle,
        String distrito,
        String ciudad,
        String numero,
        String complemento
) {
}
