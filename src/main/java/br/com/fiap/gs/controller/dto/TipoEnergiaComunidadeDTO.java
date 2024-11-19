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
public class TipoEnergiaComunidadeDTO {

    @NotNull(message = "A descrição da energia não pode ser nula")
    private String descricaoEnergia; // Descrição do tipo de energia
}
