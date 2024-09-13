package com.bank.client.domain.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona {
    private String clienteId;
    private String usuario;
    private String contrasenia;
    private Estado estado;
}
