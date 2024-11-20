package br.com.fiap.gs.controller;

import br.com.fiap.gs.controller.dto.FabricaParceiraDTO;
import br.com.fiap.gs.entity.FabricaParceira;
import br.com.fiap.gs.model.FabricaParceiraModel;
import br.com.fiap.gs.service.FabricaParceiraService;
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
@RequestMapping("api/fabrica-parceira")
public class FabricaParceiraController {

    @Autowired
    private FabricaParceiraService fabricaParceiraService;

    @Operation(summary = "Cadastrar uma nova fabrica", description = "Endpoint para cadastrar uma nova fabrica no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "fabrica criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<FabricaParceiraModel> createFabricaParceira(
            @Valid @RequestBody FabricaParceiraDTO fabricaDTO) {
        try {
            FabricaParceira savedFabrica = fabricaParceiraService.createFabrica(fabricaDTO);
            FabricaParceiraModel responseModel = toResponseModel(savedFabrica);
            responseModel.add(linkTo(methodOn(FabricaParceiraController.class).getFabricaParceiraById(savedFabrica.getIdFabrica())).withSelfRel());
            responseModel.add(linkTo(methodOn(FabricaParceiraController.class).getAllFabricasParceiras()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Buscar fabrica por ID", description = "Recupera as informações de uma fabrica pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "fabrica encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "fabrica não encontrado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<FabricaParceiraModel> getFabricaParceiraById(
            @PathVariable("id") Long id) {
        try {
            FabricaParceira fabrica = fabricaParceiraService.getById(id);
            if (fabrica == null) {
                return ResponseEntity.notFound().build();
            }
            FabricaParceiraModel responseModel = toResponseModel(fabrica);
            responseModel.add(linkTo(methodOn(FabricaParceiraController.class).getFabricaParceiraById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(FabricaParceiraController.class).getAllFabricasParceiras()).withRel("listar"));
            responseModel.add(linkTo(methodOn(FabricaParceiraController.class).updateFabricaParceira(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(FabricaParceiraController.class).deleteFabricaParceira(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Listar todas as fabricas", description = "Retorna a lista de todas as fabricas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de fabrica retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<FabricaParceiraModel>> getAllFabricasParceiras() {
        List<FabricaParceira> fabricas = fabricaParceiraService.getAllFabrica();
        List<FabricaParceiraModel> responseModels = fabricas.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(FabricaParceiraController.class).getAllFabricasParceiras()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar uma fabrica", description = "Atualiza as informações de uma fabrica existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "fabrica atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "fabrica não encontrada", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<FabricaParceiraModel> updateFabricaParceira(
            @PathVariable("id") Long id,
            @RequestBody @Valid FabricaParceiraDTO fabricaDTO) {
        try {
            FabricaParceira updatedFabrica = fabricaParceiraService.updateFabrica(id, fabricaDTO);
            FabricaParceiraModel responseModel = toResponseModel(updatedFabrica);
            responseModel.add(linkTo(methodOn(FabricaParceiraController.class).getFabricaParceiraById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(FabricaParceiraController.class).getAllFabricasParceiras()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Excluir uma fabrica", description = "Exclui uma fabrica existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "fabrica excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "fabrica não encontrada", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFabricaParceira(@PathVariable("id") Long id) {
        try {
            fabricaParceiraService.deleteFabrica(id);
            return new ResponseEntity<>("Fábrica parceira excluída com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private FabricaParceiraModel toResponseModel(FabricaParceira fabrica) {
        return new FabricaParceiraModel(
                fabrica.getIdEntidade().getIdEntidade(),
                fabrica.getIdLocalizacao().getIdLocalizacao(),
                fabrica.getDemandaEnergiaFabrica()
        );
    }
}
