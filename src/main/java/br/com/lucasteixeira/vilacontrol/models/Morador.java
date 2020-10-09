package br.com.lucasteixeira.vilacontrol.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "morador")
@Setter @Getter @NoArgsConstructor @EqualsAndHashCode
public class Morador implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_morador;

    @Column(nullable = false)
    private String nome;

    @OneToOne(mappedBy = "morador")
    private Residencia residencia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", unique = true)
    private Usuario usuario;
}
