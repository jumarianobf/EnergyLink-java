package br.com.fiap.gs.controller;

import br.com.fiap.gs.controller.dto.EstadoDTO;
import br.com.fiap.gs.entity.Estado;
import br.com.fiap.gs.model.EstadoModel;
import br.com.fiap.gs.service.EstadoService;
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
@RequestMapping("api/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @Operation(summary = "Cadastrar um novo estado", description = "Endpoint para cadastrar um novo estado no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "estado criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<EstadoModel> createEstado(@Valid @RequestBody EstadoDTO estadoDTO) {
        try {
            Estado savedEstado = estadoService.createEstado(estadoDTO);
            EstadoModel responseModel = toResponseModel(savedEstado);
            responseModel.add(linkTo(methodOn(EstadoController.class).getEstadoById(savedEstado.getIdEstado())).withSelfRel());
            responseModel.add(linkTo(methodOn(EstadoController.class).getAllEstado()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private EstadoModel toResponseModel(Estado savedEstado) {
        return new EstadoModel(
                savedEstado.getNomeEstado(),
                savedEstado.getIdPais().getIdPais()
        );
    }

    @Operation(summary = "Buscar estados por ID", description = "Recupera as informações de um estado pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<EstadoModel> getEstadoById(
            @PathVariable("id") Long id) {
        try {
            Estado estado = estadoService.getById(id);
            EstadoModel responseModel = toResponseModel(estado);
            responseModel.add(linkTo(methodOn(EstadoController.class).getEstadoById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(EstadoController.class).getAllEstado()).withRel("listar"));
            responseModel.add(linkTo(methodOn(EstadoController.class).updateEstado(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(EstadoController.class).deleteEstado(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todos os estados", description = "Retorna a lista de todos os estados cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de estados retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<EstadoModel>> getAllEstado() {
        List<Estado> estados = estadoService.getAllEstado();
        List<EstadoModel> responseModels = estados.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar um estado", description = "Atualiza as informações de um estado existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "estado atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "estado não encontrado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<EstadoModel> updateEstado(
            @PathVariable("id") Long id,
            @RequestBody @Valid EstadoDTO estadoDTO) {
        try {
            Estado updatedEstado = estadoService.updateEstado(id, estadoDTO);
            EstadoModel responseModel = toResponseModel(updatedEstado);
            responseModel.add(linkTo(methodOn(EstadoController.class).getEstadoById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(EstadoController.class).getAllEstado()).withRel("listar"));
            responseModel.add(linkTo(methodOn(EstadoController.class).updateEstado(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(EstadoController.class).deleteEstado(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Excluir um estado", description = "Exclui um estado existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "estado excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "estado não encontrado", content = @Content)
    })

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEstado(@PathVariable("id") Long id) {
        try {
            estadoService.deleteEstado(id);
            return new ResponseEntity<>("Estado excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
