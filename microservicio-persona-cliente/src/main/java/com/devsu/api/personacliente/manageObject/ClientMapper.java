package com.devsu.api.personacliente.manageObject;

import com.devsu.api.personacliente.model.dto.ClientDto;
import com.devsu.api.personacliente.model.entity.Client;
import com.devsu.api.personacliente.model.entity.Person;
import org.springframework.stereotype.Service;


@Service
public class ClientMapper {

    public ClientDto clientToClientDTO(Client cliente){
        ClientDto clienteDTO= new ClientDto();
        clienteDTO.setClientId(cliente.getClientId());
        clienteDTO.setNombre(cliente.getPersona().getNombre());
        clienteDTO.setDireccion(cliente.getPersona().getDireccion());
        clienteDTO.setEdad(cliente.getPersona().getEdad());
        clienteDTO.setGenero(cliente.getPersona().getGenero());
        clienteDTO.setTelefono(cliente.getPersona().getTelefono());
        clienteDTO.setIdentificacion(cliente.getPersona().getIdentificacion());
        clienteDTO.setPassword(cliente.getPassword());
        clienteDTO.setEstado(cliente.isEstado());
        clienteDTO.setId(cliente.getPersona().getId());

        return clienteDTO;
    }

    public Client clientDTOToClient(ClientDto clienteDTO){
        Client cliente = new Client();
        Person person = new Person();
        cliente.setPersona(person);
        cliente.getPersona().setId(clienteDTO.getId());
        cliente.setClientId(clienteDTO.getClientId());
        cliente.getPersona().setNombre(clienteDTO.getNombre());
        cliente.getPersona().setIdentificacion(clienteDTO.getIdentificacion());
        cliente.getPersona().setEdad(clienteDTO.getEdad());
        cliente.getPersona().setTelefono(clienteDTO.getTelefono());
        cliente.getPersona().setGenero(clienteDTO.getGenero());
        cliente.getPersona().setDireccion(clienteDTO.getDireccion());
        cliente.setPassword(clienteDTO.getPassword());
        cliente.setEstado(clienteDTO.isEstado());
        return cliente;
    }
}
