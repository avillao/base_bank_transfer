package com.bank.client.domain.exception.cliente;

import com.bank.client.domain.exception.DomainException;
import jakarta.annotation.Nonnull;

public class ClienteNotFoundException extends DomainException {

    public ClienteNotFoundException(String message, Throwable throwable){
        super(message, 404, throwable);
    }

    public ClienteNotFoundException(@Nonnull String message){
        this(message, null);
    }

    public ClienteNotFoundException(@Nonnull Throwable throwable){
        this();
        super.initCause(throwable);
    }

    public ClienteNotFoundException(){
        this("Cliente no encontrado");
    }
}
