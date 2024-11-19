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
public class EstadoDTO {

    @NotNull(message = "O nome não pode ser nulo")
    @Size(max = 50, message = "O nome do estado deve ter no máximo 50 caracteres")
    private String nomeEstado;

    @NotNull(message = "O país nao pode ser nulo")
    private Long idPais;
}
