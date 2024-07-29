package com.devsu.api.cuentamovimiento.service.implement;

import com.devsu.api.cuentamovimiento.excepcion.Error;
import com.devsu.api.cuentamovimiento.model.entity.Account;
import com.devsu.api.cuentamovimiento.repository.AccountRepository;
import com.devsu.api.cuentamovimiento.service.AccountService;
import com.devsu.api.cuentamovimiento.service.RabbitMQ.ClienteRequestProducerService;
import com.devsu.api.cuentamovimiento.service.RabbitMQ.ClienteResponseConsumerService;
import com.devsu.api.cuentamovimiento.util.AccountType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.devsu.api.cuentamovimiento.model.dto.AccountDto;
import com.devsu.api.cuentamovimiento.model.dto.Client;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImp implements AccountService {
    @Autowired
    private ClienteResponseConsumerService clienteResponseConsumerService;
    @Autowired
    private ClienteRequestProducerService clienteRequestProducerService;
    @Autowired
    private AccountRepository accountRepository;

    private ModelMapper modelMapper=new ModelMapper();
    @Autowired
    private ObjectMapper objectMapper;

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
        List<AccountDto> listAccount = accountRepository.findAll().stream().map(
                (account) -> modelMapper.map(account, AccountDto.class)
        ).collect(Collectors.toList());

        return changeIdToName(listAccount);
    }

    @Override
    public List<AccountDto> listAccountByClient(String idClient) {
        List<AccountDto> listAccount = accountRepository.findAllByIdCliente(idClient).stream().map(
                (account)-> modelMapper.map(account,AccountDto.class)
        ).collect(Collectors.toList());

        return changeIdToName(listAccount);
    }

    private List<AccountDto> changeIdToName(List<AccountDto> listAccount){
        List<AccountDto> listAccountFinal = new ArrayList<>();
        for (AccountDto account : listAccount) {
            AccountDto auxiliar = account;
            auxiliar.setIdCliente(getClienteById(account.getIdCliente()).getNombre());
            listAccountFinal.add(auxiliar);
        }
        return listAccountFinal;

    }

    @Override
    public AccountDto retrieveAccount(String id) {
        AccountDto accountDto = accountRepository.findById(id)
            .map(cuenta -> {
                return modelMapper.map(accountRepository.findById(id),AccountDto.class);
            })
            .orElseThrow(()-> new Error("996"));
        accountDto.setIdCliente(getClienteById(accountDto.getIdCliente()).getNombre());
        return accountDto;

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

    public Client getClienteById(String id)  {
        // Enviar solicitud a la cola de solicitudes
       try {
           clienteRequestProducerService.obtenerClientePorIdentificacion(id);
           Client cliente = new Client();
           //obtener el cliente desde rabittmq
           CompletableFuture<String> clienteStrCompletableFuture = clienteResponseConsumerService.obtenerClienteStr();

           log.info("Respuesta desde queue " + clienteStrCompletableFuture.get());
           if (!clienteStrCompletableFuture.get().isEmpty()) {
               ObjectMapper objectMapper = new ObjectMapper();
               cliente = objectMapper.readValue(clienteStrCompletableFuture.get(), Client.class);
               log.info(cliente.toString());
           }
           return cliente;
       }catch (Exception ex){
            throw new Error("980");
        }
    }
}
