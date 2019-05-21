package com.gestaoescolar.domain.enumEstado;

public enum  EstadoSexo {
    MASCULINO("Masculino"), FEMENINO("Femenino");

    private final String sexo;

    EstadoSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }
}
