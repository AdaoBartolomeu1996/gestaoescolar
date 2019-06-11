package com.gestaoescolar.domain.enumEstado;

public enum EstadoAnoLetivo {

    ABERTO("ABERTO"),FECHADO("FECHADO");

    private final String estado;

    EstadoAnoLetivo(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }


}
