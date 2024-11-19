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
@Table(name = "Contrato_Distribuicao")
public class ContratoDistribuicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato", length = 10)
    private Long idContrato;

    @OneToOne
    @JoinColumn(name = "id_comunidade", nullable = false)
    private ComunidadeProdutora idComunidade;

    @ManyToOne
    @JoinColumn(name = "id_fabrica", nullable = false)
    private FabricaParceira idFabrica;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "preco_kwh", nullable = false)
    private BigDecimal precoKwh;

    @Column(name = "status_contrato", length = 20)
    private String statusContrato;

}
