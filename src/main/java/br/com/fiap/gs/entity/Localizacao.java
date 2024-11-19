package br.com.fiap.gs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Localizacao")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localizacao", length = 10)
    private Long idLocalizacao;

    @NotNull
    @Column(name = "cep", length = 8, nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "id_cidade", nullable = false)
    @NotNull
    private Cidade idCidade;
}
