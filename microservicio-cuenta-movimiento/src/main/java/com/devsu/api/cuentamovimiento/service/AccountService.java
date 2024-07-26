package com.devsu.api.cuentamovimiento.service;

import com.devsu.api.cuentamovimiento.model.dto.AccountDto;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


public interface AccountService {
    AccountDto createAccount(AccountDto AccounteDTO);
    List<AccountDto> listallAccount();
    List<AccountDto> listAccountByClient(String idClient);
    AccountDto retrieveAccount(String id);
    AccountDto updateAccount(AccountDto AccounteDTO);
    void deleteAccount(String id);
    AccountDto retrieveAccountActive(String id);
}
