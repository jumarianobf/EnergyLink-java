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
public class CidadeDTO {

    @NotNull(message = "O nome da cidade não pode ser nula")
    @Size(max = 100, message = "O nome da cidade não pode ter mais de 100 caracteres")
    private String nomeCidade;

    @NotNull(message = "O id do estado não pode ser nula")
    private Long idEstado;
}
