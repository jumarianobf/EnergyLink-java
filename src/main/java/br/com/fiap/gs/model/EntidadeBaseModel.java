package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class EntidadeBaseModel extends RepresentationModel<EntidadeBaseModel> {

    private final String nomeEntidade;

    @JsonCreator
    public EntidadeBaseModel (
            @JsonProperty("nomeEntidade") String nomeEntidade
    ) {
        this.nomeEntidade = nomeEntidade;
    }
}
