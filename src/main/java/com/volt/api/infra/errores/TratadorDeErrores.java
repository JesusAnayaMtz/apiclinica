package com.volt.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  //actua como un proxy para interceptar las llamadas en caso de que suceda algun error o exepcion
public class TratadorDeErrores {

    //lo anotamos con exceptionhandler y dentro le pasamos la ecepcion que vamos a tratar sea lanzada y retornara la respuesta dentro del metodo
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    //para tratar error 400 cuando un campo de un resgistro nuevo no se esta mandando correctamente y lo pasamos como una lista de errores
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorVAlidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    //creamos un dto para que podamos solo mostrar el error del campo que esta fallando cachamos el mensaje y lo pasamos al metodo donde tratamos el error
    private record DatosErrorVAlidacion(String campo, String error){
        public DatosErrorVAlidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());  //cacahmos el campo del error y el mensaje que envia
        }
    }



}
