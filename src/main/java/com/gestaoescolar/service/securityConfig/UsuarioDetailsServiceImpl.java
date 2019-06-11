package com.gestaoescolar.service.securityConfig;

import com.gestaoescolar.domain.Usuario;
import com.gestaoescolar.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.findByUsername(username);

        if (usuario == null) throw  new UsernameNotFoundException("Usuário Não Existe");

        return usuario;
    }
}
