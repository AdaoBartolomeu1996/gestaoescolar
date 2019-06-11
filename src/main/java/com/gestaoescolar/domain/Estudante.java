package com.gestaoescolar.domain;

import com.gestaoescolar.domain.enumEstado.EnumMunicipio;
import com.gestaoescolar.domain.enumEstado.EstadoEstudante;
import com.gestaoescolar.domain.enumEstado.EstadoSexo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Estudante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroMatricula;

    private  String nome;

    private String bilhete;

    private String nomePai;

    private String nomeMae;

    private int telefoneEncarregado;//Na fase da Matricula

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    private String bairro;

    private String rua;

    private String residencia;

    private String fotoAluno;

    private String certificadoOrininalFoto;

    private String instituicao;

    private  int media;

    private String fotoBi;

    @Enumerated(EnumType.STRING)
    private EstadoSexo estadoSexo;

    @Enumerated(EnumType.STRING)
    private EnumMunicipio municipio;

    @Enumerated(EnumType.STRING)
    private EstadoEstudante estadoEstudante;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "estudante",cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacaos = new ArrayList<>();


    public void addAvaliacaoEstudante(Avaliacao avaliacao){
        avaliacao.setEstudante(this);
        avaliacaos.add(avaliacao);
    }

}
