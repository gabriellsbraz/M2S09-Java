package com.m2s09.Javam2s09.service;

import com.m2s09.Javam2s09.exception.RegistroExistente;
import com.m2s09.Javam2s09.model.Usuario;
import com.m2s09.Javam2s09.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;


    public Usuario inserir(Usuario usuario) {
        if (usuarioRepo.existsByEmail(usuario.getEmail()))
            throw new RegistroExistente("Usuario", usuario.getEmail());
        usuario.setSenha(usuario.getSenha());
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> consultar() {
        return usuarioRepo.findAll();
    }

}