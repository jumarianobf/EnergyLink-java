package br.com.fiap.gs.controller;

import br.com.fiap.gs.controller.dto.PagamentoDTO;
import br.com.fiap.gs.entity.Pagamento;
import br.com.fiap.gs.model.PagamentoModel;
import br.com.fiap.gs.service.PagamentoService;
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
@RequestMapping("api/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Operation(summary = "Cadastrar um novo pagamento", description = "Endpoint para cadastrar um novo pagamento no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pagamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<PagamentoModel> createPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO) {
        try {
            Pagamento savedPagamento = pagamentoService.createPagamento(pagamentoDTO);
            PagamentoModel responseModel = toResponseModel(savedPagamento);
            responseModel.add(linkTo(methodOn(PagamentoController.class).getPagamentoById(savedPagamento.getIdPagamento())).withSelfRel());
            responseModel.add(linkTo(methodOn(PagamentoController.class).getAllPagamentos()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Buscar pagamento por ID", description = "Recupera as informações de um pagamento pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "pagamento encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "pagamento não encontrado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<PagamentoModel> getPagamentoById(@PathVariable("id") Long id) {
        try {
            Pagamento pagamento = pagamentoService.getById(id);
            PagamentoModel responseModel = toResponseModel(pagamento);
            responseModel.add(linkTo(methodOn(PagamentoController.class).getPagamentoById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(PagamentoController.class).getAllPagamentos()).withRel("listar"));
            responseModel.add(linkTo(methodOn(PagamentoController.class).updatePagamento(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(PagamentoController.class).deletePagamento(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Listar todos os pagamentos", description = "Retorna a lista de todos os pagamentos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<PagamentoModel>> getAllPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.getAllPagamento();
        List<PagamentoModel> responseModels = pagamentos.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(PagamentoController.class).getAllPagamentos()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar um pagamento", description = "Atualiza as informações de um pagamento existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "pagamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "pagamento não encontrado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<PagamentoModel> updatePagamento(
            @PathVariable("id") Long id,
            @RequestBody @Valid PagamentoDTO pagamentoDTO) {
        try {
            Pagamento updatedPagamento = pagamentoService.updatePagamento(id, pagamentoDTO);
            PagamentoModel responseModel = toResponseModel(updatedPagamento);
            responseModel.add(linkTo(methodOn(PagamentoController.class).getPagamentoById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(PagamentoController.class).getAllPagamentos()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Excluir um pagamento", description = "Exclui um pagamento existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "pagamento excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "pagamento não encontrado", content = @Content)
    })

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePagamento(@PathVariable("id") Long id) {
        try {
            pagamentoService.deletePagamento(id);
            return new ResponseEntity<>("Pagamento excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private PagamentoModel toResponseModel(Pagamento pagamento) {
        return new PagamentoModel(
                pagamento.getIdContrato().getIdContrato(),
                pagamento.getDataPagamento(),
                pagamento.getValor(),
                pagamento.getStatusPagamento()
        );
    }
}
