package com.bank.client.application.dto.request.cliente;

import com.bank.client.application.validator.EstadoValid;
import com.bank.client.application.validator.GeneroValid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteUpdateDTO {
    @Pattern(
            regexp = "^[a-zA-Z]+[a-zA-Z\\d\\.\\_]+[a-zA-Z\\d]+$",
            message = "Valor no válido"
    )
    private String usuario;

    private String contrasenia;

    @EstadoValid
    private String estado;
}
