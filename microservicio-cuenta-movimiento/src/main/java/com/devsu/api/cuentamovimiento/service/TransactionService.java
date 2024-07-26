package com.devsu.api.cuentamovimiento.service;

import com.devsu.api.cuentamovimiento.model.dto.TransactionDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
    List<TransactionDto> listallTransaction();
    List<TransactionDto> listallTransactionByAccount(String numCuenta);
    TransactionDto retrieveTransaction(Long id);
    Optional<TransactionDto> retrieveEndTransaction(String numCuenta);
    TransactionDto updateTransaction(TransactionDto transactionDto);
    void deleteTransaction(Long id);
    List<TransactionDto> listallTransactionByDate(LocalDate startDate, LocalDate endDate, String cuenta);
}
