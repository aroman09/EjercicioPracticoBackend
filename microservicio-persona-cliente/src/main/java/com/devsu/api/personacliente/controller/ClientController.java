package com.devsu.api.personacliente.controller;

import com.devsu.api.personacliente.model.dto.ClientDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/clientes")
public interface ClientController {
    
        @PostMapping
        ResponseEntity<ClientDto> create(@RequestBody @Valid ClientDto clienteDTO) ;
        

        @GetMapping
        ResponseEntity<List<ClientDto>> findByAll();
        

        @GetMapping("/{id}")
        ResponseEntity<ClientDto> findById(@PathVariable String id);
        

        @DeleteMapping("/{id}")
        void delete(@PathVariable String id);

        @PutMapping
        ResponseEntity <ClientDto> update(@RequestBody @Valid ClientDto clienteDTO);

        @PatchMapping("/{id}")
        ResponseEntity <ClientDto> updateParameter(@PathVariable String id, @RequestBody Map<String, Object> parameters);

        

}
