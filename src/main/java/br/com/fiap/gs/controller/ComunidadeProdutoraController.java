package br.com.fiap.gs.controller;

import br.com.fiap.gs.controller.dto.ComunidadeProdutoraDTO;
import br.com.fiap.gs.entity.ComunidadeProdutora;
import br.com.fiap.gs.model.ComunidadeProdutoraModel;
import br.com.fiap.gs.service.ComunidadeProdutoraService;
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
@RequestMapping("api/comunidade-produtora")
public class ComunidadeProdutoraController {

    @Autowired
    private ComunidadeProdutoraService comunidadeProdutoraService;

    @Operation(summary = "Cadastrar uma nova comunidade produtora", description = "Endpoint para cadastrar uma nova comunidade produtora no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "comunidade produtora criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<ComunidadeProdutoraModel> createComunidadeProdutora(
            @Valid @RequestBody ComunidadeProdutoraDTO comunidadeProdutora) {
        try {
            ComunidadeProdutora savedComunidade = comunidadeProdutoraService.createComunidadeProdutora(comunidadeProdutora);
            ComunidadeProdutoraModel responseModel = toResponseModel(savedComunidade);
            responseModel.add(linkTo(methodOn(ComunidadeProdutoraController.class).getComunidadeProdutoraById(savedComunidade.getIdComunidade())).withSelfRel());
            responseModel.add(linkTo(methodOn(ComunidadeProdutoraController.class).getAllComunidades()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Buscar comunidade produtora por ID", description = "Recupera as informações de uma comunidade produtora pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "comunidade produtora encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "comunidade produtora não encontrada", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<ComunidadeProdutoraModel> getComunidadeProdutoraById(
            @PathVariable("id") Long id) {
        try {
            ComunidadeProdutora comunidade = comunidadeProdutoraService.getById(id);
            ComunidadeProdutoraModel responseModel = toResponseModel(comunidade);
            responseModel.add(linkTo(methodOn(ComunidadeProdutoraController.class).getComunidadeProdutoraById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(ComunidadeProdutoraController.class).getAllComunidades()).withRel("listar"));
            responseModel.add(linkTo(methodOn(ComunidadeProdutoraController.class).updateComunidadeProdutora(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(ComunidadeProdutoraController.class).deleteComunidadeProdutora(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todas as comunidades produtoras ", description = "Retorna a lista de todas as comunidades produtoras  cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de comunidades produtoras retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<ComunidadeProdutoraModel>> getAllComunidades() {
        List<ComunidadeProdutora> comunidades = comunidadeProdutoraService.getAllComunidadesProdutoras();
        List<ComunidadeProdutoraModel> responseModels = comunidades.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(ComunidadeProdutoraController.class).getAllComunidades()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar uma comunidade produtora", description = "Atualiza as informações de uma comunidade produtora existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "comunidade produtora atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "comunidade produtora não encontrada", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<ComunidadeProdutoraModel> updateComunidadeProdutora(
            @PathVariable("id") Long id,
            @RequestBody @Valid ComunidadeProdutoraDTO comunidadeProdutora) {
        try {
            ComunidadeProdutora updatedComunidade = comunidadeProdutoraService.updateComunidadeProdutora(id, comunidadeProdutora);
            ComunidadeProdutoraModel responseModel = toResponseModel(updatedComunidade);
            responseModel.add(linkTo(methodOn(ComunidadeProdutoraController.class).getComunidadeProdutoraById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(ComunidadeProdutoraController.class).getAllComunidades()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir uma comunidade produtora", description = "Exclui uma comunidade produtora existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "comunidade produtora excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "comunidade produtora não encontrada", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComunidadeProdutora(@PathVariable("id") Long id) {
        try {
            comunidadeProdutoraService.deleteComunidadeProdutora(id);
            return new ResponseEntity<>("Comunidade produtora excluída com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private ComunidadeProdutoraModel toResponseModel(ComunidadeProdutora comunidade) {
        return new ComunidadeProdutoraModel(
                comunidade.getIdEntidade().getIdEntidade(),
                comunidade.getIdTipoEnergia().getIdTipoEnergia(),
                comunidade.getCapacidadeProducao(),
                comunidade.getIdLocalizacao().getIdLocalizacao()
        );
    }
}
