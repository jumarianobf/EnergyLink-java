package br.com.fiap.gs.controller;

import br.com.fiap.gs.entity.Pais;
import br.com.fiap.gs.model.PaisModel;
import br.com.fiap.gs.service.PaisService;
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
@RequestMapping("api/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @Operation(summary = "Cadastrar um novo país", description = "Endpoint para cadastrar um novo país no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "país criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<PaisModel> createPais(@Valid @RequestBody Pais pais) {
        try {
            Pais savedPais = paisService.createPais(pais);
            PaisModel responseModel = toResponseModel(savedPais);
            responseModel.add(linkTo(methodOn(PaisController.class).getPaisById(savedPais.getIdPais())).withSelfRel());
            responseModel.add(linkTo(methodOn(PaisController.class).getAllPais()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private PaisModel toResponseModel(Pais savedPais) {
        return new PaisModel(
                savedPais.getNomePais()
        );
    }

    @Operation(summary = "Buscar país por ID", description = "Recupera as informações de um país pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "país encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "país não encontrado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<PaisModel> getPaisById(
            @PathVariable("id") Long id) {
        try {
            Pais pais = paisService.getById(id);
            PaisModel responseModel = toResponseModel(pais);
            responseModel.add(linkTo(methodOn(PaisController.class).getPaisById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(PaisController.class).getAllPais()).withRel("listar"));
            responseModel.add(linkTo(methodOn(PaisController.class).updatePais(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(PaisController.class).deletePais(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todos os países", description = "Retorna a lista de todos os países cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de países retornada com sucesso")

    @GetMapping("/listar")
    public ResponseEntity<List<PaisModel>> getAllPais() {
        List<Pais> paisList = paisService.getAllPais();
        List<PaisModel> responseModel = paisService.getAllPais().stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModel.forEach(model ->  model.add(linkTo(methodOn(PaisController.class).getAllPais()).withSelfRel()));
        return ResponseEntity.ok(responseModel);
    }

    @Operation(summary = "Atualizar um pais", description = "Atualiza as informações de um pais existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pais atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pais não encontrado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<PaisModel> updatePais(
            @PathVariable("id") Long id,
            @RequestBody @Valid Pais pais) {
        try {
            Pais updatedPais = paisService.updatePais(id, pais);
            PaisModel responseModel = toResponseModel(updatedPais);
            responseModel.add(linkTo(methodOn(PaisController.class).getPaisById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(PaisController.class).getAllPais()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir um pais", description = "Exclui um pais existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pais excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pais não encontrado", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePais(@PathVariable("id") Long id) {
        try {
            paisService.deletePais(id);
            return new ResponseEntity<>("Pais excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
