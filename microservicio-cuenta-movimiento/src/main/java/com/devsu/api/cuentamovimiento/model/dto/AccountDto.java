package com.devsu.api.cuentamovimiento.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @NotBlank(message = "El numero de cuenta no debe estar en blanco")
    private String numeroCuenta;
    @NotBlank(message = "El tipo de cuenta no debe estar en blanco")
    private String tipoCuenta;

    @Min(value = 1, message = "El saldo inicial debe ser mayor a cero")
    private double saldoInicial;
    private boolean estado;
    @NotBlank(message = "El identificador del cliente no debe estar en blanco")
    private String idCliente;

}
