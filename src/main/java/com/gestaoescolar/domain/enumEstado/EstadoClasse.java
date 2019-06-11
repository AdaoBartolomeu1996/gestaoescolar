package com.gestaoescolar.domain.enumEstado;

public enum EstadoClasse {
    CLASSE10("10ª"),CLASSE11("11ª"), CLASSE12("12ª"), CLASSE13("13ª");


    private final String classe;

    EstadoClasse(String classe) {
        this.classe = classe;
    }

    public String getClasse() {
        return classe;
    }
}
