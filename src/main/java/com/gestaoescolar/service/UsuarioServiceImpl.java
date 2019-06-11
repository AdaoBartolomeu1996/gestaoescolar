package com.gestaoescolar.service;

import com.gestaoescolar.domain.Usuario;
import com.gestaoescolar.repositorio.UsuarioRepositorio;
import com.gestaoescolar.service.map.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public Usuario procurarUsername(String username) {
        return usuarioRepositorio.findByUsername(username);
    }
}
