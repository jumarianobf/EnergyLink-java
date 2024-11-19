package br.com.fiap.gs.controller;

import br.com.fiap.gs.entity.TipoEnergiaComunidade;
import br.com.fiap.gs.model.TipoEnergiaComunidadeModel;
import br.com.fiap.gs.service.TipoEnergiaComunidadeService;
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
@RequestMapping("api/tipo-energia")
public class TipoEnergiaComunidadeController {

    @Autowired
    private TipoEnergiaComunidadeService tipoEnergiaService;

    @Operation(summary = "Cadastrar um novo tipo de energia", description = "Endpoint para cadastrar um novo usuário no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "tipo de energia criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })

    @PostMapping("/cadastrar")
    public ResponseEntity<TipoEnergiaComunidadeModel> createTipoEnergia(
            @Valid @RequestBody TipoEnergiaComunidade tipoEnergia) {
        try {
            TipoEnergiaComunidade savedTipo = tipoEnergiaService.createTipoEnergiaComunidade(tipoEnergia);
            TipoEnergiaComunidadeModel responseModel = toResponseModel(savedTipo);
            responseModel.add(linkTo(methodOn(TipoEnergiaComunidadeController.class).getTipoEnergiaById(savedTipo.getIdTipoEnergia())).withSelfRel());
            responseModel.add(linkTo(methodOn(TipoEnergiaComunidadeController.class).getAllTipoEnergia()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Buscar tipo de energia por ID", description = "Recupera as informações de um tipo de energia pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de energia encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de energia não encontrado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<TipoEnergiaComunidadeModel> getTipoEnergiaById(
            @PathVariable("id") Long id) {
        try {
            TipoEnergiaComunidade tipoEnergia = tipoEnergiaService.getById(id);
            TipoEnergiaComunidadeModel responseModel = toResponseModel(tipoEnergia);
            responseModel.add(linkTo(methodOn(TipoEnergiaComunidadeController.class).getTipoEnergiaById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(TipoEnergiaComunidadeController.class).getAllTipoEnergia()).withRel("listar"));
            responseModel.add(linkTo(methodOn(TipoEnergiaComunidadeController.class).updateTipoEnergia(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(TipoEnergiaComunidadeController.class).deleteTipoEnergia(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Listar todos os tipos de energia", description = "Retorna a lista de todos os tipos de energia cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de tipos de energia retornada com sucesso")

    @GetMapping("/listar")
    public ResponseEntity<List<TipoEnergiaComunidadeModel>> getAllTipoEnergia() {
        List<TipoEnergiaComunidade> tipoEnergiaList = tipoEnergiaService.getAllTipoEnergiaComunidade();
        List<TipoEnergiaComunidadeModel> responseModels = tipoEnergiaList.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(TipoEnergiaComunidadeController.class).getAllTipoEnergia()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar um tipo de energia", description = "Atualiza as informações de um tipo de energia existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de energia atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<TipoEnergiaComunidadeModel> updateTipoEnergia(
            @PathVariable("id") Long id,
            @RequestBody @Valid TipoEnergiaComunidade tipoEnergia) {
        try {
            TipoEnergiaComunidade updatedTipoEnergia = tipoEnergiaService.updateTipoEnergiaComunidade(id, tipoEnergia);
            TipoEnergiaComunidadeModel responseModel = toResponseModel(updatedTipoEnergia);
            responseModel.add(linkTo(methodOn(TipoEnergiaComunidadeController.class).getTipoEnergiaById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(TipoEnergiaComunidadeController.class).getAllTipoEnergia()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir um tipo de energia", description = "Exclui um tipo de energia existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de energia excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de energia não encontrado", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTipoEnergia(@PathVariable("id") Long id) {
        try {
            tipoEnergiaService.deleteTipoEnergiaComunidade(id);
            return new ResponseEntity<>("Tipo de energia excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private TipoEnergiaComunidadeModel toResponseModel(TipoEnergiaComunidade tipoEnergia) {
        return new TipoEnergiaComunidadeModel(
                tipoEnergia.getDescricaoEnergia()
        );
    }
}
