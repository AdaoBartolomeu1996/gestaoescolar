package com.gestaoescolar.domain;

import com.gestaoescolar.domain.enumEstado.EstadoEstudante;
import com.gestaoescolar.domain.enumEstado.EstadoSexo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    private String numeroInscricao;

    private  String nome;

    private String bi;

    private String nomePai;

    private String nomeMae;

    private int telefoneEncarregado;

    private String email;

    private Date data;

    private String fotoAluno;

    private String certificadoOrininalFoto;

    private String fotocopiaBi;

    @Enumerated(EnumType.STRING)
    private EstadoSexo estadoSexo;

    @Enumerated(EnumType.STRING)
    private EstadoEstudante estadoEstudante;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToOne(mappedBy = "estudante", cascade = CascadeType.ALL)
    private EstudanteTelefone telefone;

    @OneToOne(mappedBy = "estudante", cascade = CascadeType.ALL)
    private EstuanteEndereco endereco;

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
