package com.bank.client.application.controller;

import com.bank.client.application.dto.request.cliente.ClienteCreateDTO;
import com.bank.client.application.dto.request.cliente.ClienteUpdateDTO;
import com.bank.client.application.dto.response.ResponseFormatDTO;
import com.bank.client.domain.entity.Cliente;
import com.bank.client.domain.entity.Estado;
import com.bank.client.domain.service.IClienteService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/banco/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;



    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseFormatDTO<String>> createCliente(@Valid @RequestBody ClienteCreateDTO body){
        ResponseFormatDTO<String> response = new ResponseFormatDTO<>();

        Cliente cliente = modelMapper.map(body, Cliente.class);

        clienteService.create(cliente);

        response.setMessage("OK");
        response.setError(false);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "{identificacion}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseFormatDTO<String>> updateCliente(@PathVariable("identificacion") String identificacion, @Valid @RequestBody ClienteUpdateDTO body){
        ResponseFormatDTO<String> response = new ResponseFormatDTO<>();

        Cliente cliente = modelMapper.map(body, Cliente.class);
        clienteService.update(cliente, identificacion);

        response.setMessage("OK");
        response.setError(false);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "{identificacion}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseFormatDTO<Cliente>> findCliente(@PathVariable("identificacion") String identificacion){
        ResponseFormatDTO<Cliente> response = new ResponseFormatDTO<>();

        Cliente cliente = clienteService.findByIdentificacion(identificacion);

        response.setData(cliente);
        response.setMessage("OK");
        response.setError(false);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "{identificacion}", consumes = {})
    public ResponseEntity<ResponseFormatDTO<String>> deleteCliente(@PathVariable("identificacion") String identificacion){
        ResponseFormatDTO<String> response = new ResponseFormatDTO<>();

        clienteService.delete(identificacion);

        response.setMessage("OK");
        response.setError(false);

        return ResponseEntity.ok(response);
    }

}
