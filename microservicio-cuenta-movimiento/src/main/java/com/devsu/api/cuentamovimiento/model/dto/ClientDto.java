package com.devsu.api.cuentamovimiento.model.dto;

public record ClientDto
        (Long id,String nombre, String genero, int edad, String identificacion, String direccion, String telefono)
{

}