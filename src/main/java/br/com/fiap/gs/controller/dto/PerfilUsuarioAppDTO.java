package br.com.fiap.gs.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerfilUsuarioAppDTO {

    @NotNull(message = "O id do usuario não pode ser nulo")
    private Long idUsuario;

    @NotNull(message = "O id do tipo de energia não pode ser nula")
    private Long idComunidade;

    @NotNull(message = "A capacidade de producao não pode ser nula")
    private Long idFabrica;

    @NotNull(message = "A localização não pode ser nula")
    private String descricaoPerfil;


}
