package com.gestaoescolar.service;

import com.gestaoescolar.domain.AnoLetivo;
import com.gestaoescolar.domain.enumEstado.EstadoAnoLetivo;
import com.gestaoescolar.repositorio.AnoLetivoRepositorio;
import com.gestaoescolar.service.map.AnoLetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnoLetivoServiceImpl implements AnoLetivoService {


    @Autowired
    private AnoLetivoRepositorio anoLetivoRepositorio;


    @Override
    public void registrarAnoLetivo(AnoLetivo anoLetivo) {
        anoLetivo.setAnoLetivo(EstadoAnoLetivo.ABERTO);
        anoLetivoRepositorio.save(anoLetivo);
    }


    @Override
    public void fecharAnoLetivo(Long id) {
        AnoLetivo anoLetivo = procurarAnoLetivoPorId(id);
        anoLetivo.setEstado(false);
        anoLetivo.setAnoLetivo(EstadoAnoLetivo.FECHADO);
        anoLetivoRepositorio.save(anoLetivo);
    }


    @Override
    public List<AnoLetivo> listarAnoLetivoEstado() {

        return anoLetivoRepositorio.findAllByAnoLetivo(EstadoAnoLetivo.ABERTO);
    }

    @Override
    public void atualizarAnoLetivo(AnoLetivo anoLetivo) {
        anoLetivoRepositorio.save(anoLetivo);
    }

    @Override
    public AnoLetivo procurarAnoLetivo(String anoLetivo) {
        return anoLetivoRepositorio.findByAnoAbertura(anoLetivo);
    }

    @Override
    public AnoLetivo procurarAnoLetivoPorId(Long id) {
        return anoLetivoRepositorio.findById(id).orElse(null);
    }

    @Override
    public void apagarAnoletivo(Long id) {
        if (anoLetivoRepositorio.existsById(id)){
            anoLetivoRepositorio.deleteById(id);
        }
    }


    @Override
    public List<AnoLetivo> listarAnoletivo() {
        return anoLetivoRepositorio.findAll();
    }
}
