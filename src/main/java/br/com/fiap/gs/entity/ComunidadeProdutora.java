package br.com.fiap.gs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Comunidade_Produtora")
public class ComunidadeProdutora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comunidade", length = 10)
    private Long idComunidade;

    @ManyToOne
    @JoinColumn(name = "id_entidade", nullable = false)
    @NotNull
    private EntidadeBase idEntidade;

    @ManyToOne
    @JoinColumn(name = "id_tipo_energia", nullable = false)
    @NotNull
    private TipoEnergiaComunidade idTipoEnergia;

    @Column(name = "capacidade_producao")
    private BigDecimal capacidadeProducao;

    @ManyToOne
    @JoinColumn(name = "id_localizacao", nullable = false)
    @NotNull
    private Localizacao idLocalizacao;
}
