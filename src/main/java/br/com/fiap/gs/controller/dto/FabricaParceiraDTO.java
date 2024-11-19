package br.com.fiap.gs.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FabricaParceiraDTO {

    @NotNull(message = "O id da entidade não pode ser nulo")
    private Long idIdentidade;

    @NotNull(message = "A localização não pode ser nula")
    private Long idLocalizacao;

    @NotNull(message = "A demanda de energia da fábrica não pode ser nula")
    private BigDecimal demandaEnergiaFabrica; // Demanda de energia da fábrica
}
