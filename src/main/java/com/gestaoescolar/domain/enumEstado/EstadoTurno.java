package com.gestaoescolar.domain.enumEstado;

public enum EstadoTurno {
    MANHA("Manhã"), TARDE("Tarde"), NOITE("Noite");

    private final String turno;

    EstadoTurno(String turno) {
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }
}
