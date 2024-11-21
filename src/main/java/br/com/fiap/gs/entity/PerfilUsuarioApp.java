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
    @JoinColumn(name = "id_comunidade", referencedColumnName = "id_comunidade")
    private ComunidadeProdutora idComunidade;

    @ManyToOne
    @JoinColumn(name = "id_fabrica", referencedColumnName = "id_fabrica")
    private FabricaParceira idFabrica;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioEnergyLink idUsuario;

    @Column(name = "descricao_perfil", length = 50)
    private String descricaoPerfil;
}
