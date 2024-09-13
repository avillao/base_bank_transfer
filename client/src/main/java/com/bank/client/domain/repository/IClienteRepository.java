package com.bank.client.domain.repository;

import com.bank.client.domain.entity.Cliente;

import java.util.Optional;

public interface IClienteRepository {
    void create(Cliente cliente);
    void update(Cliente cliente);
    Optional<Cliente> findByIdentificacion(String identificacion);
    boolean delete(String identificacion);
}
