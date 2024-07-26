package com.devsu.api.cuentamovimiento.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "cuentas")
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "numeroCuenta",nullable = false,unique = true)
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private boolean estado;
    private String idCliente;

}
