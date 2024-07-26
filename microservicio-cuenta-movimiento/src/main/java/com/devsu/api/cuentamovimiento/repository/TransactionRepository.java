package com.devsu.api.cuentamovimiento.repository;

import com.devsu.api.cuentamovimiento.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findFirstByCuentaNumeroCuentaOrderByIdDesc(String numeroCuenta);
    List<Transaction> findAllByCuentaNumeroCuentaOrderByIdDesc(String numeroCuenta);
    @Query("SELECT m FROM Transaction m WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin AND m.cuenta.numeroCuenta = :cuenta")
    List<Transaction> findTransactionsByFechaAndNumeroCuenta(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("cuenta") String cuenta
    );


}
