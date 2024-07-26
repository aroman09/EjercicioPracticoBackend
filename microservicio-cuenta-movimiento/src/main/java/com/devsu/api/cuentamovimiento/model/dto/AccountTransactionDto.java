package com.devsu.api.cuentamovimiento.model.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionDto {
    private AccountDto cuenta;
    private List<TransactionDto> movimientos;
}
