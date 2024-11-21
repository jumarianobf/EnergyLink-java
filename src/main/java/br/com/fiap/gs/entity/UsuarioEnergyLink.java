package br.com.fiap.gs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USUARIO_ENERGYLINK")
public class UsuarioEnergyLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", length = 10)
    private Long idUsuario;

    @Column(name = "nome_usuario", length = 100, nullable = false)
    @NotNull
    private String nomeUsuario;

    @Column(name = "sobrenome_usuario", length = 50, nullable = false)
    private String sobrenome;

    @Column(name = "cpf_usuario", length = 11, unique = true, nullable = false)
    @Pattern(regexp = "^[0-9]{11}$", message = "CPF deve conter 11 dígitos numéricos")
    private String cpfUsuario;

    @Column(name = "email_usuario", length = 100, unique = true, nullable = false)
    @Email
    private String emailUsuario;

    @Column(name = "senha_usuario", length = 100, nullable = false)
    private String senhaUsuario;  // Adicionar validação de senha segura antes de salvar

    @Column(name = "telefone_usuario", unique = true, length = 11)
    private String telefoneUsuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario", nullable = false)
    @NotNull
    private TipoUsuario idTipoUsuario;

}
