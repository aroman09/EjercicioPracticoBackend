package com.devsu.api.cuentamovimiento;

import com.intuit.karate.junit5.Karate;

public class ApiTest {
    @Karate.Test
    Karate testAccount() {
        return Karate.run("classpath:api/account.feature").relativeTo(getClass());
    }

    @Karate.Test
    Karate testTransaction() {
        return Karate.run("classpath:/api/transaction.feature").relativeTo(getClass());
    }
}
