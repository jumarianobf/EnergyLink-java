package br.com.fiap.gs.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEnergyLinkDTO {

    @NotNull(message = "Nome do usuário é obrigatório")
    @Size(max = 100, message = "O nome do usuário não pode ter mais de 100 caracteres")
    private String nomeUsuario;

    @NotNull(message = "Sobrenome do usuário é obrigatório")
    @Size(max = 50, message = "O sobrenome do usuário não pode ter mais de 50 caracteres")
    private String sobrenome;

    @NotNull(message = "CPF do usuário é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    private String cpfUsuario;

    @Email(message = "Email inválido")
    @NotNull(message = "Email do usuário é obrigatório")
    @Size(max = 100, message = "O email não pode ter mais de 100 caracteres")
    private String emailUsuario;

    @Size(max = 100, message = "A senha do usuário não pode ter mais de 100 caracteres")
    private String senhaUsuario;

    @Size(max = 11, message = "O telefone não pode ter mais de 11 caracteres")
    private String telefoneUsuario;

    @NotNull(message = "Id do tipo do usuário é obrigatório")
    private Long idTipoUsuario;
}
