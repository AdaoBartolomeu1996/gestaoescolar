package com.gestaoescolar.domain;

import com.gestaoescolar.domain.enumEstado.EstadoAvaliacao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nota1;

    private int nota2;

    private int media;

    private Date anoLetivo;

    @Enumerated(EnumType.STRING)
    private EstadoAvaliacao estadoAvaliacao;

    @OneToOne
    @JoinColumn(name = "semestre_id", referencedColumnName = "id")
    private Semestre semestre;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "estudante_id")
    private Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;


}
