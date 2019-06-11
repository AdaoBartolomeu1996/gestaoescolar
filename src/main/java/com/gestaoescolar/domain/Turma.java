package com.gestaoescolar.domain;

import com.gestaoescolar.domain.enumEstado.EstadoClasse;
import com.gestaoescolar.domain.enumEstado.EstadoTurma;
import com.gestaoescolar.domain.enumEstado.EstadoTurno;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Turma{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private EstadoTurma estadoTurma;

    @Enumerated(EnumType.STRING)
    private EstadoTurno turno;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataAbertura;

    private int numeroVaga;

    private int mediaValor;

    @Enumerated(EnumType.STRING)
    private EstadoClasse classe;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataTermino;// Data Em que a turma vai fechar A mesma coisa que o Evento quando passa a data abertura feicha automaticamente;

    @OneToOne
    @JoinColumn(name = "anoletivo_id", referencedColumnName = "id")
    private AnoLetivo anoletivo;

    @OneToMany(mappedBy = "turma",orphanRemoval=true )
    private List<Estudante> estudantes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToMany
    @JoinTable(name = "turma_professor",joinColumns = @JoinColumn(name = "turma_id", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "professor_id", referencedColumnName = "id"))
    private List<Professor> professores = new ArrayList<>();

    public void addProfessor(Professor professor){
        professor.getTurmas().add(this);
        professores.add(professor);
    }


    public void addEstudante(Estudante estudante){
        estudante.setTurma(this);
        this.estudantes.add(estudante);
    }

}
