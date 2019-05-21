package com.gestaoescolar.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class EstuanteEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String municipio;

    private String Bairro;

    private String rua;

    private String residencia;

    @OneToOne
    @JoinColumn(name = "estudante_id", referencedColumnName = "id")
    private Estudante estudante;

}
