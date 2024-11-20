package br.com.fiap.gs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Fabrica_Parceira")
@Builder
public class FabricaParceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fabrica", nullable = false)
    private Long idFabrica;

    @ManyToOne
    @JoinColumn(name = "id_entidade", nullable = false)
    @NotNull
    private EntidadeBase idEntidade;

    @ManyToOne
    @JoinColumn(name = "id_localizacao", nullable = false)
    @NotNull
    private Localizacao idLocalizacao;

    @Column(name = "demanda_energia_fabrica", nullable = false)
    private BigDecimal demandaEnergiaFabrica;
}
