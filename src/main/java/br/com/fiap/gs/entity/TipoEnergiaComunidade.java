package br.com.fiap.gs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Tipo_Energia_Comunidade")
public class TipoEnergiaComunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_energia", length = 10)
    private Long idTipoEnergia;

    @Column(name = "descricao_energia", length = 50)
    @NotNull
    private String descricaoEnergia;


}

