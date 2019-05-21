package com.gestaoescolar.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class EstudanteTelefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int telefone;

    @OneToOne
    @JoinColumn(name = "estudante_id", referencedColumnName = "id")
    private Estudante estudante;

}
