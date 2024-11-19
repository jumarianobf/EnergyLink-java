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
@Table(name = "Pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento", length = 10, nullable = false)
    private Long idPagamento;

    @ManyToOne
    @JoinColumn(name = "id_contrato", nullable = false)
    private ContratoDistribuicao idContrato;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "status_pagamento", length = 10)
    private String statusPagamento;
}
