package br.com.fiap.gs.controller;

import br.com.fiap.gs.entity.EntidadeBase;
import br.com.fiap.gs.model.EntidadeBaseModel;
import br.com.fiap.gs.service.EntidadeBaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)
@RequestMapping("api/entidade-base")
public class EntidadeBaseController {

    @Autowired
    private EntidadeBaseService entidadeBaseService;

    @Operation(summary = "Cadastrar uma nova entidade base", description = "Endpoint para cadastrar uma nova entidade base no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "entidade base criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<?> createEntidadeBase(@Valid @RequestBody EntidadeBase entidadeBase) {
        try {
            EntidadeBase savedEntidadeBase = entidadeBaseService.createEntidadeBase(entidadeBase);
            EntidadeBaseModel responseModel = toResponseModel(savedEntidadeBase);
            responseModel.add(linkTo(methodOn(EntidadeBaseController.class).getEntidadeBaseById(savedEntidadeBase.getIdEntidade())).withSelfRel());
            responseModel.add(linkTo(methodOn(EntidadeBaseController.class).getAllEntidadeBase()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private EntidadeBaseModel toResponseModel(EntidadeBase savedEntidadeBase) {
        return new EntidadeBaseModel(
                savedEntidadeBase.getNomeEntidade()
        );
    }

    @Operation(summary = "Buscar entidade base por ID", description = "Recupera as informações de uma entidade base pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "entidade base encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "entidade base não encontrado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<EntidadeBaseModel> getEntidadeBaseById(
            @PathVariable("id") Long id) {
        try {
            EntidadeBase entidadeBase = entidadeBaseService.getById(id);
            EntidadeBaseModel responseModel = toResponseModel(entidadeBase);
            responseModel.add(linkTo(methodOn(EntidadeBaseController.class).getEntidadeBaseById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(EntidadeBaseController.class).getAllEntidadeBase()).withRel("listar"));
            responseModel.add(linkTo(methodOn(EntidadeBaseController.class).updateEntidadeBase(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(EntidadeBaseController.class).deleteEntidadeBase(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todas as entidades base", description = "Retorna a lista de todas as entidades base cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de entidade base retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<EntidadeBaseModel>> getAllEntidadeBase() {
        List<EntidadeBase> entidadeBaseList = entidadeBaseService.getAllEntidades();
        List<EntidadeBaseModel> responseModels = entidadeBaseList.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(EntidadeBaseController.class).getAllEntidadeBase()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar uma entidade base", description = "Atualiza as informações de uma entidade base existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "entidade base atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "entidade base não encontrada", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<EntidadeBaseModel> updateEntidadeBase(
            @PathVariable("id") Long id,
            @RequestBody @Valid EntidadeBase entidadeBase) {
        try {
            EntidadeBase updatedEntidadeBase = entidadeBaseService.updateEntidadeBase(id, entidadeBase);
            EntidadeBaseModel responseModel = toResponseModel(updatedEntidadeBase);
            responseModel.add(linkTo(methodOn(EntidadeBaseController.class).getEntidadeBaseById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(EntidadeBaseController.class).getAllEntidadeBase()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir uma entidade base", description = "Exclui uma  entidade base existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = " entidade base excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = " entidade base não encontrada", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEntidadeBase(@PathVariable("id") Long id) {
        try {
            entidadeBaseService.deleteEntidadeBase(id);
            return new ResponseEntity<>("Entidade excluída com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
