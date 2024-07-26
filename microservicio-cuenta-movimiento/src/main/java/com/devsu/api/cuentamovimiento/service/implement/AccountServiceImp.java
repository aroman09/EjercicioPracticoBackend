package com.devsu.api.cuentamovimiento.service.implement;

import com.devsu.api.cuentamovimiento.excepcion.Error;
import com.devsu.api.cuentamovimiento.model.dto.AccountDto;
import com.devsu.api.cuentamovimiento.model.entity.Account;
import com.devsu.api.cuentamovimiento.repository.AccountRepository;
import com.devsu.api.cuentamovimiento.service.AccountService;
import com.devsu.api.cuentamovimiento.util.AccountType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    private ModelMapper modelMapper=new ModelMapper();

    @Override
    public AccountDto createAccount(AccountDto accounteDTO) {
        if(accountRepository.findById(accounteDTO.getNumeroCuenta()).isPresent())
            throw new Error("997");
        if (!accounteDTO.getTipoCuenta().equals(AccountType.AHORRO)&&
                accounteDTO.getTipoCuenta().equals(AccountType.CORRIENTE))
            throw new Error("993");
        Account account = modelMapper.map(accounteDTO,Account.class);
        return modelMapper.map(accountRepository.save(account),AccountDto.class);

    }

    @Override
    public List<AccountDto> listallAccount() {
        return accountRepository.findAll().stream().map(
                (account)-> modelMapper.map(account,AccountDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> listAccountByClient(String idClient) {
        return accountRepository.findAllByIdCliente(idClient).stream().map(
                (account)-> modelMapper.map(account,AccountDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public AccountDto retrieveAccount(String id) {
        return accountRepository.findById(id)
                .map(cuenta -> {
                    return modelMapper.map(accountRepository.findById(id),AccountDto.class);
                })
                .orElseThrow(()-> new Error("996"));
    }


    @Override
    public AccountDto updateAccount(AccountDto accountDTO) {
        return accountRepository.findById(accountDTO.getNumeroCuenta())
                .map(cliente -> {
                    Account Account = modelMapper.map(accountDTO,Account.class);
                    return modelMapper.map(accountRepository.save(Account),AccountDto.class);
                })
                .orElseThrow(()-> new Error("996"));
    }

    @Override
    public void deleteAccount(String id) {
        if (!accountRepository.findById(id).isPresent())
            throw new Error("996");
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto retrieveAccountActive(String id) {
        if (!retrieveAccount(id).isEstado())
            throw new Error("991");
        return retrieveAccount(id);
    }
}
