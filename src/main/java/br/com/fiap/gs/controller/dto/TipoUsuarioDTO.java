package br.com.fiap.gs.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoUsuarioDTO {

    @NotNull(message = "O tipo da descricao  não pode ser nula")
    @Size(max = 50, message = "O tipo da descricao não pode ter mais de 50 caracteres")
    private String descricaoTipo;
}
