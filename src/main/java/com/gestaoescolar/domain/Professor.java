package com.gestaoescolar.domain;

import com.gestaoescolar.domain.enumEstado.EstadoSexo;
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
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroProfessor;

    private String nome;

    private boolean estado = false;

    private String email;

    private String telefone;

    private String bilhete;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private EstadoSexo estadoSexo;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToOne(mappedBy = "professor", orphanRemoval=true)
    private CoordenadorCurso coordenadorCurso;

    @OneToOne(mappedBy = "professor", orphanRemoval=true)
    private Diretor diretor;

    @ManyToMany(mappedBy = "professores")//,cascade = CascadeType.ALL)
    private List<Turma> turmas = new ArrayList<>();

    @ManyToMany(mappedBy = "professores",cascade = CascadeType.ALL)
    private List<Disciplina> disciplinas = new ArrayList<>();

    @OneToMany(mappedBy = "professor")//,cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacaos = new ArrayList<>();

    public void addAvaliacaoProfessor(Avaliacao avaliacao){
        avaliacao.setProfessor(this);
        avaliacaos.add(avaliacao);
    }
}
