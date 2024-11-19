package br.com.fiap.gs.controller;

import br.com.fiap.gs.entity.TipoUsuario;
import br.com.fiap.gs.model.TipoUsuarioModel;
import br.com.fiap.gs.service.TipoUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)
@RequestMapping("api/tipo-usuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @Operation(summary = "Cadastrar um novo tipo de usuário",
            description = "Endpoint para cadastrar um novo tipo de usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tipo de usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<TipoUsuarioModel> createTipoUsuario(
            @Valid @RequestBody TipoUsuario tipoUsuario) {
        try {
            TipoUsuario savedTipoUsuario = tipoUsuarioService.createTipoUsuario(tipoUsuario);
            TipoUsuarioModel responseModel = toResponseModel(savedTipoUsuario);
            responseModel.add(linkTo(methodOn(TipoUsuarioController.class).getTipoUsuarioById(savedTipoUsuario.getIdTipoUsuario())).withSelfRel());
            responseModel.add(linkTo(methodOn(TipoUsuarioController.class).getAllTipoUsuario()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Buscar tipo de usuário por ID",
            description = "Recupera as informações de um tipo de usuário pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<TipoUsuarioModel> getTipoUsuarioById(
            @PathVariable("id") Long id) {
        try {
            TipoUsuario tipoUsuario = tipoUsuarioService.getById(id);
            TipoUsuarioModel responseModel = toResponseModel(tipoUsuario);
            responseModel.add(linkTo(methodOn(TipoUsuarioController.class).getTipoUsuarioById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(TipoUsuarioController.class).getAllTipoUsuario()).withRel("listar"));
            responseModel.add(linkTo(methodOn(TipoUsuarioController.class).updateTipoUsuario(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(TipoUsuarioController.class).deleteTipoUsuario(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todos os tipos de usuários",
            description = "Retorna a lista de todos os tipos de usuários cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de tipos de usuários retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<TipoUsuarioModel>> getAllTipoUsuario() {
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioService.getAllTipoUsuario();
        List<TipoUsuarioModel> responseModels = tipoUsuarioList.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(TipoUsuarioController.class).getAllTipoUsuario()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar tipo de usuário",
            description = "Atualiza as informações de um tipo de usuário existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<TipoUsuarioModel> updateTipoUsuario(
            @PathVariable("id") Long id,
            @RequestBody @Valid TipoUsuario tipoUsuario) {
        try {
            TipoUsuario updatedTipoUsuario = tipoUsuarioService.updateTipoUsuario(id, tipoUsuario);
            TipoUsuarioModel responseModel = toResponseModel(updatedTipoUsuario);
            responseModel.add(linkTo(methodOn(TipoUsuarioController.class).getTipoUsuarioById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(TipoUsuarioController.class).getAllTipoUsuario()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir tipo de usuário",
            description = "Exclui um tipo de usuário existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTipoUsuario(@PathVariable("id") Long id) {
        try {
            tipoUsuarioService.deleteTipoUsuario(id);
            return new ResponseEntity<>("Tipo de usuário excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private TipoUsuarioModel toResponseModel(TipoUsuario tipoUsuario) {
        return new TipoUsuarioModel(
                tipoUsuario.getDescricaoTipo()
        );
    }
}

