package com.bank.client.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public enum Genero {
    @JsonProperty("M")
    Masculino("M"),

    @JsonProperty("F")
    Femenino("F");

    private String value;

    private Genero (String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return value;
    }

    public static Genero valueOfString(String value){
        Enum<Genero>[] enumConstants = Genero.class.getEnumConstants();
        Genero generoEnum = (Genero) Arrays.stream(enumConstants).filter(
                e -> ((Genero) e).getValue().equals(value)).findAny().get();
        return generoEnum;
    }
}
