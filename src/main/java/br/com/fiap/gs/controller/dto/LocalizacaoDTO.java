package br.com.fiap.gs.controller.dto;

import br.com.fiap.gs.entity.Cidade;
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
public class LocalizacaoDTO {

    @NotNull(message = "O cep não pode ser nulo")
    @Size(max = 8, message = "O cep deve ter no máximo 8 caracteres")
    private String cep;

    @NotNull(message = "O id da cidade não pode ser nulo")
    private Cidade idCidade;
}