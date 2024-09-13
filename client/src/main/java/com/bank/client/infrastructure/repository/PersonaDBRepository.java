package com.bank.client.infrastructure.repository;

import com.bank.client.infrastructure.entity.ClienteEntity;
import com.bank.client.infrastructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface PersonaDBRepository extends JpaRepository<PersonaEntity, BigDecimal> {
}
