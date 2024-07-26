package com.devsu.api.cuentamovimiento.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    @NotBlank(message = "El parametro fecha no debe estar en blanco")
    private LocalDate fecha;
    @NotBlank(message = "El parametro tipo movimiento no debe estar en blanco")
    private String tipoMovimiento;
    @NotBlank(message = "El parametro valor no debe estar en blanco")
    private double valor;
    private double saldo;
    @NotBlank(message = "El parametro numero de cuenta no debe estar en blanco")
    private String numeroCuenta;

}
