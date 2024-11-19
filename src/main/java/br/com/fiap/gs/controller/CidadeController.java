package br.com.fiap.gs.controller;

import br.com.fiap.gs.controller.dto.CidadeDTO;
import br.com.fiap.gs.entity.Cidade;
import br.com.fiap.gs.model.CidadeModel;
import br.com.fiap.gs.service.CidadeService;
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
@RequestMapping("api/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Operation(summary = "Cadastrar uma nova cidade", description = "Endpoint para cadastrar uma nova cidade no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "cidade criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<CidadeModel> createCidade(@Valid @RequestBody CidadeDTO cidadeDTO) {
        try {
            Cidade savedCidade = cidadeService.createCidade(cidadeDTO);
            CidadeModel responseModel = toResponseModel(savedCidade);
            responseModel.add(linkTo(methodOn(CidadeController.class).getCidadeById(savedCidade.getIdCidade())).withSelfRel());
            responseModel.add(linkTo(methodOn(CidadeController.class).getAllCidade()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private CidadeModel toResponseModel(Cidade savedCidade) {
        return new CidadeModel(
                savedCidade.getNomeCidade(),
                savedCidade.getIdEstado().getIdEstado()
        );
    }

    @Operation(summary = "Buscar cidade por ID", description = "Recupera as informações de uma cidade pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "cidade encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "cidade não encontrada", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<CidadeModel> getCidadeById(
            @PathVariable("id") Long id) {
        try {
            Cidade cidade = cidadeService.getById(id);
            CidadeModel responseModel = toResponseModel(cidade);
            responseModel.add(linkTo(methodOn(CidadeController.class).getCidadeById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(CidadeController.class).getAllCidade()).withRel("listar"));
            responseModel.add(linkTo(methodOn(CidadeController.class).updateCidade(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(CidadeController.class).deleteCidade(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todas as cidades", description = "Retorna a lista de todas as cidades cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de cidades retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<CidadeModel>> getAllCidade() {
        List<Cidade> cidadeList = cidadeService.getAllCidade();
        List<CidadeModel> responseModels = cidadeList.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(CidadeController.class).getAllCidade()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar uma cidade", description = "Atualiza as informações de uma cidade existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "cidade atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "cidade não encontrada", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<CidadeModel> updateCidade(
            @PathVariable("id") Long id,
            @RequestBody @Valid CidadeDTO cidadeDTO) {
        try {
            Cidade updatedCidade = cidadeService.updateCidade(id, cidadeDTO);
            CidadeModel responseModel = toResponseModel(updatedCidade);
            responseModel.add(linkTo(methodOn(CidadeController.class).getCidadeById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(CidadeController.class).getAllCidade()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir uma cidade", description = "Exclui uma cidade existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "cidade excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "cidade não encontrada", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCidade(@PathVariable("id") Long id) {
        try {
            cidadeService.deleteCidade(id);
            return new ResponseEntity<>("Cidade excluída com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
