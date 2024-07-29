package com.devsu.api.cuentamovimiento.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatementDto {
    private Client cliente;
    private List<AccountTransactionDto> cuentas;

}
