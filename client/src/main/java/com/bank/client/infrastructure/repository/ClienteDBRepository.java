package com.bank.client.infrastructure.repository;

import com.bank.client.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface ClienteDBRepository extends JpaRepository<ClienteEntity, BigDecimal> {
    @Query("Select c FROM ClienteEntity c INNER JOIN PersonaEntity p on c.persona.personaId = p.personaId WHERE p.identificacion = ?1")
    Optional<ClienteEntity> findByIdentificacion(String identificacion);
}
