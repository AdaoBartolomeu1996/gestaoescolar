package com.gestaoescolar.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProximoNumero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private AtomicInteger nextNumb;


}
