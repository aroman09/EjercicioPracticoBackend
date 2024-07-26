package com.devsu.api.cuentamovimiento.controller.implement;

import com.devsu.api.cuentamovimiento.controller.AccountController;
import com.devsu.api.cuentamovimiento.model.dto.AccountDto;
import com.devsu.api.cuentamovimiento.model.dto.AccountStatementDto;
import com.devsu.api.cuentamovimiento.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountControllerImp implements AccountController {
    @Autowired
    private AccountService accountService;
    @Override
    public ResponseEntity<AccountDto> create(AccountDto AccountDto) {
        return new ResponseEntity<>(accountService.createAccount(AccountDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<AccountDto>> findByAll() {
        return new ResponseEntity<>(accountService.listallAccount(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountDto> findById(String id) {
        return new ResponseEntity<>(accountService.retrieveAccount(id), HttpStatus.OK);
    }

    @Override
    public void delete(String id) {
        accountService.deleteAccount(id);
    }

    @Override
    public ResponseEntity<AccountDto> update(AccountDto clienteDTO) {
        return new ResponseEntity<>(accountService.updateAccount(clienteDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getAccountStatement(LocalDate startDate, LocalDate endDate, String idClient) {
        return new ResponseEntity<>("llegue", HttpStatus.OK);
    }
}
