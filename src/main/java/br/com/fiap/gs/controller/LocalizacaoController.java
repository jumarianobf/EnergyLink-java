package br.com.fiap.gs.controller;
import br.com.fiap.gs.controller.dto.LocalizacaoDTO;
import br.com.fiap.gs.entity.Localizacao;
import br.com.fiap.gs.model.LocalizacaoModel;
import br.com.fiap.gs.service.LocalizacaoService;
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
@RequestMapping("api/localizacao")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;


    @Operation(summary = "Cadastrar uma nova localizacao", description = "Endpoint para cadastrar uma nova localizacao no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Localizacao criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<LocalizacaoModel> createLocalizacao(@Valid @RequestBody LocalizacaoDTO localizacaoDTO) {
        try {
            Localizacao savedLocalizacao = localizacaoService.createLocalizacao(localizacaoDTO);
            LocalizacaoModel responseModel = toResponseModel(savedLocalizacao);
            responseModel.add(linkTo(methodOn(LocalizacaoController.class).getLocalizacaoById(savedLocalizacao.getIdLocalizacao())).withSelfRel());
            responseModel.add(linkTo(methodOn(LocalizacaoController.class).getAllLocalizacao()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private LocalizacaoModel toResponseModel(Localizacao savedLocalizacao) {
        return new LocalizacaoModel(
                savedLocalizacao.getCep(),
                savedLocalizacao.getIdCidade().getIdCidade()
        );
    }

    @Operation(summary = "Buscar localizacao por ID", description = "Recupera as informações de uma localizacao pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Localizacao encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Localizacao não encontrada", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<LocalizacaoModel> getLocalizacaoById(@PathVariable("id") Long id) {
        try {
            Localizacao localizacao = localizacaoService.getById(id);
            LocalizacaoModel responseModel = toResponseModel(localizacao);
            responseModel.add(linkTo(methodOn(LocalizacaoController.class).getLocalizacaoById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(LocalizacaoController.class).getAllLocalizacao()).withRel("listar"));
            responseModel.add(linkTo(methodOn(LocalizacaoController.class).updateLocalizacao(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(LocalizacaoController.class).deleteLocalizacao(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todas as localizacoes", description = "Retorna a lista de todas as localizacoes cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de localizacao retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<LocalizacaoModel>> getAllLocalizacao() {
        List<Localizacao> localizacaoList = localizacaoService.getAllLocalizacao();
        List<LocalizacaoModel> responseModels = localizacaoList.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseModels);
    }

    @Operation(summary = "Atualizar uma localizacao", description = "Atualiza as informações de uma localizacao existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "localizacao atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "localizacao não encontrada", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<LocalizacaoModel> updateLocalizacao(
            @PathVariable("id") Long id,
            @RequestBody @Valid LocalizacaoDTO localizacaoDTO) {
        try {
            Localizacao updatedLocalizacao = localizacaoService.updateLocalizacao(id, localizacaoDTO);
            LocalizacaoModel responseModel = toResponseModel(updatedLocalizacao);
            responseModel.add(linkTo(methodOn(LocalizacaoController.class).getLocalizacaoById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(LocalizacaoController.class).getAllLocalizacao()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir uma localizacao", description = "Exclui uma localizacao existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "localizacao excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "localizacao não encontrada", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLocalizacao(@PathVariable("id") Long id) {
        try {
            localizacaoService.deleteLocalizacao(id);
            return new ResponseEntity<>("Localizacao excluída com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
