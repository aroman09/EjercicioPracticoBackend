package com.devsu.api.personacliente.controller.implement;

import com.devsu.api.personacliente.controller.ClientController;
import com.devsu.api.personacliente.model.dto.ClientDto;
import com.devsu.api.personacliente.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientControllerImp implements ClientController {
    @Autowired
    private ClientService clientService;
    @Override
    public ResponseEntity<ClientDto> create(ClientDto clientDTO) {
        return new ResponseEntity<>(clientService.createClient(clientDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ClientDto>> findByAll() {
        return new ResponseEntity<>(clientService.listallClient(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientDto> findById(String id) {
        return new ResponseEntity<>(clientService.retrieveClient(id), HttpStatus.OK);
    }

    @Override
    public void delete(String id) {
        clientService.deleteClient(id);
    }

    @Override
    public ResponseEntity<ClientDto> update(ClientDto clienteDTO) {
        return new ResponseEntity<>(clientService.updateClient(clienteDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientDto> updateParameter(String id, Map<String, Object> parameters) {
        return new ResponseEntity<>(clientService.updateParameterClient(id,parameters), HttpStatus.OK);
    }
}
