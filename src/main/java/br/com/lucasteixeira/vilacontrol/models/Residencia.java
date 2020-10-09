package br.com.lucasteixeira.vilacontrol.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "residencia")
@Setter @Getter @NoArgsConstructor @EqualsAndHashCode
public class Residencia implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_residencia;

    @Column(nullable = false, unique = true)
    private String casa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_morador", referencedColumnName = "id_morador", unique = true)
    private Morador morador;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "residencia")
    private List<Contas> contas;
}
