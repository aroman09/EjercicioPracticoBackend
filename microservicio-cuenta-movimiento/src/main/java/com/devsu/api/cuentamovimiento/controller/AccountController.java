package com.devsu.api.cuentamovimiento.controller;

import com.devsu.api.cuentamovimiento.model.dto.AccountDto;
import com.devsu.api.cuentamovimiento.model.dto.AccountStatementDto;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/cuentas")
public interface AccountController {
    
        @PostMapping
        ResponseEntity<AccountDto> create(@RequestBody @Valid AccountDto accountDTO) ;
        

        @GetMapping
        ResponseEntity<List<AccountDto>> findByAll();
        

        @GetMapping("/{id}")
        ResponseEntity<AccountDto> findById(@PathVariable String id);
        

        @DeleteMapping("/{id}")
        void delete(@PathVariable String id);

        @PutMapping
        ResponseEntity <AccountDto> update(@RequestBody @Valid AccountDto accountDTO);

        @GetMapping("/reportes")
        ResponseEntity<String> getAccountStatement(
                @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                @RequestParam("idCliente") String idClient);
}
