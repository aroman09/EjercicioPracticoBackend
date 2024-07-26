package com.devsu.api.cuentamovimiento.controller.implement;

import com.devsu.api.cuentamovimiento.controller.TransactionController;
import com.devsu.api.cuentamovimiento.model.dto.TransactionDto;
import com.devsu.api.cuentamovimiento.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionControllerImp implements TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Override
    public ResponseEntity<TransactionDto> create(TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<TransactionDto>> findByAll() {
        return new ResponseEntity<>(transactionService.listallTransaction(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TransactionDto> findById(Long id) {
        return new ResponseEntity<>(transactionService.retrieveTransaction(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TransactionDto>> findByAccount(String cuenta) {
        return new ResponseEntity<>(transactionService.listallTransactionByAccount(cuenta), HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        transactionService.deleteTransaction(id);
    }

    @Override
    public ResponseEntity<TransactionDto> update(TransactionDto movementeDTO) {
        return new ResponseEntity<>(transactionService.updateTransaction(movementeDTO), HttpStatus.OK);
    }
}
