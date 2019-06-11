package com.gestaoescolar.domain.enumEstado;

public enum  EnumMunicipio {

    CACAUACO("Cacuaco"),Belas("Belas"), Cazenga ("Cazenga"),  Ícolo_e_Bengo("Ícolo e Bengo"), Luanda("Luanda"), Viana("Viana");

    private final String municipio;

    EnumMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getMunicipio() {
        return municipio;
    }


}
