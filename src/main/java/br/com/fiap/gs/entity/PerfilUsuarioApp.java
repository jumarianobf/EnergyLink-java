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
@Table(name = "Perfil_Usuario_App")
public class PerfilUsuarioApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil", length = 10)
    private Long idPerfil;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @NotNull
    private UsuarioEnergyLink idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_comunidade", nullable = false)
    @NotNull
    private ComunidadeProdutora idComunidade;

    @ManyToOne
    @JoinColumn(name = "id_fabrica", nullable = false)
    @NotNull
    private FabricaParceira idFabrica;

    @Column(name = "descricao_perfil", length = 50)
    private String descricaoPerfil;
}
