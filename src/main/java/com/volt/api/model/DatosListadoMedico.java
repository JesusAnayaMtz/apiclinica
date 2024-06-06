package com.volt.api.model;

//Clase para mapear solo los datos que pediremos al medico y asi obtener solo los necesarios
public record DatosListadoMedico(
        String nombre,
        String especialidad,
        String documento,
        String email

) {
    //Constructor en el cual le pasamos un medico para acceder a sus datos y maperalos a un datoslistadomeduico
    public DatosListadoMedico(Medico medico){
        this(medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
