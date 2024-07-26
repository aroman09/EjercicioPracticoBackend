package com.devsu.api.cuentamovimiento.repository;

import com.devsu.api.cuentamovimiento.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findAllByIdCliente(String idCliente);
}
