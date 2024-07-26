package com.devsu.api.personacliente.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientController clientController;

    @Test
    void create() throws Exception {
        String clienteJson = "{\"id\":1,\"nombre\":\"Pablo\",\"genero\":\"Masculino\",\"edad\":25, \"identificacion\":\"1100908789\",\"direccion\":\"Loja\", \"telefono\":\"05789\", \"password\":\"12345\",\"clientId\":\"pablo123\",\"estado\":true}";

        mockMvc.perform(post("http://localhost:8083/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Pablo"))
                .andExpect(jsonPath("$.identificacion").value("1100908789"));
    }

    @Test
    void findById() {
    }
}