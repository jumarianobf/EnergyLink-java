package br.com.fiap.gs.controller;

import br.com.fiap.gs.controller.dto.ContratoDistribuicaoDTO;
import br.com.fiap.gs.entity.ContratoDistribuicao;
import br.com.fiap.gs.model.ContratoDistribuicaoModel;
import br.com.fiap.gs.service.ContratoDistribuicaoService;
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
@RequestMapping("api/contrato-distribuicao")
public class ContratoDistribuicaoController {

    @Autowired
    private ContratoDistribuicaoService contratoDistribuicaoService;

    @Operation(summary = "Cadastrar um novo contrato", description = "Endpoint para cadastrar um novo contrato no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "contrato criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<ContratoDistribuicaoModel> createContratoDistribuicao(
            @Valid @RequestBody ContratoDistribuicaoDTO contratoDistribuicaoDTO) {
        try {
            ContratoDistribuicao savedContrato = contratoDistribuicaoService.createContratoDistribuicao(contratoDistribuicaoDTO);
            ContratoDistribuicaoModel responseModel = toResponseModel(savedContrato);
            responseModel.add(linkTo(methodOn(ContratoDistribuicaoController.class).getContratoDistribuicaoById(savedContrato.getIdContrato())).withSelfRel());
            responseModel.add(linkTo(methodOn(ContratoDistribuicaoController.class).getAllContratoDistribuicao()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Buscar contrato por ID", description = "Recupera as informações de um contrato pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "contrato encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "contrato não encontrado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<ContratoDistribuicaoModel> getContratoDistribuicaoById(
            @PathVariable("id") Long id) {
        try {
            ContratoDistribuicao contrato = contratoDistribuicaoService.getById(id);
            if (contrato == null) {
                return ResponseEntity.notFound().build();
            }
            ContratoDistribuicaoModel responseModel = toResponseModel(contrato);
            responseModel.add(linkTo(methodOn(ContratoDistribuicaoController.class).getContratoDistribuicaoById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(ContratoDistribuicaoController.class).getAllContratoDistribuicao()).withRel("listar"));
            responseModel.add(linkTo(methodOn(ContratoDistribuicaoController.class).updateContratoDistribuicao(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(ContratoDistribuicaoController.class).deleteContratoDistribuicao(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Listar todos os contratos", description = "Retorna a lista de todos os contratos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de contratos retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<ContratoDistribuicaoModel>> getAllContratoDistribuicao() {
        List<ContratoDistribuicao> contratos = contratoDistribuicaoService.getAllContratoDistribuicao();
        List<ContratoDistribuicaoModel> responseModels = contratos.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(ContratoDistribuicaoController.class).getAllContratoDistribuicao()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar um contrato", description = "Atualiza as informações de um contrato existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "contrato atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "contrato não encontrado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<ContratoDistribuicaoModel> updateContratoDistribuicao(
            @PathVariable("id") Long id,
            @RequestBody @Valid ContratoDistribuicaoDTO contratoDistribuicaoDTO) {
        ContratoDistribuicao updatedContrato = contratoDistribuicaoService.updateContratoDistribuicao(id, contratoDistribuicaoDTO);
        ContratoDistribuicaoModel responseModel = toResponseModel(updatedContrato);
        responseModel.add(linkTo(methodOn(ContratoDistribuicaoController.class).getContratoDistribuicaoById(id)).withSelfRel());
        responseModel.add(linkTo(methodOn(ContratoDistribuicaoController.class).getAllContratoDistribuicao()).withRel("listar"));
        return ResponseEntity.ok(responseModel);
    }

    @Operation(summary = "Excluir um contrato", description = "Exclui um contrato existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "contrato excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "contrato não encontrado", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteContratoDistribuicao(@PathVariable("id") Long id) {
        contratoDistribuicaoService.deleteContratoDistribuicao(id);
        return new ResponseEntity<>("Contrato de distribuição excluído com sucesso", HttpStatus.OK);
    }

    private ContratoDistribuicaoModel toResponseModel(ContratoDistribuicao contrato) {
        return new ContratoDistribuicaoModel(
                contrato.getIdContrato(),
                contrato.getIdFabrica().getIdFabrica(),
                contrato.getIdComunidade().getIdComunidade(),
                contrato.getDataInicio(),
                contrato.getDataFim(),
                contrato.getPrecoKwh(),
                contrato.getStatusContrato()
        );
    }
}
