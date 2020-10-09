package br.com.lucasteixeira.vilacontrol.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter @Getter @NoArgsConstructor @EqualsAndHashCode
public class Role implements GrantedAuthority {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_role;

    @Column(unique = true)
    private String nome;

    @ManyToMany(mappedBy = "roles")
    private Collection<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return this.nome;
    }
}