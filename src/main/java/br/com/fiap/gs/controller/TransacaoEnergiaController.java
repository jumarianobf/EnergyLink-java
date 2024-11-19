package br.com.fiap.gs.controller;

import br.com.fiap.gs.controller.dto.TransacaoEnergiaDTO;
import br.com.fiap.gs.entity.TransacaoEnergia;
import br.com.fiap.gs.model.TransacaoEnergiaModel;
import br.com.fiap.gs.service.TransacaoEnergiaService;
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
@RequestMapping("api/transacao-energia")
public class TransacaoEnergiaController {

    @Autowired
    private TransacaoEnergiaService transacaoEnergiaService;

    @Operation(summary = "Cadastrar uma nova transação de energia",
            description = "Endpoint para cadastrar uma nova transação de energia.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<TransacaoEnergiaModel> createTransacaoEnergia(@Valid @RequestBody TransacaoEnergiaDTO transacaoEnergiaDTO) {
        try {
            TransacaoEnergia saved = transacaoEnergiaService.createTransacaoEnergia(transacaoEnergiaDTO);
            TransacaoEnergiaModel responseModel = toResponseModel(saved);
            responseModel.add(linkTo(methodOn(TransacaoEnergiaController.class).getTransacaoById(saved.getIdTransacao())).withSelfRel());
            responseModel.add(linkTo(methodOn(TransacaoEnergiaController.class).getAllTransacao()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Buscar transação por ID",
            description = "Recupera as informações de uma transação de energia pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transação encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Transação não encontrada", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<TransacaoEnergiaModel> getTransacaoById(@PathVariable("id") Long id) {
        try {
            TransacaoEnergia transacao = transacaoEnergiaService.getById(id);
            TransacaoEnergiaModel responseModel = toResponseModel(transacao);
            responseModel.add(linkTo(methodOn(TransacaoEnergiaController.class).getTransacaoById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(TransacaoEnergiaController.class).getAllTransacao()).withRel("listar"));
            responseModel.add(linkTo(methodOn(TransacaoEnergiaController.class).updateTransacao(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(TransacaoEnergiaController.class).deleteTransacao(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todas as transações",
            description = "Retorna a lista de todas as transações de energia cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de transações retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<TransacaoEnergiaModel>> getAllTransacao() {
        List<TransacaoEnergia> transacaoList = transacaoEnergiaService.getAllTransacaoEnergia();
        List<TransacaoEnergiaModel> responseModel = transacaoList.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModel.forEach(model -> model.add(linkTo(methodOn(TransacaoEnergiaController.class).getAllTransacao()).withSelfRel()));
        return ResponseEntity.ok(responseModel);
    }

    @Operation(summary = "Atualizar uma transação",
            description = "Atualiza as informações de uma transação de energia existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transação atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Transação não encontrada", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<TransacaoEnergiaModel> updateTransacao(
            @PathVariable("id") Long id,
            @RequestBody @Valid TransacaoEnergiaDTO transacaoEnergiaDTO) {
        try {
            TransacaoEnergia updatedTransacao = transacaoEnergiaService.updateTransacaoEnergia(id, transacaoEnergiaDTO);
            TransacaoEnergiaModel responseModel = toResponseModel(updatedTransacao);
            responseModel.add(linkTo(methodOn(TransacaoEnergiaController.class).getTransacaoById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(TransacaoEnergiaController.class).getAllTransacao()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir uma transação",
            description = "Exclui uma transação de energia existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transação excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Transação não encontrada", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTransacao(@PathVariable("id") Long id) {
        try {
            transacaoEnergiaService.deleteTransacaoEnergia(id);
            return new ResponseEntity<>("Transação excluída com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private TransacaoEnergiaModel toResponseModel(TransacaoEnergia saved) {
        return new TransacaoEnergiaModel(
                saved.getIdContrato().getIdContrato(),
                saved.getDataTransacao(),
                saved.getQuantidadeEnergia(),
                saved.getCustoTotal(),
                saved.getStatusTransacao()
        );
    }
}
