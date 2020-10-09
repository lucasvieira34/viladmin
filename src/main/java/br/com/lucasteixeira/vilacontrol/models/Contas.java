package br.com.lucasteixeira.vilacontrol.models;

import br.com.lucasteixeira.vilacontrol.enums.StatusPagamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "contas")
@Setter @Getter @NoArgsConstructor @EqualsAndHashCode
public class Contas implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_contas;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date pagamento;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valor;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "id_residencia")
    private Residencia residencia;

    public boolean isPendente() {
        return StatusPagamento.PENDENTE.equals(this.status);
    }
}
