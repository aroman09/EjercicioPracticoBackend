package com.devsu.api.cuentamovimiento.manageObject;

import com.devsu.api.cuentamovimiento.excepcion.Error;
import com.devsu.api.cuentamovimiento.model.entity.Transaction;

import java.time.LocalDate;
import java.util.Map;


public class ProcessTransaction {

    public Transaction updateTransaction(Transaction transaction, Map<String, Object> parameters){
        parameters.forEach((key, value) -> {
            switch (key) {
                case "tipoMovimiento":
                    transaction.setTipoMovimiento((String) value);
                    break;
                case "valor":
                    transaction.setValor((double) value);
                    break;
                case "fecha":
                    transaction.setFecha((LocalDate) value);
                    break;
                case "saldo":
                    transaction.setSaldo((double) value);
                    break;
            }
        });
        return transaction;
    }

    public double calculeTotal(double saldo, double operation){
        double total = saldo + operation;
        if (total<0)
            throw new Error("994");
        return total;
    }
}
