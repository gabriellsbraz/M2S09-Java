package com.m2s09.Javam2s09.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RegistroExistente extends RuntimeException {

    private String nome;
    private String identificador;


    public RegistroExistente(String nome, Integer identificador) {
        this(nome, identificador.toString());
    }

    public RegistroExistente(String nome, Long identificador) {
        this(nome, identificador.toString());
    }


    public String getMessage() {
        if (nome == null || identificador == null)
            return null;
        return String.format("Registro '%s' já cadastrado com identificador '%s'", nome, identificador);
    }
}