package com.m2s09.Javam2s09.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RegistroNaoEncontrado extends RuntimeException {

    private String nome;
    private String identificador;


    public RegistroNaoEncontrado(String nome, Integer identificador) {
        this(nome, identificador.toString());
    }

    public RegistroNaoEncontrado(String nome, Long identificador) {
        this(nome, identificador.toString());
    }


    public String getMessage() {
        if (nome == null || identificador == null)
            return null;
        return String.format("Registro '%s' nnao encontrado com identificador '%s'", nome, identificador);
    }
}