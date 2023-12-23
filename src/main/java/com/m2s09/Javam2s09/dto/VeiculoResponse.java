package com.m2s09.Javam2s09.dto;

import com.m2s09.Javam2s09.model.TipoVeiculo;
import lombok.Data;

import java.util.List;

@Data
public class VeiculoResponse {
    private String placa;

    private TipoVeiculo tipo;

    private String nome;

    private Integer anoFabricacao;

    private String cor;

    private List<MultaResponse> multas;
}