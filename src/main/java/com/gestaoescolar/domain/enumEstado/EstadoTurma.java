package com.gestaoescolar.domain.enumEstado;

public enum EstadoTurma {
    ACTIVA("ACTIVA"), CONCLUIDA("Concluida");


    private final String turma;

    EstadoTurma(String turma) {
        this.turma = turma;
    }

    public String getTurma() {
        return turma;
    }
}
