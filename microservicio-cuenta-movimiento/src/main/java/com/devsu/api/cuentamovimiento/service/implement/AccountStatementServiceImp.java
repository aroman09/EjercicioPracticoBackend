package com.devsu.api.cuentamovimiento.service.implement;

import com.devsu.api.cuentamovimiento.excepcion.Error;
import com.devsu.api.cuentamovimiento.model.dto.*;
import com.devsu.api.cuentamovimiento.service.AccountStatementService;
import com.devsu.api.cuentamovimiento.service.RabbitMQ.ClienteRequestProducerService;
import com.devsu.api.cuentamovimiento.service.RabbitMQ.ClienteResponseConsumerService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AccountStatementServiceImp implements AccountStatementService {
    @Autowired
    AccountServiceImp accountServiceImp;
    @Autowired
    TransactionServiceImp transactionServiceImp;

    private ClienteRequestProducerService clienteRequestService;
    private ClienteResponseConsumerService clienteResponse;


    @Override
    public AccountStatementDto retrieveAccountStatement(LocalDate startDate, LocalDate endDate, String idClient) {
        if (startDate.isAfter(endDate))
            throw new Error("990");
        clienteRequestService.obtenerClientePorIdentificacion(idClient);
        List<AccountDto> listaCuentas = accountServiceImp.listAccountByClient(idClient);
        List<AccountTransactionDto> movimientos_cuenta = new ArrayList<>();
        CompletableFuture<ClientDto> clienteDTOFuture;
        AccountStatementDto accountStatementDto= new AccountStatementDto();
        try {
            clienteDTOFuture = clienteResponse.obtenerClienteDTO();
            accountStatementDto.setCliente(clienteDTOFuture.get());
        }catch (Exception ex)
        {
            throw new Error("980");
        }

        listaCuentas.stream()
                .forEach(cuenta -> {
                    AccountTransactionDto accountTransactionDto= new AccountTransactionDto();
                    accountTransactionDto.setCuenta(cuenta);
                    accountTransactionDto.setMovimientos(
                            transactionServiceImp.listallTransactionByDate(startDate,endDate,cuenta.getNumeroCuenta()));
                    movimientos_cuenta.add(accountTransactionDto);
                });
        accountStatementDto.setCuentas(movimientos_cuenta);
        return accountStatementDto;
    }

    public byte[] generatePdf(LocalDate startDate, LocalDate endDate, String idClient) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            Document document = new Document();

            PdfWriter.getInstance(document, baos);
            AccountStatementDto accountStatementDto = retrieveAccountStatement(startDate,endDate,idClient);
            document.open();
            document.add(new Paragraph("ESTADO DE CUENTA"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            document.add(new Paragraph("Fechas: " + startDate.format(formatter)+" - "+endDate.format(formatter)));
            document.add(new Paragraph("Cliente: " + accountStatementDto.getCliente().nombre()));
            PdfPTable table = new PdfPTable(7);
            PdfPCell cell = new PdfPCell(new Phrase("Cuenta"));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            PdfPCell cell1 = new PdfPCell(new Phrase("Tipo"));
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell2 = new PdfPCell(new Phrase("Saldo Inicial"));
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell3 = new PdfPCell(new Phrase("Estado"));
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell4 = new PdfPCell(new Phrase("Fecha"));
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell5 = new PdfPCell(new Phrase("Movimiento"));
            cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell6 = new PdfPCell(new Phrase("Saldo Disponible"));
            cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);

            for (AccountTransactionDto cuenta:accountStatementDto.getCuentas()){
                for (TransactionDto movimiento:cuenta.getMovimientos()){
                    table.addCell(cuenta.getCuenta().getNumeroCuenta());
                    table.addCell(cuenta.getCuenta().getTipoCuenta());
                    table.addCell(String.valueOf(cuenta.getCuenta().getSaldoInicial()));
                    table.addCell(String.valueOf(cuenta.getCuenta().isEstado()));
                    table.addCell(movimiento.getFecha().toString());
                    table.addCell(String.valueOf(movimiento.getValor()));
                    table.addCell(String.valueOf(movimiento.getSaldo()));
                }
            }
            document.add(table);
            document.close();
        }catch (Exception ex){
            throw new Error("999");
        }

        return baos.toByteArray();
    }

}
