package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContratoDistribuicaoModel extends RepresentationModel<ContratoDistribuicaoModel> {

    private final Long idComunidade;
    private final Long idFabrica;
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private final BigDecimal precoKwh;
    private final String statusContrato;

    @JsonCreator
    public ContratoDistribuicaoModel(
            @JsonProperty("idComunidade") Long idComunidade,
            @JsonProperty("idFabrica") Long idFabrica,
            Long comunidade, @JsonProperty("dataInicio") LocalDate dataInicio,
            @JsonProperty("dataFim") LocalDate dataFim,
            @JsonProperty("precoKwh") BigDecimal precoKwh,
            @JsonProperty("statusContrato") String statusContrato
    ) {
        this.idComunidade = idComunidade;
        this.idFabrica = idFabrica;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.precoKwh = precoKwh;
        this.statusContrato = statusContrato;
    }
}
