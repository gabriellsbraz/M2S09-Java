package com.m2s09.Javam2s09.dto;

import lombok.Data;

@Data
public class UsuarioResponse {

    private Integer id;

    private String nome;

    private String email;

    private String Role;
}