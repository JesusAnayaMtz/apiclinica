package com.volt.api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

//este es mi dto para no usar directamente la entidad
public record DatosMedico(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{10,12}")
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")  //validamos con exprecion regular que se aun numero de 4 a 6 digitos
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion datosDireccion

) {
}
