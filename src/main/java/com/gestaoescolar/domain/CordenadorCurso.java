package com.gestaoescolar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class CordenadorCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    private Curso curso;

    @OneToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;


}
