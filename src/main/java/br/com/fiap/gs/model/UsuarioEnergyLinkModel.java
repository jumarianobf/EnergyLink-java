package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class UsuarioEnergyLinkModel extends RepresentationModel<UsuarioEnergyLinkModel> {

    private final String nomeUsuario;
    private final String sobrenomeUsuario;
    private final String cpfUsuario;
    private final String emailUsuario;
    private final String senhaUsuario;
    private final String telefoneUsuario;
    private final Long idTipoUsuario;

    @JsonCreator
    public UsuarioEnergyLinkModel(
            @JsonProperty("nomeUsuario") String nomeUsuario,
            @JsonProperty("sobrenomeUsuario") String sobrenomeUsuario,
            @JsonProperty("cpfUsuario") String cpfUsuario,
            @JsonProperty("emailUsuario") String emailUsuario,
            @JsonProperty("senhaUsuario") String senhaUsuario,
            @JsonProperty("telefoneUsuario") String telefoneUsuario,
            @JsonProperty("idTipoUsuario") Long idTipoUsuario
    ) {
        this.nomeUsuario = nomeUsuario;
        this.sobrenomeUsuario = sobrenomeUsuario;
        this.cpfUsuario = cpfUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.idTipoUsuario = idTipoUsuario;
    }
}
