package br.com.fiap.gs.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private Long idCidade;

    @Column(name = "nome_cidade", nullable = false, length = 50)
    private String nomeCidade;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado idEstado;


}
