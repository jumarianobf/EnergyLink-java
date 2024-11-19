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
public class EntidadeBaseDTO {

    @NotNull(message = "O nome não pode ser nula")
    @Size(max = 100, message = "O nome da entidade deve ter no máximo 100 caracteres")
    private String nomeEntidade;
}
