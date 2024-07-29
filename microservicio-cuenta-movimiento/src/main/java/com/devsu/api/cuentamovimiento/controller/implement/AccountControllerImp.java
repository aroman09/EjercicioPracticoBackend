package com.devsu.api.cuentamovimiento.controller.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.devsu.api.cuentamovimiento.controller.AccountController;
import com.devsu.api.cuentamovimiento.model.dto.AccountDto;
import com.devsu.api.cuentamovimiento.model.dto.Client;
import com.devsu.api.cuentamovimiento.service.implement.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AccountControllerImp implements AccountController {
    @Autowired
    private AccountServiceImp accountService;
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

    @Override
    public ResponseEntity<Client> obtenerCliente(String id) {
        try {
            Client cliente = accountService.getClienteById(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
