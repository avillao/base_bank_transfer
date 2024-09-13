package com.bank.client.infrastructure.repository;

import com.bank.client.domain.entity.Cliente;
import com.bank.client.domain.entity.Estado;
import com.bank.client.domain.entity.Persona;
import com.bank.client.infrastructure.entity.ClienteEntity;
import com.bank.client.infrastructure.entity.PersonaEntity;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bank.client.domain.repository.IClienteRepository;

import java.util.Optional;

@Repository
public class ClienteRepository implements IClienteRepository {
    @Autowired
    private ClienteDBRepository clienteDBRepository;

    @Autowired
    private PersonaDBRepository personaDBRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public void create(Cliente cliente) {
        PersonaEntity personaEntity =  modelMapper.map(cliente, PersonaEntity.class);
        personaEntity = personaDBRepository.save(personaEntity);

        ClienteEntity clienteEntity = modelMapper.map(cliente, ClienteEntity.class);
        clienteEntity.setPersona(personaEntity);

        clienteDBRepository.save(clienteEntity);
    }

    @Transactional
    @Override
    public void update(Cliente cliente) {
        ClienteEntity clienteEntity = modelMapper.map(cliente, ClienteEntity.class);
        clienteDBRepository.save(clienteEntity);
    }


    @Override
    public Optional<Cliente> findByIdentificacion(String identificacion) {
        Optional<ClienteEntity> optionalClienteEntity = clienteDBRepository.findByIdentificacion(identificacion);
        if (optionalClienteEntity.isEmpty()){
            return Optional.empty();
        }

        ClienteEntity clienteEntity = optionalClienteEntity.get();

        Cliente cliente = modelMapper.map(clienteEntity, Cliente.class);
        modelMapper.map(clienteEntity.getPersona(), cliente);

        return Optional.of(cliente);
    }

    @Transactional
    @Override
    public boolean delete(String identificacion) {
        return false;
    }
}
