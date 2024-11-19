package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class PaisModel extends RepresentationModel<PaisModel> {


        private final String nomePais;

        @JsonCreator
        public PaisModel(
                @JsonProperty("nomePais") String nomePais
        ) {
            this.nomePais = nomePais;

        }
}

