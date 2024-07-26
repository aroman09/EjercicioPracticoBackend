package com.devsu.api.cuentamovimiento.controller;

import com.devsu.api.cuentamovimiento.model.dto.AccountStatementDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/reportes")
public interface AccountStatement {
    @GetMapping
    ResponseEntity<AccountStatementDto> getAccountStatement(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("idCliente") String idClient);

    @GetMapping("/pdf")
    ResponseEntity<byte[]> generatePdf(@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                       @RequestParam("idCliente") String idClient);
}
