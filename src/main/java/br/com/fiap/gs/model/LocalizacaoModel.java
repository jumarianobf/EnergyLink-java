package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class LocalizacaoModel extends RepresentationModel<LocalizacaoModel> {

    private final String cep;
    private final Long idCidade;

    @JsonCreator
    public LocalizacaoModel(
            @JsonProperty("cep") String cep,
            @JsonProperty("idCidade") Long idCidade
    ) {
        this.cep = cep;
        this.idCidade = idCidade;
    }
}
