package br.com.fiap.gs.controller;

import br.com.fiap.gs.controller.dto.UsuarioEnergyLinkDTO;
import br.com.fiap.gs.entity.UsuarioEnergyLink;
import br.com.fiap.gs.model.UsuarioEnergyLinkModel;
import br.com.fiap.gs.service.UsuarioEnergyLinkService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/usuario")
public class UsuarioEnergyLinkController {

    private final UsuarioEnergyLinkService usuarioEnergyLinkService;

    @Operation(summary = "Cadastrar um novo usuário", description = "Endpoint para cadastrar um novo usuário no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioEnergyLinkModel> createUsuario(@Valid @RequestBody UsuarioEnergyLinkDTO usuario) {
        try {
            UsuarioEnergyLink savedUsuario = usuarioEnergyLinkService.createUsuario(usuario);
            UsuarioEnergyLinkModel responseModel = toResponseModel(savedUsuario);
            responseModel.add(linkTo(methodOn(UsuarioEnergyLinkController.class).getUsuarioById(savedUsuario.getIdUsuario())).withSelfRel());
            responseModel.add(linkTo(methodOn(UsuarioEnergyLinkController.class).getAllUsuario()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Buscar usuário por ID", description = "Recupera as informações de um usuário pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEnergyLinkModel> getUsuarioById(@PathVariable Long id) {
        try {
            UsuarioEnergyLink usuarioEnergyLink = usuarioEnergyLinkService.getById(id);
            UsuarioEnergyLinkModel responseModel = toResponseModel(usuarioEnergyLink);
            responseModel.add(linkTo(methodOn(UsuarioEnergyLinkController.class).getUsuarioById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(UsuarioEnergyLinkController.class).getAllUsuario()).withRel("listar"));
            responseModel.add(linkTo(methodOn(UsuarioEnergyLinkController.class).updateUsuario(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(UsuarioEnergyLinkController.class).deleteUsuario(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todos os usuários", description = "Retorna a lista de todos os usuários cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioEnergyLinkModel>> getAllUsuario() {
        List<UsuarioEnergyLink> usuarioEnergyLinks = usuarioEnergyLinkService.getAllUsuario();
        List<UsuarioEnergyLinkModel> responseModels = usuarioEnergyLinks.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(UsuarioEnergyLinkController.class).getAllUsuario()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar um usuário", description = "Atualiza as informações de um usuário existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<UsuarioEnergyLinkModel> updateUsuario(
            @PathVariable("id") Long id,
            @RequestBody @Valid UsuarioEnergyLinkDTO usuario) {
        try {
            UsuarioEnergyLink updatedUsuario = usuarioEnergyLinkService.updateUsuario(id, usuario);
            UsuarioEnergyLinkModel responseModel = toResponseModel(updatedUsuario);
            responseModel.add(linkTo(methodOn(UsuarioEnergyLinkController.class).getUsuarioById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(UsuarioEnergyLinkController.class).getAllUsuario()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir um usuário", description = "Exclui um usuário existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") Long id) {
        try {
            usuarioEnergyLinkService.deleteUsuario(id);
            return new ResponseEntity<>("Usuario excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private UsuarioEnergyLinkModel toResponseModel(UsuarioEnergyLink savedUsuario) {
        return new UsuarioEnergyLinkModel(
                savedUsuario.getNomeUsuario(),
                savedUsuario.getCpfUsuario(),
                savedUsuario.getSobrenome(),
                savedUsuario.getEmailUsuario(),
                savedUsuario.getSenhaUsuario(),
                savedUsuario.getTelefoneUsuario(),
                savedUsuario.getIdTipoUsuario().getIdTipoUsuario()
        );
    }
}
