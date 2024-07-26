package com.devsu.api.cuentamovimiento.excepcion;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Error extends RuntimeException{
    private String codeError;
    public Error(String mensaje) {
        super(mensaje);
        this.codeError = mensaje;
    }

}
