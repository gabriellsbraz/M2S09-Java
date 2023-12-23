package com.m2s09.Javam2s09.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioRequest {

    @NotEmpty(message = "{required.field}")
    private String nome;

    @NotEmpty(message = "{required.field}")
    private String email;

    @NotEmpty(message = "{required.field}")
    private String senha;

    @NotEmpty(message = "{required.field}")
    private String role;
}