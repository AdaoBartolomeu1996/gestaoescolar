package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Foto;
import org.springframework.data.repository.CrudRepository;

public interface FotoRepositorio extends CrudRepository<Foto,Long> {

    Foto findByUsuario_Id(Long id);
}
