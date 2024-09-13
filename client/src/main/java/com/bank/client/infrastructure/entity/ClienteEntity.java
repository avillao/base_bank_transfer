package com.bank.client.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name = "CLIENTE", schema = "BANCO")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class ClienteEntity {
    @Id
    @Column(name="CLIENTE_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private BigDecimal clienteId;

    @OneToOne
    @JoinColumn(name = "PERSONA_ID")
    private PersonaEntity persona;

    @Column(name="USUARIO")
    private String usuario;

    @Column(name="CONTRASENIA")
    private String contrasenia;

    @Column(name="ESTADO")
    private String estado;
}
