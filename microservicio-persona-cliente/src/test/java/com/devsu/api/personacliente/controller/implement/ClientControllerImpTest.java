package com.devsu.api.personacliente.controller.implement;

import com.devsu.api.personacliente.manageObject.ClientMapper;
import com.devsu.api.personacliente.model.entity.Client;
import com.devsu.api.personacliente.model.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.devsu.api.personacliente.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerImpTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientRepository clienteRepository;

    private Client cliente;
    @BeforeEach
    public void setUp() {
        clienteRepository.deleteAll();
        cliente = new Client();
        cliente.setPersona(new Person(0L,"Juan Perez","M",32,"1105378596","Cuenca","124596"));
        cliente.setPassword("abc123..");
        cliente.setEstado(true);
        cliente.setClientId("jperez");
        cliente = clienteRepository.save(cliente);
    }
    @Test
    void create() throws Exception {
        Client nuevoCliente = new Client();
        nuevoCliente.setPersona(new Person(0L,"Pedro Perez","M",32,"1105378596","Cuenca","124596"));


        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevoCliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro Perez"))
                .andExpect(jsonPath("$.edad").value(32));

    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/api/clientes/{id}", cliente.getClientId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Perez"))
                .andExpect(jsonPath("$.clientId").value("jperez"));
    }
}