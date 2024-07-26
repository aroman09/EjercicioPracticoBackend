package com.devsu.api.cuentamovimiento.manageObject;

import com.devsu.api.cuentamovimiento.model.entity.Account;

import java.util.Map;


public class ProcessAccount {

    public Account updateAccount(Account account, Map<String, Object> parameters){
        parameters.forEach((key, value) -> {
            switch (key) {
                case "tipoCuenta":
                    account.setTipoCuenta((String) value);
                    break;
                case "saldoInicial":
                    account.setSaldoInicial((double) value);
                    break;
                case "estado":
                    account.setEstado((boolean) value);
                    break;
            }
        });
        return account;
    }
}
