package com.bank.client.domain.exception;

import lombok.Data;

@Data
public class DomainException extends RuntimeException{
    private int statusCode;

    public DomainException(String message, int statusCode, Throwable throwable){
        super(message);
        this.statusCode = statusCode;
        this.initCause(throwable);
    }
}
