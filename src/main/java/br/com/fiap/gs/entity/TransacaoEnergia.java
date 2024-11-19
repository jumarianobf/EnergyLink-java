package br.com.fiap.gs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Transacao_Energia")
public class TransacaoEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao", nullable = false)
    private Long idTransacao;

    @ManyToOne
    @JoinColumn(name = "id_contrato", nullable = false)
    private ContratoDistribuicao idContrato;

    @Column(name = "data_transacao", nullable = false)
    private LocalDate dataTransacao;

    @Column(name = "quantidade_energia", nullable = false)
    private BigDecimal quantidadeEnergia;

    @Column(name = "custo_total", nullable = false)
    private BigDecimal custoTotal;

    @Column(name = "status_transacao", length = 20, nullable = false)
    private String statusTransacao;
}
