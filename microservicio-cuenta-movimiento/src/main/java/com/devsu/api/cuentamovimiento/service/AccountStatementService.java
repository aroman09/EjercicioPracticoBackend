package com.devsu.api.cuentamovimiento.service;

import com.devsu.api.cuentamovimiento.model.dto.AccountStatementDto;

import java.time.LocalDate;

public interface AccountStatementService {
    AccountStatementDto retrieveAccountStatement(LocalDate startDate, LocalDate endDate, String idClient);
}
