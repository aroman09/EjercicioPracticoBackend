package com.devsu.api.personacliente.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "clienteId"))
public class Client {
    @Id
    @Column(nullable = false, unique = true)
    private String clientId;
    private String password;
    private boolean estado;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Person persona;

}
