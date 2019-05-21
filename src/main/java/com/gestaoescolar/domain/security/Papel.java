package com.gestaoescolar.domain.security;

import com.gestaoescolar.domain.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Papel{
//    implements

 ///      GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String papel;

    @ManyToMany(mappedBy = "papeis")
    private Set<Usuario> usuarios = new HashSet<>();

  /*  @Override
    public String getAuthority() {
        return papel;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Papel)) return false;
        Papel papel = (Papel) o;
        return Objects.equals(getId(), papel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
