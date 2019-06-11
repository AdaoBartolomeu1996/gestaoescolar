package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.AnoLetivo;
import com.gestaoescolar.domain.CoordenadorCurso;

import java.util.List;

public interface AnoLetivoService {

    void registrarAnoLetivo(AnoLetivo anoLetivo);

    void atualizarAnoLetivo(AnoLetivo anoLetivo);

    AnoLetivo procurarAnoLetivo(String anoLetivo);

    AnoLetivo procurarAnoLetivoPorId(Long id);

    void apagarAnoletivo(Long id);


    void fecharAnoLetivo(Long id);


    List<AnoLetivo> listarAnoLetivoEstado();

    List<AnoLetivo> listarAnoletivo();

}
