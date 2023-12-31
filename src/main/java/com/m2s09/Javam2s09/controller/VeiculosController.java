package com.m2s09.Javam2s09.controller;

import com.m2s09.Javam2s09.dto.MultaRequest;
import com.m2s09.Javam2s09.dto.MultaResponse;
import com.m2s09.Javam2s09.dto.VeiculoRequest;
import com.m2s09.Javam2s09.dto.VeiculoResponse;
import com.m2s09.Javam2s09.model.Multa;
import com.m2s09.Javam2s09.model.Veiculo;
import com.m2s09.Javam2s09.service.VeiculoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculosController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private VeiculoService veiculoService;


    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> consultar() {
        var veiculos = veiculoService.consultar();
        var resp = new ArrayList<VeiculoResponse>();
        for (Veiculo veiculo : veiculos) {
            var veiculoDTO = modelMapper.map(veiculo, VeiculoResponse.class);
            if (veiculo.temMultas()) {
                var multasDTO = veiculo.getMultas().stream()
                        .map(multa -> modelMapper.map(multa, MultaResponse.class)).toList();
                veiculoDTO.setMultas(multasDTO);
            }
            resp.add(veiculoDTO);
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<VeiculoResponse> consultar(@PathVariable("placa") String placa) {
        Veiculo veiculo = veiculoService.consultar(placa);
        var resp = modelMapper.map(veiculo, VeiculoResponse.class);
        if (veiculo.temMultas()) {
            var multasDTO = veiculo.getMultas().stream()
                    .map(multa -> modelMapper.map(multa, MultaResponse.class)).toList();
            resp.setMultas(multasDTO);
        }
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> cadastrarVeiculo(@RequestBody @Valid VeiculoRequest request) {
        var veiculo = modelMapper.map(request, Veiculo.class);
        veiculo = veiculoService.salvar(veiculo);
        var resp = modelMapper.map(veiculo, VeiculoResponse.class);
        return ResponseEntity.created(URI.create(veiculo.getPlaca())).body(resp);
    }

    @PostMapping("/{placa}/multas")
    public ResponseEntity<MultaResponse> cadastrarMulta(@PathVariable("placa") String placa,
                                                        @RequestBody @Valid MultaRequest request) {
        var multa = modelMapper.map(request, Multa.class);
        multa = veiculoService.cadastrarMulta(placa, multa);
        var resp = modelMapper.map(multa, MultaResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}