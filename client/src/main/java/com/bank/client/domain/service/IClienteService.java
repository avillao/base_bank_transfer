package com.bank.client.domain.service;

import com.bank.client.domain.entity.Cliente;

public interface IClienteService {
    void create(Cliente cliente);
    Cliente update(Cliente cliente, String identificacion);
    Cliente findByIdentificacion(String identificacion);
    void delete(String identificacion);
}
