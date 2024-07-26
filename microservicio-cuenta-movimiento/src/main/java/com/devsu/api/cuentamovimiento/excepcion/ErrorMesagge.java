package com.devsu.api.cuentamovimiento.excepcion;

import lombok.Data;


public enum ErrorMesagge {
    STARTDATE_MAJOR("990","Fecha Inicio no puede ser mayor a la Fecha Fin"),
    TRANSACTION_NO_VALID("992","Error movimiento no corresponde a Deposito/Retiro"),
    ACCOUNT_NO_VALID("993","Error cuenta no corresponde Ahorro/Credito"),
    SALDO_NEGATIVE("994","Saldo insuficiente!! el monto a debitar es mayor al saldo de la cuenta"),
    TRANSACTION_RETIRO("995","Si el movimiento es un retiro, el valor debe ser negativo"),
    TRANSACTION_DEPOSITO("998","Si el movimiento es un deposito, el valor debe ser positivo"),
    NOT_FOUND("996","La cuenta no existe"),
    CONFLIC("997","La cuenta ya existe"),
    NOT_ACTIVE("991","La cuenta no se encuentra activa"),
    NOT_FOUND_TRANSACTION("986","El movimiento no existe"),
    CONFLIC_TRANSACTION("987","El movimiento ya existe"),
    BAD_PATH("999","Opcion no permitida"),
    EXCEPTION("999","Error al procesar solicitud"),
    JSON_FORMAT("999","Formato Json incorrecto"),
    PARAMETER_FORMAT("999","Error en el tipo de parametro ingresado");

    public String codError;
    public String msjError;

    ErrorMesagge(String codError, String msjError) {
        this.codError = codError;
        this.msjError = msjError;
    }
}
