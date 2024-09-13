package com.bank.client.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public enum Estado {
    @JsonProperty("A")
    Activo("A"),
    @JsonProperty("I")
    Inactivo("I");

    private String value;

    private Estado (String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return value;
    }

    public static Estado valueOfString(String value){
        Enum<Estado>[] enumConstants = Estado.class.getEnumConstants();
        Estado estadoEnum = (Estado) Arrays.stream(enumConstants).filter(
                e -> ((Estado) e).getValue().equals(value)).findAny().get();
        return estadoEnum;
    }
}
