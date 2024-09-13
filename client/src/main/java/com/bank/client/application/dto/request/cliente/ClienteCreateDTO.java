package com.bank.client.application.dto.request.cliente;

import com.bank.client.application.validator.GeneroValid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateDTO {
    @NotEmpty(message = "Campo obligatorio")
    @Pattern(
            regexp = "^[a-zA-Z]+[a-zA-Z\\d\\.\\_]+[a-zA-Z\\d]+$",
            message = "Valor no válido"
    )
    private String usuario;

    @NotEmpty(message = "Campo obligatorio")
    private String contrasenia;

    @NotEmpty(message = "Campo obligatorio")
    @Pattern(
            regexp = "^([A-ZÑÁÉÍÓÚ]+['\\-\\.]{0,1}[A-ZÑÁÉÍÓÚ]+)(\\s+([A-ZÑÁÉÍÓÚ]+['\\-]{0,1}[A-ZÑÁÉÍÓÚ]+))*$",
            flags = {Pattern.Flag.CASE_INSENSITIVE},
            message = "Valor no válido"
    )
    private String nombre;

    @NotEmpty(message = "Campo obligatorio")
    @GeneroValid
    private String genero;

    @NotNull(message = "Campo obligatorio")
    private Integer edad;

    @NotEmpty(message = "Campo obligatorio")
    @Pattern(
            regexp = "\\d{10,13}",
            message = "Valor no válido"
    )
    private String identificacion;

    @NotEmpty(message = "Campo obligatorio")
    private String direccion;

    @NotEmpty(message = "Campo obligatorio")
    @Pattern(
            regexp = "\\d{10,13}",
            message = "Valor no válido"
    )
    private String telefono;
}
