package com.gestaoescolar.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Curso> cursos = new ArrayList<>();

    @OneToMany(mappedBy = "disciplina",cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacaoes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "professor_disciplina",joinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "professor_id", referencedColumnName = "id"))
    private List<Professor> professores = new ArrayList<>();

    public void addProfessor(Professor professor){
        professor.getDisciplinas().add(this);
        professores.add(professor);
    }

    public void addAvaliacaoDisciplina(Avaliacao avaliacao){
      avaliacao.setDisciplina(this);
        avaliacaoes.add(avaliacao);
    }

}
