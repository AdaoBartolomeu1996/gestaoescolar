package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario,Long> {

    Usuario findByUsername(String username);
}
