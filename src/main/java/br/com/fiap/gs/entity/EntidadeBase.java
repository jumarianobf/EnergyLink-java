package br.com.fiap.gs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Entidade_Base")
public class EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidade", length = 10)
    private Long idEntidade;

    @Column(name = "nome_entidade", length = 100, nullable = false)
    @NotNull
    private String nomeEntidade;
}
