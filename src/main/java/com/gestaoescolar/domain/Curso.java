package com.gestaoescolar.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @OneToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    private Departamento departamento;

    @OneToOne(mappedBy = "curso",cascade = CascadeType.ALL)
    private CordenadorCurso cordenadorCurso;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    private List<Turma> turmas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "curso_disciplina",joinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "disciplina_id", referencedColumnName = "id"))
    private List<Disciplina> disciplinas = new ArrayList<>();

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    private List<Estudante> estudante = new ArrayList<>();

    public  void addDisciplina(Disciplina disciplina){
        disciplina.getCursos().add(this);
        disciplinas.add(disciplina);
    }

    public void addTurma(Turma turma){
        turma.setCurso(this);
        this.turmas.add(turma);
    }

    public void addEstudante(Estudante estudante){
        estudante.setCurso(this);
        this.estudante.add(estudante);
    }
}
