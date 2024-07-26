package com.devsu.api.personacliente.excepcion;

import lombok.Data;


public enum ErrorMesagge {
    CONFLIC("997","El cliente ya existe"),
    EXCEPTION("999","Error al procesar solicitud"),
    NOT_FOUND("996","El cliente no existe"),
    BAD_PATH("999","Opcion no permitida"),
    JSON_FORMAT("999","Formato Json incorrecto"),
    PARAMETER_FORMAT("999","Error en el tipo de parametro ingresado");

    public String codError;
    public String msjError;

    ErrorMesagge(String codError, String msjError) {
        this.codError = codError;
        this.msjError = msjError;
    }
}
