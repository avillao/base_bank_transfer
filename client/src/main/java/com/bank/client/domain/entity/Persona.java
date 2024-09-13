package com.bank.client.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    protected String personaId;
    protected String nombre;
    protected Genero genero;
    protected Integer edad;
    protected String identificacion;
    protected String direccion;
    protected String telefono;
}
