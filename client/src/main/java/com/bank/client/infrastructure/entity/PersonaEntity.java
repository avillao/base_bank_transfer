package com.bank.client.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name = "PERSONA", schema = "BANCO")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class PersonaEntity {
    @Id
    @Column(name="PERSONA_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private BigDecimal personaId;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="GENERO")
    private String genero;

    @Column(name="EDAD")
    private int edad;

    @Column(name="IDENTIFICACION")
    private String identificacion;

    @Column(name="DIRECCION")
    private String direccion;

    @Column(name="TELEFONO")
    private String telefono;
}
