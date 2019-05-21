package com.gestaoescolar.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProfessorTelefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int telefone;

    @OneToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;
}
