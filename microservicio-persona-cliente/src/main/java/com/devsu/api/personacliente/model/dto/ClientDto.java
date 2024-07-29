package com.devsu.api.personacliente.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    @NotBlank(message = "El parametro nombre no debe estar en blanco")
    private String nombre;
    @NotBlank(message = "El parametro genero no debe estar en blanco")
    private String genero;
    @Positive(message="El parametro edad debe ser un valor positivo")
    @Max(value = 120, message="El parametro edad debe estar en en un rango de entre 1 y 120 años")
    private int edad;
    @NotBlank(message = "El parametro identificación no debe estar en blanco")
    @Size(min = 10,max = 13, message = "El parametro identificacion debe tener entre 10 y 13 caracteres")
    private String identificacion;
    @NotBlank(message = "El parametro dirección no debe estar en blanco")
    private String direccion;
    @NotBlank(message = "El parametro teléfono no debe estar en blanco")
    private String telefono;
    @NotBlank(message = "El parametro clientId no debe estar en blanco")
    private String clientId;
    @NotBlank(message = "El parametro password no debe estar en blanco")
    @Size (min = 8,max = 15, message = "El parametro password debe tener entre 8 y 15 caracteres")
    private String password;
    private boolean estado;

}
