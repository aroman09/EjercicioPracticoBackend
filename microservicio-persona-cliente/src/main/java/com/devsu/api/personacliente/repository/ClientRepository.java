package com.devsu.api.personacliente.repository;

import com.devsu.api.personacliente.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
