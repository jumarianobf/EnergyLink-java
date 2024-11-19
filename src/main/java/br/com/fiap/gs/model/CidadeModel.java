package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class CidadeModel extends RepresentationModel<CidadeModel> {

    private final String nomeCidade;
    private final Long idEstado;

    @JsonCreator
    public CidadeModel(
            @JsonProperty("nomeCidade") String nomeCidade,
            @JsonProperty("idEstado") Long idEstado
    ) {
        this.nomeCidade = nomeCidade;
        this.idEstado = idEstado;
    }
}
