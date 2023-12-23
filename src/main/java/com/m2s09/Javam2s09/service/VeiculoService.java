package com.m2s09.Javam2s09.service;

import com.m2s09.Javam2s09.exception.RegistroExistente;
import com.m2s09.Javam2s09.exception.RegistroNaoEncontrado;
import com.m2s09.Javam2s09.model.Multa;
import com.m2s09.Javam2s09.model.Veiculo;
import com.m2s09.Javam2s09.repository.MultaRepository;
import com.m2s09.Javam2s09.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepo;

    @Autowired
    private MultaRepository multaRepo;


    public List<Veiculo> consultar() {
        return veiculoRepo.findAll();
    }

    public Veiculo consultar(String placa) {
        return veiculoRepo.findById(placa)
                .orElseThrow(() -> new RegistroNaoEncontrado("Veiculo", placa));
    }

    public Veiculo salvar(Veiculo veiculo) {
        boolean existe = veiculoRepo.existsById(veiculo.getPlaca());
        if(existe){
            throw new RegistroExistente("Veiculo", veiculo.getPlaca());
        }
        veiculo = veiculoRepo.save(veiculo);
        return veiculo;
    }

    public Multa cadastrarMulta(String placa, Multa multa) {
        var veiculo = this.consultar(placa);
        multa.setVeiculo(veiculo);
        multa = multaRepo.save(multa);
        return multa;
    }
}