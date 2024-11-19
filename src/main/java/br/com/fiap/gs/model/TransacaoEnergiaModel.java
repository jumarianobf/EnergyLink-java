package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransacaoEnergiaModel extends RepresentationModel<TransacaoEnergiaModel> {

    private final Long idContrato;
    private final LocalDate dataTransacao;
    private final BigDecimal quantidadeEnergia;
    private final BigDecimal custoTotal;
    private final String statusTransacao;

    @JsonCreator
    public TransacaoEnergiaModel(
            @JsonProperty("idContrato") Long idContrato,
            @JsonProperty("dataTransacao") LocalDate dataTransacao,
            @JsonProperty("quantidadeEnergia") BigDecimal quantidadeEnergia,
            @JsonProperty("custoTotal") BigDecimal custoTotal,
            @JsonProperty("statusTransacao") String statusTransacao
    ) {
        this.idContrato = idContrato;
        this.dataTransacao = dataTransacao;
        this.quantidadeEnergia = quantidadeEnergia;
        this.custoTotal = custoTotal;
        this.statusTransacao = statusTransacao;
    }
}
