package com.devsu.api.cuentamovimiento.controller.implement;

import com.devsu.api.cuentamovimiento.controller.AccountStatement;
import com.devsu.api.cuentamovimiento.model.dto.AccountStatementDto;
import com.devsu.api.cuentamovimiento.service.implement.AccountStatementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class AccountStatementImp implements AccountStatement {

    @Autowired
    AccountStatementServiceImp accountStatementServiceImp;

    @Override
    public ResponseEntity<AccountStatementDto> getAccountStatement(LocalDate startDate, LocalDate endDate, String idClient) {
        return new ResponseEntity<>(accountStatementServiceImp.retrieveAccountStatement(startDate,endDate,idClient), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> generatePdf(LocalDate startDate, LocalDate endDate, String idClient) {
            byte[] pdfBytes = accountStatementServiceImp.generatePdf(startDate, endDate, idClient);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=document.pdf");
            headers.setContentType(MediaType.APPLICATION_PDF);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
