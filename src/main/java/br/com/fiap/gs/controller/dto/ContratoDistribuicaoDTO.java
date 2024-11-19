package br.com.fiap.gs.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContratoDistribuicaoDTO {

    @NotNull(message = "A comunidade não pode ser nula")
    private Long idComunidade; // ID da comunidade (relacionado à entidade ComunidadeProdutora)

    @NotNull(message = "A fábrica não pode ser nula")
    private Long idFabrica; // ID da fábrica (relacionado à entidade FabricaParceira)


    @NotNull(message = "A data não pode ser nula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataInicio; // Data de início do contrato

    @NotNull(message = "A data não pode ser nula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFim; // Data de fim do contrato (pode ser nula)

    @NotNull(message = "O preço por kWh não pode ser nulo")
    private Double precoKwh; // Preço por kWh

    @Size(max = 20, message = "O status do contrato deve ter no máximo 20 caracteres")
    private String statusContrato; // Status do contrato
}
