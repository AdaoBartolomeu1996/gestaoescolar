package com.gestaoescolar.domain;


import com.gestaoescolar.domain.security.Papel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_papel",
            joinColumns = @JoinColumn(name = "usuario", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "papel", referencedColumnName = "id"))
    private List<Papel> papeis = new ArrayList<>();

    private String username; // O NUMERO DE MATRICULA E O NUMERO DE FUNCIONARIP

    private String password;

    private boolean estado = true;

    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Professor professor;

    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Foto foto;

    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Estudante estudante;

    public void addPapel(Papel papel){
        papel.getUsuarios().add(this);
        papeis.add(papel);
    }

    public void clearPapel(Papel papel){
        papel.getUsuarios().remove(this);
        papeis.remove(papel);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return papeis;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return estado;
    }

    @Override
    public boolean isAccountNonLocked() {
        return estado;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return estado;
    }

    @Override
    public boolean isEnabled() {
        return estado;
    }
}
