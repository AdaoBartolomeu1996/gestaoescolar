package com.gestaoescolar.domain;


import com.gestaoescolar.domain.enumEstado.EstadoAnoLetivo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class AnoLetivo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate anoAbertura;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataMatriculaInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataMatriculaFim;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInscricaoInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInscricaoFim;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private boolean estado = true;

    @Enumerated(EnumType.STRING)
    private EstadoAnoLetivo anoLetivo;

    @OneToOne(mappedBy = "anoletivo",orphanRemoval=true)
    private Turma turma;

    //private Avaliacao avaliacao;

}
