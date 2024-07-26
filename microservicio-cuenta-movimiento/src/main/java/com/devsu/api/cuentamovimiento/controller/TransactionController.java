package com.devsu.api.cuentamovimiento.controller;

import com.devsu.api.cuentamovimiento.model.dto.TransactionDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/movimientos")
public interface TransactionController {
    
        @PostMapping
        ResponseEntity<TransactionDto> create(@RequestBody TransactionDto movementDto) ;
        

        @GetMapping
        ResponseEntity<List<TransactionDto>> findByAll();
        

        @GetMapping("/{id}")
        ResponseEntity<TransactionDto> findById(@PathVariable Long id);

        @GetMapping("/cuenta")
        ResponseEntity<List<TransactionDto>> findByAccount(@RequestParam("cuenta") String cuenta);
        

        @DeleteMapping("/{id}")
        void delete(@PathVariable Long id);

        @PutMapping
        ResponseEntity <TransactionDto> update(@RequestBody @Valid TransactionDto movementDto);
        

}
