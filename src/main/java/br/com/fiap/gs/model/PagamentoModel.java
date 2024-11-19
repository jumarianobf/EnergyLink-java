package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagamentoModel extends RepresentationModel<PagamentoModel> {

    private final Long idContrato;
    private final LocalDate dataPagamento;
    private final BigDecimal valor;
    private final String statusPagamento;

    @JsonCreator
    public PagamentoModel(
            @JsonProperty("idContrato") Long idContrato,
            @JsonProperty("dataPagamento") LocalDate dataPagamento,
            @JsonProperty("valor") BigDecimal valor,
            @JsonProperty("statusPagamento") String statusPagamento
    ) {
        this.idContrato = idContrato;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.statusPagamento = statusPagamento;
    }
}
