package com.bank.client.domain.service;

import com.bank.client.domain.entity.Cliente;
import com.bank.client.domain.entity.Estado;
import com.bank.client.domain.exception.cliente.ClienteAlreadyExistsException;
import com.bank.client.domain.exception.cliente.ClienteNotFoundException;
import com.bank.client.domain.repository.IClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void create(Cliente cliente) {
        cliente.setEstado(Estado.Activo);

        Optional<Cliente> optionalCliente = clienteRepository.findByIdentificacion(cliente.getIdentificacion());
        if(optionalCliente.isPresent()){
            throw new ClienteAlreadyExistsException();
        }
        clienteRepository.create(cliente);
    }

    @Override
    public Cliente update(Cliente cliente, String identification) {
        Optional<Cliente> optionalCliente = clienteRepository.findByIdentificacion(identification);
        if (optionalCliente.isEmpty()){
            throw new ClienteNotFoundException();
        }
        Cliente clienteGuardado = optionalCliente.get();
        modelMapper.map(cliente, clienteGuardado);
        clienteRepository.update(clienteGuardado);
        return clienteGuardado;
    }

    @Override
    public Cliente findByIdentificacion(String identificacion) {
        Optional<Cliente> optionalCliente =  clienteRepository.findByIdentificacion(identificacion);
        if (optionalCliente.isEmpty()){
            throw new ClienteNotFoundException();
        }
        return optionalCliente.get();
    }

    @Override
    public void delete(String identificacion) {
        Optional<Cliente> optionalCliente =  clienteRepository.findByIdentificacion(identificacion);
        if (optionalCliente.isEmpty()){
            throw new ClienteNotFoundException();
        }

        Cliente cliente = optionalCliente.get();
        cliente.setEstado(Estado.Inactivo);

        clienteRepository.update(cliente);
    }
}
