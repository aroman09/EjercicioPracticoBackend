package com.devsu.api.personacliente.manageObject;

import com.devsu.api.personacliente.model.entity.Client;

import java.util.Map;


public class ProcessClient {

    public Client updateClient(Client client, Map<String, Object> parameters){
        parameters.forEach((key, value) -> {
            switch (key) {
                case "nombre":
                    client.getPersona().setNombre((String) value);
                    break;
                case "genero":
                    client.getPersona().setGenero((String) value);
                    break;
                case "edad":
                    client.getPersona().setEdad((Integer) value);
                    break;
                case "direccion":
                    client.getPersona().setDireccion((String) value);
                    break;
                case "telefono":
                    client.getPersona().setTelefono((String) value);
                    break;
                case "password":
                    client.getPersona().setTelefono((String) value);
                    break;
                case "estado":
                    client.setEstado((Boolean) value);
                    break;
            }
        });
        return client;
    }
}
