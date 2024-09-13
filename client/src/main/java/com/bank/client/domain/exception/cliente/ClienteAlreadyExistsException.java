package com.bank.client.domain.exception.cliente;

import com.bank.client.domain.exception.DomainException;
import jakarta.annotation.Nonnull;

public class ClienteAlreadyExistsException extends DomainException {

    public ClienteAlreadyExistsException(String message, Throwable throwable){
        super(message, 404, throwable);
    }

    public ClienteAlreadyExistsException(@Nonnull String message){
        this(message, null);
    }

    public ClienteAlreadyExistsException(@Nonnull Throwable throwable){
        this();
        super.initCause(throwable);
    }

    public ClienteAlreadyExistsException(){
        this("Cliente ya existe");
    }
}
