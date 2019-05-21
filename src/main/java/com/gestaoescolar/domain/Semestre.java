package com.gestaoescolar.domain;

import com.gestaoescolar.domain.enumEstado.EstadoSemestre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoSemestre semestre;

    @OneToOne(mappedBy = "semestre",cascade = CascadeType.ALL)
    private Avaliacao avaliacao;
}
